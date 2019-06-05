package br.edu.edu.exercicios.junit;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExerciciosJUnit {

	// TerminaCom
	@Test
	public void terminaComSimples() {
		String testado = "Estrutura de dados";
		assertEquals("Hue", true, testado.charAt(testado.length() - 1) == 's');
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void terminaComVazio() {
		String testado = "";
		testado.charAt(testado.length() - 1);
		fail("Not yet implemented");
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void terminaCom() {
		String testado = "";
		testado.charAt(testado.length() - 1);
		fail("Not yet implemented");
	}

	// ELIMINA ESPAÇO
	@Test
	public void eliminaEspacosSimples() {
		String testado = "Estrutura de Dados";
		assertEquals("Hue", "EstruturadeDados", testado.replaceAll(" ", ""));
	}

	@Test
	public void eliminaEspacosSemEspacos() {
		String testado = "EstruturadeDados";
		assertEquals("Hue", "EstruturadeDados", testado.replaceAll(" ", ""));
	}

	@Test
	public void eliminaEspacosVazio() {
		String testado = "";
		assertEquals("Hue", "", testado.replaceAll(" ", ""));
	}

	// CHAR AT
	@Test
	public void charAtSimples() {
		String testado = "Estrutura de Dados";
		assertEquals("Hue", 'a', testado.charAt(8));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void charAtOutofbounds() {
		String testado = "ED2";
		testado.charAt(4);
		fail("Not yet implemented");
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void charAtVazio() {
		String testado = "";
		testado.charAt(0);
		fail("Not yet implemented");
	}

	// SPLIT
	@Test
	public void splitSimples() {
		String stringComposta = "Ola;Oi;Iae;Greetings";
		String[] conversa = { "Ola", "Oi", "Iae", "Greetings" };
		assertArrayEquals("Hue", conversa, stringComposta.split(";"));
	}

	@Test
	public void splitCharErrado() {
		String stringComposta = "Ola;Oi;Iae;Greetings";
		String[] conversa = { "Ola;Oi;Iae;Greetings" };
		assertArrayEquals("Hue", conversa, stringComposta.split(","));
	}

	@Test
	public void splitVazio() {
		String stringComposta = "";
		String[] conversa = { "" };
		assertArrayEquals("Hue", conversa, stringComposta.split(";"));
	}
}
