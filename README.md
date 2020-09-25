# How to start this App
Go to `SpringDroolsApplication` in IntelliJ and hit start button
	
# Spring Boot Drools sample Application
Spring Boot sample application with Drools rules

Sample application that will load all drools rules on startup
and will expose a webservice to pass the object and execute
rules on the object.

TLDR; This is not tested for production quality in the scale of 100s and thousands of rules.

# Singleton KieSession Bean
KieSession is wrapped in a Spring Singleton Bean.
```
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

    @Bean("accountService")
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

    public int fireRules(Item item) {
        this.kieSession.insert(item);
        return this.kieSession.fireAllRules();

    }
}
```

# Web Service to pass Object and execute rules.
```
@RestController
public class RulesController {

    @Autowired
    RulesService rulesService;

    @PostMapping("/rules")
    public String index(@RequestBody Item item) {
        int fired = this.rulesService.fireRules(item);
```
# Sample JSON call to pass Object
```
curl -X POST localhost:8080/items -H 'Content-Type: application/json' -d '{"id": 2, "name": "Test", "cost": 100}'

Output:
{"id":2,"name":"Test","cost":100.0,"category":"LOW_RANGE"}
```

# Your Task
Implement the following:    
1. Create a purchase model, where you can input the a list of items and get the total cost  
	This model must have the following attributes:  
	1. List of Items purchased  
	2. State where the purchase was created (You may want to create a helper state model to support this)  
2. Implement a single item sale, where a specific item has an X% sale (20% off on frosted flakes)  
3. Implement a "total cost" sale, where a specific total cost receives a %discount (>$100 purchase, 20% off)  
3. Implement a tax on a purchase, with the following rules:  
	1. California: 7.25 %  
	2. Colorado: 2.90 %  
	3. India: 18.00 %  
	4. British Columbia: 12.00 %  
4. Remember that these rules have to stack!  
5. Implement `RulesController#totalCost` to return the following information:  
	1. Which discounts were applied  
	2. List of items that were purchased  
	3. Subtotal Pretax  
	4. Tax  
	5. Total after Tax  
	
	
# Useful Drools Resources
https://www.drools.org/  
https://www.javainuse.com/spring/spring-boot-drools-hello-world  
https://www.baeldung.com/drools-spring-integration  
https://www.tutorialspoint.com/drools/index.htm  
