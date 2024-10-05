/*
 * Course: CSC1120
 * Summer 2024
 * Lab 9 - Auto Complete
 * Name: Vlad Miziuk
 * Created: 7/21/2024
 */

import java.util.*;

/**
 * An OrderedList class that implements the AutoCompleter interface. Has methods
 * for adding words, checking if a word exists, getting the number of words,
 * and finding matches based on the prefix. Allows for both an ArrayList and
 * a Linked List implementation.
 */
public class OrderedList implements AutoCompleter {

    private final List<String> items;

    /**
     * OrderedList constructor that takes in a list of strings, checks whether
     * each string in the list is unique, and initializes that items list with
     * the unique string from the input list.
     * @param list list of initial items
     */
    public OrderedList(List<String> list) {
        Set<String> unique = new HashSet<>(list);
        list.clear();
        list.addAll(unique);
        Collections.sort(list);
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

        ListIterator<String> iterator = items.listIterator();

        while (iterator.hasNext()) {
            String current = iterator.next();
            if (current.equals(word)) {
                return false;
            }
            if (current.compareTo(word) > 0) {
                iterator.previous();
                iterator.add(word);
                return true;
            }
        }
        items.add(word);
        return true;
    }

    /**
     * Checks if the specified word exists in the auto completer.
     *
     * @param target target word to check.
     * @return true if the word exists, false otherwise, or if target is null or empty.
     */
    @Override
    public boolean exactMatch(String target) {
        return target != null && !target.isEmpty() && Collections.binarySearch(items, target) >= 0;
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

        List<String> matches = new ArrayList<>();
        int startIndex = Collections.binarySearch(items, prefix);

        if (startIndex < 0) {
            startIndex = -(startIndex + 1);
        }

        int i = startIndex;
        while (i < items.size() && items.get(i).startsWith(prefix)) {
            matches.add(items.get(i));
            i++;
        }
        return matches.toArray(new String[0]);
    }
}
