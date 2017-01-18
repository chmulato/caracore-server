package com.example.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jarbas on 05/10/15.
 */
@RestController
public abstract class BaseController {

    private Logger logger;

    public BaseController() {
        logger = LoggerFactory.getLogger(this.getClass());
    }

    public Logger getLogger() {
        return logger;
    }
}
