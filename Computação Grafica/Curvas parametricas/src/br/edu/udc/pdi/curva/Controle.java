package br.edu.udc.pdi.curva;

public class Controle {

	private static Controle instancia;

	private Controle() {
	}

	public static Controle getInstancia() {
		if (Controle.instancia == null)
			Controle.instancia = new Controle();

		return Controle.instancia;
	}
	
	public static void main(String[] args) {
	}
}
