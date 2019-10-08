package geral;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Processo {

	private boolean vivo = true;
	private int id;
	private Cluster referenciaCluster;

	public Processo(Cluster cluster) {
		this.referenciaCluster = cluster;
		this.referenciaCluster.addProcesso(this);
		this.gerarId();
	}

	public Processo(Cluster cluster, boolean iniciarExecucao) {
		this(cluster);
	}

	public synchronized void eleicao() {
		final List<Processo> processosVivos = new ArrayList<>();
		for (Processo p : this.referenciaCluster.getListaProcessos()) {
			
			if (p.getId() > this.id) {
				boolean pong = ping(p);
				if (pong)
					processosVivos.add(p);
			}
		}
		if (processosVivos.isEmpty()) {
			this.referenciaCluster.setCoordenador(this);
			this.atualizarCoordenador(this);
			this.referenciaCluster.setEleicaoAtiva(false);
		} else
			for (Processo processo : processosVivos)
				processo.eleicao();
	}

	public synchronized void atualizarCoordenador(Processo novoCoordenador) {
		System.out.println(Config.DATE_FORMATTER.format(new Date()) + " - Novo coordenador com o PID:" + novoCoordenador.getId());
		this.referenciaCluster.setCoordenador(novoCoordenador);
	}

	public boolean ping(Processo processo) {
		return processo.pong();
	}

	public boolean pong() {
		return isVivo();
	}

	public void gerarId() {
		boolean idValido = false;
		int id = 0;
		do {
			idValido = true;
			final Random random = new Random();
			id = random.nextInt(Config.ID_MAX_PROCESSO);
			for (Processo p : this.referenciaCluster.getListaProcessos()) {
				if (p.getId() == id)
					idValido = false;
			}
		} while (!idValido);
		this.id = id;
	}

	public String toStringListaProcessos() {
		final StringBuffer resultado = new StringBuffer();
		resultado.append("[");

		boolean isPrimeiro = true;

		for (Processo p : this.referenciaCluster.getListaProcessos()) {
			if (isPrimeiro)
				isPrimeiro = false;
			else
				resultado.append(", ");

			resultado.append(p.getId());
		}

		resultado.append("]");
		return resultado.toString();
	}

	public boolean verificarCoordenadorVivo() {
		System.out.println(Config.DATE_FORMATTER.format(new Date()) + " - Processo verifica se coordenador esta vivo.");
		return this.referenciaCluster.getCoordenador().isVivo();
	}

	@Override
	public String toString() {
		return "Processo " + this.id + (isCoordenador() ? "\t(C)" : "\t( )");
	}

	/*
	 * Get e Set
	 */
	public boolean isVivo() {
		return this.vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isCoordenador() {
		return this.referenciaCluster.getCoordenador() == this;
	}

	public Cluster getCluster() {
		return referenciaCluster;
	}

	public void setCluster(Cluster cluster) {
		this.referenciaCluster = cluster;
	}
}
