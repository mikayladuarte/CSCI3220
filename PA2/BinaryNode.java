/**
 * Program Title: BinaryNode
 * Author: Mikayla Duarte
 * Class: CSCI-3220 section 821 Fall 2020
 * Assignment 2A
 *
 * Objective of this assignment is to construct a binary search tree based on user choices to insert/delete nodes, or
 *      print different pieces of information about the binary search tree
 *
 * This class is the binary node object, which will hold relevant information pertaining to the individual nodes of
 *      the binary search tree
 */

public class BinaryNode {
    /** Value stored in the node **/
    private int value;

    /** Left child **/
    private BinaryNode left;

    /** Right child **/
    private BinaryNode right;

    /**
     * Constructor which initializes a node with a given value and no children.
     *
     * @param value  value of the node
     */
    public BinaryNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    /**
     * Getter function to get the value of the Binary Node.
     *
     * @return value of binary node
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Getter function to get the left child.
     *
     * @return left child node
     */
    public BinaryNode getLeft() {
        return this.left;
    }

    /**
     * Getter function to get the right child.
     *
     * @return right child node
     */
    public BinaryNode getRight() {
        return this.right;
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

    public void setValue(BinaryNode value) {
        this.value = value.getValue();
    }

    /**
     * Setter function to set the left child.
     *
     * @param left  node to set as the left child
     */
    public void setLeft(BinaryNode left) {
        this.left = left;
    }

    /**
     * Setter function to set the right child.
     *
     * @param right  node to set as the right child
     */
    public void setRight(BinaryNode right) {
        this.right = right;
    }
}
