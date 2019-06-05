package br.edu.udc.exercicios.vetor;

/**
Crie uma classe que represente um "Veiculo" contendo "marca" e "modelo" do tipo String;
Crie um teste unitário com os seguintes cenários utilizando a sua "ListaGenerica" (cada um @Test):
Adicione 10.000.000 Veiculos calculando o tempo do algoritmo;
Adicione 10.000.000 Veiculos e depois adicione mais 1 Veiculo na posição 1000, calculando o tempo do algoritmo;
Desenvolva os mesmos cenários, utilizando a classe da API Java, "ArrayList" e compare o desempenho; 
*/

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class VetorTest {
	
	@SuppressWarnings("unused")
	private class Veiculo{
		
		public String marca;
		public String modelo;
		
		public Veiculo(String marca, String modelo){
			this.marca = marca;
			this.modelo = modelo;
		}
	}
	
	//VETOR
	@Test
	public void vetorAdicionaDezMilhoes(){
		Vetor<Veiculo> testado = new Vetor<>();
		Veiculo veiculoGenerico = new Veiculo("Generico", "Generico");
		
		for(int i=0; i<10000000; i++){
			testado.adicionar(veiculoGenerico);			
		}
		
		assertEquals("Hue", 10000000, testado.tamanho());
	}
	
	@Test
	public void vetorAdicionaPocicaoMil(){
		Vetor<Veiculo> testado = new Vetor<>();
		Veiculo veiculoGenerico = new Veiculo("Generico", "Generico");
		
		for(int i=0; i<10000000; i++){
			testado.adicionar(veiculoGenerico);			
		}
		
		testado.adicionar(1000, veiculoGenerico);
		
		assertEquals("Hue", 10000001, testado.tamanho());
	}
	
	//ARRAY LIST
	@Test
	public void arrayListAdicionaDezMilhoes(){
		ArrayList<Veiculo> testado = new ArrayList<>();
		Veiculo veiculoGenerico = new Veiculo("Generico", "Generico");
		
		for(int i=0; i<10000000; i++){
			testado.add(veiculoGenerico);			
		}
		
		assertEquals("Hue", 10000000, testado.size());
	}
	
	@Test
	public void arrayListAdicionaPocicaoMil(){
		ArrayList<Veiculo> testado = new ArrayList<>();
		Veiculo veiculoGenerico = new Veiculo("Generico", "Generico");
		
		for(int i=0; i<10000000; i++){
			testado.add(veiculoGenerico);			
		}
		
		testado.add(1000, veiculoGenerico);
		
		assertEquals("Hue", 10000001, testado.size());
	}
}
