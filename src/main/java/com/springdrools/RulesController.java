package com.springdrools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RulesController {

    @Autowired
    RulesService rulesService;

    @PostMapping("/rules")
    public String index(@RequestBody Item item) {
        int fired = this.rulesService.fireRules(item);

        System.out.println(item.toString());
        System.out.println("Number of rules fired" + fired);
        return "Greetings from Spring Boot!";
    }
}
