package escalonador.modelo;

import java.util.List;

public class Escalonador implements Runnable {
	
	private Algoritmo algoritmo;
	private boolean terminado;
	
	public Escalonador(Algoritmo algoritmo) {
		this.algoritmo = algoritmo;
	}
	
	public void forcarTermino() {
		Algoritmo.TIMEOUT = 1; 
	}
	
	public List<Processo> getTodosProcessos() {
		return algoritmo.processos;
	}
	
	public List<Processo> getProcessosBloqueados() {
		return algoritmo.bloqueados;
	}
	
	public List<Processo> getProcessosProntos() {
		return algoritmo.prontos;
	}
	
	public Processo getProcessoExecutando() {
		return algoritmo.executando;
	}
	
	public boolean isTerminado() {
		return terminado;
	}
	
	@Override
	public void run() {
		terminado = false;
		algoritmo.escalonar();			
		
		
		terminado = true;
	}
}