package net.iqbusiness.masterclass.model;

import java.math.BigDecimal;

/**
 * Created by abawa on 2017/04/23.
 */
public class Tea extends Beverage {

    public Tea() {
        super();
    }

    public Tea(String description, BigDecimal price) {
        super("Tea", description, price);
    }

    @Override
    public String toString() {
        return "Tae{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", price=" + getPrice() +
                '}';
    }
}
