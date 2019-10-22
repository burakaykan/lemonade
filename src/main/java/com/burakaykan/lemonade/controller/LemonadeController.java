package com.burakaykan.lemonade.controller;

import com.burakaykan.lemonade.model.User;
import com.naturalprogrammer.spring.lemon.LemonController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/core")
public class LemonadeController extends LemonController<User,Long> {
}
