package com.group43.cse360_project;

import java.util.LinkedList;

public class User {
    private String asuID;
    private String password;
    private UserType type;
    private String name;
    private String email;
    private LinkedList<String> cart;

    public User(String asuID, String password, UserType type, String name, String email) {
        this.asuID = asuID;
        this.password = password;
        this.type = type;
        this.name = name;
        this.email = email;
    }
    //Getters
    public String getAsuID() {
        return asuID;
    }

    public UserType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LinkedList<String> getCart() {
        return cart;
    }

    public void addToCart(String book){
        cart.add(book);
    }
    public void removeFromCart(String book){
        cart.remove(book);
    }


}
