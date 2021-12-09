package edu.iu.c212;

import edu.iu.c212.models.Item;
import edu.iu.c212.models.User;
import edu.iu.c212.places.Inventory;
import edu.iu.c212.places.Lobby;
import edu.iu.c212.places.Place;
import edu.iu.c212.places.Store;
import edu.iu.c212.places.games.TriviaGame;
import edu.iu.c212.places.games.blackjack.BlackjackGame;
import edu.iu.c212.utils.FileUtils;

import java.io.IOException;
import java.util.*;

public class Arcade implements IArcade{

    //Kyle Liss

    private User currentUser;
    private List<User> allUsers;
    private List<Place> allPlaces = new ArrayList<>();

    public Arcade(){
        allUsers = getUserSaveDataFromFile();

        currentUser = getUserOnArcadeEntry();

        allPlaces.add(new Lobby(this));
        allPlaces.add(new Store(this));
        allPlaces.add(new Inventory(this));

        allPlaces.add(new TriviaGame());
        allPlaces.add(new BlackjackGame(this));

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
            FileUtils.writeUserDataToFile(getAllUsers());
        } catch(IOException ignored){}
    }

    @Override
    public void transitionArcadeState(String newPlaceNameToGoTo) {
        boolean con = false;
        for (Place allPlace : allPlaces) {
            if (allPlace.getPlaceName().equals(newPlaceNameToGoTo)) {
                if (currentUser.getBalance() >= allPlace.getEntryFee()) {
                    currentUser.setBalance(currentUser.getBalance() - allPlace.getEntryFee());
                    this.saveUsersToFile();
                    allPlace.onEnter(currentUser);
                } else {
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

        for (User value : allUsers) {
            if (value.getUsername().equals(name)) {
                System.out.println("Welcome back to the Arcade!!!");
                return value;
            }
        }

        System.out.println("Welcome to the Arcade!!!");

        System.out.print("Enter your balance: ");

        double bal = -1.0;

        while (bal < 0) {
            try {
                String balanceInput = in.next();
                bal = Double.parseDouble(balanceInput);
            } catch (NumberFormatException e) {
                System.out.print("Invalid balance. Enter a number: ");
            }
        }

        List<Item> items = new ArrayList<>();
        User user = new User(name, bal, items);
        allUsers.add(user);

        return user;
    }

    @Override
    public List<Place> getAllPlaces() {
        return allPlaces;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public List<User> getAllUsers() {
        int currentUserIndex = -1;

        for (int i = 0; i < allUsers.size(); i++) {
            if (Objects.equals(allUsers.get(i).getUsername(), currentUser.getUsername())) {
                currentUserIndex = i;
                break;
            }
        }

        if (currentUserIndex >= 0) allUsers.set(currentUserIndex, currentUser);

        return allUsers;
    }
}
