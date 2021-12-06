package edu.iu.c212.models;

import java.util.List;

public class User {

    //Kyle Liss

    private String username;
    private double balance;
    private List<Item> inventory;

    public User(String username, double balance, List<Item> inventory){
        this.username = username;
        this.balance = balance;
        this.inventory = inventory;
    }

    public double getBalance() {
        return balance;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public String getUsername() {
        return username;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
