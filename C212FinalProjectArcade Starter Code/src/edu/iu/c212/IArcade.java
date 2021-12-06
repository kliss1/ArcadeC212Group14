package edu.iu.c212;

import edu.iu.c212.models.User;
import edu.iu.c212.places.Place;

import java.io.IOException;
import java.util.List;

public interface IArcade {

    //Kyle Liss

    List<User> getUserSaveDataFromFile() throws IOException;

    void saveUsersToFile() throws IOException;

    void transitionArcadeState(String newPlaceNameToGoTo);

    User getUserOnArcadeEntry();

    List<Place> getAllPlaces();



}
