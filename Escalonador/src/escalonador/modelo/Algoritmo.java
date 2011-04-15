package escalonador.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstrata para representar um algoritmo. Uma lista de classes concretas
 * que herdam de {@link Algoritmo} são:
 * <ul>
 * <li>{@linkplain RoundRobin}</li>
 * <li>{@linkplain ShortJobFirst}</li>
 * <li>{@linkplain Preemptive}</li>
 * </ul>
 * */
public abstract class Algoritmo {

	private int tempoCpuOciosa;
	private int tempoSimulacao;

	public void incrementaTempoCpuOciosa(){
		this.tempoCpuOciosa++;
	}
	
	public void incrementaTempoSimulacao() {
		this.tempoSimulacao++;
	}

	public int getTempoSimulacao() {
		return tempoSimulacao;
	}

	public void setTempoSimulacao(int tempoSimulacao) {
		this.tempoSimulacao = tempoSimulacao;
	}

	public int getTempoCpuOciosa() {
		return tempoCpuOciosa;
	}

	public void setTempoCpuOciosa(int tempoCpuOciosa) {
		this.tempoCpuOciosa = tempoCpuOciosa;
	}

	/**
	 * Lista que cont&eacute;m todos os processos. Independente do seu estado.
	 * */
	protected List<Processo> processos;

	/**
	 * Lista que cont&eacute;m somente os processos no estado
	 * {@link EstadoProcesso#PRONTO}.
	 * */
	protected List<Processo> prontos;

	/**
	 * Lista que cont&eacute;m somente os processos no estado
	 * {@link EstadoProcesso#BLOQUEADO}.
	 * */
	protected List<Processo> bloqueados;

	/**
	 * Lista que cont&eacute;m somente os processos no estado
	 * {@link EstadoProcesso#TERMINADO}.
	 * */
	protected List<Processo> terminados;

	/**
	 * Vari&aacute;vel que cont&eacute;m o processo no estado
	 * {@link EstadoProcesso#EXECUTANDO} na CPU.
	 * */
	protected Processo executando;
	protected Processo atual;

	public Algoritmo(List<Processo> processos) {
		this.processos = new ArrayList<Processo>(processos);
		this.prontos = new ArrayList<Processo>(processos);
		this.bloqueados = new ArrayList<Processo>();
		this.terminados = new ArrayList<Processo>();

		for (Processo processo : prontos) {
			processo.setEstado(EstadoProcesso.PRONTO);
		}
	}

	/**
	 * A implementa&ccedil;&atilde;o do algoritmo deve-se dar nesse método.
	 * */

	public abstract void escalonar();
	
	public final void esperar() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Processo getExecutando() {
		return executando;
	}

	public List<Processo> getProntos() {
		return prontos;
	}

	public List<Processo> getBloqueados() {
		return bloqueados;
	}

	public List<Processo> getProcessos() {
		return processos;
	}

	public List<Processo> getTerminados() {
		return terminados;
	}
}