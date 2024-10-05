/*
 * Course: CSC1120
 * Summer 2024
 * Lab 9 - Auto Complete
 * Name: Vlad Miziuk
 * Created: 7/21/2024
 */

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * A Binary Search Tree class that implements the AutoCompleter interface. Has methods
 * for adding words, checking if a word exists, getting the number of words,
 * and finding matches based on the prefix.
 */
public class BinarySearchTree implements AutoCompleter {
    private final TreeSet<String> items;

    /**
     * Binary Search Tree class constructor that initializes the items list
     * @param initialItems initial Items
     */
    public BinarySearchTree(List<String> initialItems) {
        items = new TreeSet<>(initialItems);
    }

    /**
     * Adds a word to the auto completer.
     *
     * @param word the word to add.
     * @return true if the word was added, false if already exists.
     * @throws IllegalArgumentException if word is null or empty.
     */
    @Override
    public boolean add(String word) throws IllegalArgumentException {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Word cannot be empty or null. ");
        }
        return items.add(word);
    }

    /**
     * Checks if the specified word exists in the auto completer.
     *
     * @param target target word to check.
     * @return true if the word exists, false otherwise, or if target is null or empty.
     */
    @Override
    public boolean exactMatch(String target) {
        return target != null && !target.isEmpty() && items.contains(target);
    }

    /**
     * The number of words in the auto completer.
     *
     * @return the size of the auto completer.
     */
    @Override
    public int size() {
        return items.size();
    }

    /**
     * A String indicating the fully qualified name of the data structure used to store
     * the words for the AutoCompleter.
     *
     * @return name of the fully qualified data structure.
     */
    @Override
    public String getBackingClass() {
        return items.getClass().getName();
    }

    /**
     * An array of all the strings in the object that begin with the prefix. If prefix
     * is an empty string, an array of all the strings in the auto completer is returned.
     * If prefix is null, an empty array is returned.
     *
     * @param prefix first couple letter of the word.
     * @return array of strings that begin with the prefix.
     */
    @Override
    public String[] allMatches(String prefix) {
        if (prefix == null) {
            return new String[0];
        }
        if (prefix.isEmpty()) {
            return items.toArray(new String[0]);
        }
        String end = prefix.substring(0, prefix.length() - 1)
                + (char) (prefix.charAt(prefix.length() - 1) + 1);

        SortedSet<String> subset = items.subSet(prefix, end);
        return subset.toArray(new String[0]);
    }
}
