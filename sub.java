package helloWorld;

import java.util.Arrays;

public class sub {
    
    /**
     * This method performs subtraction of two arrays representing numbers.
     * @param array1 - The first number as an array.
     * @param array2 - The second number as an array.
     * @return - The result of the subtraction as an array.
     */
    public static int[] sub(int[] array1, int[] array2) {
        // Reverse the arrays
        int[] num1 = Arrays.copyOf(array1, array1.length);
        int[] num2 = Arrays.copyOf(array2, array2.length);
        reverseArray(num1);
        reverseArray(num2);

        // Check if both arrays are of the same length
        int maxLength = Math.max(num1.length, num2.length);
        if (num1.length < maxLength) {
            num1 = Arrays.copyOf(num1, maxLength);
        }
        if (num2.length < maxLength) {
            num2 = Arrays.copyOf(num2, maxLength);
        }

        int borrow = 0;
        int[] result = new int[maxLength];

        // Perform subtraction digit by digit
        for (int i = 0; i < maxLength; i++) {
            int difference = num1[i] - borrow - num2[i];
            if (difference < 0) {
                result[i] = difference + 10;
                borrow = 1;
            } else {
                result[i] = difference;
                borrow = 0;
            }
        }

        // Remove leading zeros from the result
        int length = result.length;
        while (length > 1 && result[length - 1] == 0) {
            length--;
        }
        return Arrays.copyOf(result, length);
    }

    /**
     * This method reverses an integer array.
     * @param array - The array to be reversed.
     */
    public static void reverseArray(int[] array) {
        int start = 0;
        int end = array.length - 1;
        while (start < end) {
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * This method converts arrays to numbers, performs subtraction, and handles the edge case of negative results.
     * @param array1 - The first number as an array.
     * @param array2 - The second number as an array.
     */
    public static void arraysToNumbers(int[] array1, int[] array2) {
        // Convert arrays to numbers
        int number1 = Integer.parseInt(Arrays.toString(array1).replaceAll("[\\[\\]\\s,]", ""));
        int number2 = Integer.parseInt(Arrays.toString(array2).replaceAll("[\\[\\]\\s,]", ""));

        // Perform subtraction
        if (number1 < number2) {
            int[] temp = array1;
            array1 = array2;
            array2 = temp;
            int[] result = sub(array1, array2);
            System.out.print("-");
            
            printArray(result); // Add '-' symbol to indicate negative result
        } else {
            printArray(sub(array1, array2));
        }
    }

    /**
     * This method prints an integer array.
     * @param array - The array to be printed.
     */
    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }
        System.out.println();
    }

    // Test cases
    public static void main(String[] args) {
        int[] array1 = {0, 9, 1};
        int[] array2 = {0, 9, 1};
        arraysToNumbers(array1, array2);
    }
}
