import java.util.ArrayList;

public class Solution {
    ArrayList<Float> solution = new ArrayList<>();
    ArrayList<Float> previousSolution = new ArrayList<>();
    int iterations;

    void solve (LinearSystem system, float fault) {
        iterations = 0;
        if (checkSystem(system)) {
            system.sort();
            System.out.println("Преобразованная матрица:");

            for (Equation d : system.system) {
                System.out.println(d.list.toString());
            }

            for (int i = 0; i < system.getSize(); i++) {
                solution.add(system.getFactor(i, system.getEquation(i).list.size() - 1) / system.getFactor(i, i));
                previousSolution.add(0.0f);
            }
            iterations++;

            do {
                setPreviousSolution(solution);
                for (int i = 0; i < solution.size(); i++) {
                    solution.set(i, solveX(system, i));
                }
                iterations++;
            } while ( !isEnough(fault) );
        }
    }

    private float solveX (LinearSystem system, int i) {
        float X = system.getFactor(i, system.getEquation(i).list.size() - 1);
        for (int j = 0; j < system.getEquation(i).list.size() - 1; j++) {
            if(j != i) X -=  system.getFactor(i, j) * solution.get(j);
        }
        return (X / system.getFactor(i, i));
    }

    void setPreviousSolution (ArrayList<Float> list) {
        for (int i = 0; i < list.size(); i++) {
            previousSolution.set(i, list.get(i));
        }
    }

    private boolean checkSystem(LinearSystem system){
        if (system.system.size() < 2) return false;
        for(int i = 0; i < system.system.size(); i++){
            if (system.system.get(i).list.size() != (system.system.size() + 1)){
                return false;
            }
        }
        if (!system.isDiagonalDominant()) return false;
        for (int i = 0; i < system.maxIndexes.size(); i++) {
            for (int j = 0; j < system.maxIndexes.size(); j++) {
                if (i != j && system.maxIndexes.get(i).equals(system.maxIndexes.get(j))) {
                    System.out.println("Невозможно добиться диагонального преобладания.");
                    return false;
                }

            }
        }
        return true;
    }

    private boolean isEnough (float fault) {
        for (int i = 0; i < solution.size(); i++) {
            if (Math.abs(solution.get(i) - previousSolution.get(i)) > fault) return false;
        }
        return true;
    }

    int getSolutionSize () {
        return solution.size();
    }
}
