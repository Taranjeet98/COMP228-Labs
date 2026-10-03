import java.util.Random;

public class Lab2_TaranjeetSingh {

    public static void main(String[] args) {

        Random random = new Random();
        int dice1 = random.nextInt(6) + 1;
        int dice2 = random.nextInt(6) + 1;
        int dice3 = random.nextInt(6) + 1;
        int dice4 = random.nextInt(6) + 1;

        int sum = dice1 + dice2 + dice3 + dice4;

        System.out.println("Dice 1: " + dice1);
        System.out.println("Dice 2: " + dice2);
        System.out.println("Dice 3: " + dice3);
        System.out.println("Dice 4: " + dice4);
        System.out.println("Total dice numbers: " + sum);

        if (sum == 7 || sum == 11 || sum == 15 || sum == 21) {
            System.out.println("You WIN!");
        }
        else if (sum == 10 || sum == 12 || sum == 19 || sum == 20 || sum == 22 || sum == 23 || sum == 24) {
            System.out.println("You LOSE!");
        }
        else if (sum == 13) {
            System.out.println("You LOSE!");
        }
        else {
            int gn = sum;
            System.out.println("Kindly, roll the dice again!");
            dice1 = random.nextInt(6) + 1;
            dice2 = random.nextInt(6) + 1;
            dice3 = random.nextInt(6) + 1;
            dice4 = random.nextInt(6) + 1;

            int secSum = dice1 + dice2 + dice3 + dice4;

            System.out.println("Second roll total dice numbers: " + secSum);

            if (secSum == gn) {
                System.out.println("You WIN!");
            }
            else {
                System.out.println("You LOSE!");
            }
        }
    }
}