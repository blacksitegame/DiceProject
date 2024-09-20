package opgave03;

import java.util.Scanner;
import java.util.Arrays;

public class PigsInklusivAverageDerVirker {
    // Point counter of players
    public static int pointsPlayer1 = 0; // Global variable for player 1
    public static int pointsPlayer2 = 0; // Global varibale for player 2

    // Value of total rolls (for each player)
    public static int player1_roll = 0;
    public static int player2_roll = 0;

    // Value of rounds
    public static int player1Round = 0;
    public static int player2Round = 0;

    // Array of sum of value per round - exstra task
    // Incomplete
    public static int[] sum_player1RoundArr = new int[1000];
    public static int[] sum_player2RoundArr = new int[1000];

    // Main method of the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // scanner for user input

        greeting_users (); // Greeting the users
        printRules(); // Printing the rules for the game

        System.out.println("Would you like to play a game of Pigs? (y/n)");
        String startGameAnswer = scanner.next(); // User input (scanner) stored in variable

        // While loop for user promt yes to wanting to play the game
        while (startGameAnswer.equals("y")) { // player input equals to string value

            playPigs(); // Method invoke containing the game
            endGameStatistics();

            System.out.println("Do you wish to play again?"); // Output to user...
            //... Requesting respons from user
            startGameAnswer = scanner.next();
            pointsPlayer1 = 0; // Player_1 score counter
            pointsPlayer2 = 0; // Player_2 score counter
        }
    }

    // Method for greeting user
    private static void greeting_users (){
        System.out.println("=====================================================");
        System.out.println("Welcome to a game of Pigs");
    }

    // Method for presenting the rules of the game to the users
    private static void printRules() {
        System.out.println("=====================================================");
        System.out.println("The rules is as followed:");
        System.out.println("Each player gets one dice, which they choose to throw.");
        System.out.println("Each player will throw until the value of the dice is 1.");
        System.out.println("If he value of 1 is thrown, the players total points is reset.");
        System.out.println("The play can choose to end the game at any point -");
        System.out.println("the total value of the dices thrown will be summed together.");
        System.out.println("The player to first get 100 points win.");
        System.out.println("Good luck.");
        System.out.println("=====================================================");
    }

    // Method for the game
    private static void playPigs() {
        Scanner scanner = new Scanner(System.in); // Scanner for user input

        // Request user to input max points of the game
        System.out.println("How many rounds do you wish to play to?");
        final int roundsMaxPoint = maxPoints(); // Final varibale - never changes throughout the game

        // Output to user - informs the user of the beginning of the game
        System.out.println("The game consists of two players. Player 1 starts!");
        System.out.println("===========================================");

        // While loop for iterating over points of players in comparison to variable roundsMaxPoint
        while (pointsPlayer1 < roundsMaxPoint || pointsPlayer2 < roundsMaxPoint) {

            // Request feedback from user - want to roll the dice
            System.out.println("Would player 1 want to roll the dice? (y/n)");
            String rollDiceAnswer1 = scanner.next();

            // Counting player points in sum
            int player1Sum = 0;
            int player2Sum = 0;

            // Player 1 while loop
            while (rollDiceAnswer1.equals("y")) { // While player 1 wants to play

                // Adds 1 to playerx_roll for every dice throw
                player1_roll++;
                int[] player1Roll = rollDice(); // Value of current dice roll

                // Informing user of value
                System.out.println("You rolled: " + Arrays.toString(player1Roll));
                System.out.println("===========================================");

                if (rollDiceOne(player1Roll, player1Sum, pointsPlayer1)) {

                    // If statement that activates if the player didnt roll a 1
                    for (int i = 0; i < player1Roll.length; i++) {
                        pointsPlayer1 += player1Roll[i];
                        player1Sum += player1Roll[i];
                    }

                    System.out.println("Du har nu " + pointsPlayer1 + " point!");

                    // Win condtion method
                    if (determining_win(pointsPlayer1, roundsMaxPoint)){
                        player1Round++;
                        sum_player1RoundArr[player1Round - 1] = player1_roll;
                        player1_roll=0;
                        return;
                    }
                    // Asks the player if the want to roll again
                    System.out.println("Do you wish to roll again? (y/n)");
                    rollDiceAnswer1 = scanner.next();
                }
                else {
                    pointsPlayer1 -= player1Sum;
                    break;
                }
            }

            //
            player1Round++;
            sum_player1RoundArr[player1Round - 1] = player1_roll;
            player1_roll=0;

            //
            System.out.println("It's player 2's round!");
            System.out.println("===========================================");
            System.out.println("Would player 2 want to roll the dice?");
            String rollDiceAnswer2 = scanner.next();

            // Spiller 2 while loop

            while (rollDiceAnswer2.equals("y")) {

                // Adds 1 to playerx_roll for every dice throw
                player2_roll++;
                int[] player2Roll = rollDice();

                // Informing user of value
                System.out.println("You rolled: " + Arrays.toString(player2Roll));
                System.out.println("===========================================");

                // If statement that activates if the player didnt roll a 1
                if (rollDiceOne(player2Roll, player2Sum, pointsPlayer2)) {

                    for (int i = 0; i < player2Roll.length; i++) {
                        pointsPlayer2 += player2Roll[i];
                        player2Sum += player2Roll[i];
                    }

                    System.out.println("Du har nu " + pointsPlayer2 + " point!");

                    // Win condtion method
                    if (determining_win(pointsPlayer2, roundsMaxPoint)){
                        player2Round++;
                        sum_player2RoundArr[player2Round]=player2_roll;
                        player2_roll=0;
                        return;
                    }

                    System.out.println("Do you wish to roll again? (y/n)");
                    rollDiceAnswer2 = scanner.next();
                }
                else {
                    pointsPlayer2 -= player2Sum;
                    break;
                }
            }

            player2Round++;
            sum_player2RoundArr[player2Round]=player2_roll;
            player2_roll=0;

            printStatistics();

            System.out.println("===========================================");
            System.out.println("Nu er det spiller 1 igen!");
            System.out.println("===========================================");
        }
    }

    // Method for determining value of dices rolled
    private static int[] rollDice() {
        int[] diceArr = new int[2]; // Array with two index
        int arrayLength = diceArr.length; // length of array
        for (int i = 0; i < arrayLength; i++) { // Iteration over index in array
            int diceValue = (int) (Math.random() * 6 + 1); // dice value - random number (using math method) 1 to 6
            diceArr[i] = diceValue; // array value in index i is equal to value of diceValue
        }
        return diceArr; // Returning variable to method invoke
    }

    // Method for max points of the game
    private static int maxPoints() {
        Scanner scanner = new Scanner(System.in); // New scanner
        int numberOfRounds = scanner.nextInt(); // no of rounds equal to user input
        return numberOfRounds; // Returning value of rounds to method invoke
    }

    // Method for if dice rolled value of one
    private static boolean rollDiceOne (int [] player_roll, int sum, int points){

        if (player_roll[0]==1 || player_roll[1]==1){ // If value of
            System.out.println("Du slog desværre 1 så ingen point til dig denne runde");
            System.out.println("===========================================");
            sum=0;
            return false;
        }
        if (player_roll[0]==1 && player_roll[1]==1){
            System.out.println("Du slog desværre 2 1ere så du mister alle dine point :(");
            System.out.println("===========================================");
            reset_score(points);
            return false;
        }
        return true;
    }

    // Method for determining win
    private static boolean determining_win (int playerpoint, int rounds){
        boolean winCondition;
        if (pointsPlayer1 >= rounds){
            System.out.println("Congratulations, Player 1 WON!");
            return true;
        } if (pointsPlayer2 >= rounds){
            System.out.println("Congratulations, Player 2 WON!");
            return true;
        }
        return false;
    }

    private static int reset_score(int current_player){
        return current_player = 0;
    }

    private static double average_roll_per_round(int[] player_arr, int round_value){

        double sum = 0;
        double result = 0.0;

        if (round_value>0){
            int length = player_arr.length;

            for(int index = 0; index < length; index++){
                sum += player_arr[index];
            }
            result = sum / round_value;
        }

        return result;
    }

    private static void printStatistics (){
        System.out.println("Spiller 1 har " + pointsPlayer1 + "Point");
        System.out.println("Spiller 2 har " + pointsPlayer2 + "Point");

    }

    private static void endGameStatistics() {
        //
        System.out.print("Average roll per round: ");
        System.out.println(average_roll_per_round(sum_player1RoundArr, player1Round));

        //
        System.out.print("Average roll per round: ");
        System.out.println(average_roll_per_round(sum_player2RoundArr, player2Round));
    }
}
