/*
 * Course: CSC1120
 * Summer 2024
 * Lab 7 - JUnit Testing
 * Name: Vlad Miziuk
 * Created: 7/6/2024
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 Tests for the AutoCompleter interface implementations.
 */
class AutoCompleterTest {

    private UnorderedList unOrdListUsingAL;
    private UnorderedList unOrdListUsingLL;
    private OrderedList ordListUsingAL;
    private OrderedList ordListUsingLL;
    private BinarySearchTree bstAL;
    private BinarySearchTree bstLL;
    private HashTable hashTable;

    @BeforeEach
    void initialize() {
        unOrdListUsingAL = new UnorderedList(new ArrayList<>());
        unOrdListUsingLL = new UnorderedList(new LinkedList<>());

        ordListUsingAL = new OrderedList(new ArrayList<>());
        ordListUsingLL = new OrderedList(new LinkedList<>());

        bstAL = new BinarySearchTree(new ArrayList<>());
        bstLL = new BinarySearchTree(new LinkedList<>());

        hashTable = new HashTable(new ArrayList<>());
    }

    @Test
    void testUnorderedListSize() {
        for (UnorderedList unorderedList : Arrays.asList(unOrdListUsingAL, unOrdListUsingLL)) {
            Assertions.assertEquals(0, unorderedList.size());
            unorderedList.add("one");
            unorderedList.add("two");
            unorderedList.add("three");
            unorderedList.add("three");
            Assertions.assertEquals(3, unorderedList.size());
        }
    }

    @Test
    void testOrderedListSize() {
        for (OrderedList orderedList : Arrays.asList(ordListUsingAL, ordListUsingLL)) {
            Assertions.assertEquals(0, orderedList.size());
            orderedList.add("one");
            orderedList.add("two");
            orderedList.add("three");
            orderedList.add("three");
            Assertions.assertEquals(3, orderedList.size());
        }
    }

    @Test
    void testBinarySearchTreeSize() {
        for (BinarySearchTree bst : Arrays.asList(bstAL, bstLL)) {
            Assertions.assertEquals(0, bst.size());
            bst.add("one");
            bst.add("two");
            bst.add("three");
            bst.add("three");
            Assertions.assertEquals(3, bst.size());
        }
    }

    @Test
    void testHashTable() {
        Assertions.assertEquals(0, hashTable.size());
        hashTable.add("one");
        hashTable.add("two");
        hashTable.add("three");
        hashTable.add("three");
        Assertions.assertEquals(3, hashTable.size());
    }

    @Test
    void testUnorderedListGetBackingClass() {
        Assertions.assertEquals("java.util.ArrayList", unOrdListUsingAL.getBackingClass(),
                "Expected backing class name for UnorderedList is java.util.ArrayList");
        Assertions.assertEquals("java.util.LinkedList", unOrdListUsingLL.getBackingClass(),
                "Expected backing class name for UnorderedList is java.util.LinkedList");
    }

    @Test
    void testOrderedListGetBackingClass() {
        Assertions.assertEquals("java.util.ArrayList", ordListUsingAL.getBackingClass(),
                "Expected backing class name for OrderedList is java.util.ArrayList");
        Assertions.assertEquals("java.util.LinkedList", ordListUsingLL.getBackingClass(),
                "Expected backing class name for OrderedList is java.util.LinkedList");
    }

    @Test
    void testBinarySearchTreeGetBackingClass() {
        Assertions.assertEquals("java.util.TreeSet", bstAL.getBackingClass(),
                "Expected backing class name for Binary Search Tree is java.util.TreeSet");
    }

    @Test
    void testHashTableGetBackingClass() {
        Assertions.assertEquals("java.util.HashSet", hashTable.getBackingClass(),
                "Expected backing class name for HashTable is java.util.HashSet");
    }

