package br.edu.udc.ia.mochila;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class SelecaoNatural {

	public final static Integer POPULACAO_INICIAL = 10;
	public final static Integer POPULACAO_MAXIMA = SelecaoNatural.POPULACAO_INICIAL * 3;
	private Float taxaNatalidade = 0.5F;
	private Float taxaMutacao = 0.3F;
	private static final int[] POSICOES_CORTE = { 4, 7, 10, 12 };

	private List<Mochila> populacao;

	public SelecaoNatural() {

		final int pesoMaximo = 10;
		final int valorMaximo = 25;
		final int quantidadeObjetos = 14;
		final Random gerador = new Random();

		final Objeto[] objetos = new Objeto[quantidadeObjetos];
		for (int i = 0; i < quantidadeObjetos; i++) {
			final int peso = Math.abs(gerador.nextInt(pesoMaximo + 1));
			final int valor = Math.abs(gerador.nextInt(valorMaximo + 1));
			objetos[i] = new Objeto(peso, valor);
		}

		Mochila.setObjetos(objetos);
		this.populacao = new LinkedList<>();
	}

	public SelecaoNatural(Objeto[] objetos) {

		Mochila.setObjetos(objetos);
		this.populacao = new LinkedList<>();
	}

	public void adicionarMochila() {

		final Random gerador = new Random();
		final boolean[] relacao = new boolean[Mochila.getQuantidadeObjetos()];

		for (int j = 0; j < Mochila.getQuantidadeObjetos(); j++)
			relacao[j] = gerador.nextBoolean();

		this.populacao.add(new Mochila(relacao));
		Collections.sort(this.populacao);
	}

	public void adicionarMochila(boolean[] relacao) {
		this.populacao.add(new Mochila(relacao));
		Collections.sort(this.populacao);
	}

	public Collection<Mochila> selecionaPais() {

		Float valorTotalPopulacao = 0F;
		for (Mochila mochila : this.populacao)
			valorTotalPopulacao += mochila.getValorTotal();

		Collections.sort(this.populacao);
		Collection<Mochila> pais = new LinkedList<>();
		final Random gerador = new Random();

		for (int i = 0; i < 2; i++) {
			final float roleta = Math.abs(gerador.nextInt(valorTotalPopulacao.intValue()));
			float acomulador = 0F;
			for (Mochila mochila : this.populacao) {

				final int novoAcomulador = (int) (acomulador + mochila.getValorTotal());
				if (novoAcomulador >= roleta) {
					pais.add(mochila);
					break;
				}

				acomulador = novoAcomulador;
			}
		}

		return pais;
	}

	public void mortalidade() {

		Collections.sort(this.populacao);

		final float valorSelecionador = this.populacao.get(0).getValorTotal() / 2;
		List<Mochila> novaPopulacao = new LinkedList<>();

		for (Mochila mochila : populacao) {
			if (mochila.verificarDeficiencia() || novaPopulacao.size() > POPULACAO_MAXIMA)
				continue;

			if (mochila.getValorTotal() >= valorSelecionador)
				novaPopulacao.add(mochila);
			else
				break;
		}

		this.populacao = novaPopulacao;
	}

	public void natalidade() {

		final int numeroGeracoes = (int) (this.populacao.size() * this.taxaNatalidade);
		Queue<Mochila> pais = new LinkedBlockingQueue<>();

		for (int i = 0; i < numeroGeracoes; i++)
			pais.addAll(this.selecionaPais());

		while (pais.size() >= 2)
			this.populacao.addAll(pais.poll().crossOver(pais.poll(), POSICOES_CORTE));
	}

	public void mutacao() {

		final Random gerador = new Random();
		final int qtdPopulacao = this.populacao.size();
		final int numeroMutacoes = (int) (this.populacao.size() * this.taxaMutacao);

		for (int i = 0; i < numeroMutacoes; i++)
			this.populacao.get(gerador.nextInt(qtdPopulacao)).mutar(1);
	}

	public void soluciona() {

		for (int i = this.populacao.size(); i < SelecaoNatural.POPULACAO_INICIAL; i++)
			this.adicionarMochila();

		int numeroGeracao = 0;
		int numeroRepeticaoMelhor = 0;
		Mochila fitiestAnterior = null;

		while (numeroRepeticaoMelhor < 7) {
			this.natalidade();
			this.mortalidade();

			this.mutacao();
			this.mortalidade();
			Collections.sort(this.populacao);

			System.out.println(numeroGeracao + ": " + this.populacao);

			Mochila fitiestAtual = this.populacao.get(0);

			if (fitiestAtual.equals(fitiestAnterior))
				numeroRepeticaoMelhor++;
			else {
				fitiestAnterior = (Mochila) fitiestAtual.clone();
				numeroRepeticaoMelhor = 0;
			}

			numeroGeracao++;
		}
	}
}
