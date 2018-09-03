package com.springdrools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class RulesStartupService {

    @Autowired
    RulesService rulesService;

    @PostConstruct
    public void init() {
        // Load all rules into Kie session.
        this.rulesService.initializeRules();
        System.out.println("RulesStartupService initialize rules");

    }

}
