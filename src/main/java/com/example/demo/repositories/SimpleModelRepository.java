package com.example.demo.repositories;

import com.example.demo.model.BaseModel;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by jarbas on 09/10/15.
 */
@NoRepositoryBean
public interface SimpleModelRepository<T extends BaseModel>
		extends BaseRepository, PagingAndSortingRepository<T, Long> {

}
