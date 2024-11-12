package org.example;

import java.util.Arrays;
import java.util.Random;

/**
 * Compare the real time speed between sorting algorithms on large sets of data.
 */
public class Main {
    public static void main(String[] args) {
        int size = 1000;
        int randomMaxValue = 1000000;
        int[] ar1;
        ar1 = createArrayWithRandomData(size,randomMaxValue);
        int[] ar2 = Arrays.copyOf(ar1, ar1.length);
        int[] ar3 = Arrays.copyOf(ar1, ar1.length);



        System.out.println("Array 1: ");
        show(ar1);
        System.out.println("Array 2: ");
        show(ar2);
        System.out.println("Array 3: ");
        show(ar3);

        long time1 = runJavaSort(ar1);
        long time2 = bubbleSort(ar2);
        long time3 = countingSort(ar3,randomMaxValue);
        System.out.println("runJavaSort:\t" + time1);
        System.out.println("bubbleSort:\t\t" + time2);
        System.out.println("countingSort:\t" + time3);
    }

    /**
     * This method allocates a new array of int (number of elements is size).
     *
     * @param size  number of elements is size
     * @param randomMaxValue The random numbers should be between 0 and randomMaxValue
     * @return a new array of int
     */
    public static int[] createArrayWithRandomData(int size, int randomMaxValue){
        Random rand = new Random(); //instance of Random class
        int randNum;

        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            // Generates a random number in the range 0-randomMaxValue
            randNum= rand.nextInt(randomMaxValue+1);
            array[i] = randNum;
        }

        return array;
    }



    /**
     * display all data in the array on screen
     * @param ar array need to print
     */
    public static void show(int[] ar){
        for(int n: ar){
            System.out.print(n+" ");
        }
        System.out.println();
    }

    /**
     * This method calls the Arrays.sort to sort ar
     * @param ar array need to be sort
     * @return time of speed
     */
    public static long runJavaSort(int[] ar){
        long startTime = System.nanoTime();
        Arrays.sort(ar);
        long endTime = System.nanoTime();
        return  endTime - startTime;
    }

    /**
     * Implement the bubble sort algorithm
     * @param ar array need to be sort by bubble sort
     * @return time of speed
     */
    public static long bubbleSort(int[] ar){
        long startTime = System.nanoTime();
        int temp;
        for (int i = 1; i < ar.length; i++) {
            for (int j = 0; j < ar.length-i; j++) {
                if(ar[j] > ar[j+1]){
                    temp = ar[j];
                    ar[j] = ar[j + 1];
                    ar[j + 1] = temp;
                }
            }
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    /**
     * Implement the counting sort algorithm
     * @param in array need to be sort by counting sort
     * @param k  max value
     * @return time of speed
     */
    public static long countingSort(int[] in, int k){
        long startTime = System.nanoTime();
        int[] count = new int[k+1];
        int[] out = new int[in.length];
        for (int i : in) {
            count[i]++;
        }

        // ignore first item
        for (int i = 1; i <= k; i++) {
            count[i] = count[i]+count[i-1];
        }

        // counting sort
        for (int i = in.length-1; i >= 0; i--) {
            int item = in[i];
            out[--count[item]] = item;
        }
        System.arraycopy(out, 0, in, 0, in.length);
        long endTime = System.nanoTime();
        return endTime - startTime;

    }
}