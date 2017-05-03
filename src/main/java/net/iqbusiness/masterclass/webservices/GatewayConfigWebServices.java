package net.iqbusiness.masterclass.webservices;

import net.iqbusiness.masterclass.model.RouteConfig;
import net.iqbusiness.masterclass.repository.RoutingConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by abawa on 2017/04/23.
 */
@RestController
@RequestMapping("/gateway/config")
public class GatewayConfigWebServices {

    private static final Logger log = LoggerFactory.getLogger(GatewayConfigWebServices.class);

    @Autowired
    RoutingConfigRepository routingConfigRepository;

    @RequestMapping(value = "/tea", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RouteConfig> getAvailableTeaRouteConfigs() {
        return routingConfigRepository.getAvailableTeaRouteConfigs();
    }

    @RequestMapping(value = "/tea", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RouteConfig createTeaRouteConfig(@NotNull @RequestParam String serviceName, @NotNull @RequestParam String serviceUrl) {
        return routingConfigRepository.addTeaRouteMappingConfig(serviceName, serviceUrl);
    }

    @RequestMapping(value = "/tea/active", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public RouteConfig getActiveTeaRouteConfig() {
        return routingConfigRepository.getActiveTeaRouteConfig();
    }

    @RequestMapping(value = "/tea/active/{configId}", method = RequestMethod.POST)
    public void setActiveTeaRouteConfig(@NotNull @PathVariable long configId) {
        routingConfigRepository.setActiveTeaRouteConfig(configId);
    }

    @RequestMapping(value = "/coffee", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RouteConfig> getAvailableCoffeeRouteConfigs() {
        return routingConfigRepository.getAvailableCoffeeRouteConfigs();
    }

    @RequestMapping(value = "/coffee", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RouteConfig createCoffeeRouteConfig(@NotNull @RequestParam String serviceName, @NotNull @RequestParam String serviceUrl) {
        return routingConfigRepository.addCoffeeRouteMappingConfig(serviceName, serviceUrl);
    }

    @RequestMapping(value = "/coffee/active", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public RouteConfig getActiveCoffeeRouteConfig() {
        return routingConfigRepository.getActiveCoffeeRouteConfig();
    }

    @RequestMapping(value = "/coffee/active/{configId}", method = RequestMethod.POST)
    public void setActiveCoffeeRouteConfig(@NotNull @PathVariable long configId) {
        routingConfigRepository.setActiveCoffeeRouteConfig(configId);
    }
}
