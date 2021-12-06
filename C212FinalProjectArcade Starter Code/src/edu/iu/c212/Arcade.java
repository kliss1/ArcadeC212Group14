package edu.iu.c212;

import edu.iu.c212.models.Item;
import edu.iu.c212.models.User;
import edu.iu.c212.places.Inventory;
import edu.iu.c212.places.Lobby;
import edu.iu.c212.places.Place;
import edu.iu.c212.places.Store;
import edu.iu.c212.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Arcade implements IArcade{

    //Kyle Liss

    private User currentUser;
    private List<User> allUsers;
    private List<Place> allPlaces;

    public Arcade(){

        currentUser = getUserOnArcadeEntry();
        allUsers = new ArrayList<>();
        allUsers.add(currentUser);
        allPlaces = new ArrayList<>();
        allPlaces.add(new Lobby(this));
        allPlaces.add(new Store(this));
        allPlaces.add(new Inventory(this));


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
    public void saveUsersToFile(){
        try {
            FileUtils.writeUserDataToFile(allUsers);
        }
        catch(IOException ignored){
        }
    }

    @Override
    public void transitionArcadeState(String newPlaceNameToGoTo) {
        boolean con = false;
        for(int i = 0; i < allPlaces.size(); i++){

            if(allPlaces.get(i).getPlaceName().equals(newPlaceNameToGoTo)){
                if(currentUser.getBalance() >= allPlaces.get(i).getEntryFee()) {
                    currentUser.setBalance(currentUser.getBalance()-allPlaces.get(i).getEntryFee());
                    this.saveUsersToFile();
                    allPlaces.get(i).onEnter(currentUser);
                }
                else{
                    con = true;
                }
            }
        }

        if(con){
            transitionArcadeState("Lobby");
        }

    }

    @Override
    public User getUserOnArcadeEntry() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a username: ");
        String name = in.next();

        List<User> temp = getUserSaveDataFromFile();
        for(int i = 0; i < temp.size(); i++){
            if(temp.get(i).getUsername().equals(name)){
                System.out.println("Welcome back to the Arcade!!!");
                return temp.get(i);
            }
        }

        System.out.println("Welcome to the Arcade!!!");

        System.out.print("Enter your balance as a decimal: ");
        double bal = in.nextDouble();

        List<Item> items = new ArrayList<>();
        User fin = new User(name, bal, items);
        allUsers.add(fin);
        this.saveUsersToFile();

        return fin;
    }

    @Override
    public List<Place> getAllPlaces() {
        return allPlaces;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public List<User> getAllUsers() {
        return allUsers;
    }
}
