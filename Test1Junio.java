import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Test1Junio {

	public class MatrixMultiplierTest {

	    @Test
	    public void testMultiplicarMatrices() {
	        int[][] matriz1 = {{1, 2, 3}, {4, 5, 6}};
	        int[][] matriz2 = {{7, 8}, {9, 10}, {11, 12}};
	        int[][] resultadoEsperado = {{58, 64}, {139, 154}};

	        int[][] resultado = MatrixMultiplier.multiplicarMatrices(matriz1, matriz2);

	        Assertions.assertArrayEquals(resultadoEsperado, resultado);
	    }

	    @Test
	    public void testMultiplicarMatrices_DimensionesInvalidas() {
	        int[][] matriz1 = {{1, 2, 3}, {4, 5, 6}};
	        int[][] matriz2 = {{7, 8}, {9, 10}};

	        int[][] resultado = MatrixMultiplier.multiplicarMatrices(matriz1, matriz2);

	        Assertions.assertNull(resultado);
	    }
	}

	}

