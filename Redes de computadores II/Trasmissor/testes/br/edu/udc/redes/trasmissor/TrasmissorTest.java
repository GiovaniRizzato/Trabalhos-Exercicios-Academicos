package br.edu.udc.redes.trasmissor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TrasmissorTest {

	@Test
	void unsSeguidos_comeco() {
		assertEquals("11111010010", Trasmissor.marcarUnsSeguidos("1111110010"));
	}

	@Test
	void unsSeguidos_meio() {
		assertEquals("101111101001", Trasmissor.marcarUnsSeguidos("10111111001"));
	}

	@Test
	void unsSeguidos_fim() {
		assertEquals("10101111101", Trasmissor.marcarUnsSeguidos("1010111111"));
	}

	@Test
	void unsSeguidos_2ocorrencias() {
		assertEquals("111110101011011111010101110111110", Trasmissor.marcarUnsSeguidos("111111010110111111010111011111"));
	}

	@Test
	void unsSeguidos_7seguidos() {
		assertEquals("10010111110110010", Trasmissor.marcarUnsSeguidos("1001011111110010"));
	}
	
	@Test
	void unsSeguidos_frame1() {
		assertEquals("001110010011011100111001001110000011100100111001", Trasmissor.marcarUnsSeguidos("001110010011011100111001001110000011100100111001"));
	}
	

}
