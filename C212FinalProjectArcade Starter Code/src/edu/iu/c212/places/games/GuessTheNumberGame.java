package edu.iu.c212.places.games;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;

public class GuessTheNumberGame extends Game {

    public void run(User player) throws IOException
    {
    //check if player is able to afford cost to play
    if(player.getBalance()>=getEntryFee())
    {
        player.setBalance(player.getBalance()-getEntryFee());
        //generate a random number between 1 and 100 inclusive
        Random r = new Random();
        int randomNum = r.nextInt(1, 101);
        System.out.println("Welcome to the random number guessing game!");
        System.out.println("You will be given 5 guesses, and will win $10 if you guess correctly");
        System.out.println("The number is between 1 and 100 (inclusive)");
        int count = 1;
        boolean found = false;
        Scanner k = new Scanner(System.in);
        //loop will either run 5 times or until the player guesses the number
        while(count < 6 && !found)
        {
            System.out.println("########################################");
            System.out.println("Guess " + count);
            System.out.print("What is your guess: ");
            //scanner gets the player's guess
            int guess = k.nextInt();
            //if guess is less than the answer, it lets player know
            if(guess<randomNum)
            {
                System.out.println("Your guess is too low");
            }
            //if guess is greater than the answer, it lets player know
            else if(guess>randomNum)
            {
                System.out.println("Your guess is too high");
            }
            //if guess is answer, it ends loop early and lets player know
            else
            {
                found = true;
                //gives player $10 for winning
                player.setBalance(player.getBalance()+10);
                System.out.println("You found the number!");
                System.out.println("It took you " + count + " guesses.");
            }
            //counts how many guesses the player has made
            count++;
        }
        //if the player did not find the answer, the game lets them know
        if(!found)
        {
            System.out.println("Too bad, you didn't guess the number. The number was " + randomNum);
        }
        k.close();
    }
    //clause for if player did not have enough money to play the game
    else
        System.out.println("You do not have enough money to play this game");
    }

/*    public static void main(String args[]) throws IOException
    {
        GuessTheNumberGame game = new GuessTheNumberGame();
        User users = new User("name", 5, new ArrayList<>());
        // run game
        game.run(users);
        // print balance 
        System.out.println(users.getBalance());
    }
    */
    @Override
    public Arcade getArcade() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public double getEntryFee() {
        // TODO Auto-generated method stub
        return 5;
    }
    @Override
    public String getPlaceName() {
        // TODO Auto-generated method stub
        return "GuessTheNumberGame";
    }
    @Override
    public void onEnter(User user) {
        // TODO Auto-generated method stub
        try {
            run(user);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Name: " + getPlaceName() + " | Entry Fee: " + getEntryFee() + " | Game: True";
    }
}
