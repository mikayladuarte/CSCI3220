import java.util.Random;
import java.util.Scanner;

/**
 * Program Title: maxSumTest
 * Author: Mikayla Duarte
 * Class: CSCI-3220 section 821 Fall 2020
 * Assignment 1
 *
 * Objective of this assignment is to take the max sum algorithms, modify them to return the starting and ending
 *      indices on top of their sums, and then time the execution of each algorithm with varying numbers of
 *      input
 */
public class maxSumTest {
    /**
     * Quadratic maximum contiguous subsequence sum algorithm.
     *
     * @param a  an integer array to search to find the maximum subsequence sum numbers.
     * @return   the sum of the maximum subsequence, the starting index, and the ending index.
     */
    public static int[] maxSubSum2(int[] a) {
        int maxSum = 0;
        int maxStart = 0;
        int maxEnd = a.length - 1;

        for (int i = 0; i < a.length; i++) {
            int thisSum = 0;
            for (int j = i; j < a.length; j++) {
                thisSum += a[j];

                if (thisSum > maxSum) {
                    maxSum = thisSum;
                    maxStart = i;
                    maxEnd = j;
                }
            }
        }

        return new int[] {maxSum, maxStart, maxEnd};
    }

    /**
     * Driver for divide-and-conquer maximum contiguous subsequence sum algorithm.
     *
     * @param a   an integer array to search to find the maximum subsequence sum numbers.
     * @return    the sum of the maximum subsequence, the starting index, and the ending index.
     */
    public static int[] maxSubSum3(int[] a) {
        return maxSumRec(a, 0, a.length - 1);
    }

    /**
     * Linear-time maximum contiguous subsequence sum algorithm.
     *
     * @param a  an integer array to search to find the maximum subsequence sum numbers.
     * @return   the maximum sum, the starting index of the max subsequence, and the ending index.
     */
    public static int[] maxSubSum4(int[] a) {
        int maxSum = 0, thisSum = 0, maxStart = 0, maxEnd = 0;

        for (int j = 0; j < a.length; j++) {
            thisSum += a[j];

            if (thisSum > maxSum) {
                maxSum = thisSum;
                maxEnd = j;
            } else if (thisSum < 0){
                thisSum = 0;
                maxStart = j + 1;
            }
        }

        return new int[] {maxSum, maxStart, maxEnd};
    }

    /**
     * Recursive maximum contiguous subsequence sum algorithm.
     * Finds maximum sum in subarray spanning a[left..right].
     * Does not attempt to maintain actual best sequence.
     *
     * @param a      an integer array to search to find the maximum subsequence sum numbers.
     * @param left   the starting index to search in the array.
     * @param right  the ending index to search in the array
     * @return       the maximum sum, the starting index of the max subsequence, and the ending index.
     */
    private static int[] maxSumRec(int[] a, int left, int right) {
        if (left == right) {  // base case
            return new int[] {Math.max(a[left], 0), left, left};
        }

        int center = (left + right) / 2, maxStart = left, maxEnd = right;
        int[] maxLeftSum = maxSumRec(a, left, center);
        int[] maxRightSum = maxSumRec(a, center + 1, right);

        int maxLeftBorderSum = 0, leftBorderSum = 0;
        for (int i = center; i >= left; i--) {
            leftBorderSum += a[i];
            if (leftBorderSum > maxLeftBorderSum) {
                maxLeftBorderSum = leftBorderSum;
                maxStart = i;
            }
        }

        int maxRightBorderSum = 0, rightBorderSum = 0;
        for (int i = center + 1; i <= right; i++) {
            rightBorderSum += a[i];
            if (rightBorderSum > maxRightBorderSum) {
                maxRightBorderSum = rightBorderSum;
                maxEnd = i;
            }
        }

        int max = Math.max(Math.max(maxLeftSum[0], maxRightSum[0]), maxLeftBorderSum + maxRightBorderSum);

        if (max == maxLeftSum[0]){
            return maxLeftSum;
        } else if (max == maxRightSum[0]) {
            return maxRightSum;
        } else {
            return new int[] {maxLeftBorderSum + maxRightBorderSum, maxStart, maxEnd};
        }
    }

    /**
     * Generates an int array of length num of random input values for the subsequence algorithms.
     *
     * @param num  number of values we want generated in the array.
     * @return     int array of random values of length num.
     */
    private static int[] generateRandomInputs(int num) {
        return new Random().ints(num, -9999, 9999).toArray();
    }

    /**
     * Prints the results from running one of the algorithms to the command line.
     *
     * @param result  result array from running the algorithm.
     * @param start   starting time for execution of the algorithm.
     * @param end     ending time for execution of the algorithm.
     */
    private static void printResults(int[] result, long start, long end) {
        System.out.printf("MaxSum: %d, S_index: %d, E_index: %d\n", result[0], result[1], result[2]);
        System.out.printf("Execution Time: %d milliseconds\n", (end - start));
    }

    /**
     * Main execution of the program.
     * This will ask the user for the size of the input array, generate a random number input array of the defined
     *      size, and run this array against algorithms 2, 3, 4, all while timing the execution and printing the
     *      results to the command line.
     *
     * @param args  command line arguments passed into the program.
     */
    public static void main(String[] args) {
        // Ask the user for the size of the input array
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the size of the problem (N): ");
        int size = scanner.nextInt();

        // Generate the random number array
        int[] inputArray = generateRandomInputs(size);

        // Run algorithm 2 while timing the result, then print sum/starting index/ending index/execution time
        System.out.println("\nAlgorithm 2:");
        long start = System.currentTimeMillis();
        int[] result = maxSubSum2(inputArray);
        long end = System.currentTimeMillis();
        printResults(result, start, end);

        // Run algorithm 3 while timing the result, then print sum/starting index/ending index/execution time
        System.out.println("\nAlgorithm 3:");
        start = System.currentTimeMillis();
        result = maxSubSum3(inputArray);
        end = System.currentTimeMillis();
        printResults(result, start, end);

        // Run algorithm 4 while timing the result, then print sum/starting index/ending index/execution time
        System.out.println("\nAlgorithm 4:");
        start = System.currentTimeMillis();
        result = maxSubSum4(inputArray);
        end = System.currentTimeMillis();
        printResults(result, start, end);
    }
}