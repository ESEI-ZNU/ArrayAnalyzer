package ua.edu.znu.arrayanalizer;

import java.util.Random;
import java.util.Scanner;

import static ua.edu.znu.arrayanalizer.AppConstants.*;

/**
 * Обробляє перевірений ввід користувача з консолі.
 *
 * @author Student
 * @version 1.0
 */
public class InputHandler {
    /**
     * Використовується для зчитування введення користувача з консолі
     */
    private final Scanner scanner = new Scanner(System.in);
    /**
     * Використовується для генерування випадкових цілих чисел.
     */
    private final Random random = new Random();

    /**
     * Зчитує та перевіряє розмір масиву.
     *
     * @return перевірений розмір масиву
     */
    public int readArraySize() {
        System.out.println("Введіть розмір масиву:");
        while (true) {
            int arraySize = readInt();
            if (arraySize > 0) {
                return arraySize;
            }
            System.out.println("Розмір масиву має бути більшим за 0!");
        }
    }

    /**
     * Зчитує та перевіряє режим введення значень елементів масиву.
     *
     * @return {@code 1} для ручного введення або {@code 2} для випадкового введення значень
     */
    public int readInputMode() {
        System.out.printf("Введіть спосіб введення значень елементів масиву: %n"
                + "%d - значення вручну, %d - випадкові значення%n", INPUT_MODE_MANUAL, INPUT_MODE_RANDOM);
        while (true) {
            int inputMode = readInt();
            if (inputMode == INPUT_MODE_MANUAL || inputMode == INPUT_MODE_RANDOM) {
                return inputMode;
            }
            System.out.printf("Будь ласка, введіть число від %d до %d!%n", INPUT_MODE_MANUAL, INPUT_MODE_RANDOM);
        }
    }

    /**
     * Організує введення значень кожного елемента масиву з консолі.
     *
     * @param array масив, який потрібно заповнити
     */
    public void fillArrayManually(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("Введіть елемент [%d]:", i);
            array[i] = readInt();
        }
    }

    /**
     * Зчитує нижню та верхню межі з консолі та заповнює масив псевдовипадковими числами.
     * <p>
     * Якщо нижня межа більша за верхню, метод виводить повідомлення про помилку
     * та повторює запит до введення коректних значень.
     *
     * @param array масив, який потрібно заповнити
     */
    public void readAndFillArrayRandomly(int[] array) {
        while (true) {
            System.out.println("Введіть нижню межу для випадкових значень:");
            int lowerBound = readInt();
            System.out.println("Введіть верхню межу для випадкових значень:");
            int upperBound = readInt();
            if (lowerBound <= upperBound) {
                long rangeSize = (long) upperBound - lowerBound + 1L;
                if (rangeSize <= Integer.MAX_VALUE) {
                    // Межі коректні — заповнити масив та вийти з циклу
                    fillArrayRandomly(array, lowerBound, upperBound);
                    return;
                }
                System.out.println("Завеликий діапазон! Вкажіть межі так, щоб кількість можливих значень була не більшою за "
                        + Integer.MAX_VALUE + ".");
                continue;
            }
            // Нижня межа більша за верхню — показати помилку та запитати ще раз
            System.out.println("Нижня межа має бути меншою або рівною верхній! Спробуйте ще раз.");
        }
    }

    /**
     * Заповнює масив псевдовипадковими цілими числами в діапазоні
     * [randomValueLowerBound, randomValueUpperBound] включно.
     *
     * @param array                 масив, який потрібно заповнити
     * @param randomValueLowerBound нижня границя випадкових значень (включно)
     * @param randomValueUpperBound верхня границя випадкових значень (включно)
     */
    private void fillArrayRandomly(int[] array, int randomValueLowerBound, int randomValueUpperBound) {
        int bound = (int) ((long) randomValueUpperBound - randomValueLowerBound + 1L);
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(bound) + randomValueLowerBound;
        }
    }

    /**
     * Зчитує ціле число з консолі з повторною спробою у разі недійсного формату.
     * <p>
     * Цей метод викликається щоразу, коли потрібне ціле число від користувача.
     * Якщо користувач вводить щось, що не є дійсним цілим числом (наприклад, літери або символи),
     * він побачить повідомлення про помилку та буде запропоновано ввести значення ще раз.
     * <p>
     * Цей цикл продовжується, доки не буде введено дійсне ціле число.
     *
     * @return розпізнане ціле число
     */
    public int readInt() {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line); // Спроба перетворити текст на ціле число
            } catch (NumberFormatException exception) {
                // Користувач ввів нецілочисельний текст — показати помилку та запитати ще раз
                System.out.println("Некоректне введення! Будь ласка, введіть ціле число!");
            }
        }
    }
}
