package com.example.demo.repositories;

import com.example.demo.model.User;

/**
 * Created by jarbas on 09/10/15.
 */
public interface UsersRepository extends BaseRepository, SimpleModelRepository<User> {

	User getByEmail(String email);
}
