import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Program Title: PA4BinaryHeap
 * Author: Mikayla Duarte
 * Class: CSCI-3220 section 821 Fall 2020
 * Assignment 4
 * <p>
 * Objective of this assignment is to perform several basic binary heap operations, including buildHeap, insert,
 * deleteMin, remove, and changeValue
 * <p>
 * This class is the driver for the program, which calls different methods based on the user choices
 */
public class PA4BinaryHeap {
    /**
     * Initialize the binary heap object used in the driver.
     */
    private static BinaryHeap binaryHeap = new BinaryHeap();

    /**
     * Initialize the scanner object for the menu.
     */
    private static final Scanner input = new Scanner(System.in);

    /**
     * Menu option 1: Build the heap with a list of int values passed in as an int array.
     */
    private static void buildHeap() {
        // Prompt user for values
        System.out.print("Enter the values you'd like to insert into the heap, separated by commas (up to 20): ");
        String answer = input.nextLine();

        // Create a stream of string values
        Stream<String> values = Stream.of(answer.split(","));

        // Stream values to an int array and build a binary heap with it
        int[] items = values.mapToInt(Integer::parseInt).toArray();

        if (items.length <= 20) {
            binaryHeap = new BinaryHeap(items);
        } else {
            System.out.println("You can only insert up to 20 items into the heap");
        }

        System.out.println();
    }

    /**
     * Menu option 2: Insert a value into the heap.
     */
    private static void insert() {
        // Prompt user for value to insert
        System.out.print("Enter a value to insert into the heap: ");

        // Read value from input and insert it into the heap
        int value = input.nextInt();

        System.out.println("Inserting into the heap: " + value);
        binaryHeap.insert(value);

        // Clear the scanner
        input.nextLine();

        System.out.println();
    }

    /**
     * Menu option 3: Delete the smallest item from the heap
     */
    private static void deleteMin() {
        // delete the item
        int deleted = binaryHeap.deleteMin();

        // Print the item that we've deleted
        if (deleted != -1) {
            System.out.println("Item deleted from the heap: " + deleted);
        }

        System.out.println();
    }

    /**
     * Menu option 4: Remove a specified value from the heap array
     */
    private static void remove() {
        // Prompt user for value to be deleted
        System.out.print("Enter a value to be removed from the heap: ");

        // Read value from input and remove it from the heap
        int value = input.nextInt();

        System.out.println("Removing from the heap: " + value);
        binaryHeap.remove(value);

        // Clear the scanner
        input.nextLine();

        System.out.println();
    }

    private static void changeValue() {
        // Prompt user for value to be changed
        System.out.print("Enter a value to be changed in the heap: ");

        // Read this value and clear the scanner
        int value = input.nextInt();
        input.nextLine();

        // Prompt the user for the new value
        System.out.print("Enter the new value: ");

        // Read this value and clear the scanner
        int newValue = input.nextInt();
        input.nextLine();

        // Change the value in the heap and restore heap order
        System.out.printf("Changing this value in the heap: %d to %d\n", value, newValue);
        binaryHeap.changeValue(value, newValue);

        System.out.println();
    }

    /**
     * Menu option 6: Print the values in the heap.
     */
    private static void printHeap() {
        System.out.println("The heap has the following elements:");
        for (int item : binaryHeap.getArray()) {
            if (item != 0) {
                System.out.print(item + " ");
            }
        }
        System.out.println();
        System.out.println();
    }

    /**
     * Main execution of the program.
     * This will ask the user to pick from a list of choices from a menu, including building a heap with a list of
     * ints, inserting an int into the binary heap, deleting the smallest value, removing a key, or changing the value
     * of a key, and printing the result.
     *
     * @param args  command line arguments passed into the program
     */
    public static void main(String[] args) {
        // Prompt the user to pick an item from the list
        boolean quit = false;
        do {
            System.out.println("Enter choice [1-6] from menu below:");
            System.out.println("1) Build Heap");
            System.out.println("2) Insert value into heap");
            System.out.println("3) Delete minimum value from heap");
            System.out.println("4) Remove value from heap");
            System.out.println("5) Change the value of a value in the heap");
            System.out.println("6) Print the contents of the heap");
            System.out.println("7) Exit");

            // Record users choice
            System.out.print("Choice: ");
            int choice = input.nextInt();
            input.nextLine();  // clear the scanner
            System.out.println();

            // Perform the action
            switch(choice) {
                case 1 :
                    buildHeap();
                    printHeap();
                    break;
                case 2 :
                    insert();
                    printHeap();
                    break;
                case 3 :
                    deleteMin();
                    printHeap();
                    break;
                case 4 :
                    remove();
                    printHeap();
                    break;
                case 5 :
                    changeValue();
                    printHeap();
                    break;
                case 6 :
                    printHeap();
                    break;
                case 7 :
                    quit = true;
                    break;
                default :
                    System.out.println("Not a valid option, please pick again.");
                    break;
            }

        } while (!quit);

    }

}
