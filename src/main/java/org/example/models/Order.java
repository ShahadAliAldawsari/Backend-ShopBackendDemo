package org.example.models;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import static java.util.Collections.min;

public class Order {
    private final UUID orderId;
    private Map<Product, Integer> orderItems = new HashMap<>();

    public Order (Map<Product, Integer> orderItems){
        orderId = UUID.randomUUID();
        setOrderItems(orderItems);

    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{" +
                "orderId=" + orderId +
                ", orderItems=" + orderItems +
                '}';
    }

    public Map<Product, Integer> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Map<Product, Integer> orderItems) {
        if (orderItems==null || orderItems.isEmpty())
            throw new IllegalArgumentException("The Order is empty!");
        if (min(orderItems.values())<1)
            throw new IllegalArgumentException("The quantity should be more than 0!");
        this.orderItems = orderItems;
    }

    public void addItem(Product product, Integer quantity){
        if(!(quantity>0))
            throw new IllegalArgumentException("The quantity should be more than 0!");
        if(product==null)
            throw new IllegalArgumentException("The Product is empty!");
        orderItems.put(product, quantity);

    }
}
