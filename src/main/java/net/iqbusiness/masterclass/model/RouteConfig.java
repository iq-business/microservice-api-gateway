package net.iqbusiness.masterclass.model;

/**
 * Created by abawa on 2017/04/23.
 */
public class RouteConfig {

    private static long lastUsedId = 0L;

    private Long id;
    private String name;
    private String serviceUrl;

    private RouteConfig() {
    }

    public RouteConfig(String name, String serviceUrl) {
        this.id = getNextAvailableId();
        this.name = name;
        this.serviceUrl = serviceUrl;
    }

    private synchronized static long getNextAvailableId(){
        lastUsedId += 1L;
        return lastUsedId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RouteConfig that = (RouteConfig) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        return serviceUrl.equals(that.serviceUrl);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + serviceUrl.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RouteConfig{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", serviceUrl='" + serviceUrl + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }
}
