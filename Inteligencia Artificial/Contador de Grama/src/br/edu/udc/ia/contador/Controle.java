package br.edu.udc.ia.contador;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import br.edu.udc.ia.contador.Campo.Situação;

public class Controle {

	private static Controle instancia;

	private Controle() {
		this.estadosCampo = new LinkedList<>();
	}

	public static Controle getInstancia() {

		if (Controle.instancia == null)
			Controle.instancia = new Controle();

		return Controle.instancia;
	}

	private Campo campo;
	private Cortador cortador;

	private List<Situação> estadosCampo;

	public Campo getCampo() {
		return this.campo;
	}

	public void setCampo(Campo campo) {
		this.campo = campo;
	}

	public Cortador getCortador() {
		return this.cortador;
	}

	public void setCortador(Cortador cortador) {
		this.cortador = cortador;
	}

	public void atualiza() {
		this.estadosCampo.add(this.campo.getSituaçãoAtual());
	}

	public List<Situação> getHistorico() {
		return this.estadosCampo;
	}

	public static void main(String[] args) {

		Controle controle = Controle.getInstancia();
		Scanner scaner = new Scanner(System.in);
		Random random = new Random();

		System.out.println("Qual a dimensão do campo?");
		System.out.print("Em X: ");
		final int tamanhoX = scaner.nextInt();
		System.out.print("Em Y: ");
		final int tamanhoY = scaner.nextInt();

		controle.setCampo(new Campo(tamanhoX, tamanhoY));
		Campo campo = controle.getCampo();

		System.out.print("Quantidade de 'gramas altas'? ");
		final int qtdGramas = scaner.nextInt();

		for (int i = 0; i < qtdGramas; i++) {
			int x = random.nextInt(tamanhoX), y = random.nextInt(tamanhoY);
			while (campo.getCloneMatriz()[x][y] == Campo.gramaAlta) {
				x = random.nextInt(tamanhoX);
				y = random.nextInt(tamanhoY);
			}
			campo.setPosicao(x, y, Campo.gramaAlta);
		}

		System.out.print("Quantidade de formigueiros? ");
		final int qtdFormigueiros = scaner.nextInt();
		for (int i = 0; i < qtdFormigueiros; i++) {
			System.out.print("Em X: ");
			final int x = scaner.nextInt();
			System.out.print("Em Y: ");
			final int y = scaner.nextInt();

			campo.setPosicao(x, y, Campo.formigueiro);
		}

		controle.setCortador(new Cortador());
		controle.getCortador().cortarTodaGrama();

		for (Campo.Situação situação : controle.estadosCampo) {
			System.out.println(situação);
		}

		scaner.close();
	}
}
