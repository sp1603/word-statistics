/**
 * The WordStatistics class gives statistics about text and writing. The total
 * number of initial words that appear in sentences and the length of the
 * longest sentence in a short paragraph are counted. Additionally, the age of a
 * certain text is calulcated.
 *
 * @author Sahithya Pasagada
 * @version 1.0
 */
public class WordStatistics {
    /**The content that will be staticized. */
    private String text;

    /**The date in which this text was published. */
    private String history;

    /**  The total number of words ever counted by a WordStatistics Instance. */
    private static int totalWordCount = 0;

    /** The length of the longest sentence ever recorded by a WordStatistics Instance. */
    private static int longestSentenceLength = 0;

    /**
     * Constructor taking a text and history.
     *
     * @param text    The content that is staticized.
     * @param history The date the text was published.
     */
    public WordStatistics(String text, String history) {
        this.text = text;
        this.history = history;
    }

    /**
     * This method indicates how many unique words there are in the text for a
     * specifed count.
     *
     * @param num The number of unique initial words in the text that will have
     *            their frequencies counted throughout the text.
     * @return Returns a 2D String array where each subarray holds the unique word
     *         and its subsequent frequency.
     */
    public String[][] countInitWords(int num) {
        if (text.trim().length() == 0) {
            String[][] empty = new String[num][2];
            return empty;
        }
        String[] splitText = text.trim().toLowerCase().replaceAll("\\.", "").replaceAll(",", "").split(" ");
        return findUnique(splitText, num);
    }

    /**
     * This method generates the unique words to be counted and their frequencies.
     *
     * @param splitText A String array with the individual words of the text.
     * @param num       The number of unique initial words in the text that will
     *                  have their frequencies counted throughout the text.
     * @return Returns a 2D String array where each subarray holds the unique word
     *         and its subsequent frequency.
     */
    private String[][] findUnique(String[] splitText, int num) {
        String[] words = new String[num];
        String[] count = new String[num];
        boolean doesExist;
        int currentCount = 0;
        int compareTo = 0;

        for (int i = 0; i < num; i++) {
            currentCount = 1;
            doesExist = exist(words, splitText[i]);

            if (!doesExist) {
                words[i] = splitText[i];
                compareTo = i + 1;
            } else if (doesExist && i != splitText.length - 1) {
                words[i] = splitText[i + 1];
                compareTo = i + 2;
            }

            for (int j = compareTo; j < splitText.length; j++) {
                if (words[i].equals(splitText[j])) {
                    ++currentCount;
                }
            }
            if (words[i] == null) {
                currentCount = 0;
            }
            totalWordCount = totalWordCount + currentCount;
            count[i] = String.valueOf(currentCount);
        }
        return populateArray(words, count, num);
    }

    /**
     * This method checks to see if there are duplicates within the text that needs
     * to be counted for as one element of the individual words array.
     *
     * @param words            A String array with the unique words whose
     *                         frequencies are going to be counted.
     * @param splitTextElement The word within the text that is being checked
     *                         against to determine if it is a duplicate.
     * @return Returns a boolean indicating true if there the word within the text
     *         already exists in the individual words array and false otherwise.
     */
    private boolean exist(String[] words, String splitTextElement) {
        for (String each : words) {
            if (each != null && each.equals(splitTextElement)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method populates the 2D array needed for the countInitWords method.
     * @param words A String array with the unique words whose frequencies are going to be counted.
     * @param count A String array with the frequencies of the unique words.
     * @param num The number of unique initial words in the
     * text that will have their frequencies counted throughout the text.
     * @return Returns a 2D String array where each subarray holds the unique word and its subsequent frequency.
     */
    private String[][] populateArray(String[] words, String[] count, int num) {
        String[][] result = new String[num][2];
        int i = 0;
        int j = 0;

        for (String word : words) {
            result[i][0] = word;
            i++;
        }
        for (String counter : count) {
            result[j][1] = counter;
            j++;
        }
        return result;
    }

    /**
     * This method finds the length of the longest sentence within the text.
     *
     * @return Returns the length of the longest sentence within the text.
     */
    public int longestSentence() {
        if (text.trim().length() == 0) {
            return 0;
        }
        String[] splitSentence = text.split("\\.");
        int[] wordsPerSentence = wordCount(splitSentence);

        int instanceLength = 0;
        int currentSentence = 0;
        int currentMax = wordsPerSentence[0];
        for (int k = 0; k < wordsPerSentence.length; k++) {
            if (wordsPerSentence[k] > currentMax) {
                currentMax = wordsPerSentence[k];
                currentSentence = k;
            }
        }
        if (currentMax > longestSentenceLength) {
            instanceLength = currentMax;
        }

        if (instanceLength > longestSentenceLength) {
            longestSentenceLength = instanceLength;
        }
        System.out.println(splitSentence[currentSentence].trim() + ".");
        return instanceLength;
    }

    /**
     * This method counts the number of words within each sentence.
     *
     * @param splitSentence The String array with the individual sentences of the text.
     * @return Returns the integer array with the word count of each sentence within the text.
     */
    private int[] wordCount(String[] splitSentence) {
        int[] wordsPerSentence = new int[splitSentence.length];
        int spacesCount = 0;

        for (int i = 0; i < wordsPerSentence.length; i++) {
            String trimmed = splitSentence[i].trim();
            char[] temp = trimmed.toCharArray();
            for (int j = 0; j < temp.length; j++) {
                if (temp[j] == ' ') {
                    spacesCount++;
                }
            }
            wordsPerSentence[i] = spacesCount + 1;
            spacesCount = 0;
        }
        return wordsPerSentence;
    }

    /**
     * This method calculates the age of the text.
     *
     * @return Returns the age of the text.
     */
    public int textAge() {
        return ((2022 - (Integer.parseInt(history.substring(10, 14)))) + 1);
    }
}
