import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Program Title: PA2BST
 * Author: Mikayla Duarte
 * Class: CSCI-3220 section 821 Fall 2020
 * Assignment 2A
 *
 * Objective of this assignment is to construct a binary search tree based on user choices to insert/delete nodes, or
 *      print different pieces of information about the binary search tree
 *
 * This class is the driver for the program, which calls different methods based on the user choices
 */
public class PA2BST {
    /** Initialize binary search tree to use in the program **/
    private static final BinarySearchTree BST = new BinarySearchTree();

    /** Initialize scanner object **/
    private static final Scanner input = new Scanner(System.in);

    /**
     * Menu option 1: Insert node(s) into the binary search tree.
     */
    private static void insertNode() {
        // Get the nodes we would like to insert into the tree from the user
        System.out.print("Enter the nodes you'd like to insert, separated by commas: ");
        String answer = input.nextLine();

        // Create a stream of string values
        Stream<String> values = Stream.of(answer.split(","));

        // Stream those values into integers, instantiate new BinaryNode objects with each integer value, and then
        // insert them into the binary search tree
        values.mapToInt(Integer::parseInt).mapToObj(BinaryNode::new).forEach(BST::insert);
    }

    /**
     * Menu option 3: Get the number of leaves in a tree.
     *
     * @param node  the root to start searching for leaves on the tree
     * @return      number of leaves in the tree
     */
    private static int numLeaves(BinaryNode node) {
        if (node == null) {
            return 0;
        }

        if (node.getNumberOfChildren() == 0) {
            return 1;
        } else {
            return numLeaves(node.getLeft()) + numLeaves(node.getRight());
        }
    }

    /**
     * Menu option 4: Get the number of nodes in a tree that only contain 1 child.
     *
     * @param node  the root of the tree to search for one child nodes
     * @return      number of nodes that only contain one child
     */
    private static int numOneChildNodes(BinaryNode node) {
        if (node == null || node.getNumberOfChildren() == 0) {
            return 0;
        }

        if (node.getNumberOfChildren() == 1) {
            return 1 + numOneChildNodes(node.getLeft()) + numOneChildNodes(node.getRight());
        } else {
            return numOneChildNodes(node.getLeft()) + numOneChildNodes(node.getRight());
        }
    }

    /**
     * Menu option 5: Get the number of nodes in a tree that contain 2 children.
     *
     * @param node  the root of the tree to search for two child nodes
     * @return      number of nodes that contain two children
     */
    private static int numTwoChildNodes(BinaryNode node) {
        if (node == null || node.getNumberOfChildren() == 0) {
            return 0;
        }

        if (node.getNumberOfChildren() == 1) {
            return numTwoChildNodes(node.getLeft()) + numTwoChildNodes(node.getRight());
        } else {
            return 1 + numTwoChildNodes(node.getLeft()) + numTwoChildNodes(node.getRight());
        }
    }

    /**
     * Menu option 6: Print the level order traversal line by line.
     *
     * @param node  node from which to start printing
     */
    private static void levelOrder(BinaryNode node) {
        for (int i = 0; i <= BST.height(node); i++) {
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
    private static void printGivenLevel(BinaryNode root, int level) {
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
     * Menu option 7: Remove a user defined node.
     */
    private static void removeNode() {
        // Get the nodes we would like to remove from the tree from the user
        System.out.print("Enter the node you'd like to remove: ");
        BinaryNode removeNode = new BinaryNode(input.nextInt());
        input.nextLine();  // clear the scanner

        // Remove the node from the tree
        BST.remove(removeNode);
    }

    /**
     * Main execution of the program.
     * This will ask the user to pick from a list of choices from a menu, including inserting/deleting nodes into a
     *      binary search tree, printing information about the tree, or exiting the program
     *
     * @param args  command line arguments passed into the program
     */
    public static void main(String[] args) {
        // Prompt the user to pick an item from the menu
        boolean quit = false;
        do {
            System.out.println("Enter choice [1-8] from menu below: ");
            System.out.println("1) Insert node(s)");
            System.out.println("2) Print tree (in-order)");
            System.out.println("3) Print number of leaves in tree (subpart a)");
            System.out.println("4) Print number of nodes in tree that contain only one child (subpart b)");
            System.out.println("5) Print the number of nodes in tree that contain two children (subpart c)");
            System.out.println("6) Print the level order transversal of the tree (subpart d)");
            System.out.println("7) Delete a node");
            System.out.println("8) Exit program");

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
                    System.out.println("The tree printed in-order: ");
                    BST.printTree();
                    break;
                case 3 :
                    System.out.println("The number of leaves in this tree: " + numLeaves(BST.getRoot()));
                    break;
                case 4 :
                    System.out.println("The number of nodes with only one child: " + numOneChildNodes(BST.getRoot()));
                    break;
                case 5 :
                    System.out.println("The number of nodes with two children: " + numTwoChildNodes(BST.getRoot()));
                    break;
                case 6 :
                    System.out.println("Level order traversal:");
                    levelOrder(BST.getRoot());
                    break;
                case 7 :
                    removeNode();
                    break;
                case 8 :
                    quit = true;
                    break;
                default :
                    System.out.println("Not a valid option, please pick again.");
                    break;
            }
        } while (!quit);
    }
}
