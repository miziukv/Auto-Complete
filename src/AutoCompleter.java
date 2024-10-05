/*
 * Course: CSC1120
 * Summer 2024
 * Lab 7 - JUnit Testing
 * Name: Vlad Miziuk
 * Created: 7/6/2024
 */

import java.util.Random;

/**
 AutoCompleter interface that provides methods for adding words, checking if a word exists,
 getting the number of words, and finding matches based on the prefix. The class is able
 to work with various qualified data structures used to store the words. It also provides
 a default formatting method.
 */
public interface AutoCompleter {

    /**
     * Adds a word to the auto completer.
     * @param word the word to add.
     * @return true if the word was added, false if already exists.
     * @throws IllegalArgumentException if word is null or empty.
     */
    boolean add(String word);

    /**
     * Checks if the specified word exists in the auto completer.
     * @param target target word to check.
     * @return true if the word exists, false otherwise, or if target is null or empty.
     */
    boolean exactMatch(String target);

    /**
     * The number of words in the auto completer.
     * @return the size of the auto completer.
     */
    int size();

    /**
     * A String indicating the fully qualified name of the data structure used to store
     * the words for the AutoCompleter.
     * @return name of the fully qualified data structure.
     */
    String getBackingClass();

    /**
     * An array of all the strings in the object that begin with the prefix. If prefix
     * is an empty string, an array of all the strings in the auto completer is returned.
     * If prefix is null, an empty array is returned.
     * @param prefix first couple letter of the word.
     * @return array of strings that begin with the prefix.
     */
    String[] allMatches(String prefix);

    /**
     * Formats the duration in nanoseconds in a user-friendly manner
     * @param nanoseconds the duration in nanoseconds
     * @return a formatted string on the time
     * @throws IllegalArgumentException if the nanoseconds value is negative
     */
    static String format(long nanoseconds) {

        if (nanoseconds < 0) {
            throw new IllegalArgumentException("Duration can't be negative.");
        }

        final long nsPerMicSec = 1_000;
        final long nsPerMilSec = 1_000_000;
        final long nsPerSec = 1_000_000_000;
        final long nsPerMin = nsPerSec * 60;
        final long nsPerHour = nsPerMin * 60;
        final long nsPerDay = nsPerHour * 24;

        long days = nanoseconds / nsPerDay;
        nanoseconds %= nsPerDay;

        long hours = nanoseconds / nsPerHour;
        nanoseconds %= nsPerHour;

        long minutes = nanoseconds / nsPerMin;
        nanoseconds %= nsPerMin;

        long seconds = nanoseconds / nsPerSec;
        nanoseconds %= nsPerSec;

        long milliseconds = nanoseconds / nsPerMilSec;
        nanoseconds %= nsPerMilSec;

        long microseconds = nanoseconds / nsPerMicSec;
        nanoseconds %= nsPerMicSec;

        long remainingNs = nanoseconds;

        double totalSec = seconds + ((double) milliseconds / 1000)
                + ((double) microseconds / 1000000) + ((double) nanoseconds / 1000000000);

        double totalMilSec = milliseconds + ((double) microseconds / 1000)
                + ((double) nanoseconds / 1000000);

        double totalMicSec = microseconds + ((double) nanoseconds / 1000);

        if (days > 0) {
            return days + " day(s) " + hours + " hour(s) " + minutes + " minute(s)";
        }
        if (hours > 0) {
            return hours + " hour(s) " + minutes + " minute(s) "
                    + Math.round(totalSec) + " second(s)";
        }
        if (minutes > 0) {
            return String.format("%d minute(s) %.1f second(s)", minutes, totalSec);
        }
        if (seconds > 0) {
            return String.format("%.1f second(s)", totalSec);
        }
        if (milliseconds > 0) {
            return String.format("%.1f millisecond(s)", totalMilSec);
        }
        if (microseconds > 0) {
            return String.format("%.1f microsecond(s)", totalMicSec);
        } else {
            return remainingNs + " nanosecond(s)";
        }
    }

    /**
     * Creates a string of randomly generated lowercase letters of a given length.
     * @param length length of the desired string to be created
     * @return the random string
     */
    static String getString(int length) {
        Random rand = new Random();
        StringBuilder randomString = new StringBuilder();
        final int numLetters = 26;

        for (int i = 0; i < length; i++) {
            randomString.append((char) ('a' + rand.nextInt(numLetters)));
        }
        return randomString.toString();
    }
}
