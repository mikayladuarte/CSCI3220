import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Program Title: PA3AVL
 * Author: Mikayla Duarte
 * Class: CSCI-3220 section 821 Fall 2020
 * Assignment 3
 * <p>
 * Objective of this assignment is to construct an AVL tree based on user choices to insert/delete nodes, or
 * print different pieces of information about the AVL tree
 * <p>
 * This class is the driver for the program, which calls different methods based on the user choices
 */
public class PA3AVL {
    /**
     * Initialize AVL tree to use in the program
     **/
    private static final AVLTree AVL = new AVLTree();

    /**
     * Initialize scanner object
     **/
    private static final Scanner input = new Scanner(System.in);

    /**
     * Menu option 1: Insert node(s) into the AVL tree.
     */
    private static void insertNode() {
        // Get the nodes we would like to insert into the tree from the user
        System.out.print("Enter the nodes you'd like to insert, separated by commas: ");
        String answer = input.nextLine();

        // Create a stream of string values
        Stream<String> values = Stream.of(answer.split(","));

        // Stream those values into integers, instantiate new AVLNode objects with each integer value, and then
        // insert them into the AVL tree
        values.mapToInt(Integer::parseInt).mapToObj(AVLNode::new).forEach(AVL::insert);
    }

    /**
     * Menu option 2: Remove a user defined node.
     */
    private static void removeNode() {
        // Get the nodes we would like to remove from the tree from the user
        System.out.print("Enter the node you'd like to remove: ");
        AVLNode removeNode = new AVLNode(input.nextInt());
        input.nextLine();  // clear the scanner

        // Remove the node from the tree
        AVL.remove(removeNode);
    }

    /**
     * Menu option 3: Print the level order traversal line by line.
     *
     * @param node  node from which to start printing
     */
    private static void levelOrder(AVLNode node) {
        System.out.println("Level order traversal:");
        for (int i = 0; i <= AVL.height(node); i++) {
            printGivenLevel(node, i);
            System.out.println();
        }
    }

    /**
     * Print the nodes starting on the given level.
     *
     * @param root   root node from where to begin printing
     * @param level  level from which the node resides
     */
    private static void printGivenLevel(AVLNode root, int level) {
        if (root == null) {
            return;
        }

        if (level == 0) {
            System.out.print(root.getValue() + " ");
        } else {
            printGivenLevel(root.getLeft(), level - 1);
            printGivenLevel(root.getRight(), level - 1);
        }
    }

    /**
     * Main execution of the program.
     * This will ask the user to pick from a list of choices from a menu, including inserting/deleting nodes into a
     *      AVL tree, printing information about the tree, or exiting the program
     *
     * @param args  command line arguments passed into the program
     */
    public static void main(String[] args) {
        // Prompt the user to pick an item from the menu
        boolean quit = false;
        do {
            System.out.println("Enter choice [1-4] from menu below: ");
            System.out.println("1) Insert node(s)");
            System.out.println("2) Delete a node");
            System.out.println("3) Print the level order transversal of the tree");
            System.out.println("4) Exit program");

            // Record users choice
            System.out.print("Choice: ");
            int choice = input.nextInt();
            input.nextLine();  // clear the scanner

            // Perform action
            switch (choice) {
                case 1 :
                    insertNode();
                    break;
                case 2 :
                    removeNode();
                    break;
                case 3 :
                    levelOrder(AVL.getRoot());
                    break;
                case 4 :
                    quit = true;
                    break;
                default :
                    System.out.println("Not a valid option, please pick again.");
                    break;
            }
        } while (!quit);
    }
}
