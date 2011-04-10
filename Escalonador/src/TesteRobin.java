import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import escalonador.modelo.Escalonador;
import escalonador.modelo.Processo;
import escalonador.modelo.algoritmos.RoundRobin;
import escalonador.modelo.algoritmos.ShortJobFirst;


public class TesteRobin {
	
	public static void main(String[] args) throws InterruptedException {
ArrayList<Processo> processos = new ArrayList<Processo>();
		
		Processo p = new Processo();
		p.setPid(1);
		p.setQuantum(2);
		p.setSolicitacaoES(.09f);
		p.setTempoComputacao(10);
		p.setTempoES(1);
		
		processos.add(p);
		
		p = new Processo();
		p.setPid(2);
		p.setQuantum(3);
		p.setSolicitacaoES(.59f);
		p.setTempoComputacao(4);
		p.setTempoES(2);		
		
		processos.add(p);
		
		p = new Processo();
		p.setPid(3);
		p.setQuantum(3);
		p.setSolicitacaoES(.3f);
		p.setTempoComputacao(5);
		p.setTempoES(7);
		
		processos.add(p);
		
		p = new Processo();
		p.setPid(4);
		p.setQuantum(1);
		p.setSolicitacaoES(1f);
		p.setTempoComputacao(1);
		p.setTempoES(1);
		
		processos.add(p);
		
		RoundRobin roundRobin = new RoundRobin(processos);
		
		ExecutorService service = Executors.newFixedThreadPool(1);
		service.execute(new Escalonador(roundRobin));
		
		
		while(!roundRobin.getProntos().isEmpty()) {
			synchronized (roundRobin) {
				roundRobin.notify();
			}
			Thread.sleep(100);
			
		}
		
		service.shutdown();	
		System.out.println();
		for(Processo p_ : roundRobin.getTerminados()) {
			System.out.println(p_);
		}
	}

}
