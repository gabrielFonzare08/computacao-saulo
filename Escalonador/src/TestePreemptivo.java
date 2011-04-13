import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import escalonador.modelo.Algoritmo;
import escalonador.modelo.Escalonador;
import escalonador.modelo.EstadoProcesso;
import escalonador.modelo.Processo;
import escalonador.modelo.algoritmos.Preemptivo;
import escalonador.modelo.algoritmos.ShortJobFirst;
import escalonador.visao.Janela;


public class TestePreemptivo {

	public static void main(String[] args) {
		//new Janela();
		
		ArrayList<Processo> processos = new ArrayList<Processo>();
		
		Processo p = new Processo();
		p.setPid(1);
		p.setSolicitacaoES(.09f);
		p.setTempoComputacao(10);
		p.setTempoES(1);
		
		p.setPrioridade(3);
		p.setEstado(EstadoProcesso.PRONTO);
		
		processos.add(p);
		
		p = new Processo();
		p.setPid(2);
		p.setSolicitacaoES(.59f);
		p.setTempoComputacao(4);
		p.setTempoES(2);
		
		p.setPrioridade(5);
		p.setEstado(EstadoProcesso.PRONTO);
		
		processos.add(p);
		
		p = new Processo();
		p.setPid(3);
		p.setSolicitacaoES(.9f);
		p.setTempoComputacao(5);
		p.setTempoES(7);
		
		p.setPrioridade(0);
		p.setEstado(EstadoProcesso.PRONTO);
		
		processos.add(p);
		
		p = new Processo();
		p.setPid(4);
		p.setSolicitacaoES(0.4f);
		p.setTempoComputacao(1);
		p.setTempoES(2);
		
		p.setPrioridade(1);
		p.setEstado(EstadoProcesso.PRONTO);
		processos.add(p);
		
		Preemptivo preemptivo = new Preemptivo(processos);
		
		
		/*
		ExecutorService service = Executors.newFixedThreadPool(1);
		service.execute(new Escalonador(preemptivo));
		
		while(!preemptivo.getProntos().isEmpty()) {
			synchronized (preemptivo) {
				preemptivo.notify();
				try {
					preemptivo.wait(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		

		
		*/
		
		preemptivo.escalonar();
		
		
		//service.shutdown();
		//System.out.println(processos);
	}
}
