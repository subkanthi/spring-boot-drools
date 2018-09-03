package com.springdrools;

import javafx.css.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RulesController {

    @Autowired
    RulesService rulesService;

    @RequestMapping("/")
    public String index() {
        int fired = this.rulesService.fireRules();

        System.out.println("Number of rules fired" + fired);
        return "Greetings from Spring Boot!";
    }
}
