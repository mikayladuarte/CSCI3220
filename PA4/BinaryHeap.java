import java.nio.BufferUnderflowException;
import java.util.NoSuchElementException;

/**
 * Program Title: BinaryHeap
 * Author: Mikayla Duarte
 * Class: CSCI-3220 section 821 Fall 2020
 * Assignment 4
 * <p>
 * Objective of this assignment is to perform several basic binary heap operations, including buildHeap, insert,
 * deleteMin, remove, and changeValue
 * <p>
 * This class is the BinaryHeap object, which will perform the basic operations of a binary heap
 */
public class BinaryHeap {
    /** The heap array **/
    private int[] array;

    /** The number of elements in the heap **/
    private int currentSize;

    /**
     * Constructor which initializes an empty int array of size 20.
     */
    public BinaryHeap() {
        this.array = new int[20];
        this.currentSize = 0;
    }

    /**
     * Constructor which initializes a binary heap using the linear-time algorithm to insert items and percolate them
     * down as necessary.
     * @param items  values to insert into the heap.
     */
    public BinaryHeap(int[] items) {
        this.currentSize = items.length;
        this.array = new int[20];
        int i = 1;

        for (int item : items) {
            this.array[i++] = item;
        }

        buildHeap();
    }

    /**
     * Insert into the heap array, maintaining heap order and percolating the value up as necessary
     * @param value  the item to insert
     */
    public void insert(int value) {
        int hole = ++this.currentSize;
        percolateUp(hole, value);
    }

    /**
     * Remove the smallest item from the heap array.
     * @return the smallest item
     * @throws BufferUnderflowException for an empty heap array
     */
    public int deleteMin() {
        if (this.currentSize == 0) {
             throw new BufferUnderflowException();
        }

        // The smallest item in the array should be at the root
        int minItem = this.array[1];

        // The new root becomes the last item of the heap
        this.array[1] = array[this.currentSize--];
        this.array[this.currentSize + 1] = 0;

        // Percolate this item down
        percolateDown(1);

        return minItem;
    }

    /**
     * Removes a specified value from the heap array
     * @param value  the value to be deleted
     */
    public void remove(int value) {
        // Find the position of the value we want to remove
        int pos = findPos(value);
        if (pos == -1) {
            return;
        }

        // Decrease the key by the max integer value and percolate it up to the top
        decreaseKey(pos, Integer.MAX_VALUE);

        // DeleteMin to delete the value from the array
        deleteMin();
    }

    /**
     * Changes the value of an element in the heap to the specified new value
     * @param value     the current value
     * @param newValue  the value we want to change to
     */
    public void changeValue(int value, int newValue) {
        // Find the position of the value we're changing
        int pos = findPos(value);
        if (pos == -1) {
            return;
        }

        // If the new value is less than the current value, then we need to use decreaseKey to decrease it's value
        // and percolate up
        if (newValue < value) {
            int delta = value - newValue;
            decreaseKey(pos, delta);
        }
        // Otherwise we need to use increaseKey to increase it's value and percolate down
        else {
            int delta = newValue - value;
            increaseKey(pos, delta);
        }
    }

    /**
     * Getter method to get the heap array.
     * @return  the heap array
     */
    public int[] getArray() {
        return this.array;
    }

    /**
     * Find the position of a value in the heap array
     * @param value  value to be found
     * @return the position of the value
     */
    private int findPos(int value) {
        // iterate over the array using index i to find at which index the value is located
        for (int i = 0; i < this.currentSize; i++) {
            if (this.array[i] == value) {
                return i;
            }
        }

        // If we got here we didn't find the value so throw an exception
        System.out.println("Value was not found in the array: " + value);
        return -1;
    }

    /**
     * Internal method to establish heap order properly from an arbitrary arrangement of items using linear time.
     */
    private void buildHeap() {
        // We start at currentSize / 2 because that eliminates the entire last row of values, which we don't need to
        // percolate down because they're already on bottom
        for (int i = this.currentSize / 2; i > 0; i--) {
            percolateDown(i);
        }
    }

    /**
     * Internal method to decrease the value of an item in the heap array. a percolate up operation is then performed
     * to restore heap order.
     * @param pos    position of the item in the array
     * @param delta  the amount that we're changing the value by
     */
    private void decreaseKey(int pos, int delta) {
        this.array[pos] -= delta;
        percolateUp(pos, this.array[pos]);
    }

    /**
     * Internal method to increase the value of an item in the heap array. A percolate down operation is then performed
     * to restore heap order.
     * @param pos    position of the item in the array
     * @param delta  the amount that we're changing the value by
     */
    private void increaseKey(int pos, int delta) {
        this.array[pos] += delta;
        percolateDown(pos);
    }

    /**
     * Internal method to percolate down in the heap.
     * @param hole  the index at which the percolate begins
     */
    private void percolateDown(int hole) {
        // The child of the hole
        int child;
        // The value that we need to percolate down is where the hole is
        int tmp = this.array[hole];

        // While 2x the hole is less than the current size, the hole becomes the child
        for (; hole * 2 <= this.currentSize; hole = child) {
            // The child index becomes 2x the hole, which puts it at the left child of the hole
            child = hole * 2;

            // If the child is still within the contents of the array and the right child is smaller than the left
            // child, then we increment the child by one so that the hole will go to the right
            if (child != this.currentSize && this.array[child + 1] < this.array[child]) {
                child++;
            }

            // If the child is less than the value of the hole index, then that child moves up to where the hole was
            if (this.array[child] < tmp) {
                this.array[hole] = this.array[child];
            }
            // Otherwise we reached where the hole belongs should break out to assign the value
            else {
                break;
            }
        }

        // The value that we percolated down now gets assigned to the spot we landed in
        this.array[hole] = tmp;
    }

    /**
     * Internal method to percolate a value up through the heap array after it has been inserted at the end.
     * @param hole   the index of the position of the hole
     * @param value  the value to be inserted into the heap array
     */
    private void percolateUp(int hole, int value) {
        // We percolate the hole up through the array, and when we find where the value would belong into the array
        // we insert it into the hole
        for (array[0] = value; value < this.array[hole / 2]; hole /= 2) {
            // if the value is smaller than it's parent node, then the hole moves up and the parent moves down
            this.array[hole] = this.array[hole / 2];
        }

        // The value now gets assigned to it's position, reassign the 0 index element to 0
        this.array[hole] = value;
        this.array[0] = 0;
    }
}
