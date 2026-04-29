package ua.edu.znu.arrayanalizer;

/**
 * Спільні константи програми, що використовуються класами {@link ua.edu.znu.arrayanalizer.Main}
 * та {@link ua.edu.znu.arrayanalizer.InputHandler}.
 *
 * @author Student
 * @version 1.0
 */
final class AppConstants {

    private AppConstants() {
    }

    /**
     * Константа для режиму ручного введення значень елементів масиву.
     */
    static final int INPUT_MODE_MANUAL = 1;
    /**
     * Константа для режиму введення випадкових значень елементів масиву.
     */
    static final int INPUT_MODE_RANDOM = 2;
    /**
     * Константа для опції меню "Вихід з програми".
     */
    static final int MENU_EXIT = 0;
    /**
     * Константа для опції меню "Відображення статистики масиву".
     */
    static final int MENU_STATS = 1;
    /**
     * Константа для опції меню "Виконання лінійного пошуку елементу масиву".
     */
    static final int MENU_LINEAR_SEARCH = 2;
    /**
     * Константа для опції меню "Виконання бінарного пошуку елемента масиву".
     */
    static final int MENU_BINARY_SEARCH = 3;
    /**
     * Константа для опції меню "Виведення масиву на екран".
     */
    static final int MENU_PRINT_ARRAY = 4;
}
