package br.edu.udc.pdi.curva.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PdiMathTest {

	@Test
	void testMultiplicacao1() {
		double[][] a = { { 2, 3 }, { 1, 0 }, { 4, 5 } };
		double[][] b = { { 3, 1 }, { 2, 4 } };

		double[][] resultado = { { 12, 14 }, { 3, 1 }, { 22, 24 } };

		PdiMath.multiplicacaoMatriz(a, b);

		assertArrayEquals(PdiMath.multiplicacaoMatriz(a, b), resultado);
	}

	@Test
	void testMultiplicacao2() {
		double[][] a = { { 1, 4 }, { 3, 3 } };
		double[][] b = { { 1, 4 }, { 3, 3 } };

		double[][] resultado = { { 13, 16 }, { 12, 21 } };

		assertArrayEquals(PdiMath.multiplicacaoMatriz(a, b), resultado);
	}
}
