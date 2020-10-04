package com.springdrools.service;

import com.springdrools.model.Item;
import com.springdrools.model.Purchase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Singleton class that wraps
 * the Drools KieSession and is responsible
 * for loading rules and firing rules.
 */
@Component
public class RulesService {

    private KieServices ks;
    private KieContainer kContainer;
    private KieSession kieSession;

    @Bean("RulesService")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public RulesService getRulesService() {
        return new RulesService();
    }

    /**
     * Function to initialize the kieSession.
     * This will be initialized on
     * application startup.
     */
    public void initializeRules() {

        this.ks = KieServices.Factory.get();
        this.kContainer = ks.getKieClasspathContainer();
        this.kieSession =  kContainer.newKieSession();
    }

    public Item fireRules(Item item) {
        this.kieSession.insert(item);
        this.kieSession.fireAllRules();
        return item;
    }

    public Purchase firePurchaseRules(Purchase purchase) {
        ArrayList<Item> items = purchase.getItems();
        for (int i = 0; i < items.size(); i += 1) {
            purchase.setItem(i, fireRules(items.get(i)));
        }
        this.kieSession.insert(purchase);
        this.kieSession.fireAllRules();
        return purchase;
    }
}
