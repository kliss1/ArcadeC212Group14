package edu.iu.c212.places.games;
import edu.iu.c212.models.User;
import edu.iu.c212.utils.*;
import edu.iu.c212.utils.http.HttpUtils;
import edu.iu.c212.utils.http.TriviaQuestion;

import java.util.*;
import java.io.*;

// David Resinos
/*
Trivia Game Prints 5 randomly generated Questions and the corresponding answers.
 */

public class TriviaGame extends Game {

    public void loop(User trivia) throws IOException {
        int n = 5;
        int x = 1;
        // Print welcome statement with rules and show the total amount of questions.
        System.out.println("Welcome to C212 trivia. You get $2 for every correct answer - there are "+ n + " total questions in this trivia round. ");
        ArrayList<TriviaQuestion> questions = (ArrayList<TriviaQuestion>) HttpUtils.getTriviaQuestions(n);
        // Get trivia questions and assign to questions variable as list
        for(TriviaQuestion question : questions){
            System.out.println("You are now on question "+ x++ +" Ready?");
            // Assign correct answer to string.
            String correctAnswer = question.getCorrectAnswer();
            ArrayList<String> answers = (ArrayList<String>) question.getIncorrectAnswers();
            answers.add(correctAnswer);
            // shuffle answers using built in java function
            Collections.shuffle(answers);
            String useranswer = ConsoleUtils.printMenuToConsole(question.getQuestion(),answers,true);
            // add balance to the users wallet if correct
            if(useranswer == correctAnswer){
                System.out.println("You got it right! You got $2.");
                trivia.setBalance(trivia.getBalance()+2);
            }
            // print incorrect statement
            else{
                System.out.println("You got it wrong :( The correct answer is: "+ correctAnswer);
            }
        }
    }

    public static void main(String args[]) throws IOException {
        // create new instance of class
        TriviaGame game = new TriviaGame();
        User users = new User("name", 2, new ArrayList<>());
        // run loop
        game.loop(users);
        // print balance 
        System.out.println(users.getBalance());

    }
}