    @Test
    void testUnorderedListAdd() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> unOrdListUsingAL.add(null),
                "Expected to throw IllegalArgumentException when adding null.");
        Assertions.assertThrows(IllegalArgumentException.class, () -> unOrdListUsingAL.add(""),
                "Expected to throw IllegalArgumentException when adding an empty string.");

        Assertions.assertTrue(unOrdListUsingAL.add("one"));
        Assertions.assertFalse(unOrdListUsingAL.add("one"));
        Assertions.assertTrue(unOrdListUsingAL.add("two"));
        Assertions.assertTrue(unOrdListUsingAL.add("three"));
        Assertions.assertEquals(3, unOrdListUsingAL.size());
    }

    @Test
    void testOrderedListAdd() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> ordListUsingAL.add(null),
                "Expected to throw IllegalArgumentException when adding null. ");
        Assertions.assertThrows(IllegalArgumentException.class, () -> ordListUsingAL.add(""),
                "Expected to throw IllegalArgumentException when adding an empty string. ");

        Assertions.assertTrue(ordListUsingAL.add("one"));
        Assertions.assertFalse(ordListUsingAL.add("one"));
        Assertions.assertTrue(ordListUsingAL.add("two"));
        Assertions.assertTrue(ordListUsingAL.add("three"));
        Assertions.assertEquals(3, ordListUsingAL.size());
    }

    @Test
    void testBinarySearchTreeAdd() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> bstAL.add(null),
                "Expected to throw IllegalArgumentException when adding null.");
        Assertions.assertThrows(IllegalArgumentException.class, () -> bstAL.add(""),
                "Expected to throw IllegalArgumentException when adding empty string.");

        Assertions.assertTrue(ordListUsingAL.add("one"));
        Assertions.assertFalse(ordListUsingAL.add("one"));
        Assertions.assertTrue(ordListUsingAL.add("two"));
        Assertions.assertTrue(ordListUsingAL.add("three"));
        Assertions.assertEquals(3, ordListUsingAL.size());
    }

    @Test
    void testHashTableAdd() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> hashTable.add(null),
                "Expected to throw IllegalArgumentException when adding null. ");
        Assertions.assertThrows(IllegalArgumentException.class, () -> hashTable.add(""),
                "Expected to throw IllegalArgumentException when adding empty string. ");

        Assertions.assertTrue(hashTable.add("one"));
        Assertions.assertFalse(hashTable.add("one"));
        Assertions.assertTrue(hashTable.add("two"));
        Assertions.assertTrue(hashTable.add("three"));
        Assertions.assertEquals(3, hashTable.size());
    }

    @Test
    void testUnorderedListExactMatch() {
        unOrdListUsingAL.add("one");
        unOrdListUsingAL.add("two");

        Assertions.assertTrue(unOrdListUsingAL.exactMatch("one"));
        Assertions.assertTrue(unOrdListUsingAL.exactMatch("two"));
        Assertions.assertFalse(unOrdListUsingAL.exactMatch("three"));

        Assertions.assertFalse(unOrdListUsingAL.exactMatch(null),
                "Expected false when input is null.");
        Assertions.assertFalse(unOrdListUsingAL.exactMatch(""),
                "Expected false when input is empty string.");
    }

    @Test
    void testOrderedListExactMatch() {
        ordListUsingAL.add("four");
        ordListUsingAL.add("five");

        Assertions.assertTrue(ordListUsingAL.exactMatch("four"));
        Assertions.assertTrue(ordListUsingAL.exactMatch("five"));
        Assertions.assertFalse(ordListUsingAL.exactMatch("six"));

        Assertions.assertFalse(ordListUsingAL.exactMatch(null),
                "Expected false when input is null.");
        Assertions.assertFalse(ordListUsingAL.exactMatch(""),
                "Expected false when input is empty string.");
    }

    @Test
    void testBinarySearchTreeExactMatch() {
        bstAL.add("seven");
        bstAL.add("eight");

        Assertions.assertTrue(bstAL.exactMatch("seven"));
        Assertions.assertTrue(bstAL.exactMatch("eight"));
        Assertions.assertFalse(bstAL.exactMatch("nine"));

        Assertions.assertFalse(bstAL.exactMatch(null),
                "Expected false when input is null.");
        Assertions.assertFalse(bstAL.exactMatch(""),
                "Expected false when input is empty string.");
    }

    @Test
    void testHashTableExactMatch() {
        hashTable.add("ten");
        hashTable.add("eleven");

        Assertions.assertTrue(hashTable.exactMatch("ten"));
        Assertions.assertTrue(hashTable.exactMatch("eleven"));
        Assertions.assertFalse(hashTable.exactMatch("twelve"));

        Assertions.assertFalse(hashTable.exactMatch(null),
                "Expected false when input is null.");
        Assertions.assertFalse(hashTable.exactMatch(""),
                "Expected false when input is empty string.");
    }

    @Test
    void testUnorderedListAllMatches() {
        unOrdListUsingAL.add("John");
        unOrdListUsingAL.add("Jacob");
        unOrdListUsingAL.add("Jonathan");
        unOrdListUsingAL.add("Jade");
        unOrdListUsingAL.add("jack");
        unOrdListUsingAL.add("Michael");

        String[] allNames = {"John", "Jacob", "Jonathan", "Jade", "jack", "Michael"};
        String[] capitalJNames = {"John", "Jacob", "Jonathan", "Jade"};
        String[] theJaNames = {"Jacob", "Jade"};

        Assertions.assertArrayEquals(allNames, unOrdListUsingAL.allMatches(""),
                "Expected an array of all the objects in the list when input in an empty string.");
        Assertions.assertArrayEquals(new String[0], unOrdListUsingAL.allMatches(null),
                "Expected an empty array when input in null.");
        Assertions.assertArrayEquals(capitalJNames, unOrdListUsingAL.allMatches("J"),
                "Expected an array of strings that start with \"J\".");
        Assertions.assertArrayEquals(theJaNames, unOrdListUsingAL.allMatches("Ja"),
                "Expected an array of strings that start with \"Ja\".");
        Assertions.assertArrayEquals(new String[0], unOrdListUsingAL.allMatches("notInList"),
                "Expected an empty array because of no match.");
    }

    @Test
    void testOrderedListAllMatches() {
        ordListUsingAL.add("apple");
        ordListUsingAL.add("orange");
        ordListUsingAL.add("Acai");
        ordListUsingAL.add("Grape");
        ordListUsingAL.add("Avocado");
        ordListUsingAL.add("Apricot");

        String[] allNamesOrdered = {"Acai", "Apricot", "Avocado", "Grape", "apple", "orange"};
        String[] capitalANames = {"Acai", "Apricot", "Avocado"};
        String[] theAvNames = {"Avocado"};

        Assertions.assertArrayEquals(allNamesOrdered, ordListUsingAL.allMatches(""),
                "Expected an array of all the objects in the list when input in an empty string. ");
        Assertions.assertArrayEquals(new String[0], ordListUsingAL.allMatches(null),
                "Expected an empty array when input in null.");
        Assertions.assertArrayEquals(capitalANames, ordListUsingAL.allMatches("A"),
                "Expected an array of strings that start with \"A\".");
        Assertions.assertArrayEquals(theAvNames, ordListUsingAL.allMatches("Av"),
                "Expected an array of strings that start with \"Av\".");
        Assertions.assertArrayEquals(new String[0], ordListUsingAL.allMatches("notInList"),
                "Expected an empty array because of no match.");
    }

    @Test
    void testBinarySearchTreeAllMatches() {
        bstAL.add("apples");
        bstAL.add("oranges");
        bstAL.add("Acais");
        bstAL.add("Grapes");
        bstAL.add("Avocados");
        bstAL.add("Apricots");

        String[] allNamesOrdered = {"Acais", "Apricots", "Avocados", "Grapes", "apples", "oranges"};
        String[] capitalANames = {"Acais", "Apricots", "Avocados"};
        String[] theAvNames = {"Avocados"};

        Assertions.assertArrayEquals(allNamesOrdered, bstAL.allMatches(""),
                "Expected an array of all the objects in the list when input in an empty string.");
        Assertions.assertArrayEquals(new String[0], bstAL.allMatches(null),
                "Expected an empty array when input in null.");
        Assertions.assertArrayEquals(capitalANames, bstAL.allMatches("A"),
                "Expected an array of strings that start with \"A\".");
        Assertions.assertArrayEquals(theAvNames, bstAL.allMatches("Av"),
                "Expected an array of strings that start with \"Av\".");
        Assertions.assertArrayEquals(new String[0], bstAL.allMatches("notInList"),
                "Expected an empty array because of no match.");
    }

    @Test
    void testHashTableAllMatches() {
        hashTable.add("Johns");
        hashTable.add("Jacobs");
        hashTable.add("Jonathans");
        hashTable.add("Jades");
        hashTable.add("jacks");
        hashTable.add("Michaels");

        String[] allNames = {"Johns", "Jacobs", "Jonathans", "Jades", "jacks", "Michaels"};
        String[] capitalJNames = {"Johns", "Jacobs", "Jonathans", "Jades"};
        String[] theJaNames = {"Jacobs", "Jades"};

        assertArrayContentsEqual(allNames, hashTable.allMatches(""),
                "Expected an array of all the objects in the list when input in an empty string. ");
        assertArrayContentsEqual(new String[0], hashTable.allMatches(null),
                "Expected an empty array when input in null.");
        assertArrayContentsEqual(capitalJNames, hashTable.allMatches("J"),
                "Expected an array of strings that start with \"J\".");
        assertArrayContentsEqual(theJaNames, hashTable.allMatches("Ja"),
                "Expected an array of strings that start with \"Ja\".");
        assertArrayContentsEqual(new String[0], hashTable.allMatches("notInList"),
                "Expected an empty array because of no match.");
    }

    private void assertArrayContentsEqual(String[] expected, String[] actual, String message) {
        Set<String> expectedSet = new HashSet<>(Arrays.asList(expected));
        Set<String> actualSet = new HashSet<>(Arrays.asList(actual));
        Assertions.assertEquals(expectedSet, actualSet, message);
    }

    @Test
    void testFormat() {
        final long testLengthFormatOne = 192750000000000L;
        final long testLengthFormatTwo = 51728000000000L;
        final long testLengthFormatThree = 2575300000000L;
        final long testLengthFormatFour = 18800000000L;
        final long testLengthFormatFive = 998800000L;
        final long testLengthFormatSix = 318800L;
        final long testLengthFormatSeven = 7L;
        final long testLengthFormatEight = 51727999999980L;

        Assertions.assertThrows(IllegalArgumentException.class, () -> AutoCompleter.format(-1),
                "Expected to throw IllegalArgumentException when input is a negative number.");

        Assertions.assertEquals("2 day(s) 5 hour(s) 32 minute(s)",
                AutoCompleter.format(testLengthFormatOne));
        Assertions.assertEquals("14 hour(s) 22 minute(s) 8 second(s)",
                AutoCompleter.format(testLengthFormatTwo));
        Assertions.assertEquals("14 hour(s) 22 minute(s) 8 second(s)",
                AutoCompleter.format(testLengthFormatEight));
        Assertions.assertEquals("42 minute(s) 55.3 second(s)",
                AutoCompleter.format(testLengthFormatThree));
        Assertions.assertEquals("18.8 second(s)",
                AutoCompleter.format(testLengthFormatFour));
        Assertions.assertEquals("998.8 millisecond(s)",
                AutoCompleter.format(testLengthFormatFive));
        Assertions.assertEquals("318.8 microsecond(s)",
                AutoCompleter.format(testLengthFormatSix));
        Assertions.assertEquals("7 nanosecond(s)",
                AutoCompleter.format(testLengthFormatSeven));
        Assertions.assertEquals("0 nanosecond(s)",
                AutoCompleter.format(0));

    }
}

  /*
  Discussion: What method did you find most difficult to test? Why?

  I think that a difficult test to implement was the allMatches() test, as it required checking
  and testing multiple scenarios, where the output differed and had to be very specific,
  returning an array with exact strings. Additionally, I had to test for null and empty input
  variables. My test implementations weren't that difficult to construct, so I'm not sure if they
  are sufficient, however I didn't find any other possible errors as I believe they should
  cover all major ones.
  */
