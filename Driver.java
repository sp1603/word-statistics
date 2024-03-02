import java.util.Scanner;
/**
 * The Driver class implements the WordStatistics class and is used to test code.
 *
 * @author Sahithya Pasagada
 * @version 1.0
 */
public class Driver {
    /**
     * This is the main method which makes use of the WordStatistics Class and its respective methods.
     * @param args Unused.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Set text: ");
        String userText = input.nextLine();
        System.out.print("Enter the initial number of words: ");
        String userNum = input.nextLine();

        WordStatistics word = new WordStatistics(userText, "Published_18700101");
        String[][] countedWords = word.countInitWords(Integer.parseInt(userNum));
        for (String[] i : countedWords) {
            for (String j : i) {
                System.out.println(j);
            }
        }

        if (userText.trim().length() != 0) {
            System.out.print("Longest Sentence: ");
            word.longestSentence();
        }

        System.out.println("Age of text: " + word.textAge());

    }
}

