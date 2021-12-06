package edu.iu.c212.places;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;

public class Inventory extends Place{

    //Kyle Liss

    public Inventory(Arcade a){
        placeName = "Inventory";
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
    public void onEnter(User user) {

    }

    @Override
    public String toString() {
        return "Name: " + getPlaceName() + " Entry Fee: " + getEntryFee() + " Game?: False";
    }
}
