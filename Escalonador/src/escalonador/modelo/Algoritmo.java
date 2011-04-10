package escalonador.modelo;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe abstrata para representar um algoritmo.
 * Uma lista de classes concretas que herdam de {@link Algoritmo} são:
 * <ul>
 * <li>{@linkplain RoundRobin}</li>
 * <li>{@linkplain ShortJobFirst}</li>
 * <li>{@linkplain Preemptive}</li>
 * </ul>
 * */
public abstract class Algoritmo implements Runnable {
	
	/**
	 * Lista que cont&eacute;m todos os processos.
	 * Independente do seu estado.
	 * */
	protected List<Processo> processos;
	
	
	/**
	 * Lista que cont&eacute;m somente os processos no estado {@link EstadoProcesso#PRONTO}.
	 * */
	protected List<Processo> prontos;
	
	/**
	 * Lista que cont&eacute;m somente os processos no estado {@link EstadoProcesso#BLOQUEADO}.
	 * */
	protected List<Processo> bloqueados;
	
	/**
	 * Lista que cont&eacute;m somente os processos no estado {@link EstadoProcesso#TERMINADO}.
	 * */
	protected List<Processo> terminados;
	
	/**
	 * Vari&aacute;vel que cont&eacute;m o processo no estado {@link EstadoProcesso#EXECUTANDO} 
	 * na CPU.
	 * */
	protected Processo executando;
	
	
	
	
	public Algoritmo(List<Processo> processos) {
		this.processos = new ArrayList<Processo>();	
		this.prontos = new ArrayList<Processo>();
		this.bloqueados = new ArrayList<Processo>();
		
		for(Processo processo : processos) {
			processo.setEstado(EstadoProcesso.EXECUTANDO);
			this.processos.add(processo);
			this.prontos.add(processo);
		}
	}
	
	@Override
	public void run() {
		synchronized (this) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		escalonar();
	}
	
	/**
	 * A implementa&ccedil;&atilde;o do algoritmo deve-se
	 * dar nesse método.
	 * */
	public abstract void escalonar();
	
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
}