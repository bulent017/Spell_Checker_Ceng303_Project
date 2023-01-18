package org.example;

import java.util.Scanner;
import structures.SpellChecker;
import java.util.List;

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
        
        // The program
        System.out.print("Enter a text to check misspellings (To exit type '*'): ");
        String line = scanner.nextLine();
        
        while ( !line.equals("*") ) {
            line = line.replaceAll("[^A-Za-z ]", "").toLowerCase();
            String[] words = line.split("\\s");

            for (String word : words) {
                if (spellChecker.isMisspelled(word)) {
                    System.out.printf("The word '%s' is misspelled!\n", word);  
                    System.out.println("\nSuggestion         Difference");
                    List<Suggestion> suggestions = spellChecker.getSuggestions(word);
                    suggestions.forEach(System.out::print);
                    System.out.printf("\nDid you mean '%s'?\n\n", suggestions.get(0).word);
                } else
                    System.out.printf("The word '%s' is spelled correctly!\n", word);
            }

            System.out.print("\nEnter a text to check misspellings (To exit type '*'): ");
            line = scanner.nextLine();
    }
}
