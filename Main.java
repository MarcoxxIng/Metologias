import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String fileMatrix1 = "matrix1.txt";
        String fileMatrix2 = "matrix2.txt";
        String fileResult = "result.txt";

        // Leer matrices desde los archivos de texto
        int[][] matrix1 = readMatrixFromFile(fileMatrix1);
        int[][] matrix2 = readMatrixFromFile(fileMatrix2);

        // Multiplicar las matrices
        MatrixMultiplier multiplier = new MatrixMultiplier();
        int[][] result = multiplier.multiply(matrix1, matrix2);

        // Escribir el resultado en un archivo de texto
        writeMatrixToFile(result, fileResult);

        System.out.println("La multiplicación de matrices se ha completado.");
    }

    public static int[][] readMatrixFromFile(String fileName) {
        int[][] matrix = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            List<int[]> rows = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                int[] row = new int[values.length];
                for (int i = 0; i < values.length; i++) {
                    row[i] = Integer.parseInt(values[i].trim());
                }
                rows.add(row);
            }
            if (!rows.isEmpty()) {
                matrix = new int[rows.size()][rows.get(0).length];
                for (int i = 0; i < rows.size(); i++) {
                    matrix[i] = rows.get(i);
                }
                if (matrix.length < 2 || matrix[0].length < 2) {
                    throw new IllegalArgumentException("Las dimensiones de la matriz son menores a 2x2.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matrix;
    }


    public static void writeMatrixToFile(int[][] matrix, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (int[] row : matrix) {
                StringBuilder sb = new StringBuilder();
                for (int value : row) {
                    sb.append(value).append(",");
                }
                sb.deleteCharAt(sb.length() - 1); // Eliminar la última coma
                writer.println(sb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}