package net.iqbusiness.masterclass.repository;

import net.iqbusiness.masterclass.model.RouteConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by abawa on 2017/04/23.
 */

@Repository
public class RoutingConfigRepository {

    private static final Logger log = LoggerFactory.getLogger(RoutingConfigRepository.class);

    private static Map<String, RouteConfig> teaRouteMappings = new HashMap<>();
    private static Map<String, RouteConfig> coffeeRouteMappings = new HashMap<>();

    private static RouteConfig activeTeaRouteConfig;
    private static RouteConfig activeCoffeeRouteConfig;

    @PostConstruct
    private void setupRouteConfigData() {

        log.info("\n\n\t >> Starting @PostConstruct setupRouteConfigData() >>\n");
        if(teaRouteMappings.isEmpty()) {
            addTeaRouteMappingConfig("Tea Service - Monolith", "http://localhost:8080/api/tea");
            addTeaRouteMappingConfig("Tea Service - Microservice", "http://localhost:9191/api/tea");
            addTeaRouteMappingConfig("Tea Service 2.0 - Microservice", "http://localhost:9393/api/tea");
            setActiveTeaRouteConfig(teaRouteMappings.get("Tea Service - Monolith"));
        }

        if(coffeeRouteMappings.isEmpty()) {
            addCoffeeRouteMappingConfig("Coffee Service - Monolith", "http://localhost:8080/api/coffee");
            addCoffeeRouteMappingConfig("Coffee Service - Microservice", "http://localhost:9292/api/coffee");
            setActiveCoffeeRouteConfig(coffeeRouteMappings.get("Coffee Service - Monolith"));
        }
        log.info("\n\n\t << Done @PostConstruct setupRouteConfigData() <<\n");
    }

    public RouteConfig addTeaRouteMappingConfig(String name, String serviceUrl) {
        RouteConfig teaRouteConfig = new RouteConfig(name, serviceUrl);
        teaRouteMappings.put(name, teaRouteConfig);
        return teaRouteConfig;
    }

    public RouteConfig addCoffeeRouteMappingConfig(String name, String serviceUrl) {
        RouteConfig coffeeRouteConfig = new RouteConfig(name, serviceUrl);
        coffeeRouteMappings.put(name, coffeeRouteConfig);
        return coffeeRouteConfig;
    }

    public RouteConfig getActiveTeaRouteConfig() {
        return activeTeaRouteConfig;
    }

    public void setActiveTeaRouteConfig(RouteConfig activeTeaRouteConfig) {
        log.info("\n\n\t >>> setActiveTeaRouteConfig: {} >>>\n", activeTeaRouteConfig);
        RoutingConfigRepository.activeTeaRouteConfig = activeTeaRouteConfig;
    }

    public void setActiveTeaRouteConfig(long configId) {
        for (RouteConfig routeConfig: getAvailableTeaRouteConfigs()) {
            if(configId == routeConfig.getId()) {
                setActiveTeaRouteConfig(routeConfig);
            }
        }
    }

    public RouteConfig getActiveCoffeeRouteConfig() {
        return activeCoffeeRouteConfig;
    }

    public void setActiveCoffeeRouteConfig(RouteConfig activeCoffeeRouteConfig) {
        log.info("\n\n\t >>> setActiveCoffeeRouteConfig: {} >>>\n", activeCoffeeRouteConfig);
        RoutingConfigRepository.activeCoffeeRouteConfig = activeCoffeeRouteConfig;
    }

    public List<RouteConfig> getAvailableTeaRouteConfigs() {
        List<RouteConfig> routeConfigs = new ArrayList<>(teaRouteMappings.size());
        routeConfigs.addAll(teaRouteMappings.values());
        return routeConfigs;
    }

    public void setActiveCoffeeRouteConfig(long configId) {
        for (RouteConfig routeConfig: getAvailableCoffeeRouteConfigs()) {
            if(configId == routeConfig.getId()) {
                setActiveCoffeeRouteConfig(routeConfig);
            }
        }
    }

    public List<RouteConfig> getAvailableCoffeeRouteConfigs() {
        List<RouteConfig> routeConfigs = new ArrayList<>(coffeeRouteMappings.size());
        routeConfigs.addAll(coffeeRouteMappings.values());
        return routeConfigs;
    }
}
