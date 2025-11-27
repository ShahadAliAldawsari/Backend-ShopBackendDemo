package org.example.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class User {
    private final UUID userId;

    private static final String phoneRegex = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";
    private static final Pattern phonePattern = Pattern.compile(phoneRegex);

    private static final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern emailPattern = Pattern.compile(emailRegex);

    private static final int maxUserNameLength = 100;

    private String userName;
    private String userPhone;
    private String userEmail;
    private List<Order> userOrders = new ArrayList<>();

    public User(String userName, String userPhone, String userEmail){
        userId=UUID.randomUUID();
        setUserName(userName);
        setUserPhone(userPhone);
        setUserEmail(userEmail);
        userOrders = new ArrayList<>(); //Orders will be added gradually by addOrder(order) function
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+
                '{' +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", number of orders='"+ userOrders.size()+'\'' +
                '}';
    }

    public UUID getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if(userName==null || userName.isBlank())
            throw new IllegalArgumentException("The name can not be empty");
        if(userName.length()>maxUserNameLength)
            throw new IllegalArgumentException("The nam can not be more than "+maxUserNameLength+" chars");
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        if(phonePattern.matcher(userPhone).matches())
            this.userPhone = userPhone;
        else
            throw new IllegalArgumentException("Please enter a valid phone number");
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        if(emailPattern.matcher(userEmail).matches())
            this.userEmail = userEmail;
        else
            throw new IllegalArgumentException("Please enter a valid email");
    }

    public List<Order> getUserOrders() {
        return new ArrayList<>(userOrders); //defensive copy
    }

//    public void setUserOrders(List<Order> userOrders) {
//        if(userOrders==null)
//            throw new IllegalArgumentException("The orders should not be null");
//        this.userOrders = new ArrayList<>(userOrders); //defensive copy
//    }

    public void addOrder(Order order) {
        if(order==null || order.getOrderItems()==null || order.getOrderItems().isEmpty())
            throw new IllegalArgumentException("The order should contain at least one item");
        this.userOrders.add(new Order(order.getOrderItems())); //defensive copy
    }

}
