package com.example.demo.services;

import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.BaseModel;
import com.example.demo.repositories.SimpleModelRepository;
import com.example.demo.util.SecurityUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by jarbas on 08/10/15.
 */
public abstract class SimpleModelService<T extends BaseModel> extends BaseService {

    protected static final int PAGE_SIZE = 15;

    @Autowired
    private EntityManager em;

    private Class<T> currentClass;

    public SimpleModelService(Class<T> currentClass) {
        super();
        this.currentClass = currentClass;
    }

    public void delete(Long id) {
        T object = get(id);
        if (object == null) {
            throw new ObjectNotFoundException();
        }

        checkWriteAccess(object);
        getRepository().delete(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public T get(Long id) {
        return getRepository().findOne(id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<T> list(Integer page) {
        PageRequest request = page >= 0 ? new PageRequest(page, PAGE_SIZE) : new PageRequest(0, Integer.MAX_VALUE);
        return getRepository().findAll(request);
    }

    public T save(T object) {
        checkWriteAccess(object);
        prepareToSave(object);

        T saved = getRepository().save(object);
        return saved;
    }

    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<T> search(Integer start, Integer length, String filter, String[] filterColumns, String[] orderColumns) {
        Query query = createQuery(filter, filterColumns, orderColumns, false);
        query.setMaxResults(length);
        query.setFirstResult(start);

        List<T> data = query.getResultList();

        Query countQuery = createQuery(filter, filterColumns, null, true);
        Long count = (Long) countQuery.getSingleResult();

        Integer currentPage = start / length;
        Pageable pageable = new PageRequest(currentPage, length);
        Page<T> page = new PageImpl<T>(data, pageable, count);

        return page;
    }

    protected abstract SimpleModelRepository<T> getRepository();

    protected void checkWriteAccess(T object) {
    }

    protected Query createQuery(String filter, String[] filterColumns, String[] orderColumns, boolean count) {
        StringBuilder queryStr = new StringBuilder();

        if (count) {
            queryStr.append("SELECT COUNT(*) ");
        }

        queryStr.append("FROM " + currentClass.getCanonicalName())
                .append(" WHERE ")
                .append(getDefaultFilter());
        if (StringUtils.isNotBlank(filter) && filterColumns != null && filterColumns.length > 0) {
            queryStr.append(" AND ( ");

            for (int i = 0; i < filterColumns.length; i++) {
                queryStr.append(i > 0 ? " OR " : "").append(filterColumns[i]).append(" LIKE ? ");
            }

            queryStr.append(" )");
        }

        if (orderColumns != null && orderColumns.length > 0) {
            queryStr.append(" ORDER BY ").append(ArrayUtils.toString(orderColumns).replaceAll("[\\{\\}]", ""));
        }

        Query query = em.createQuery(queryStr.toString());
        if (StringUtils.isNotBlank(filter)) {
            for (int i = 0; i < filterColumns.length; i++) {
                query.setParameter(i + 1, filter + "%");
            }
        }

        return query;
    }

    protected String getDefaultFilter() {
        return " 1 = 1 ";
    }

    protected Long getUserId() {
        return SecurityUtil.getCurrentUserId();
    }

    protected void prepareToSave(T object) {
    }
}
