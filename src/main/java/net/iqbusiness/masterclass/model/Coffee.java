package net.iqbusiness.masterclass.model;

import java.math.BigDecimal;

/**
 * Created by abawa on 2017/04/17.
 */
public class Coffee extends  Beverage {

    public Coffee() {
        super();
    }

    public Coffee(String description, BigDecimal price) {
        super("Coffee", description, price);
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", price=" + getPrice() +
                '}';
    }


}
