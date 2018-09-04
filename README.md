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
HTTP POST
http://localhost:8080/rules
{
	"id": 2,
	"name": "Test",
	"cost": 100
}
```


