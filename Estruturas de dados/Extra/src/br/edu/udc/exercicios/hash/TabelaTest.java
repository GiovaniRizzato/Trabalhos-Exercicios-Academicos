package br.edu.udc.exercicios.hash;

/**
Crie uma classe "Produto" com os seguintes atributos:
String nome;
String descricao;
Long quantidade;

Utilizando a estrutura de dados baseado em Tabela de Espalhamento crie pelo menos 3 testes unitários para cada método;
Garanta que os produtos fiquem bem espalhados na Tabela;
 */

import static org.junit.Assert.*;

import org.junit.Test;

import br.edu.udc.exercicios.vetor.Vetor;

public class TabelaTest {
	
	private Produto detergenteAjax = new Produto("Ajax", "Detergente", 10);
	private Produto sabaoemPoAjax = new Produto("Ajax", "Sabão em pó", 10);
	private Produto detergenteYpe = new Produto("Ype", "Detergente");
	private Produto refrigerante = new Produto("Coca", "Refrigerante");
	private Produto acucar = new Produto("União", "Acucar");
	private Produto carro = new Produto("Gol", "Carro");

	@Test
	public void testarEspalahamento() {
		Tabela<Produto> estoque = new Tabela<>();
		
		estoque.adiciona(detergenteAjax);
		estoque.adiciona(sabaoemPoAjax);
		estoque.adiciona(detergenteYpe);
		estoque.adiciona(refrigerante);
		estoque.adiciona(acucar);
		estoque.adiciona(carro);
		
		Vetor<Produto> listaProdutos = new Vetor<>();
	}
	
	/**@Test
	public void testeImprecaohasCode() {
		final Veiculo veiculo1 = new Veiculo();
		veiculo1.setCor("azul");
		veiculo1.setConversivel(false);
		veiculo1.setModelo("brazilia");
		System.out.println(veiculo1);

		final Veiculo veiculo2 = new Veiculo();
		veiculo2.setCor("azul");
		veiculo2.setConversivel(false);
		veiculo2.setModelo("outro carro");
		System.out.println(veiculo2);
	}*/

}
