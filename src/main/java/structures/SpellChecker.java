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


}
