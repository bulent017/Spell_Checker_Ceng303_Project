package structures;

import java.util.HashMap;

public class Keyboard {
    
    public HashMap<Character, Key> keyboardLayout;

    /**
     * Constructor for Keyboard class
     */
    public Keyboard() {
        keyboardLayout = new HashMap<>();
        generateKeyboard();
    }

    /**
     * Using English keyboard key orders generate keyboard layout.
     */
    public void generateKeyboard() {
        String[] rows = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
        
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows[i].length(); j++) {
                keyboardLayout.put(rows[i].charAt(j), new Key(rows[i].charAt(j), i, j));
            }
        }
        
        keyboardLayout.put('\'', new Key('\'', -1, 1));
    }
}
