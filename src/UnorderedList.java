/*
 * Course: CSC1120
 * Summer 2024
 * Lab 7 - JUnit Testing
 * Name: Vlad Miziuk
 * Created: 7/6/2024
 */

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * An UnorderedList class that implements the AutoCompleter interface. Has methods
 * for adding words, checking if a word exists, getting the number of words,
 * and finding matches based on the prefix. Allows for both an ArrayList and
 * a Linked List implementation.
 */
public class UnorderedList implements AutoCompleter {
    private final List<String> items;

    /**
     * UnorderedList constructor that takes in a list of strings, checks whether
     * each string in the list is unique, and initializes that items list with
     * the unique string from the input list.
     * @param list list of initial items
     */
    public UnorderedList(List<String> list) {
        Set<String> unique = new HashSet<>(list);
        list.clear();
        list.addAll(unique);
        items = list;
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
            throw new IllegalArgumentException("Word cannot be empty or null.");
        }
        boolean added = true;
        if (items.contains(word)) {
            added = false;
        } else {
            items.add(word);
        }
        return added;
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
        return prefix == null ? new String[0] :
                items.stream()
                     .filter(s -> s.startsWith(prefix))
                     .toArray(String[]::new);

    }
}
