# Auto-Complete

## Introduction
The **AutoComplete JavaFX Application** is a desktop application that provides word suggestions based on user input, simulating the autocomplete feature commonly found in search engines and text editors. This project demonstrates the implementation and performance comparison of different data structures used for storing and retrieving words efficiently. The data structures implemented include:

- Unordered List (with ArrayList and LinkedList)
- Ordered List (with ArrayList and LinkedList)
- Binary Search Tree
- Hash Table

The application measures and displays the performance (execution time) of exact matches and prefix-based searches using these data structures. It features a user-friendly interface developed with JavaFX FXML and SceneBuilder, adhering to the Model-View-Controller (MVC) architectural pattern for organized code structure.

## Features

- **Multiple Data Structures:** Implemented various data structures to store and retrieve words, allowing performance comparison.
- **Performance Metrics:** Measures and displays execution times for exact word matches and prefix-based searches.
- **Dynamic Data Structure Switching:** Users can switch between ArrayList and LinkedList implementations for Unordered and Ordered Lists.
- **User-Friendly Interface:** Intuitive GUI built with JavaFX FXML and SceneBuilder.
- **Real-Time Search:** Updates search results and performance metrics as the user types.
- **File Selection:** Allows users to select different text files to populate the word list.
- **Error Handling:** Displays alerts for invalid inputs or file access issues.

## Architecture and Design

### Model-View-Controller (MVC) Pattern

The application follows the MVC architectural pattern:

- **Model:** The data structures (UnorderedList, OrderedList, BinarySearchTree, HashTable) that store and manage the words.
- **View:** The FXML layout files (sceneBuilderAutoComplete.fxml) that define the user interface.
- **Controller:** The AutoCompleteController class that handles user interactions, updates the view, and communicates with the model.

This separation of concerns enhances code modularity, maintainability, and scalability.

## Data Structures Implemented

### 1. Unordered List:

- Implemented using both ArrayList and LinkedList.
- Stores words without any specific order.
- Performs linear search for both exact match and prefix search.

### 2. Ordered List:

- Implemented using both ArrayList and LinkedList.
- Stores words in sorted order.
- Uses binary search for exact match.
- Iterates over a subset for prefix search.

### 3. Binary Search Tree:

- Uses Java's TreeSet to store words.
- Provides logarithmic time complexity for search operations.
- Leverages the natural ordering of words.

### 4. Hash Table:

- Uses Java's HashSet to store words.
- Offers constant time complexity for exact match searches.
- Performs linear search for prefix matches.

## Performance Analysis

The application measures the execution time of exact match and all matches (prefix search) operations for each data structure. The performance metrics help users understand the efficiency of each data structure in different scenarios.

- **Exact Match:** Time taken to check if a specific word exists in the data structure.
- **All Matches:** Time taken to retrieve all words that start with a given prefix.

The execution times are displayed in user-friendly formats (e.g., milliseconds, microseconds) for better comprehension.

## Installation and Setup

### Prerequisites

- Java Development Kit (JDK): Version 8 or higher.
- JavaFX SDK: Ensure JavaFX is included in your Java installation or available in your classpath.
- Integrated Development Environment (IDE): Such as IntelliJ IDEA, Eclipse, or NetBeans.

### Running the Application

1. Clone or Download the Repository
   - git clone https://github.com/miziukv/AutoComplete.git
2. Open the Project in Your IDE:
   - Import the project as a Java project.
   - Ensure that the JavaFX library is properly configured.

3. Build the Project:
   - Compile the source code.
   - Resolve any dependencies or library references.
4. Run the Application:
   - Run the AutoComplete class, which contains the main method.

## Usage

### Selecting a Text File

1. Launch the Application.
2. Click on the "Select text" MenuButton.
3. Choose a Text File:
   - companies.txt
   - names2011.txt
   - smlwords.txt
   - words.txt
   - words2.txt
*Note:* Ensure that these files are located in a data folder relative to the application's root directory.

### Performing Searches

1. Enter a Search Term:
   - Type into the Search text field.
   - The ListView below displays matching words in real-time.

2. View Performance Metrics:
   - Execution times for exact match and all matches are displayed for each data structure.

### Switching Data Structures

- **Unordered List Type**:
   - Click the Type: AL or Type: LL button to switch between ArrayList and LinkedList implementations.

- **Sorted List Type**:
   - Similarly, switch between ArrayList and LinkedList implementations for the ordered list.

*Note:* Changing the list type updates the performance metrics accordingly.

## Classes and Interfaces

### AutoCompleter Interface

Defines the methods that all autocomplete data structures must implement:

- add(String word): Adds a word to the data structure.
- exactMatch(String target): Checks if a word exists.
- allMatches(String prefix): Retrieves all words starting with a prefix.
- size(): Returns the number of words.
- getBackingClass(): Returns the name of the underlying data structure.

Includes static utility methods:

- format(long nanoseconds): Formats execution time.
- getString(int length): Generates a random string of a given length.

### UnorderedList Class

Implements AutoCompleter using an unordered list:

- Internal Storage: List<String> (can be ArrayList or LinkedList).
- Exact Match: Linear search (O(n)).
- All Matches: Stream API with filter (O(n)).

### OrderedList Class

Implements AutoCompleter using an ordered (sorted) list:

- Internal Storage: List<String> (can be ArrayList or LinkedList), kept sorted.
- Exact Match: Binary search (O(log n)).
- All Matches: Iterates over relevant subset (O(k), where k is the number of matches).

### BinarySearchTree Class

Implements AutoCompleter using a binary search tree:

- Internal Storage: TreeSet<String>.
- Exact Match: Tree search (O(log n)).
- All Matches: Uses subSet method for efficient retrieval.

### HashTable Class

Implements AutoCompleter using a hash table:

- Internal Storage: HashSet<String>.
- Exact Match: Constant time search (O(1)).
- All Matches: Linear search over the set (O(n)).

### AutoCompleteController Class

Handles user interactions and updates the UI:

- Event Handling: Listens to text input changes and button clicks.
- Data Management: Loads selected text files and initializes data structures.
- Performance Measurement: Times the execution of searches and updates the metrics displayed.

### AutoComplete Class

The main entry point of the application:

- start(Stage stage): Sets up the primary stage with the user interface defined in sceneBuilderAutoComplete.fxml.
- Exception Handling: Displays an alert if the FXML file fails to load.

## FXML Layout

The sceneBuilderAutoComplete.fxml file defines the GUI layout:
- MenuButton: Allows users to select a text file.
- TextField: For entering search terms.
- ListView: Displays the list of matching words.
- Buttons: Switch between ArrayList and LinkedList implementations.
- Labels and TextFields: Display performance metrics for each data structure.

## Limitations and Future Work

- Large Data Sets: Performance may degrade with very large word lists due to increased memory usage and longer search times.
- Case Sensitivity: The current implementation is case-sensitive. Modifying it to be case-insensitive could improve usability.
- Multithreading: Implementing searches in separate threads could improve responsiveness for large data sets.
- Additional Data Structures: Exploring other data structures like Tries or Ternary Search Trees for autocomplete functionality.
- GUI Enhancements: Improving the user interface aesthetics and adding features like highlighting matched prefixes.

## Acknowledgments
- Dr. Taylor: For support and feedback throughout the development process.

Thank you for your interest in the AutoComplete JavaFX Application!


