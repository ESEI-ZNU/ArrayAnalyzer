package ua.edu.znu.arrayanalizer;

/**
 * Допоміжний клас зі статичними методами (допоміжними функціями) для обчислення статистики масиву цілих чисел,
 * такої як мінімум, максимум, середнє значення та підрахунок парних та непарних чисел.
 *
 * @author Student
 * @version 1.0
 */
public class ArrayStats {
    /**
     * Знаходить мінімальний елемент масиву.
     *
     * @param array масив, для якого потрібно знайти мінімальний елемент
     * @return мінімальний елемент масиву
     */
    public static int getMin(int[] array) {
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    /**
     * Знаходить максимальний елемент масиву.
     *
     * @param array масив, для якого потрібно знайти максимальний елемент
     * @return максимальний елемент масиву
     */
    public static int getMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    /**
     * Розраховує середнє значення елементів масиву.
     *
     * @param array масив, для якого потрібно розрахувати середнє значення елементів
     * @return середнє значення елементів масиву
     */
    public static double getAverage(int[] array) {
        long sum = 0;
        for (int j : array) {
            sum += j;
        }
        return (double) sum / array.length;
    }

    /**
     * Підраховує кількість парних елементів масиву.
     *
     * @param array масив, для якого потрібно підрахувати кількість парних елементів
     * @return кількість парних елементів масиву
     */
    public static int countEven(int[] array) {
        int count = 0;
        for (int j : array) {
            if (j % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * Підраховує кількість непарних елементів масиву.
     *
     * @param array масив, для якого потрібно підрахувати кількість непарних елементів
     * @return кількість непарних елементів масиву
     */
    public static int countOdd(int[] array) {
        int count = 0;
        for (int j : array) {
            if (j % 2 != 0) {
                count++;
            }
        }
        return count;
    }
}
