import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MatrixCalculatorTest {
    @Test
    void testCalculateMatrixB() throws MatrixCalculator.MatrixDimensionException {
        int[][] matrixA = {{1, 2}, {3, 4}};
        int[][] matrixC = {{5, 6}, {7, 8}};
        int[][] expectedMatrixB = {{4, 4}, {4, 4}};
        int[][] actualMatrixB = MatrixCalculator.calculateMatrixB(matrixA, matrixC);
        Assertions.assertArrayEquals(expectedMatrixB, actualMatrixB);
    }

    // Agrega más pruebas para otros métodos y casos de prueba
}

