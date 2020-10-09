import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Выберите способ ввода данных:\n1- файловый ввод;\n2- консольный ввод;\n3- рандомные коэффиценты\n" +
                "Затем введите размерность матрицы и допустимую погрешность.");
        Scanner scanner = new Scanner(System.in);
        int input_way = scanner.nextInt();
        int EQUATIONS_NUMBER = scanner.nextInt();
        float REASONABLE_FAULT = scanner.nextFloat();

        LinearSystem system = new LinearSystem();

        switch (input_way) {
            case 1:
                try {
                    FileReader fileReader = new FileReader("src\\matrix.txt");
                    Scanner fileScanner = new Scanner(fileReader);
                    System.out.println("Исходная матрица:");
                    for (int i = 0; i < EQUATIONS_NUMBER; i++) {
                        Equation equation = new Equation();
                        equation.generate_via_file(fileScanner, EQUATIONS_NUMBER);
                        system.addEquation(equation);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Файл не найден.");
                }
                break;

            case 2:
                for (int i = 0; i < EQUATIONS_NUMBER; i++) {
                    Equation equation = new Equation();
                    equation.generate_via_input(scanner, EQUATIONS_NUMBER);
                    system.addEquation(equation);
                }
                break;

            case 3:
                System.out.println("Исходная матрица:");
                for (int i = 0; i < EQUATIONS_NUMBER; i++) {
                    Equation equation = new Equation();
                    equation.generate_via_random(EQUATIONS_NUMBER);
                    system.addEquation(equation);
                }
                break;
        }

        Solution solution = new Solution();
        solution.solve(system, REASONABLE_FAULT);

        System.out.println("Решение системы:");
        for (int i = 0; i < solution.getSolutionSize(); i++) {
            System.out.print("x[" + (i + 1) + "] = " + solution.solution.get(i) + " ");
        }

        System.out.println("\nПогрешность:");
        for (int i = 0; i < solution.getSolutionSize(); i++) {
            System.out.print("dx[" + (i + 1) + "] = " + Math.abs(solution.solution.get(i) - solution.previousSolution.get(i)) + " ");
        }

        System.out.println("\nКоличество итераций: " + solution.iterations);
    }
}
