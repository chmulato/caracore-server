package com.example.demo.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repositories.SimpleModelRepository;
import com.example.demo.repositories.UsersRepository;

/**
 * Created by jarbas on 08/10/15.
 */
@Service
@Transactional
@ConfigurationProperties(prefix = "app.usersservice")
public class UsersService extends SimpleModelService<User> {

    @Autowired
    private UsersRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public UsersService() {
		super(User.class);
	}

    @Override
    protected SimpleModelRepository<User> getRepository() {
        return repository;
    }
}
