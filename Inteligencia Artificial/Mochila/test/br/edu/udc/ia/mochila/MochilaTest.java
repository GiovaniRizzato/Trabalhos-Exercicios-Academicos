package br.edu.udc.ia.mochila;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.junit.jupiter.api.Test;

class MochilaTest {

	@Test
	void crossOver1() {

		boolean[] bm1 = { true, true, true };
		Mochila m1 = new Mochila(bm1);

		boolean[] bm2 = { false, false, false };
		Mochila m2 = new Mochila(bm2);

		int[] posicaoCorte = { 1 };
		Collection<Mochila> filhos = m1.crossOver(m2, posicaoCorte);

		assertEquals("[[false, false, true], [true, true, false]]", filhos.toString());
	}

	@Test
	void crossOver2() {

		boolean[] bm1 = { true, true, true, true };
		Mochila m1 = new Mochila(bm1);

		boolean[] bm2 = { false, false, false, false };
		Mochila m2 = new Mochila(bm2);

		int[] posicaoCorte = { 1, 2 };
		Collection<Mochila> filhos = m1.crossOver(m2, posicaoCorte);

		assertEquals("[[false, false, true, false], [true, true, false, true]]", filhos.toString());
	}
}
