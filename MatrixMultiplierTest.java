import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MatrixMultiplierTest {
	@Test
	public void testMatrixMultiplication() {
		MatrixMultiplier multiplier = new MatrixMultiplier();

		int[][] matrix1 = { { 1, 2, 3 }, { 4, 5, 6 } };
		int[][] matrix2 = { { 7, 8 }, { 9, 10 }, { 11, 12 } };
		int[][] expected = { { 58, 64 }, { 139, 154 } };

		int[][] result = multiplier.multiply(matrix1, matrix2);

		assertArrayEquals(expected, result);
	}

	@Test
	public void testMatrixMultiplication2() {
		MatrixMultiplier multiplier = new MatrixMultiplier();

		int[][] matrix1 = { { 1, 2 }, { 3, 4 } };
		int[][] matrix2 = { { 5, 6 }, { 7, 8 } };
		int[][] expected = { { 19, 22 }, { 43, 50 } };

		int[][] result = multiplier.multiply(matrix1, matrix2);

		Assertions.assertArrayEquals(expected, result);
	}

	@Test
    public void testMatrixMultiplicationWithInvalidInput() {
        MatrixMultiplier multiplier = new MatrixMultiplier();

        int[][] matrix1 = {{1, 2}, {3, 4}};
        int[][] matrix2 = {{5, 6}, {7, 8}};

        // Modificar la matriz 2 para incluir un valor no numérico
        matrix2[0][1] = (int) 'A';

        // Intentar multiplicar las matrices
        int[][] result = multiplier.multiply(matrix1, matrix2);

        // Verificar que el resultado es null
        Assertions.assertNull(result);
    }

	@Test
	public void testMatrixMultiplicationWithInvalidInput1() {
		MatrixMultiplier multiplier = new MatrixMultiplier();

		int[][] matrix1 = { { 1, 2 }, { 3, 4 } };
		int[][] matrix2 = { { 5, 6 }, { 7, 8 } };

		// Crear archivos de texto con valores inválidos (strings en lugar de números)
		Main.writeMatrixToFile(new int[][] { { 1, 2 }, { 3, 4 } }, "matrix1.txt");
		Main.writeMatrixToFile(new int[][] { { 0, 0 }, { 0, 0 } }, "matrix2.txt");

		// Intentar multiplicar las matrices
		int[][] result = multiplier.multiply(matrix1, matrix2);

		// Verificar que el resultado es null
		Assertions.assertNull(result);
	}
	
	
}
