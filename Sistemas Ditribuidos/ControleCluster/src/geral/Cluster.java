package geral;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Cluster {

	private volatile List<Processo> listaProcessos;
	private volatile boolean eleicaoAtiva = false;
	private volatile Processo coordenador;

	public Cluster() {
		this.listaProcessos = new CopyOnWriteArrayList<>();
	}

	public int getQtdProcessosVivos() {
		int contagem = 0;
		for (Processo p : this.listaProcessos) {
			if (p.isVivo()) {
				contagem++;
			}
		}
		return contagem;
	}

	public void matarCoordenador() {
		this.coordenador.setVivo(false);
		System.out.println(Config.DATE_FORMATTER.format(new Date()) + " - Matou o coordenador");
		System.out.println("---");
		System.out.println();
	}

	public void getCoordenadorVivo() {
		boolean notifyed = false;
		if (this.listaProcessos.size() >= 2) {
			do {
				Processo p = this.listaProcessos.get(new Random().nextInt(this.listaProcessos.size()));
				if (!p.isCoordenador()) {
					if (!p.verificarCoordenadorVivo()) {
						if (!isEleicaoAtiva()) {
							System.out.println(
									Config.DATE_FORMATTER.format(new Date()) + " - Iniciou um processo de eleição.");
							this.eleicaoAtiva = true;
							p.eleicao();
						}
					}
					notifyed = true;
				}
			} while (!notifyed);
		}
	}

	public void addProcesso(Processo p) {
		this.listaProcessos.add(p);
	}

	public void matarProcessoAleatorio(boolean incluirCoordenador) {
		boolean matou = false;
		do {
			Processo p = this.listaProcessos.get(new Random().nextInt(this.listaProcessos.size()));
			if (p.isCoordenador()) {
				if (incluirCoordenador) {
					p.setVivo(false);
					matou = true;
				}
			} else {
				p.setVivo(false);
				matou = true;
			}
		} while (!matou);

		System.out.println(Config.DATE_FORMATTER.format(new Date()) + " - Matou um processo aleatÃ³rio");
		System.out.println(toStringProcessos());
		System.out.println("---");
		System.out.println();
	}

	public String toStringProcessos() {
		final StringBuffer resultado = new StringBuffer();
		for (Processo p : this.listaProcessos) {
			if (p.isVivo()) {
				resultado.append(p);
				resultado.append("\n");
			}
		}
		return resultado.toString();
	}

	/*
	 * Get e Set
	 */
	public List<Processo> getListaProcessos() {
		return this.listaProcessos;
	}

	public void setListaProcessos(List<Processo> listaProcessos) {
		this.listaProcessos = listaProcessos;
	}

	public Boolean isEleicaoAtiva() {
		return this.eleicaoAtiva;
	}

	public void setEleicaoAtiva(Boolean eleicaoAtiva) {
		this.eleicaoAtiva = eleicaoAtiva;
	}

	public Processo getCoordenador() {
		return this.coordenador;
	}

	public void setCoordenador(Processo coordenador) {
		this.coordenador = coordenador;
	}
}
