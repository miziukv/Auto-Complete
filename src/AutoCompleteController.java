/*
 * Course: CSC1120
 * Summer 2024
 * Lab 9 - Auto Complete
 * Name: Vlad Miziuk
 * Created: 7/21/2024
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Controller class for handling AutoCompete  operations in a JavaFX application.
 * Provides functionalities to select text, enter letters/words into a search field
 * and see matching words. Provides data on time required to execute the operations
 * when using an Ordered List, an Unordered List, and a Binary Search Tree. Provides
 * option to choose between an ArrayList and a LinkedList implementation for both
 * the UnorderedList and the OrderedList.
 */
public class AutoCompleteController {

    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> resultList;

    @FXML
    private TextField unsortedExactQueryTime;

    @FXML
    private TextField unsortedAllMatchesTime;

    @FXML
    private TextField sortedExactQueryTime;

    @FXML
    private TextField sortedAllMatchesTime;

    @FXML
    private TextField bstExactQueryTime;

    @FXML
    private TextField bstAllMatchesTime;

    @FXML
    private TextField hashTableExactQueryTime;

    @FXML
    private TextField hashTableAllMatchesTime;

    @FXML
    private Button unsortedListType;

    @FXML
    private Button sortedListType;

    private List<String> allItems = new ArrayList<>();

    @FXML
    private void initialize() {
        setNotEditable();

        searchField.textProperty().addListener((observable, oldValue, newValue)
                -> search(newValue));
        
        unsortedListType.setOnAction(this::handleUnsortedListType);
        sortedListType.setOnAction(this::handleSortedListType);
    }

    private void setNotEditable() {
        unsortedExactQueryTime.setEditable(false);
        unsortedAllMatchesTime.setEditable(false);
        sortedExactQueryTime.setEditable(false);
        sortedAllMatchesTime.setEditable(false);
        bstExactQueryTime.setEditable(false);
        bstAllMatchesTime.setEditable(false);
        hashTableExactQueryTime.setEditable(false);
        hashTableAllMatchesTime.setEditable(false);
    }

    @FXML
    private void selectText(ActionEvent actionEvent) {
        try {
            MenuItem item = (MenuItem) actionEvent.getSource();
            switch (item.getText()) {
                case "companies.txt" -> setText("data/companies.txt");
                case "names2011.txt" -> setText("data/names2011.txt");
                case "smlwords.txt" -> setText("data/smlwords.txt");
                case "words.txt" -> setText("data/words.txt");
                case "words2.txt" -> setText("data/words2.txt");
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error selecting file.");
            alert.setHeaderText(null);
            alert.setContentText("The file can't be opened. Please check if it exists and if" +
                    "it is located in a \"data\" folder.");
            alert.showAndWait();
        }
    }

    private void setText(String path) throws IOException {
        List<String> items = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                items.add(line);
            }
        }
        allItems = items;
        resultList.getItems().setAll(items);
    }

    private void search(String prefix) {
        if (prefix.isEmpty()) {
            resultList.getItems().setAll(allItems);
            clearStats();
        } else {
            OrderedList list = new OrderedList(new ArrayList<>(allItems));
            resultList.getItems().setAll(list.allMatches(prefix));
            updateStats(prefix);
        }
    }

    private void clearStats() {
        unsortedExactQueryTime.setText(null);
        unsortedAllMatchesTime.setText(null);
        sortedExactQueryTime.setText(null);
        sortedAllMatchesTime.setText(null);
        bstExactQueryTime.setText(null);
        bstAllMatchesTime.setText(null);
        hashTableExactQueryTime.setText(null);
        hashTableAllMatchesTime.setText(null);
    }

    private void updateStats(String prefix) {
        updateListStats(prefix, unsortedListType, unsortedExactQueryTime,
                unsortedAllMatchesTime, true);
        updateListStats(prefix, sortedListType, sortedExactQueryTime,
                sortedAllMatchesTime, false);
        updateBSTStats(prefix);
        updateHashTableStats(prefix);
    }

    private void updateListStats(String prefix, Button listType, TextField exactQuery,
                                 TextField allMatches, boolean unsorted) {
        List<String> itemCopyAL = new ArrayList<>(allItems);
        List<String> itemCopyLL = new LinkedList<>(allItems);

        AutoCompleter listAL = unsorted ? new UnorderedList(itemCopyAL)
                : new OrderedList(itemCopyAL);
        AutoCompleter listLL = unsorted ? new UnorderedList(itemCopyLL)
                : new OrderedList(itemCopyLL);

        if (listType.getText().equals("Type: AL")) {
            measure(listAL::exactMatch, prefix, exactQuery);
            measure(listAL::allMatches, prefix, allMatches);
        } else {
            measure(listLL::exactMatch, prefix, exactQuery);
            measure(listLL::allMatches, prefix, allMatches);
        }
    }

    private void updateBSTStats(String prefix) {
        BinarySearchTree tree = new BinarySearchTree(new ArrayList<>(allItems));

        measure(tree::exactMatch, prefix, bstExactQueryTime);
        measure(tree::allMatches, prefix, bstAllMatchesTime);
    }

    private void updateHashTableStats(String prefix) {
        HashTable hashTable = new HashTable(new ArrayList<>(allItems));

        measure(hashTable::exactMatch, prefix, hashTableExactQueryTime);
        measure(hashTable::allMatches, prefix, hashTableAllMatchesTime);
    }

    private void measure(Consumer<String> method, String prefix, TextField textField) {
        long startTime = System.nanoTime();
        method.accept(prefix);
        long endTime = System.nanoTime() - startTime;
        textField.setText(AutoCompleter.format(endTime));
    }

    @FXML
    private void handleUnsortedListType(ActionEvent actionEvent) {
        handleListType(actionEvent);
        updateListStats(searchField.getText(), unsortedListType,
                unsortedExactQueryTime, unsortedAllMatchesTime, true);
    }

    @FXML
    private void handleSortedListType(ActionEvent actionEvent) {
        handleListType(actionEvent);
        updateListStats(searchField.getText(), sortedListType,
                sortedExactQueryTime, sortedAllMatchesTime, false);
    }

    private static void handleListType(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        if (button.getText().equals("Type: AL")) {
            button.setText("Type: LL");
        } else {
            button.setText("Type: AL");
        }
    }
}
