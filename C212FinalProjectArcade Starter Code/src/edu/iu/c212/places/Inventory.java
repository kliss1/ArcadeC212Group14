package edu.iu.c212.places;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.Item;
import edu.iu.c212.models.User;

import java.io.IOException;
import java.util.*;

public class Inventory extends Place{

    //Kyle Liss

    public Inventory(Arcade a){
        placeName = "Inventory";
        entryFee = 0.0;
        arcade = a;

    }

    static class UniqueItem {
        int quantity;
        Item item;

        public UniqueItem (int quantity, Item item) {
            this.quantity = quantity;
            this.item = item;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    @Override
    public Arcade getArcade() {
        return arcade;
    }

    @Override
    public double getEntryFee() {
        return entryFee;
    }

    @Override
    public String getPlaceName() {
        return placeName;
    }

    @Override
    public void onEnter(User user) {
        System.out.println("Hi, " + user.getUsername() + "! Your inventory looks like this:");

        ArrayList<UniqueItem> uniqueItems = new ArrayList<>();
        ArrayList<Item> unique = new ArrayList<>(new HashSet<>(user.getInventory()));
        ArrayList<String> names = new ArrayList<>();

        for (int i = 0; i < user.getInventory().size(); i++) {
            names.add(user.getInventory().get(i).getReadableName());
        }

        for (Item value : unique) {
            String name = value.getReadableName();
            uniqueItems.add(new UniqueItem(Collections.frequency(names, name), value));
        }

        double totalWorth = 0;
        int totalItems = 0;
        for (UniqueItem item : uniqueItems) {
            totalItems += item.quantity;
            totalWorth += item.quantity * item.item.getValue();
            System.out.println(item.item.getReadableName() + ": " + item.quantity + " (value: " + item.item.getValue() * item.quantity + ")");
        }

        System.out.println("Total net worth: " + totalWorth);

        if (totalItems == 3)
            System.out.println("REMEMBER! You can only have 3 items at a time. Sell one by going to the store");

        arcade.transitionArcadeState("Lobby");
    }

    @Override
    public String toString() {
        return "Name: " + getPlaceName() + " | Entry Fee: " + getEntryFee() + " | Game: False";
    }
}
