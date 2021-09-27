import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// Класс описывает линейное уравнение
public class Equation {
    ArrayList<Float> list = new ArrayList<>();// Коллекция коэффицентов уравнения
    int numberOfMax;// Позиция ведущего элемента в ряду

    // Получение коээфицентов уравнения через консольный ввод
    void generate_via_input (Scanner s, int size) {
        for (int i = 0; i <= size; i++) {
            this.list.add( Float.valueOf(s.next()) );
        }
    }

    // Получение коээфицентов уравнения записанной в файле
    void generate_via_file (Scanner s, int size) {
        if (size < 2) size = 2;
        this.list.clear();
        for (int i = 0; i <= size; i++){
            this.list.add( Float.valueOf(s.next()) );
        }
        System.out.println(list.toString());
    }

    // Получение коээфицентов уравнения с помощью RNG
    void generate_via_random (int size) {
        if (size < 2) size = 2;
        this.list.clear();
        for (int i = 0; i <= size; i++){
            Random random = new Random();
            this.list.add((float) (random.nextInt()%10) + 1);
        }
        System.out.println(list.toString());
    }

    // Метод проверяет позволяет ли ряд добиться диагонального преобладания в матрице
    boolean isDominant () {
        float sum = 0.0f;
        float max = 0.0f;
        for (int i = 0; i < list.size() - 1; i++) {
            sum += Math.abs(list.get(i));
            if (Math.abs(list.get(i)) > max) {
                max = Math.abs(list.get(i));
                numberOfMax = i;
            }
        }
        return (2 * max >= sum);
    }
}
