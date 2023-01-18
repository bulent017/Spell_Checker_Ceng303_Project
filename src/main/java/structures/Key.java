package structures;

public class Key {
    
    public final char letter;   // English alphabet letter
    public final int row;       // Horizontal coordinate
    public final int col;       // Vertical coordinate

    /**
     * Constructor to create Key object.
     * @param letter - character uniquely identifies the key
     * @param row - horizontal coordinate for the character
     * @param col - vertical coordinate for the character
     */
    public Key(char letter, int row, int col) {
        this.letter = letter;
        this.row = row;
        this.col = col;
    }

    /**
     * @param key - other keyboard key.
     * @return - Euclidean distance between two keys.
     */
    public double getDistance(Key key) {
        return Math.sqrt((this.row - key.row) * (this.row - key.row) + (this.col - key.col) * (this.col - key.col));
    }
    

    @Override
    public String toString() {
        return String.format("(%d, %d, %c)", row, col, letter);
    }
}
