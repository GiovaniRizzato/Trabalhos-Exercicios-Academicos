package br.edu.udc.exercicios.hash;

import br.edu.udc.exercicios.vetor.Vetor;

public class Tabela<T> {
	private Vetor<Vetor<T>> tabela = new Vetor<Vetor<T>>();
	private final short TAMANHO_MINIMO = 10;
	private int quantidade = 0;

	public Tabela() {
		for (int i = 0; i < TAMANHO_MINIMO; i++) {
			final Vetor<T> lista = new Vetor<T>();
			this.tabela.adicionar(lista);
		}
	}

	private void redimencionarTabela(int novaCapacidade) {
		final Vetor<T> objetos = this.todas();
		this.tabela = new Vetor<>();

		for (int i = 0; i < novaCapacidade; i++) {
			this.tabela.adicionar(new Vetor<T>());
		}

		for (int i = 0; i < objetos.tamanho(); i++) {
			final T objeto = objetos.obtem(i);
			final int indice = this.calculaIndice(objeto);
			this.tabela.obtem(indice).adicionar(objeto);
		}
	}

	private void verificaCapacidade() {
		int capacidade = this.tabela.tamanho();
		final int carga = this.tamanho() / capacidade;

		if (carga >= 0.75) {
			this.redimencionarTabela(capacidade * 2);
		}
		if (carga <= 0.25) {
			final int novaCapacidade = capacidade / 2;
			if (novaCapacidade > TAMANHO_MINIMO) {
				this.redimencionarTabela(novaCapacidade);
			}

		}

	}

	private int calculaIndice(T objeto) {
		int codigoDeEspalhamento = objeto.hashCode();
		Math.abs(codigoDeEspalhamento);

		return codigoDeEspalhamento % this.tabela.tamanho();
	}

	public void remove(T objeto) {
		if (this.contem(objeto)) {
			final int indice = this.calculaIndice(objeto);
			final Vetor<T> lista = this.tabela.obtem(indice);
			for (int i = 0; i < lista.tamanho(); i++) {
				if (lista.obtem(i).equals(objeto)) {
					lista.remove(i);
					break;

				}
			}
			this.quantidade--;
			this.verificaCapacidade();
		} else {
			throw new RuntimeException("Object not part of the list");
		}
	}

	public void adiciona(T objeto) {
		if (!this.contem(objeto)) {
			this.verificaCapacidade();

			final int indice = this.calculaIndice(objeto);
			this.tabela.obtem(indice).adicionar(objeto);
			this.quantidade++;

		}
	}

	public boolean contem(T objeto) {
		final int indice = this.calculaIndice(objeto);
		return this.tabela.obtem(indice).contem(objeto);
	}

	public Vetor<T> todas() {
		final Vetor<T> todosObjetos = new Vetor<T>();

		for (int i = 0; i < this.tabela.tamanho(); i++) {
			final Vetor<T> palavras = this.tabela.obtem(i);

			for (int j = 0; j < palavras.tamanho(); j++) {
				final T objeto = todosObjetos.obtem(j);
				todosObjetos.adicionar(objeto);
			}
		}
		return todosObjetos;
	}

	public int tamanho() {

		return this.quantidade;
	}

	public void imprimir() {

		System.out.println("quantidade" + this.tabela.tamanho());
		for (int i = 0; i < this.tabela.tamanho(); i++) {

			final Vetor<T> objetos = tabela.obtem(i);

			if (objetos.tamanho() == 0)
				continue;
			System.out.println("o codigo ;" + i + " total " + objetos.tamanho());

			for (int j = 0; j < objetos.tamanho(); j++) {
				// System.out.println(palavras.obtem(j));

			}
		}

	}

}
