package escalonador.controle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import escalonador.modelo.Algoritmo;
import escalonador.modelo.Escalonador;
import escalonador.modelo.algoritmos.Preemptivo;
import escalonador.modelo.algoritmos.RoundRobin;
import escalonador.modelo.algoritmos.ShortJobFirst;
import escalonador.visao.paineis.PainelSimulacao;

public class ControladorSimulacao extends Controlador {

	public static final int PREEMPTIVO = 0;
	public static final int ROUND_ROBIN = 1;
	public static final int SHORT_JOB_FIRST = 2;

	private int algoritmo;

	private static ControladorSimulacao instance;
	private PainelSimulacao painel;
	private boolean terminar = false;

	private ControladorSimulacao(PainelSimulacao painelSimulacao, int algoritmo) {
		this.painel = painelSimulacao;
		this.algoritmo = algoritmo;
	}

	public static ControladorSimulacao getInstance(
			PainelSimulacao painelSimulacao, int algoritmo) {

		if (instance == null) {
			instance = new ControladorSimulacao(painelSimulacao, algoritmo);
		}
		if(algoritmo != -1) {
			instance.algoritmo = algoritmo;			
		}
		return instance;
	}

	public void terminar() {
		terminar = true;
	}
	
	public String getNomeAlgoritmo() {
		switch (this.algoritmo) {
		case SHORT_JOB_FIRST:
			return "Short Job First";
		case ROUND_ROBIN:
			return "Round Robin";
		default:
			return "Preemptivo por Prioridades";
		}
	}

	public void simular() {
		Algoritmo algoritmo = null;

		switch (this.algoritmo) {
		case SHORT_JOB_FIRST:
			algoritmo = new ShortJobFirst(processos);
			break;

		case ROUND_ROBIN:
			algoritmo = new RoundRobin(processos);
			break;

		default:
			algoritmo = new Preemptivo(processos);
		}
		
		Escalonador escalonador = new Escalonador(algoritmo);
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.execute(escalonador);

		int timeout = 200;
		while (!escalonador.isTerminado()) {
			if(terminar) {
				timeout = 1;
				escalonador.forcarTermino();
			}
			
			try {					
				Thread.sleep(timeout);
			} catch (Exception e) { }
						
				
			if (algoritmo.getExecutando() != null) {
				painel.setExecutando(algoritmo.getExecutando().getPid() + "");
			} else {
				painel.setExecutando("");
			}
			
				
			
			//painel.setExecutando("");
			painel.setProcessosProntos(algoritmo.getProntos().toArray());
			painel.setProcessosTerminados(algoritmo.getTerminados().toArray());
			painel.setProcessosBloqueados(algoritmo.getBloqueados().toArray());

		}
		
		painel.setProcessosProntos(algoritmo.getProntos().toArray());
		painel.setProcessosTerminados(algoritmo.getTerminados().toArray());
		painel.setProcessosBloqueados(algoritmo.getBloqueados().toArray());
		
		executorService.shutdown();
		
		ControladorRelatorio controladorRelatorio = ControladorRelatorio.getInstance(painel.getJanela().getPainelRelatorio());
		controladorRelatorio.setProcessos();
		painel.getJanela().getPainelRelatorio().porEmFoco();
		
		controladorRelatorio.gerarArquivo(algoritmo);
		
	}
}