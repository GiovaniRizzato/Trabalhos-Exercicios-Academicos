package br.edu.udc.redes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ConversorTest {

	@Test
	void Asc_umCaracter() {
		assertEquals("01100001", Conversor.AscToBinary("a"));
	}

	@Test
	void Asc_tresCaracter() {
		assertEquals("011000010110001001100011", Conversor.AscToBinary("abc"));
	}

	@Test
	void Bin_umCaracter() {
		assertEquals("a", Conversor.BinaryToAsc("01100001"));
	}

	@Test
	void Bin_tresCaracter() {
		assertEquals("abc", Conversor.BinaryToAsc("011000010110001001100011"));
	}

	@Test
	void Bin_zeroEsqueda() {
		assertEquals("a", Conversor.BinaryToAsc("1100001"));
		assertEquals("b", Conversor.BinaryToAsc("1100010"));
	}

	@Test
	void Bin_Asc_simples() {
		final String palavra = "abc";

		assertEquals(palavra, Conversor.BinaryToAsc((Conversor.AscToBinary(palavra))));
	}

	@Test
	void Bin_Asc_complexo() {
		final String palavra = "Giovani Rizzato!";

		assertEquals(palavra, Conversor.BinaryToAsc((Conversor.AscToBinary(palavra))));
	}

	@Test
	void checkSum_1() {
		final String dividendo = "000111001100000";
		final String divisor = "11001";

		assertEquals("110", Conversor.checkSum(dividendo, divisor));
	}

	@Test
	void checkSum_2() {
		final String dividendo = "00100100000";
		final String divisor = "1101";

		assertEquals("1", Conversor.checkSum(dividendo, divisor));
	}

	@Test
	void checkSum_frame() {
		final String dividendo = "001110010011011100111001001110000011100100111001";
		final String divisor = Conversor.polinomio;
		final String checkSum = Conversor.checkSum(dividendo + "000000", divisor);

		assertEquals("011001", ("000000" + checkSum).substring(checkSum.length()));

		final String confereCheck = Conversor.checkSum(dividendo + "011001", divisor);

		assertEquals("0", confereCheck);
	}
}
