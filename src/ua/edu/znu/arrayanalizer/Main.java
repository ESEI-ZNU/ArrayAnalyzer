package ua.edu.znu.arrayanalizer;

import java.util.Arrays;

import static ua.edu.znu.arrayanalizer.AppConstants.*;

/**
 * Містить стартовий метод програми, надає користувачеві меню з можливими функціями програми
 * та виконує обробку вибору користувача.
 *
 * @author Student
 * @version 1.0
 */
public class Main {

    /**
     * Головний метод, який запускає програму, обробляє введені користувачем дані та керує за допомогою меню
     * операціями аналізу масиву та пошуку його елементів.
     *
     * @param args аргументи командного рядка (не використовуються у даній програмі)
     */
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        int arraySize = inputHandler.readArraySize();
        // Масив, який буде проаналізовано.
        int[] array = new int[arraySize];
        int inputMode = inputHandler.readInputMode();
        switch (inputMode) {
            case INPUT_MODE_MANUAL -> {
                inputHandler.fillArrayManually(array);
                System.out.println("Заповнений вручну масив : " + Arrays.toString(array));
            }
            case INPUT_MODE_RANDOM -> {
                inputHandler.readAndFillArrayRandomly(array);
                System.out.println("Масив, заповнений випадковими значеннями: " + Arrays.toString(array));
            }
            default ->
                    System.out.printf("Неочікуваний режим введення! Виберіть %d або %d.%n", INPUT_MODE_MANUAL, INPUT_MODE_RANDOM);
        }
        int choice;
        do {
            printMenu();
            choice = readMenuChoice(inputHandler);
            executeMenuAction(choice, array, inputHandler);
        } while (choice != MENU_EXIT);
    }

    /**
     * Виконує обрану операцію над масивом на основі вибору користувача.
     * <p>
     * Для пункту бінарного пошуку сортується копія масиву, тому повернений індекс
     * належить відсортованій копії, а початковий масив не змінюється.
     *
     * @param choice опція меню, обрана користувачем
     * @param array  масив для виконання операцій над ним
     * @param input  обробник вводу користувача (використовується для зчитування елементів, що шукатимуться)
     */
    private static void executeMenuAction(int choice, int[] array, InputHandler input) {
        switch (choice) {
            case MENU_STATS -> {
                // Відображає різні статистичні дані про масив (мінімум, максимум, середнє, кількість парних/непарних елементів)
                System.out.println("Статистичні дані масиву:");
                System.out.println("Мінімальне значення: " + ArrayStats.getMin(array));
                System.out.println("Максимальне значення: " + ArrayStats.getMax(array));
                System.out.println("Середньо-арифметичне значення: " + ArrayStats.getAverage(array));
                System.out.println("Кількість парних елементів: " + ArrayStats.countEven(array));
                System.out.println("Кількість непарних елементів: " + ArrayStats.countOdd(array));
            }
            case MENU_LINEAR_SEARCH -> {
                // Лінійний пошук: перевіряємо кожен елемент масиву, доки не знайдемо ціль
                // Часова складність: O(n) — може знадобитися перевірити всі елементи
                System.out.println("Введіть значення елемента масиву для пошуку:");
                int value = input.readInt();
                int index = ArraySearch.linearSearch(array, value);
                if (index != -1) {
                    System.out.printf("Елемент масиву %d знайдений за індексом: %d%n", value, index);
                } else {
                    System.out.printf("Елемент масиву %d не знайдений.%n", value);
                }
            }
            case MENU_BINARY_SEARCH -> {
                // Бінарний пошук: швидкий метод пошуку, але ПОТРІБНО попередньо відсортувати масив
                System.out.println("Введіть значення для пошуку:");
                int value = input.readInt();
                System.out.println("Масив буде відсортований перед бінарним пошуком!");
                int index = ArraySearch.binarySearch(array, value);
                if (index != -1) {
                    System.out.printf("Елемент відсортованого масиву %d знайдений за індексом: %d%n", value, index);
                } else {
                    System.out.printf("Елемент масиву %d не знайдений.%n", value);
                }
            }
            case MENU_PRINT_ARRAY -> System.out.println("Поточний масив: " + Arrays.toString(array));
            case MENU_EXIT -> System.out.println("Вихід з програми. До побачення!");
            default -> System.out.println("Недійсний вибір! Виберіть дійсний варіант із меню.");
        }
    }

    /**
     * Зчитує та перевіряє вибір меню користувача.
     * <p>
     * Цей метод запитує введення, доки користувач не введе коректний пункт меню (від 0 до 4).
     * Неправильне введення викликає повідомлення про помилку та повторює запит.
     *
     * @param input обробник вводу користувача (використовується для зчитування вибору меню)
     * @return коректний вибір меню (0, 1, 2, 3 або 4)
     */
    private static int readMenuChoice(InputHandler input) {
        while (true) {
            int choice = input.readInt();
            if (choice >= MENU_EXIT && choice <= MENU_PRINT_ARRAY) {
                return choice; // Коректний вибір — повернути його значення
            }
            // Некоректний вибір — показати помилку та запитати ще раз
            System.out.printf("Введіть пункт меню від %d до %d.%n", MENU_EXIT, MENU_PRINT_ARRAY);
        }
    }

    /**
     * Відображає меню операцій, які можна виконати над масивом.
     * <p>
     * У меню відображаються всі доступні операції з їхніми номерами:
     * <ul>
     * <li> 0: Вихід (зупинити програму) </li>
     * <li> 1: Статистика (показати мінімум, максимум, середнє, кількість парних/непарних елементів) </li>
     * <li> 2: Лінійний пошук (знайти значення, перевіряючи кожен елемент) </li>
     * <li> 3: Бінарний пошук (пошук у відсортованій копії масиву; індекс повертається для цієї копії) </li>
     * <li> 4: Вивести масив (показати поточний стан масиву на екрані) </li>
     * </ul>
     */
    private static void printMenu() {
        System.out.println("Оберіть операцію для виконання над масивом:");
        System.out.println(MENU_STATS + " - Відображення статистики масиву");
        System.out.println(MENU_LINEAR_SEARCH + " - Виконання лінійного пошуку елементу масиву");
        System.out.println(MENU_BINARY_SEARCH + " - Виконання бінарного пошуку елементу відсортованого масиву");
        System.out.println(MENU_PRINT_ARRAY + " - Виведення масиву на екран");
        System.out.println(MENU_EXIT + " - Вихід з програми");
    }
}
