import java.util.Scanner;

public class Main {
    private static final char[] vowels = {'a', 'e', 'i', 'o', 'u', 'y'};
    private static long getFibonacciNumber(long n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return getFibonacciNumber(n - 1) + getFibonacciNumber(n - 2);
    }

    private static long getFactorial(long n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * getFactorial(n - 1);
    }

    private static int getNumberOfLetters(char[] array) {
        int cnt = 0;
        for (char c : array) {
            if (c >= 'a' && c <= 'z') {
                ++cnt;
            }
        }
        return cnt;
    }

    private static int getNumberOfVowels(char[] array) {
        int cnt = 0;
        for (char c : array) {
            for (char vowel : vowels) {
                if (c == vowel) {
                    cnt++;
                    break;
                }
            }
        }
        return cnt;
    }
    public static void printFizzBuzzNumbers() {
        for (int i = 1; i <= 100; ++i) {
            if (i % 3 == 0) {
                System.out.print("Fizz");
            }
            if (i % 5 == 0) {
                System.out.print("Buzz");
            }
            if (i % 3 != 0 && i % 5 != 0) {
                System.out.printf("%s", i);
            }
            System.out.println();
        }
    }

    public static void printFibonacciNumbers(Scanner in) {
        System.out.print("Input: ");
        long num = in.nextInt();
        for (int i = 0; i < num; ++i) {
            long result = getFibonacciNumber(i);
            System.out.printf("%d \n", result);
        }
    }

    public static void printFactorial(Scanner in) {
        System.out.print("Input: ");
        long num = in.nextInt();
        long result = getFactorial(num);
        System.out.printf("%d \n", result);
    }

    public static void sortArray(int[] array) {
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < array.length - 1; ++j) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }

    public static void bubbleSort() {
        int[] array = {-1, 92, 43, -4, 51, 88, -102, 11, 20, 0, -1, 45, 20, 115};
        System.out.print("Array: ");
        for (int num: array) {
            System.out.printf("%d ", num);
        }
        System.out.println();
        sortArray(array);
        System.out.print("Sorted array: ");
        for (int num: array) {
            System.out.printf("%d ", num);
        }
    }

    public static void countNumberOfConsonantsAndVowels(Scanner in) {
        System.out.print("Input: ");
        String str = in.next().toLowerCase(); // nextLine don't catch str?
        char[] textArray = str.toCharArray();
        int vowelsCount = getNumberOfVowels(textArray);
        int consonantsCount = getNumberOfLetters(textArray) - vowelsCount;
        System.out.printf("String has %d consonants and %d vowels\n", consonantsCount, vowelsCount);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("\nTask #1.1");
        printFizzBuzzNumbers();
        System.out.println("\nTask #1.2");
        printFibonacciNumbers(in);
        System.out.println("\nTask #1.3");
        printFactorial(in);
        System.out.println("\nTask #1.4");
        bubbleSort();
        System.out.println("\nTask #1.5");
        countNumberOfConsonantsAndVowels(in);
        System.out.println();
        in.close();
    }
}