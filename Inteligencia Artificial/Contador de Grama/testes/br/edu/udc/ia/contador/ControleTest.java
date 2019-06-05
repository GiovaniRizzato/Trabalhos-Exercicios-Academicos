package br.edu.udc.ia.contador;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ControleTest {

	@Test
	void extremidade_superior() {

		Controle controle = Controle.getInstancia();
		controle.setCampo(new Campo(3, 3));

		Campo campo = controle.getCampo();
		campo.setPosicao(0, 1, Campo.formigueiro);

		controle.setCortador(new Cortador());
		controle.getCortador().cortarTodaGrama();

		String finalCorreto = "g\tf\tg\t\n" + "g\tg\tg\t\n" + "g\tg\tg*\t\n";

		assertEquals(finalCorreto, controle.getHistorico().get(controle.getHistorico().size() - 1).toString());

	}

	@Test
	void extremidade_inferior() {

		Controle controle = Controle.getInstancia();
		controle.setCampo(new Campo(3, 3));

		Campo campo = controle.getCampo();
		campo.setPosicao(2, 1, Campo.formigueiro);

		controle.setCortador(new Cortador());
		controle.getCortador().cortarTodaGrama();

		String finalCorreto = "g\tg\tg\t\n" + "g\tg\tg\t\n" + "g\tf\tg*\t\n";

		assertEquals(finalCorreto, controle.getHistorico().get(controle.getHistorico().size() - 1).toString());
	}
	
	@Test
	void extremidade_recursao() {

		Controle controle = Controle.getInstancia();
		controle.setCampo(new Campo(4,4));

		Campo campo = controle.getCampo();
		campo.setPosicao(1, 1, Campo.formigueiro);
		campo.setPosicao(2, 2, Campo.formigueiro);

		controle.setCortador(new Cortador());
		controle.getCortador().cortarTodaGrama();

		String finalCorreto = "g\tg\tg\tg\t\n" + "g\tf\tg\tg\t\n" + "g\tg\tf\tg\t\n" + "g*\tg\tg\tg\t\n";

		assertEquals(finalCorreto, controle.getHistorico().get(controle.getHistorico().size() - 1).toString());
	}
	
	@Test
	void extremidade_4() {

		Controle controle = Controle.getInstancia();
		controle.setCampo(new Campo(4,4));

		Campo campo = controle.getCampo();
		campo.setPosicao(0, 2, Campo.formigueiro);

		controle.setCortador(new Cortador());
		controle.getCortador().cortarTodaGrama();

		String finalCorreto = "g\tg\tf\tg\t\n" + "g\tg\tg\tg\t\n" + "g\tg\tg\tg\t\n" + "g*\tg\tg\tg\t\n";

		assertEquals(finalCorreto, controle.getHistorico().get(controle.getHistorico().size() - 1).toString());
	}

}
