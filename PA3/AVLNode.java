/**
 * Program Title: AVLNode
 * Author: Mikayla Duarte
 * Class: CSCI-3220 section 821 Fall 2020
 * Assignment 3
 * <p>
 * Objective of this assignment is to construct an AVL tree based on user choices to insert/delete nodes, or
 * print different pieces of information about the AVL tree
 * <p>
 * This class is the AVL node object, which will hold relevant information pertaining to the individual nodes of
 * the AVL search tree
 */

public class AVLNode {
    /**
     * Value stored in the node
     **/
    private int value;

    /**
     * Left child
     **/
    private AVLNode left;

    /**
     * Right child
     **/
    private AVLNode right;

    /**
     * Height of the node
     */
    private int height;

    /**
     * Constructor which initializes a node with a given value and no children.
     *
     * @param value value of the node
     */
    public AVLNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.height = 0;
    }

    /**
     * Getter function to get the value of the AVL Node.
     *
     * @return value of AVL node
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Getter function to get the left child.
     *
     * @return left child node
     */
    public AVLNode getLeft() {
        return this.left;
    }

    /**
     * Getter function to get the right child.
     *
     * @return right child node
     */
    public AVLNode getRight() {
        return this.right;
    }

    /**
     * Getter function to get the height of the node.
     *
     * @return height of the node
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Getter function to get the number of children the node has.
     *
     * @return the number of children
     */
    public int getNumberOfChildren() {
        if (this.left != null && this.right != null) {
            return 2;
        } else if (this.left != null || this.right != null) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Setter function to set the value of the node.
     *
     * @param value the value of the node
     */
    public void setValue(AVLNode value) {
        this.value = value.getValue();
    }

    /**
     * Setter function to set the left child.
     *
     * @param left node to set as the left child
     */
    public void setLeft(AVLNode left) {
        this.left = left;
    }

    /**
     * Setter function to set the right child.
     *
     * @param right node to set as the right child
     */
    public void setRight(AVLNode right) {
        this.right = right;
    }


    /**
     * Setter function to set the height of the node.
     *
     * @param height the height of the node in the tree
     */
    public void setHeight(int height) {
        this.height = height;
    }
}
