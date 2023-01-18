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
  
}
