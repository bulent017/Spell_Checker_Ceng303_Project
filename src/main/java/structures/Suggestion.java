package structures;

import java.lang.Comparable;

public class Suggestion implements Comparable<Suggestion> {
    
    public String word;
    public double distance; // Add a declaration for the distance field

    /**
     * Constructor for Suggestion class
     * @param word - a word
     * @param distance - distance to the user typed word
     */
    public Suggestion(String word, double distance) {
        this.word = word;
        this.distance = distance;
    }

    // Compare two suggestions based on their distance
    public int compareTo(Suggestion other) {
        return Double.compare(this.distance, other.distance);
    }

    @Override
    public String toString() {
        return String.format("%-20s %.2f\n", word, distance);
    }
}
