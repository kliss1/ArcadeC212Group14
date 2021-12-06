package edu.iu.c212.places;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;

public abstract class Place {

    //Kyle Liss

    String placeName;
    Arcade arcade;
    double entryFee;

    public abstract Arcade getArcade();

    public abstract double getEntryFee();

    public abstract String getPlaceName();

    public abstract void onEnter(User user);

    @Override
    public abstract String toString();


}
