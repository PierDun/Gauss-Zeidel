import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LinearSystem {
    List<Equation> system = new ArrayList<>();
    List<Integer> maxIndexes = new ArrayList<>();

    void addEquation (Equation list) {
        system.add(list);
    }

    Equation getEquation (int number) {
        return system.get(number);
    }

    int getSize () {
        return system.size();
    }

    float getFactor (int i, int j) {
        return getEquation(i).list.get(j);
    }

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
