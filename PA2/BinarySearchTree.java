/**
 * Program Title: BinarySearchTree
 * Author: Mikayla Duarte
 * Class: CSCI-3220 section 821 Fall 2020
 * Assignment 2A
 *
 * Objective of this assignment is to construct a binary search tree based on user choices to insert/delete nodes, or
 *      print different pieces of information about the binary search tree
 *
 * This class is the binary search tree object, which will hold relevant information pertaining to the BST as a whole
 */

public class BinarySearchTree {
    /** Root of the tree **/
    private BinaryNode root;

    /**
     * Constructor which initializes an empty binary search tree with a null root.
     */
    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * Getter function to get the root of the tree.
     *
     * @return  root of the tree
     */
    public BinaryNode getRoot() {
        return this.root;
    }

    /**
     * Checks to see if the tree is empty.
     *
     * @return true if the root is null else false
     */
    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * Insert a binary node into the binary search tree.
     *
     * @param node  Binary node to be inserted
     */
    public void insert(BinaryNode node) {
        this.root = insert(node, this.root);
    }

    /**
     * Remove a binary node from the binary search tree.
     *
     * @param node  Binary node to be removed
     */
    public void remove(BinaryNode node) {
        this.root = remove(node, this.root);
    }

    /**
     * Print the tree contents in-order.
     */
    public void printTree() {
        if (isEmpty()) {
            System.out.println("Empty tree");
        } else {
            printTree(this.root);
        }
    }

    /**
     * Find the height of a given subtree.
     *
     * @param node  root of the subtree
     * @return      height of the node (root will be tallest)
     */
    public int height(BinaryNode node) {
        if (node == null) {
            return -1;
        } else {
            return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
        }
    }

    /**
     * Internal function to insert into a subtree.
     *
     * @param insertNode  node to be inserted
     * @param subRoot     root of the subtree
     * @return            the new root of the subtree
     */
    private BinaryNode insert(BinaryNode insertNode, BinaryNode subRoot) {
        // if the subroot is null, then we insert the node at the root
        if (subRoot == null) {
            return insertNode;
        }

        // If it's less than the value of the subroot, then we need to traverse the left side of the subtree
        // If it's greater, then we need to traverse the right side of the subtree
        if (insertNode.getValue() < subRoot.getValue()) {
            subRoot.setLeft(insert(insertNode, subRoot.getLeft()));
        } else if (insertNode.getValue() > subRoot.getValue()) {
            subRoot.setRight(insert(insertNode, subRoot.getRight()));
        }

        return subRoot;
    }

    /**
     * Remove a binary node from the binary search tree and adjust the tree accordingly.
     *      This means if the node has 2 children, it picks the smallest from the right subtree.
     *      If it has one child, it moves that child up into the deleted space.
     *
     * @param removeNode  node to be removed
     * @param subRoot     root of the subtree
     * @return            the new root of the subtree
     */
    private BinaryNode remove(BinaryNode removeNode, BinaryNode subRoot) {
        // If removeNode is null, do nothing
        if (removeNode == null) {
            return null;
        }

        // If the remove node is less than the subRoot, we need to traverse the left side of the tree
        // If it's greater, we need to traverse the right side of the subtree
        // If it's equal, we've reached the node we need to remove
        // If it has 2 children, we need to find the smallest node in the right subtree to replace it with
        // If it has 1 child, we just replace with the left or right child depending on which one isn't null
        if (removeNode.getValue() < subRoot.getValue()) {
            subRoot.setLeft(remove(removeNode, subRoot.getLeft()));
        } else if (removeNode.getValue() > subRoot.getValue()) {
            subRoot.setRight(remove(removeNode, subRoot.getRight()));
        } else if (subRoot.getNumberOfChildren() == 2) {
            System.out.println(findMin(subRoot.getRight()).getValue());
            subRoot.setValue(findMin(subRoot.getRight()));
            subRoot.setRight(remove(subRoot, subRoot.getRight()));
        } else {
            subRoot = (subRoot.getLeft() != null) ? subRoot.getLeft() : subRoot.getRight();
        }

        return subRoot;
    }

    /**
     * Internal function to recursively print a tree in-order.
     *
     * @param node  node which to traverse in-order, going L-N-R
     */
    private void printTree(BinaryNode node) {
        if (node != null) {
            printTree(node.getLeft());
            System.out.println(node.getValue());
            printTree(node.getRight());
        }
    }

    /**
     * Internal function to find the smallest node in the right subtree, for removal purposes.
     *
     * @param node  the subtree root from which to start the search
     * @return      the smallest node
     */
    private BinaryNode findMin(BinaryNode node) {
        if (node == null) {
            return null;
        } else if (node.getLeft() == null) {
            return node;
        }

        return findMin(node.getLeft());
    }
}
