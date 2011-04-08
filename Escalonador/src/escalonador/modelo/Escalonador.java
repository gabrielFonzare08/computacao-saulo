package escalonador.modelo;

import java.util.List;

public class Escalonador implements Runnable {
	
	private Algoritmo algoritmo;
	
	public Escalonador(Algoritmo algoritmo) {
		this.algoritmo = algoritmo;
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
	
	@Override
	public void run() {
		algoritmo.run();
	}
}