/**
 * Program Title: AVLTree
 * Author: Mikayla Duarte
 * Class: CSCI-3220 section 821 Fall 2020
 * Assignment 3
 * <p>
 * Objective of this assignment is to construct a AVL tree based on user choices to insert/delete nodes, or
 * print different pieces of information about the AVL tree
 * <p>
 * This class is the AVL tree object, which will hold relevant information pertaining to the AVL tree as a whole
 */

public class AVLTree {
    /**
     * Root of the tree
     **/
    private AVLNode root;

    /**
     * The allowed height differences of subtrees before they become unbalanced
     */
    private static final int ALLOWED_IMBALANCE = 1;

    /**
     * Constructor which initializes an empty AVL tree with a null root.
     */
    public AVLTree() {
        this.root = null;
    }

    /**
     * Getter function to get the root of the tree.
     *
     * @return root of the tree
     */
    public AVLNode getRoot() {
        return this.root;
    }

    /**
     * Insert a AVL node into the AVL tree.
     *
     * @param node AVL node to be inserted
     */
    public void insert(AVLNode node) {
        this.root = insert(node, this.root);
    }

    /**
     * Remove a AVL node from the AVL tree.
     *
     * @param node AVL node to be removed
     */
    public void remove(AVLNode node) {
        this.root = remove(node, this.root);
    }

    /**
     * Find the height of a given subtree.
     *
     * @param node root of the subtree
     * @return height of the node (root will be tallest)
     */
    public int height(AVLNode node) {
        if (node == null) {
            return -1;
        } else {
            return node.getHeight();
        }
    }

    /**
     * Internal function to insert into a subtree.
     *
     * @param insertNode node to be inserted
     * @param subRoot    root of the subtree
     * @return the new root of the subtree
     */
    private AVLNode insert(AVLNode insertNode, AVLNode subRoot) {
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

        // Balance when we insert the node
        return balance(subRoot);
    }

    /**
     * Remove a AVL node from the AVL tree and adjust the tree accordingly.
     * This means if the node has 2 children, it picks the smallest from the right subtree.
     * If it has one child, it moves that child up into the deleted space.
     * Balances and then returns the new root of the subtree
     *
     * @param removeNode node to be removed
     * @param subRoot    root of the subtree
     * @return the new root of the subtree
     */
    private AVLNode remove(AVLNode removeNode, AVLNode subRoot) {
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
            subRoot.setValue(findMin(subRoot.getRight()));
            subRoot.setRight(remove(subRoot, subRoot.getRight()));
        } else {
            subRoot = (subRoot.getLeft() != null) ? subRoot.getLeft() : subRoot.getRight();
        }

        return balance(subRoot);
    }

    /**
     * Internal function to find the smallest node in the subtree, for removal purposes.
     *
     * @param node the subtree root from which to start the search
     * @return the smallest node
     */
    private AVLNode findMin(AVLNode node) {
        if (node == null) {
            return null;
        } else if (node.getLeft() == null) {
            return node;
        }

        return findMin(node.getLeft());
    }

    /**
     * Internal function to balance a subtree.
     *
     * @param node the node for which we are checking to see if there is an imbalance
     * @return the new root of the subtree
     */
    private AVLNode balance(AVLNode node) {
        if (node == null) {
            return null;
        }

        // Determine if we need to perform a rotation in the left subtree
        if (height(node.getLeft()) - height(node.getRight()) > ALLOWED_IMBALANCE) {
            // If the left child node has a greater height than the right child node then LL single rotation
            // If the right child node has the greater height, then LR double rotation
            if (height(node.getLeft().getLeft()) >= height(node.getLeft().getRight())) {
                node = rotateWithLeftChild(node);
            } else {
                node = doubleWithLeftChild(node);
            }
        }

        // Determine if we need to perform a rotation in the right subtree
        else if (height(node.getRight()) - height(node.getLeft()) > ALLOWED_IMBALANCE) {
            // If the right child has a greater height than the left child, then RR single rotation
            // If the left child node has the greater height, the RL double rotation
            if (height(node.getRight().getRight()) >= height(node.getRight().getLeft())) {
                node = rotateWithRightChild(node);
            } else {
                node = doubleWithRightChild(node);
            }
        }

        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
        return node;
    }

    /**
     * Rotate binary tree with the left child.
     * For AVL trees, this is a single rotation.
     * Update heights, then return the new root of the subtree.
     *
     * @param k2 the node on which to perform the single LL rotation
     * @return the new root of the subtree (k1)
     */
    private AVLNode rotateWithLeftChild(AVLNode k2) {
        AVLNode k1 = k2.getLeft();
        k2.setLeft(k1.getRight());
        k1.setRight(k2);
        k2.setHeight(Math.max(height(k2.getLeft()), height(k2.getRight())) + 1);
        k1.setHeight(Math.max(height(k1.getLeft()), k2.getHeight()) + 1);

        return k1;
    }

    /**
     * Rotate binary tree with the right child.
     * For AVL trees, this is a single rotation
     * Update heights, then return the new root of the subtree.
     *
     * @param k1 the node on which to perfor the single RR rotation
     * @return the new root of the subtree (k2)
     */
    private AVLNode rotateWithRightChild(AVLNode k1) {
        AVLNode k2 = k1.getRight();
        k1.setRight(k2.getLeft());
        k2.setLeft(k1);
        k1.setHeight(Math.max(height(k1.getLeft()), height(k1.getRight())) + 1);
        k2.setHeight(Math.max(height(k2.getLeft()), k1.getHeight()) + 1);

        return k2;
    }

    /**
     * Double rotate binary tree node: first left child with its right child, then node k3 with
     * the new left child
     *
     * Updates height, then returns the new root of the subtree
     *
     * @param k3 the node on which to perform the double LR rotation
     * @return the new root of the subtree
     */
    private AVLNode doubleWithLeftChild(AVLNode k3) {
        k3.setLeft(rotateWithRightChild(k3.getLeft()));
        return rotateWithLeftChild(k3);
    }

    /**
     * Double rotate binary tree node: first right child with its left child, then node k3 with
     * the new right child
     *
     * Updates height, then returns the new root of the subtree
     *
     * @param k1 the node on which to perform the double RL rotation
     * @return the new root of the subtree
     */
    private AVLNode doubleWithRightChild(AVLNode k1) {
        k1.setRight(rotateWithLeftChild(k1.getRight()));
        return rotateWithRightChild(k1);
    }
}

