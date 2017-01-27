package com.example.demo.controllers;

import com.example.demo.controllers.datatables.Request;
import com.example.demo.controllers.datatables.Response;
import com.example.demo.exceptions.SaveException;
import com.example.demo.model.BaseModel;
import com.example.demo.services.SimpleModelService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jarbas on 05/10/15.
 */
public abstract class BaseModelController<T extends BaseModel> extends BaseController {

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
        getService().delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public T get(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
        return getService().get(id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Page<T> list(@RequestParam(value = "p", required = false, defaultValue = "0") Integer page,
                        HttpServletRequest request, HttpServletResponse response) {
        return getService().list(page);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public T save(@Validated @RequestBody T object, HttpServletRequest request, HttpServletResponse response) {
        T result = null;
        try {
            result = getService().save(object);
        } catch (DataIntegrityViolationException di) {
            getLogger().warn("Trying to insert a duplicated data: " + di.getMessage());
            throw new SaveException(di, object);
        } catch (Exception e) {
            getLogger().error("Error saving object: ", e);
            throw new SaveException(e, object);
        }

        return result;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Response<T> search(@Validated Request dtrequest,
                              HttpServletRequest request, HttpServletResponse response) {

        Page<T> page = getService().search(dtrequest.getStart(), dtrequest.getLength(), dtrequest.getSearch().getValue(),
                dtrequest.getFilterColumns(), dtrequest.getOrderColumns());
        return Response.fromPage(dtrequest.getDraw(), page);
    }

    protected abstract SimpleModelService<T> getService();
}
