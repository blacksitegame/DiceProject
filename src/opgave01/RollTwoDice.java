package opgave01;

import java.util.Scanner;
import java.util.Arrays;

public class RollTwoDice {
    private static int rollCount = 0;
    private static int totalSum = 0;
    private static int totalSameEyes = 0;
    private static int [] sameEyes = new int[6];

    public static void main(String[] args) {
        System.out.println("Velkommen til spillet, rul en terning.");
        printRules();
        System.out.println();

        playOneDie();

        System.out.println();
        System.out.println("Tak for at spille, rul en terning.");
    }

    private static void printRules() {
        System.out.println("=====================================================");
        System.out.println("Regler for rul en terning");
        System.out.println("Spilleren ruller en terning, så længde man lyster.");
        System.out.println("=====================================================");
    }

    private static void playOneDie() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Rul en terning? ('ja/nej') ");
        String answer = scanner.nextLine();
        while (!answer.equals("nej")) {
            int [] faces = rollDice();
            System.out.println("Du har slået: " + Arrays.toString(faces));
            int sum = sum(faces);
            System.out.println("Du slog i alt: " + sum);

            System.out.println("Største slag var: " + Math.max(faces[0],faces[1] ));

            updateStatistics(faces);

            System.out.print("Rul en terning? ('ja/nej') ");
            answer = scanner.nextLine();
        }

        printStatistics();
        System.out.println();
        scanner.close();
    }

    private static int[] rollDice() {

        int [] diceArr = new int[2];
        int arrayLength = diceArr.length;
        for (int i = 0; i < arrayLength; i++){
            int diceValue = (int)(Math.random()*6+1);
            diceArr[i]=diceValue;
        }

        return diceArr;
    }


    private static int sum (int [] diceFaces){
        int [] result = diceFaces;

        int sum = result[0] + result[1];

        return sum;
    }

    private static void updateStatistics(int [] faces) {
        rollCount++;
        for (int i = 1; i <= 6; i++) {
            if (faces[0] == i) {
                sameEyes[i-1]++;
            }
            if (faces[1] == i) {
                sameEyes[i-1]++;
            }
        }
        if (faces[0]==faces[1]){
            System.out.println("Du slog par.");
            totalSameEyes++;
        }
        totalSum+=faces[0]+faces[1];
    }

    private static void printStatistics() {
        System.out.println("Results:");
        System.out.println("-------");
        System.out.println("Antal rul: " + rollCount);
        System.out.println("Total øjne af slag: " + totalSum);
        System.out.println("Antal gange terningerne havde samme øjne: " + totalSameEyes);
        for (int i = 0; i<sameEyes.length; i++)
            System.out.println("Antal " + (i+1) + "ere: " + sameEyes[i]);
    }

}
