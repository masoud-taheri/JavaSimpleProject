package guessinggame;
import javax.swing.*;

public class GuessingGame  {
    public static void main(String[] args) {
        int ComputerNumber = (int) (Math.random()*100 + 1);
        int userAnswer = 0;
        System.out.println("The correct guess would be " + ComputerNumber);
        int count = 1;

        while (userAnswer != ComputerNumber)
        {
            String response = JOptionPane.showInputDialog(null,
                "Enter a guess between 1 and 100", "Guessing Game", 3);
            userAnswer = Integer.parseInt(response);
            JOptionPane.showMessageDialog(null, ""+ determineGuess(userAnswer, ComputerNumber, count));
            count++;
        }  
    }
    public static String determineGuess(int userReponse, int ComputerNumber, int count){
        if (userReponse <=0 || userReponse >100) {
            return "Your guess is invalid";
        }
        else if (userReponse == ComputerNumber ){
            return "Correct!\nTotal Guesses: " + count;
        }
        else if (userReponse > ComputerNumber) {
            return "Your guess is too high, try again.\nTry Number: " + count;
        }
        else if (userReponse < ComputerNumber) {
            return "Your guess is too low, try again.\nTry Number: " + count;
        }
        else {
            return "Your guess is incorrect\nTry Number: " + count;
        }
    }
}
