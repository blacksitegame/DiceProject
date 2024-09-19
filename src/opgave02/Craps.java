package opgave02;

import java.util.Scanner;

public class Craps {

    /* Her starter spillet med en lille velkomst besked og man bliver
    spurgt om hvorvidt man vil starte spillet */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hej og velkommen til Craps");
        System.out.println("===========================");
        System.out.println("Ønsker du at spille? (y/n)");
        String answerStartGame = scanner.next();

        // Her er et while loop der sørger for at man kan starter igen når spillet slutter

        while (answerStartGame.equals("y")) {

            // Her er metodekaldet til selve spillet "playCraps"

            playCraps();

            System.out.println("Ønsker du at spille igen?");
            answerStartGame = scanner.next();
        }
        System.out.println("Tak for spillet.");

    }


    // Her er metoden for selve spillet

    private static void playCraps (){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ønsker du at kaste først terning? (y/n)");
        String answerFirstThrow  = scanner.next();

        // Her bliver de første kast kastet med at kald af "rollDice" metoden

        if (answerFirstThrow.equals("y")) {
            int[] rollDiceResult = rollDice();


            System.out.println("Først kast");
            System.out.println("==================================");
            System.out.println("Terning 1: " + rollDiceResult[0]);
            System.out.println("Terning 2: " + rollDiceResult[1]);

            int sumFirstThrow = rollDiceResult[0] + rollDiceResult[1];

            System.out.println("Total: " + sumFirstThrow);

            /* Her fortsætter sillet ved hjælp af "crapsFirstThrow" metoden.
               alt afhængigt af udfaldet for den metode bliver spilleren enten sendt tilbage til main metoden
               ellers spillet forsætter. */

            if (crapsFirstThrow(sumFirstThrow)){
                System.out.println("Vil du kaste næste terning? (y/n)");
                String answerContinue = scanner.next();
                while (answerContinue.equals("y")){

                    /*Her kalder vi på "rollForPoint" metoden som afgør hvorvidt spillet slutter eller man skal slå igen
                    ud fra slagene slået efter første slag */

                    if (rollForPoint(sumFirstThrow)){
                        System.out.println("Du vandt");
                        return;
                    }
                    else {
                        System.out.println("Kast igen? (y/n)");
                        answerContinue = scanner.next();
                    }

                }
            }
            else {
                return;
            }
        }
        else {
            System.out.println("Fair nok");
            return;
        }



    }

    // Her er metoden som sørger for udfaldet af det første slag

    public static boolean crapsFirstThrow(int sum){
        if (sum == 7 || sum == 11){
            System.out.println("Winner winner chicken dinner");
            return false;
        }
        else if (sum == 2 || sum == 3 || sum == 12){
            System.out.println("Du er en taber");
            return false;
        }
        else{
            return true;
        }

    }

    // Her er metoden som sørger for udfaldet af slagende efter det første slag

    public static boolean rollForPoint (int point){
        int [] rollForPoint = rollDice();
        int sum2 = rollForPoint[0]+rollForPoint[1];
        System.out.println("Slag: " + sum2);
        if (sum2==7 || sum2==point){
            return true;
        }
        else {
            return false;
        }


    }

    // Her er metoden som sørger for selve terningerne

    private static int[] rollDice() {

        int [] diceArr = new int[2];
        int arrayLength = diceArr.length;
        for (int i = 0; i < arrayLength; i++){
            int diceValue = (int)(Math.random()*6+1);
            diceArr[i]=diceValue;
        }

        return diceArr;
    }


}
