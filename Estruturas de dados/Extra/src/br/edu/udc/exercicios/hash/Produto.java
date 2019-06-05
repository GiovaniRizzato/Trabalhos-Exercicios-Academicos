package br.edu.udc.exercicios.hash;

public class Produto {

	private String nome;
	private String descricao;
	private Long quantidade;

	// Contrutores
	public Produto(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
		this.quantidade = (long) 0;
	}

	public Produto(String nome, String descricao, long quantidade) {
		this.nome = nome;
		this.descricao = descricao;
		this.quantidade = quantidade;
	}

	// Metodos para utilização eficaz de estrutura de dados
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + descricao.hashCode();
		result = prime * result + nome.hashCode();
		result = prime * result + quantidade.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Produto other = (Produto) obj;
		// Nome
		if (!nome.equals(other.nome))
			return false;

		// Descrição
		if (!descricao.equals(other.descricao))
			return false;

		return true;
	}

	// Nome
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	// Descrição
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	// Quantidade
	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

}
