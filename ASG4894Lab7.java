/*Ganesh, Akshara
 */
package asg4894lab7;

import java.util.Arrays;
import java.util.Scanner;

public class ASG4894Lab7 {

    public static void main(String[] args) {
        // Do not change anything in the main method.
        int numRolls = 1000;
        int scale = 10;
        int[] diceArray = getDice();
        int[] rolls = rollDice(diceArray, numRolls);
        System.out.println(getTable(rolls, diceArray, numRolls));
        System.out.println(getGraph(rolls, scale));
        // Do not change anything in the main method.
    }

    public static int[] getDice() {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the number of dice between 2 & 6: ");
        int numDice = keyboard.nextInt();
        int[] sides = new int[numDice];
        for (int i = 1; i < numDice + 1; i++) {
            System.out.print("Sides for Dice " + i + ": ");
            int numSides = keyboard.nextInt();
            sides[i - 1] = numSides;
        }
        return sides;
    }

    public static int[] rollDice(int[] diceArray, int numRolls) {
        int temp = 0;
        for (int i = 0; i < diceArray.length; i++) {
            temp += diceArray[i];//max sum
        }
        int[] frequency = new int[temp - diceArray.length + 1];
        for (int j = 0; j < numRolls; j++) {
            int total = 0;
            for (int i = 0; i < diceArray.length; i++) {
                total += (int) (Math.random() * diceArray[i] + 1);
            }
            frequency[total - diceArray.length] += 1;
        }
        return frequency;
    }

    public static String getTable(int[] rolls, int[] diceArray, int numRolls) {
        String results = "Result: ";
        for (int i = 0; i < rolls.length; i++) {
            results += rolls[i] + "\t";
        }
        String value = "Value: " + "\t" + diceArray.length;
        for (int i = 1; i < rolls.length; i++) {
            value += "\t" + (i + diceArray.length);
        }
        String sentence = "Rolling " + diceArray.length + " dice " + Arrays.toString(diceArray) + " " + numRolls + " times.";
        String odds = "Odds: " + "\t";
        for (int i = 0; i < rolls.length; i++) {
            odds += String.format("%.3f", (double) rolls[i] / numRolls) + "\t";
        }
        return sentence + "\n" + value + "\n" + results + "\n" + odds;
    }

    public static String getGraph(int[] rolls, int scale) {
        String drawStars = "";
        int numRows = 0;
        int largestNum = 0;
        for (int j = 0; j < rolls.length; j++) {
            if (rolls[j] > largestNum) {
                largestNum = rolls[j];
            }
        }
        numRows = (int) ((((double) largestNum / (double) scale)) + (double) .9);

        int[][] stars = new int[numRows][rolls.length];

        for (int col = 0; col < rolls.length; col++) {
            int numStars = (int) ((((double) rolls[col] / (double) scale) + (double) .9));
            for (int row = 0; row < numRows; row++) {
                if ((numRows - row) <= numStars) {
                    stars[row][col] = 1;//where to put 1 in the array
                }
            }
        }
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < rolls.length; col++) {
                if (stars[row][col] == 1) {
                    drawStars += "*  ";
                } else {
                    drawStars += "   ";
                }
                if (col == (rolls.length - 1)) {
                    drawStars += "\n";
                }
            }
        }

        return drawStars;

    }
}
