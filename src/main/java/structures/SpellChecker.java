package structures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class SpellChecker {

    // Threshold for the edit distance between the misspelled word and the correct word
    private static final int THRESHOLD = 3;
    
    public HashSet<String> dictionary;  // Dictionary
    private final String sourceFile;    // Resource file
    private final Keyboard keyboard;    // Keyboard Coordinate

    /**
     * Constructor for SpellChecker
     * @param sourceFile - Resource of Dictionary
     */
    public SpellChecker(String sourceFile) {
        dictionary = new HashSet<>(62000);
        keyboard = new Keyboard();
        this.sourceFile = sourceFile;
        createDictionary();
    }
    /**
     * Using resource file build up the dictionary.
     */
    public void createDictionary() {

        try {
            final BufferedReader br = new BufferedReader(new FileReader(sourceFile));
            String line;
            while ((line = br.readLine()) != null) {
                dictionary.add(line);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while creating dictionary!");
        }
    }
    
  /**
     * Method to check if a word is misspelled
     * @param word - user typed word.
     * @return whether the typed word is misspelled.
     */
    // 
    public boolean isMisspelled(String word) {
        return !dictionary.contains(word);
    }

    /**
     * Checker whether two strings are anagram.
     * @param word1 - first string
     * @param word2 - second string
     * @return true if they are anagram, else false.
     */
    private boolean areTheyAnagram(String word1, String word2) {

        if ( word1.length() != word2.length() )
            return false;

        int[] letterFrequencies = new int[26];
        for (int i = 0; i < word1.length(); i++) {

            if ( !Character.isLetter(word1.charAt(i)) || !Character.isLetter(word2.charAt(i)) )
                continue;

            letterFrequencies[word1.charAt(i) - 'a']++;
            letterFrequencies[word2.charAt(i) - 'a']--;
        }

        return IntStream.range(0, 26).noneMatch(i -> letterFrequencies[i] != 0);
    }

    /**
     * This algorithm is also known as Edit Distance. In this
     * method we try to find similar each words in our dictionary
     * for misspelled word. In this method we didn't calculate actual distance.
     * In the implementation Dynamic Programming is used. (dpMatrix)
     * @param word1 - first string
     * @param word2 - second string
     * @return - minimum number of edits to convert first string to second string
     */
    private int getLevenstheinDistance(String word1, String word2) {
        
        int distance = Math.max(word1.length(), word2.length()); // Initialize the distance to the length of the longer word
        
        if (word1.equals(word2)) { // If the words are the same, the distance is 0
            return 0;
        }

        // If one of the words is empty, the distance is the length of the other word
        if (word1.length() == 0 || word2.length() == 0) {
            return distance;
        }

        // Initialize a matrix to store the distances between substrings of the two words
        int[][] dpMatrix = new int[word1.length() + 1][word2.length() + 1];

        // Initialize the first row and column of the matrix
        for (int i = 0; i <= word1.length(); i++) {
            dpMatrix[i][0] = i;
        }
        for (int j = 0; j <= word2.length(); j++) {
            dpMatrix[0][j] = j;
        }

        // Calculate the distances for the rest of the matrix
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                // Calculate the cost of replacing the i-th character of word1 with the j-th character of word2
                int cost = (word1.charAt(i - 1) == word2.charAt(j - 1)) ? 0 : 1;
                
                // Calculate the minimum distance by considering the three possible operations (insertion, deletion, replacing)
                dpMatrix[i][j] = Math.min(Math.min(dpMatrix[i - 1][j] + 1, dpMatrix[i][j - 1] + 1), dpMatrix[i - 1][j - 1] + cost);
            }
        }

        // The distance is the value in the bottom right corner of the matrix
        return dpMatrix[word1.length()][word2.length()];
    }
}
