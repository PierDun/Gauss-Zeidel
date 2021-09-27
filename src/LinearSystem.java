import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Класс описывает систему линейных уранений
public class LinearSystem {
    List<Equation> system = new ArrayList<>(); // Коллекция коллекций коэффицентов уранений
    List<Integer> maxIndexes = new ArrayList<>(); // Коллекция ведущих элементов

    // Добавить ряд в матрицу
    void addEquation (Equation list) {
        system.add(list);
    }

    // Получить ряд матрицы
    Equation getEquation (int number) {
        return system.get(number);
    }

    // Получить количество рядов в матрице
    int getSize () {
        return system.size();
    }

    // Получить элемент матрицы на основании ряда и столба соответственно
    float getFactor (int i, int j) {
        return getEquation(i).list.get(j);
    }

    // Метод проверяет позволяеют ли ряды добиться диагонального преобладания
    boolean isDiagonalDominant () {
        for (int i = 0; i < system.size(); i++) {
            if (!getEquation(i).isDominant()) {
                System.out.println("Введенная матрица не обладает диагональным преобладанием.");
                return false;
            }
            maxIndexes.add(getEquation(i).numberOfMax);
        }
        return true;
    }

    // Переставить ряды в матрице таким образом, чтобы она стала обладать диагональным преобладанием
    void sort () {
        for (int i = 0; i < maxIndexes.size(); i++) {
            for (int j = 0; j < maxIndexes.size(); j++) {
                if (maxIndexes.get(i) < maxIndexes.get(j) && i > j) {
                    Collections.swap(system, i, j);
                    Collections.swap(maxIndexes, i, j);
                }
            }
        }
    }
}
