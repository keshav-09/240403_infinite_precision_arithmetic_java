package helloWorld;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Example usage:
        int[] num1 = {3, 6};
        int[] num2 = {4, 5, 0};

        try {
            int[] ans = Addition(num1, num2);
            System.out.println("Addition of the two given array: " + Arrays.toString(ans));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // Example usage for multiplication:
        int[] array1 = {3, 6};
        int[] array2 = {4};
        try {
            int[] resultArray = Multiplication(array1, array2);
            System.out.println("Multiplication of the two given array: " + Arrays.toString(resultArray));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // Test cases
        int[] edgeCase1 = {0}; // Single digit zero
        int[] edgeCase2 = {9, 9, 9}; // Large number
        int[] edgeCase3 = {}; // Empty array
        int[] edgeCase4 = {1, 2, 3}; // Array with non-numeric value

        // Testing edge cases
        try {
            System.out.println("Edge case 1: " + Arrays.toString(Addition(edgeCase1, array2))); // Expected output: [0]
            System.out.println("Edge case 2: " + Arrays.toString(Multiplication(edgeCase2, edgeCase2))); // Expected output: [9, 9, 8, 0, 0, 1]
            System.out.println("Edge case 3: " + Arrays.toString(Addition(edgeCase3, edgeCase3))); // Expected output: [0]
            System.out.println("Edge case 4: " + Arrays.toString(Multiplication(edgeCase4, edgeCase4))); // Expected output: Error
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Function to reverse an array
    public static int[] reverseArray(int[] arrayList) {
        int[] reversedArray = new int[arrayList.length];
        for (int i = 0; i < arrayList.length; i++) {
            reversedArray[i] = arrayList[arrayList.length - 1 - i];
        }
        return reversedArray;
    }

    // Function to perform addition of two given arrays representing numbers
    public static int[] Addition(int[] number1, int[] number2) throws Exception {
        // Check if arrays contain only numbers
        for (int num : number1) {
            if (!isNumber(num)) {
                throw new Exception("Only number data type will be accepted here");
            }
        }
        for (int num : number2) {
            if (!isNumber(num)) {
                throw new Exception("Only number data type will be accepted here");
            }
        }

        // Reverse both arrays
        int[] output = new int[Math.max(number1.length, number2.length) + 1];
        int[] reverseArray1 = reverseArray(number1);
        int[] reverseArray2 = reverseArray(number2);
        int i = 0, carry = 0;

        // Traverse both arrays until one of them ends
        while (i < reverseArray1.length || i < reverseArray2.length) {
            int temp = (i < reverseArray1.length ? reverseArray1[i] : 0) +
                    (i < reverseArray2.length ? reverseArray2[i] : 0) + carry;
            carry = temp / 10;
            output[i] = temp % 10;
            i++;
        }

        // If carry is still present after traversing both arrays
        if (carry > 0) {
            output[i] = carry;
        }

        // Reverse the output array to get the desired result
        return reverseArray(Arrays.copyOf(output, i + 1));
    }

    // Function to check if a value is a number
    public static boolean isNumber(int value) {
        return (value >= 0 && value <= 9);
    }

    // Function to perform multiplication of two numbers given as arrays
    public static int[] Multiplication(int[] num1Array, int[] num2Array) throws Exception {
        // If any of the input arrays is a single-digit zero, return [0]
        if ((num1Array.length == 1 && num1Array[0] == 0) || (num2Array.length == 1 && num2Array[0] == 0)) {
            return new int[]{0};
        }

        // Rest of the code for multiplication
        int[] result = new int[num1Array.length + num2Array.length]; // Initialize result with zeros

        // Iterate through each digit of the second number
        for (int i = num2Array.length - 1; i >= 0; i--) {
            int carry = 0;

            // Iterate through each digit of the first number
            for (int j = num1Array.length - 1; j >= 0; j--) {
                int product = num1Array[j] * num2Array[i] + result[i + j + 1] + carry;
                result[i + j + 1] = product % 10; // Add the least significant digit to the result array
                carry = product / 10; // Update the carry
            }

            // Update the carry in the result array
            result[i] += carry;
        }

        // Remove leading zeros
        int firstNonZeroIndex = 0;
        while (firstNonZeroIndex < result.length && result[firstNonZeroIndex] == 0) {
            firstNonZeroIndex++;
        }
        if (firstNonZeroIndex == result.length) {
            return new int[]{0}; // If all zeros, return [0]
        } else {
            return Arrays.copyOfRange(result, firstNonZeroIndex, result.length);
        }
    }
}
