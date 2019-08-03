package br.edu.udc.redes.receptor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Vector;

import org.junit.jupiter.api.Test;

import br.edu.udc.redes.Conversor;

class ReceptorTest {

	final static String str1 = "123456789";
	final static String str2 = "abcde";
	final static String str3 = "a1b2c3";

	@Test
	void unsSeguidos_comeco() {
		assertEquals("1111110010", Receptor.removeZeros("11111010010"));
	}

	@Test
	void unsSeguidos_meio() {
		assertEquals("10111111001", Receptor.removeZeros("101111101001"));
	}

	@Test
	void unsSeguidos_fim() {
		assertEquals("1010111111", Receptor.removeZeros("10101111101"));
	}

	@Test
	void unsSeguidos_2ocorrencias() {
		assertEquals("111111010110111111010111011111", Receptor.removeZeros("111110101011011111010101110111110"));
	}

	@Test
	void unsSeguidos_7seguidos() {
		assertEquals("1001011111110010", Receptor.removeZeros("10010111110110010"));
	}

	@Test
	void codigifcacao() {

		final String recebido = "01111110011001110110100101101111010100011111100111111001110110011000010110111001011101111110011111100110100100101101111110";
		Vector<String> quadros = new Vector<>();

		for (String str : recebido.split(Conversor.chaveDobrada)) {

			if (str.indexOf(Conversor.chave) == 0)// Se começar com a chave
				str = str.substring(8);

			final String[] possiveisQuadros = str.split(Conversor.chave);
			for (int i = 0; i < possiveisQuadros.length; i += 2) {
				quadros.add(possiveisQuadros[i]);
			}
		}

		assertEquals("011001110110100101101111010100", quadros.get(0));
		assertEquals("011101100110000101101110010111", quadros.get(1));
		assertEquals("01101001001011", quadros.get(2));
	}
}
