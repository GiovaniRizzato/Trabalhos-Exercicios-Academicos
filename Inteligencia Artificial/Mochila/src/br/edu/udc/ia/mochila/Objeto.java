package br.edu.udc.ia.mochila;

public class Objeto {

	public Objeto(int peso, int valor) {
		super();
		this.peso = peso;
		this.valor = valor;
	}

	private Integer peso;
	private Integer valor;

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public float getEficiencia() {
		return this.valor.floatValue() / this.peso.floatValue();
	}
}
