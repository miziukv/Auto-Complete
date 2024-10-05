/*
 * Course: CSC1120
 * Summer 2024
 * Lab 10 - Even More Auto Complete
 * Name: Vlad Miziuk
 * Created: 7/28/2024
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Benchmarking Program that shows gets data to characterize the performance of add(),
 * exactMatch() and allMatches() methods defined in the AutoCompleter interface as a function
 * of input size.
 */
public class BenchmarkingProgram {
    private static final int LENGTH_OF_RAND_STRING = 10;
    public static void main(String[] args) {
        final int[] sizes = {1000,
                5000, 10000, 30000, 50000, 70000, 100000, 110000,
                120000, 130000, 140000, 150000, 160000, 170000, 180000, 190000,
                200000, 210000, 220000, 230000, 240000, 250000, 260000,
                270000, 280000, 290000, 300000};

        benchmarkOrderedListLL(sizes);
        System.out.println();
        benchmarkBST(sizes);
    }

    private static void benchmarkOrderedListLL(int[] sizes) {
        System.out.println("Benchmarks for OrderedList (LL)");
        for (int size : sizes) {
            List<String> words = generateRandWords(size, "LL");
            OrderedList orderedList = new OrderedList(words);

            benchmarkResults(size, orderedList);
        }
    }

    private static void benchmarkBST(int[] sizes) {
        System.out.println("Benchmarks for Binary Search Tree");
        for (int size : sizes) {
            List<String> words = generateRandWords(size, "AL");
            BinarySearchTree tree = new BinarySearchTree(words);

            benchmarkResults(size, tree);
        }
    }

    private static void benchmarkResults(int size, AutoCompleter list) {
        double addTime = measureTime(() ->
                list.add(AutoCompleter.getString(LENGTH_OF_RAND_STRING)));
        double exactMatchTime = measureTime(() -> list.exactMatch("a"));
        double allMatchesTime = measureTime(() -> list.allMatches("b"));

        System.out.printf("Size: %d, add() time: %.2f microseconds, " +
                        "exactMatch() time: %.2f microseconds, allMatches() time: " +
                        "%.2f microseconds\n",
                size, addTime, exactMatchTime, allMatchesTime);
    }

    private static List<String> generateRandWords(int count, String dataStructure) {
        List<String> words;
        if (dataStructure.equals("AL")) {
            words = new ArrayList<>();
        } else {
            words = new LinkedList<>();
        }
        IntStream.range(0, count).forEach(i ->
                words.add(AutoCompleter.getString(LENGTH_OF_RAND_STRING)));
        return words;
    }

    private static double measureTime(Runnable task) {
        final int iterations = 100;
        final int nanoToMicroSecRatio = 1000;
        long totalTime = 0;
        for (int i = 0; i < iterations; i++) {
            System.gc();
            long start = System.nanoTime();
            task.run();
            totalTime += System.nanoTime() - start;
        }
        return (double) totalTime/ iterations / nanoToMicroSecRatio;
    }
}
