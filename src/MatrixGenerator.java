import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

// Класс для создания матрицы из коэффицентов системы уравнений
public class MatrixGenerator {
    // Получить матрицу из файла
    public LinearSystem getMatrixViaFile (int EQUATIONS_NUMBER, String filePath) {
        LinearSystem system = new LinearSystem();
        try {
            FileReader fileReader = new FileReader(filePath);
            Scanner fileScanner = new Scanner(fileReader);
            System.out.println("Исходная матрица:");
            for (int i = 0; i < EQUATIONS_NUMBER; i++) {
                Equation equation = new Equation();
                equation.generate_via_file(fileScanner, EQUATIONS_NUMBER);
                system.addEquation(equation);
            }
            return system;
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
            return system;
        }
    }

    // Получить матрицу на основе того, что пользователь введёт в консоль
    public LinearSystem getMatrixViaInput (int EQUATIONS_NUMBER, Scanner scanner) {
        LinearSystem system = new LinearSystem();
        for (int i = 0; i < EQUATIONS_NUMBER; i++) {
            Equation equation = new Equation();
            equation.generate_via_input(scanner, EQUATIONS_NUMBER);
            system.addEquation(equation);
        }
        return system;
    }

    // Рандомно сгенерировать элементы матрицы
    public LinearSystem getMatrixViaRandom (int EQUATIONS_NUMBER) {
        LinearSystem system = new LinearSystem();
        System.out.println("Исходная матрица:");
        for (int i = 0; i < EQUATIONS_NUMBER; i++) {
            Equation equation = new Equation();
            equation.generate_via_random(EQUATIONS_NUMBER);
            system.addEquation(equation);
        }
        return system;
    }
}
