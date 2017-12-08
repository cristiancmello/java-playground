package com.template;

/**
 * Created by david on 10/04/17.
 * In Template pattern, an abstract class exposes defined way / template to execute its methods
 * <p>
 * Its subclasses can override the method implementation as per need but the invocation
 * is to be in the same way as defined by an abstract class
 */
public class App {

    public static void main(String[] args) {
        int[] numbers = {100, 5, 7, -6, 0};
        Algorithm bubblesort = new BubbleSort(numbers);
        bubblesort.sort();

        Algorithm insertionSort = new InsertionSort(numbers);
        insertionSort.sort();

    }
}
