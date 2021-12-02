package edu.iu.c212;

import edu.iu.c212.models.User;
import edu.iu.c212.places.Place;
import edu.iu.c212.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arcade implements IArcade{

    private User currentUser;
    private List<User> allUsers;
    private List<Place> allPlaces;

    public Arcade(){

        currentUser = getUserOnArcadeEntry();
        allUsers = new ArrayList<>();
        allPlaces = new ArrayList<>();

        transitionArcadeState("Lobby");
    }
    @Override
    public List<User> getUserSaveDataFromFile(){
        try{
            return FileUtils.getUserDataFromFile();
        }
        catch(IOException error){
            System.exit(0);
        }
        return null;
    }

    @Override
    public void saveUsersToFile() throws IOException {
        FileUtils.writeUserDataToFile(allUsers);
    }

    @Override
    public void transitionArcadeState(String newPlaceNameToGoTo) {

    }

    @Override
    public User getUserOnArcadeEntry() {
        return null;
    }

    @Override
    public List<Place> getAllPlaces() {
        return allPlaces;
    }
}
