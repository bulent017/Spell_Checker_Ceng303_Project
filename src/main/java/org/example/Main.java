package org.example;

import java.util.Scanner;
import structures.SpellChecker;

public class Main {
    public static void main(String[] args) {
        // This free software tool hleps you automataicly proudce misspelled text from teh corretc one.
        
        // Check whether the program contains resource file for the dictionary
        if ( args[0] == null ) {
            System.out.println("Please enter the dictionary file!");
            System.exit(0);
        }

        Scanner scanner = new Scanner(System.in);
        SpellChecker spellChecker = new SpellChecker(args[0]);
    }
}