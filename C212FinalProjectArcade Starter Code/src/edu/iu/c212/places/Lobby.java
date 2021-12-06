package edu.iu.c212.places;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;
import edu.iu.c212.utils.ConsoleUtils;

public class Lobby extends Place{

    //Kyle Liss

    public Lobby(Arcade a){
         placeName = "Lobby";
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
        System.out.println("Your balance is currently: " + user.getBalance());
        Place choice = ConsoleUtils.printMenuToConsole("Welcome to the Arcade!", arcade.getAllPlaces(), true);
        assert choice != null;
        arcade.transitionArcadeState(choice.getPlaceName());
    }

    @Override
    public String toString() {
        return "Name: " + getPlaceName() + " Entry Fee: " + getEntryFee() + " Game?: False";
    }
}


