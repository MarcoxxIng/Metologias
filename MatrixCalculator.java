import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MatrixCalculator {
    private static final String FILE_A = "matrizA.txt";
    private static final String FILE_C = "matrizC.txt";
    private static final String FILE_B = "matrizB.txt";

    public static void main(String[] args) {
        try {
            int[][] matrixA = readMatrixFromFile(FILE_A);
            int[][] matrixC = readMatrixFromFile(FILE_C);
            int[][] matrixB = calculateMatrixB(matrixA, matrixC);
            writeMatrixToFile(matrixB, FILE_B);
            System.out.println("Matriz B ha sido calculada y guardada en " + FILE_B);
        } catch (IOException e) {
            System.out.println("Error al leer o escribir archivos: " + e.getMessage());
        } catch (MatrixDimensionException e) {
            System.out.println("Error: Las matrices no tienen las dimensiones adecuadas");
        }
    }

    private static int[][] readMatrixFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        String[] dimensions = line.split(" ");
        int rows = Integer.parseInt(dimensions[0]);
        int columns = Integer.parseInt(dimensions[1]);
        int[][] matrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            line = reader.readLine();
            String[] values = line.split(" ");
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = Integer.parseInt(values[j]);
            }
        }
        reader.close();
        return matrix;
    }

    private static void writeMatrixToFile(int[][] matrix, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(matrix.length + " " + matrix[0].length);
        writer.newLine();
        for (int[] row : matrix) {
            for (int i = 0; i < row.length; i++) {
                writer.write(row[i] + " ");
            }
            writer.newLine();
        }
        writer.close();
    }

    static int[][] calculateMatrixB(int[][] matrixA, int[][] matrixC) throws MatrixDimensionException {
        int rowsA = matrixA.length;
        int columnsA = matrixA[0].length;
        int rowsC = matrixC.length;
        int columnsC = matrixC[0].length;

        if (rowsA != rowsC || columnsA != columnsC) {
            throw new MatrixDimensionException();
        }

        int[][] matrixB = new int[rowsA][columnsA];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < columnsA; j++) {
                matrixB[i][j] = matrixC[i][j] - matrixA[i][j];
            }
        }
        return matrixB;
    }

    static class MatrixDimensionException extends Exception {
        public MatrixDimensionException() {
            super("Las matrices no tienen las dimensiones adecuadas");
        }
    }
}