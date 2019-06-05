package br.edu.udc.ia.mochila;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

public class Mochila implements Comparable<Mochila> {

	private static Objeto[] relacaObjetos;
	public final static Integer PESO_MAXIMO = 25;

	private boolean[] conteudo;

	public Mochila(boolean[] conteudo) {
		this.conteudo = conteudo.clone();
	}

	public static void setObjetos(Objeto[] relacao) {
		if (Mochila.relacaObjetos == null)
			Mochila.relacaObjetos = relacao;
		else
			throw new IllegalStateException("Relação de objetos já 'setada' anteriormente");
	}

	public static int getQuantidadeObjetos() {
		return Mochila.relacaObjetos.length;
	}

	public boolean verificarDeficiencia() {

		int acomulador = 0;

		for (int i = 0; i < Mochila.relacaObjetos.length; i++)
			acomulador += (conteudo[i]) ? Mochila.relacaObjetos[i].getPeso() : 0;

		return acomulador > Mochila.PESO_MAXIMO;
	}

	public void mutar(int numeroGens) {

		Random gerador = new Random();

		for (int i = 0; i < numeroGens; i++) {
			int posicao = gerador.nextInt(this.conteudo.length);
			this.conteudo[posicao] = !this.conteudo[posicao];
		}
	}

	public int getValorTotal() {

		Integer acomuladorValor = 0;

		for (int i = 0; i < Mochila.relacaObjetos.length; i++)
			if (this.conteudo[i])
				acomuladorValor += Mochila.relacaObjetos[i].getValor();

		return acomuladorValor;
	}

	public Collection<Mochila> crossOver(Mochila outro, int[] posicoesCorte) {

		final Collection<Mochila> filhos = new LinkedList<>();
		final Mochila filho1 = new Mochila(this.conteudo.clone());
		final Mochila filho2 = new Mochila(outro.conteudo.clone());

		int posicaoVetorCorte = 0;
		int posicaoProximoCorte = posicoesCorte[posicaoVetorCorte];
		for (int i = 0; i < this.conteudo.length; i++) {

			filho1.conteudo[i] = outro.conteudo[i];
			filho2.conteudo[i] = this.conteudo[i];

			if (i >= posicaoProximoCorte) {
				final int proximaPosicaoI = posicaoVetorCorte + 1;
				if (proximaPosicaoI >= posicoesCorte.length)
					break;
				else
					i = posicoesCorte[proximaPosicaoI];

				final int proximaPosicaoVetorCorte = posicaoVetorCorte + 2;
				if (proximaPosicaoVetorCorte >= posicoesCorte.length)
					posicaoProximoCorte = this.conteudo.length;
				else {
					posicaoProximoCorte = proximaPosicaoVetorCorte;
					posicaoVetorCorte += 2;
				}
			}
		}

		filhos.add(filho1);
		filhos.add(filho2);
		return filhos;
	}

	@Override
	public int compareTo(Mochila outra) {
		return (int) (outra.getValorTotal() - this.getValorTotal());
	}

	@Override
	public String toString() {

		StringBuffer acomulador = new StringBuffer();
		acomulador.append("[");

		for (int i = 0; i < this.conteudo.length; i++) {
			acomulador.append(this.conteudo[i] ? 1 : 0);
			acomulador.append(",");
		}

		acomulador.deleteCharAt(acomulador.length() - 1);
		acomulador.append("]");
		acomulador.append(this.getValorTotal());

		return acomulador.toString();
	}

	@Override
	protected Object clone() {
		return new Mochila(this.conteudo.clone());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Mochila other = (Mochila) obj;
		if (!Arrays.equals(conteudo, other.conteudo))
			return false;
		return true;
	}
}
