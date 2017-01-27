package com.example.demo.controllers;

import com.example.demo.util.SecurityUtil;
import com.example.demo.services.SimpleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.User;
import com.example.demo.services.UsersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jarbas on 08/10/15.
 */
@RestController
@RequestMapping("/users")
public class UsersController extends BaseModelController<User> {

    @Autowired
    private UsersService service;

    @Override
    protected SimpleModelService<User> getService() {
        return service;
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public User getCurrent(HttpServletRequest request, HttpServletResponse response) {
        User user = SecurityUtil.getCurrentUser();
        user.setActivated(null);
        user.setPassword(null);
        user.setRepassword(null);
        return user;
    }
}
