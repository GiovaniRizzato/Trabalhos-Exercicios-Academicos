package br.edu.udc.ia.contador;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Cortador {

	public class Posicao {
		public int x;
		public int y;

		public Posicao() {
			this.x = 0;
			this.y = 0;
		}

		public Posicao(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		protected Object clone() {
			Posicao clone = new Posicao();
			clone.x = this.x;
			clone.y = this.y;
			return clone;
		}
	};

	private Posicao posicao;
	private boolean indoLeste;

	private Queue<Posicao> memoriaMovimentos;

	public Cortador() {
		this.memoriaMovimentos = new LinkedBlockingQueue<>();
		this.posicao = new Posicao();
		this.indoLeste = true;
	}

	public Posicao getPosicao() {
		return (Posicao) this.posicao.clone();
	}

	private void mover(int deslocamentoX, int deslocamentoY) {
		this.posicao.x += deslocamentoX;
		this.posicao.y += deslocamentoY;

		this.memoriaMovimentos.add(new Posicao(deslocamentoX, deslocamentoY));

		Controle.getInstancia().atualiza();
	}

	private void limpaMemoriaMovimentos() {
		this.memoriaMovimentos = new LinkedBlockingQueue<>();
	}

	void cortarGrama() {
		Campo campo = Controle.getInstancia().getCampo();

		if (campo.getCloneMatriz()[posicao.x][posicao.y] == Campo.gramaAlta) {
			campo.setPosicao(posicao.x, posicao.y, Campo.gramaBaixa);

			Controle.getInstancia().atualiza();
		}
	}

	void irInicio() {
		// TODO ir para o canto superior esquerdo
	}

	void cortarTodaGrama() {

		Campo campo = Controle.getInstancia().getCampo();
		this.irInicio();

		this.indoLeste = true;

		for (int j = 0; j < campo.tamanhoX; j++) {

			this.cortarGrama();
			while (this.posicao.y < campo.tamanhoY && this.posicao.y >= 0) {

				final int posicaoDestino = this.posicao.y + ((this.indoLeste) ? 1 : -1);

				if (posicaoDestino >= campo.tamanhoY || posicaoDestino < 0)// Se for o final do campo
					break;// WHILE

				if (campo.getCloneMatriz()[this.posicao.x][posicaoDestino] == Campo.formigueiro)
					this.desvia(false, false, false, false);
				else
					this.mover(0, ((this.indoLeste) ? 1 : -1));

				this.cortarGrama();
			}

			this.mudaLane();
		}
	}

	void desvia(boolean sulBloqueado, boolean norteBloqueado, boolean lesteBloqueado, boolean oesteBloqueado) {

		// TODO desenvolver uma melhor exceção para este caso
		if (lesteBloqueado && oesteBloqueado || lesteBloqueado && oesteBloqueado)
			throw new IllegalStateException("Campo tem uma 'barreira' de formigueiros impossibilidando corta-lo todo");

		Campo campo = Controle.getInstancia().getCampo();

		final boolean estaLimiteEsquerdo = this.posicao.y <= 0;
		final boolean estaLimiteDireito = this.posicao.y >= campo.tamanhoY;
		final int direçãoHorizontal = (estaLimiteEsquerdo && !estaLimiteDireito) ? 1 : -1;

		final boolean estaLimiteSuperior = this.posicao.x <= 0;
		final boolean estaLimiteInferior = this.posicao.x >= campo.tamanhoX;
		final int direçãoVertical = (estaLimiteSuperior && !estaLimiteInferior) ? 1 : -1;

		while (campo.getCloneMatriz()[this.posicao.x + direçãoVertical][this.posicao.y] == Campo.formigueiro)
			this.mover(0, -direçãoHorizontal);

		this.mover(direçãoVertical, 0);

		int auxY = this.posicao.y * direçãoHorizontal;
		int destinoEmY = auxY + 2;

		while (auxY < destinoEmY) {
			if (campo.getCloneMatriz()[this.posicao.x][this.posicao.y + direçãoHorizontal] == Campo.formigueiro)
				desvia(false, false, false, false);
			else
				this.mover(0, direçãoHorizontal);

			auxY = (this.indoLeste) ? this.posicao.y : -this.posicao.y;
		}

		// Se bater na parede a frente, desviar pelo outro lado
		while (campo.getCloneMatriz()[posicao.x - direçãoVertical][posicao.y] == Campo.formigueiro)
			this.mover(0, direçãoHorizontal);

		this.mover(-direçãoVertical, 0);

		while (campo.getCloneMatriz()[this.posicao.x][this.posicao.y - direçãoHorizontal] != Campo.formigueiro)
			this.mover(0, -direçãoHorizontal);
	}

	void mudaLane() {

		Campo campo = Controle.getInstancia().getCampo();
		if (this.posicao.x + 1 >= campo.tamanhoX || this.posicao.y >= campo.tamanhoY)
			return;

		while (campo.getCloneMatriz()[this.posicao.x + 1][this.posicao.y] == Campo.formigueiro)
			this.mover(0, (indoLeste) ? 1 : -1);

		this.mover(1, 0);
		this.indoLeste = !this.indoLeste;
	}

}
