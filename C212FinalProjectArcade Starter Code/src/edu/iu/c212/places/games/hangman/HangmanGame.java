package edu.iu.c212.places.games.hangman;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;
import edu.iu.c212.places.games.Game;
import edu.iu.c212.utils.ConsoleUtils;
import edu.iu.c212.utils.http.HttpUtils;

public class HangmanGame extends Game implements IHangmanGame{

    @Override
    public String getBlurredWord(List<Character> guesses, String word) {
        // TODO Auto-generated method stub
        String ret = "";
        //runs through each character in the word
        for(int x = 0; x<word.length(); x++)
        {
            //if the list of guesses contains the character, then add the character to the return string
            if(guesses.contains(word.charAt(x)))
            {
                ret = ret + word.charAt(x);
            }
            //else, add an * instead
            else
                ret = ret + "*";
        }
        //return the string with hidden (and revealed) characters
        return ret;
    }

    @Override
    public List<Character> getValidLexicon() {
        // TODO Auto-generated method stub
        //makeing a list of all lowercase characters
        ArrayList<Character> letters = new ArrayList<Character>();
        letters.add('a');
        letters.add('b');
        letters.add('c');
        letters.add('d');
        letters.add('e');
        letters.add('f');
        letters.add('g');
        letters.add('h');
        letters.add('i');
        letters.add('j');
        letters.add('k');
        letters.add('l');
        letters.add('m');
        letters.add('n');
        letters.add('o');
        letters.add('p');
        letters.add('q');
        letters.add('r');
        letters.add('s');
        letters.add('t');
        letters.add('u');
        letters.add('v');
        letters.add('w');
        letters.add('x');
        letters.add('y');
        letters.add('z');
        return letters;
    }

    public void run(User player) throws IOException
    {
        //check if the player has enough money to play game
        if(player.getBalance()>=getEntryFee())
        {
            //deduct the cost from the player's balance
            player.setBalance(player.getBalance()-getEntryFee());
            System.out.println("Welcome to the hangman game!");
            System.out.println("You will guess letters in the hidden word until you find the whole word");
            System.out.println("If you guess 6 incorrct letters, you will lose the game.");
            System.out.println("If you guess the word correctly, you win $15!");
            //variable that tracks if player wins or not
            boolean found = false;
            //variable that tracks how many incorrect guesses have been made
            int count = 0;
            //list of guesses made
            ArrayList<Character> guesses = new ArrayList<Character>();
            //the randomly generated word the player tries to guess
            String randWord = HttpUtils.getRandomHangmanWord();
            //list of characters that the player can guess from
            ArrayList<Character> lexicon = new ArrayList<Character>(getValidLexicon());
            //what the player guesses
            String g = "";
            while (count<6 && !found)
            {
                System.out.println("####################################################################");
                System.out.println("The current word is " + getBlurredWord(guesses, randWord));
                System.out.println("You have done " + count + " incorrect guesses. Guesses done:" + guesses);
                System.out.println("Please choose a word from the list: " + lexicon);
                //gets the player's guess from the console
                g = ConsoleUtils.readLineFromConsole();
                //sets the characters in the guess to lowercase for comparison to the lexicon
                g = g.toLowerCase();
                //checks if the player's guess is valid (only one character and it is in the lexicon)
                if(g.length()==1 && lexicon.contains(g.charAt(0)))
                {
                    //checks if the guess is not correct
                    if(!randWord.contains(g))
                    {
                        count++;
                    }
                    //adds the guess to the list of guesses
                    guesses.add(g.charAt(0));
                    //removes the guess from the list of avalible characters to guess
                    lexicon.remove(lexicon.indexOf(g.charAt(0)));
                    //checks if the player has found the entire word
                    if(!getBlurredWord(guesses, randWord).contains("*"))
                    {
                        found = true;
                    }
                }
                //if the guess was not valid, still counts it as an incorrect guess
                else
                {
                    System.out.println("Error, not a valid guess. Guess deducted");
                    count++;
                }
            }
            //checks if the word was found, and if so it adds $15 to the player's balance
            if(found == true)
            {
                System.out.println("You found the word! It took you " + count + " incorrect guesses");
                System.out.println("You won $15");
                player.setBalance(player.getBalance()+15);
            }
            //for if the player did not find the word
            else
            {
                System.out.println("Too bad, you didn't find the word");
                System.out.println("The word was " + randWord);
            }
        }
        //for if the player did not have enough money to play
        else
            System.out.println("You do not have enough money to play this game");
    }

   /* public static void main(String args[]) throws IOException
    {
        HangmanGame game = new HangmanGame();
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
        return "HamgmanGame";
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Name: " + getPlaceName() + " | Entry Fee: " + getEntryFee() + " | Game: True";
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
}
