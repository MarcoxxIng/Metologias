
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class MatrixCalculatorTest2 {
    @Test
    void testReadMatrixFromFile() throws IOException {
        int[][] expectedMatrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] actualMatrix = MatrixCalculator.readMatrixFromFile("matrizA.txt");
        Assertions.assertArrayEquals(expectedMatrix, actualMatrix);
    }

    @Test
    void testWriteMatrixToFile() throws IOException {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        MatrixCalculator.writeMatrixToFile(matrix, "matrizB.txt");
        int[][] readMatrix = MatrixCalculator.readMatrixFromFile("matrizB.txt");
        Assertions.assertArrayEquals(matrix, readMatrix);
    }

    @Test
    void testCalculateMatrixB() throws MatrixCalculator.MatrixDimensionException {
        int[][] matrixA = {{1, 2}, {3, 4}};
        int[][] matrixC = {{5, 6}, {7, 8}};
        int[][] expectedMatrixB = {{4, 4}, {4, 4}};
        int[][] actualMatrixB = MatrixCalculator.calculateMatrixB(matrixA, matrixC);
        Assertions.assertArrayEquals(expectedMatrixB, actualMatrixB);
    }

    @Test
    void testCalculateMatrixB_DifferentDimensions() {
        int[][] matrixA = {{1, 2, 3}, {4, 5, 6}};
        int[][] matrixC = {{5, 6}, {7, 8}};
        Assertions.assertThrows(MatrixCalculator.MatrixDimensionException.class, () -> {
            MatrixCalculator.calculateMatrixB(matrixA, matrixC);
        });
    }
}
