import java.util.Scanner;

public class Main {
    public static int EQUATIONS_NUMBER; // Количество уравнений в системе
    public static float REASONABLE_FAULT; // Допустимая погрешность

    public static void main(String[] args) {
        System.out.println("Выберите способ ввода данных:\n1- файловый ввод;\n2- консольный ввод;\n3- рандомные коэффиценты\n" +
                "Затем введите размерность матрицы и допустимую погрешность.");
        Scanner scanner = new Scanner(System.in);
        int input_way = scanner.nextInt(); // переменная определяет метод создания матрица
        EQUATIONS_NUMBER = scanner.nextInt();
        REASONABLE_FAULT = scanner.nextFloat();

        LinearSystem system = null;
        MatrixGenerator generator = new MatrixGenerator();

        switch (input_way) {
            case 1:
                system = generator.getMatrixViaFile(EQUATIONS_NUMBER, "src\\matrix.txt");
                break;
            case 2:
                system = generator.getMatrixViaInput(EQUATIONS_NUMBER, scanner);
                break;
            case 3:
                system = generator.getMatrixViaRandom(EQUATIONS_NUMBER);
                break;
            default:
                System.out.println("Выбран некорректный способ ввода данных.");
                System.exit(1);
        }

        Solution solution = new Solution();
        solution.solve(system, REASONABLE_FAULT);

        System.out.println("Решение системы:");
        for (int i = 0; i < solution.getSolutionSize(); i++) {
            System.out.print("x[" + (i + 1) + "] = " + solution.solution.get(i) + " ");
        }

        System.out.println("\nПогрешность:");
        for (int i = 0; i < solution.getSolutionSize(); i++) {
            float fault = Math.abs(solution.solution.get(i) - solution.previousSolution.get(i)); // Разница между новым и старым решением
            System.out.print("dx[" + (i + 1) + "] = " + fault + " ");
        }

        System.out.println("\nКоличество итераций: " + solution.iterations);
    }
}
