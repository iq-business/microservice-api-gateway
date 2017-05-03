package net.iqbusiness.masterclass.webservices;

import net.iqbusiness.masterclass.model.Coffee;
import net.iqbusiness.masterclass.model.RouteConfig;
import net.iqbusiness.masterclass.model.Tea;
import net.iqbusiness.masterclass.repository.RoutingConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by abawa on 2017/04/23.
 */

@RestController
@RequestMapping("/api")
public class GatewayRoutingWebServices {

    private static final Logger log = LoggerFactory.getLogger(GatewayRoutingWebServices.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RoutingConfigRepository routingConfigRepository;

    @RequestMapping(value = "/coffee", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Coffee routeCoffeeService() {
        RouteConfig coffeeRouteConfig = routingConfigRepository.getActiveCoffeeRouteConfig();
        String routeName = coffeeRouteConfig.getName();
        String url = coffeeRouteConfig.getServiceUrl();
        log.info("\n\n\t>>> Routing Request for Coffee to [{}] with URL: [{}] >>>\n", routeName, url);
        Coffee coffee = restTemplate.getForObject(url, Coffee.class);
        log.info("\n\n\t<<< Returning Response for Coffee from [{}] with URL: [{}] -  Coffee: {} <<<\n", routeName, url, coffee);
        return coffee;
    }

    @RequestMapping(value = "/tea", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Tea routeTeaService() {
        RouteConfig teaRouteConfig = routingConfigRepository.getActiveTeaRouteConfig();
        String routeName = teaRouteConfig.getName();
        String url = teaRouteConfig.getServiceUrl();
        log.info("\n\n\t>>> Routing Request for Tea to url: [{}] with URL: [{}] >>>", routeName, url);
        Tea tea = restTemplate.getForObject(url, Tea.class);
        log.info("\n\n\t<<< Returning Response for Tea from [{}] with URL: [{}] -  Coffee: {} <<<", routeName, url, tea);
        return tea;
    }
}
