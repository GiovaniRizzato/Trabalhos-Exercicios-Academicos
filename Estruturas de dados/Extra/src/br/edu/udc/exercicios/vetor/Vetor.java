package br.edu.udc.exercicios.vetor;

import java.util.Arrays;

public class Vetor <T>{
	private int quantidade = 0;
	private Object[] object =new  Object[100];
	
	public void adicionar(T aluno){
		this.verificaCapacidade();
		
		this.object[quantidade] =aluno;
		this.quantidade++;
		
	}
	
	private void verificaCapacidade(){
		if(this.quantidade == this.object.length){
			final Object[] novoArray = new Object[this.object.length *2];
			
			for(int i=0;i<this.object.length;i++){
				novoArray[i] = this.object[i]; 
			}
			this.object = novoArray;
		}
		
		
	}
	
	
	
	public void adicionar(int posicao,T aluno){
		if(!this.posicaoOcupada(posicao) && posicao != this.quantidade ){
			
			throw new IndexOutOfBoundsException("posicao invalida");
		}
		this.verificaCapacidade();
		for(int i = this.quantidade-1; i>posicao;i-=1){
			this.object[i+1]=this.object[i];
			
		}
		this.object[posicao] = aluno;
		this.quantidade++;
		
	}
	
	private boolean posicaoOcupada(int posicao){
		return posicao >= 0 &&  posicao < this.quantidade;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public T obtem (int posicao){
		if(!this.posicaoOcupada(posicao))
			throw new IndexOutOfBoundsException("posicao invalida");
		return (T) this.object[posicao];
	}
	
	
	
	
	
	public void remove (int posicao){
		if(!this.posicaoOcupada(posicao)){
			throw new  IndexOutOfBoundsException("posicao invalida");
		}
		for(int i=posicao;i<this.quantidade-1;i++){
			this.object[i] = this.object[i+1];
			
		}
		this.quantidade--;
		
	}
	
	
	
	
	public boolean contem(T aluno){
		for(int i=0 ; i < this.quantidade; i++)
			if(aluno.equals (this.object[i])){
				return true;
			}
		return false;
	}
	public int tamanho(){
		return this.quantidade;
	}
	public String toString(){
		return Arrays.toString(this.object);
	}

}
