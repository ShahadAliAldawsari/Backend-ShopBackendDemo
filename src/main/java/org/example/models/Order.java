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
            throw new IllegalArgumentException("Order must contain at least one product!");
        if (min(orderItems.values())<1)
            throw new IllegalArgumentException("The quantity should be more than 0!");
        this.orderItems = new HashMap<>(orderItems); //a defensive copy
    }

    public void addItem(Product product, Integer quantity){
        if(product==null)
            throw new IllegalArgumentException("The Product is empty!");
        if(!(quantity>0))
            throw new IllegalArgumentException("The quantity should be more than 0!");
        //orderItems.put(product, quantity);//it does not tchick if the product is already exist
        orderItems.merge(product, quantity, Integer::sum);//if the product exists it will just increase the quantity

    }

}
