package com.springdrools;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RulesController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
