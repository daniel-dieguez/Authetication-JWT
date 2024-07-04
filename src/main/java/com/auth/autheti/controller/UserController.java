package com.auth.autheti.controller;


import com.auth.autheti.service.impl.IUserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserImpl iUserImp;

     



}
