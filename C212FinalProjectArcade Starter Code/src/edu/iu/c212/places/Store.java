package edu.iu.c212.places;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.Item;
import edu.iu.c212.models.User;
import edu.iu.c212.utils.ConsoleUtils;
import edu.iu.c212.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Store extends Place{

    //Kyle Liss

    public Store(Arcade a){
        placeName = "Store";
        entryFee = 0.0;
        arcade = a;

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
    public void onEnter(User user){
        try {
            Scanner in = new Scanner(System.in);
            List<String> menuOptions = new ArrayList<>();
            menuOptions.add("Buy");
            menuOptions.add("Sell");
            menuOptions.add("Leave");

            List<Item> buyOptions = new ArrayList<>();
            buyOptions.add(Item.CANDY);
            buyOptions.add(Item.FOURWHEELER);
            buyOptions.add(Item.NERFGUN);
            buyOptions.add(Item.YOYO);
            buyOptions.add(Item.NINTENDOSWITCH);


            boolean fin = false;

            while (!fin) {

                String choice = ConsoleUtils.printMenuToConsole("Store Menu", menuOptions, true);

                if (choice.equals("Buy")) {
                    StoreAction buy = StoreAction.BUY;
                    System.out.println(buy);
                    if(arcade.getCurrentUser().getInventory().size() == 3){
                        System.out.println("Your inventory is full!");
                    }

                    else{
                        Item t = ConsoleUtils.printMenuToConsole("Items for Sale Menu: ", buyOptions, true);
                        assert t != null;
                        if(t.getValue() <= arcade.getCurrentUser().getBalance()) {
                            System.out.println("Are you sure you want to buy [" + t + "] ? Type y if yes and n if no.");
                            String input = in.next();
                            if (input.equals("y")) {
                                double val = t.getValue();
                                arcade.getCurrentUser().setBalance(arcade.getCurrentUser().getBalance() - val);
                                arcade.getCurrentUser().getInventory().remove(t);
                                FileUtils.writeUserDataToFile(arcade.getAllUsers());
                            }
                        }
                        else{
                            System.out.println("You don't have enough money to purchase your selected item.");
                        }

                    }

                }

                if (choice.equals("Sell")) {
                    StoreAction sell = StoreAction.SELL;
                    System.out.println(sell);
                    if (arcade.getCurrentUser().getInventory().size() == 0) {
                        System.out.println("Why the heck do you want to sell if you have nothing to give?!?!");
                    }
                    else {
                        Item t = ConsoleUtils.printMenuToConsole("Sell Menu (Warning!!! You will only receive 50% of value back from item): ", arcade.getCurrentUser().getInventory(), true);
                        System.out.println("Are you sure you want to sell [" + t + "] ? Type y if yes and n if no.");
                        String input = in.next();
                        if (input.equals("y")) {
                            assert t != null;
                            double val = t.getValue() * .50;
                            arcade.getCurrentUser().setBalance(arcade.getCurrentUser().getBalance() + val);
                            arcade.getCurrentUser().getInventory().remove(t);
                            FileUtils.writeUserDataToFile(arcade.getAllUsers());
                        }
                    }
                }

                if (choice.equals("Leave")) {
                    StoreAction leave = StoreAction.LEAVE;
                    System.out.println(leave);
                    fin = true;
                }
            }
        }

        catch(IOException ignore) {
        }

    }

    @Override
    public String toString() {
        return "Name: " + getPlaceName() + " Entry Fee: " + getEntryFee() + " Game?: False";
    }


}

enum StoreAction{

    BUY{
        @Override
        public String toString(){
            return "You've chosen the Buy Action";
        }

    }
    ,
    SELL{
        @Override
        public String toString(){
            return "You've chosen the Sell Action";
        }

    }
    ,
    LEAVE{
        @Override
        public String toString(){
            return "Thank you for visiting the Store!";
        }

    }
    ;

}
