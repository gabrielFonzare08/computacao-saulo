package escalonador.controle;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import escalonador.modelo.Algoritmo;
import escalonador.modelo.Escalonador;
import escalonador.modelo.Processo;
import escalonador.modelo.algoritmos.Preemptivo;
import escalonador.modelo.algoritmos.RoundRobin;
import escalonador.modelo.algoritmos.ShortJobFirst;
import escalonador.visao.paineis.PainelSimulacao;

public class ControladorSimulacao extends Controlador {
	
	public static final int PREEMPTIVO		= 0;
	public static final int ROUND_ROBIN		= 1;
	public static final int SHORT_JOB_FIRST	= 2;
	
	public static final int TIMEOUT			= 1000;
	public int algoritmo;
	
	private static ControladorSimulacao instance;
	private PainelSimulacao painelSimulacao;
	private boolean terminar = false;
	
	
	private ControladorSimulacao(PainelSimulacao painelSimulacao, int algoritmo) {
		this.painelSimulacao = painelSimulacao;
		this.algoritmo = algoritmo;
	}
	
	public static ControladorSimulacao getInstance(PainelSimulacao painelSimulacao, int algoritmo) {
		
		if(instance == null) {
			instance = new ControladorSimulacao(painelSimulacao, algoritmo);
		}
		instance.algoritmo = algoritmo;
		return instance;
	}
	
	public void terminar() {
		terminar = true;
	}
	
	public void simular(List<Processo> processos) {
		Algoritmo algoritmo = null;
		
		switch (this.algoritmo) {
			case SHORT_JOB_FIRST:
				algoritmo = new ShortJobFirst(processos);
				break;
				
			case ROUND_ROBIN:
				algoritmo = new RoundRobin(processos);
				break;
	
			default: algoritmo = new Preemptivo(processos);
		}
		
		Escalonador escalonador = new Escalonador(algoritmo);
		
		for(int i = 0; i < 33; i++) {
			Thread t = 	new Thread(escalonador);
			t.start();
			while (!escalonador.isTerminado()) {
				if(algoritmo.getExecutando() != null) {
					painelSimulacao.setExecutando(algoritmo.getExecutando().getPid() + "");				
				}
				
				painelSimulacao.setProcessosProntos(algoritmo.getProntos().toArray());
				painelSimulacao.setProcessosTerminados(algoritmo.getTerminados().toArray());
				painelSimulacao.setProcessosBloqueados(algoritmo.getBloqueados().toArray());
				
			}
			
			t = null;
		}
	}
}