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
  
}
