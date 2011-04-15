import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import escalonador.modelo.Escalonador;
import escalonador.modelo.Processo;
import escalonador.modelo.algoritmos.ShortJobFirst;
import escalonador.visao.Janela;


public class TesteSJF {

	public static void main(String[] args) {
		
		
		ArrayList<Processo> processos = new ArrayList<Processo>();
		
		Processo p = new Processo();
		p.setPid(1);
		p.setSolicitacaoES(.09f);
		p.setTempoComputacao(10);
		p.setTempoES(1);
		
		processos.add(p);
		
		p = new Processo();
		p.setPid(2);
		p.setSolicitacaoES(.59f);
		p.setTempoComputacao(4);
		p.setTempoES(2);		
		
		processos.add(p);
		
		p = new Processo();
		p.setPid(3);
		p.setSolicitacaoES(.3f);
		p.setTempoComputacao(5);
		p.setTempoES(7);
		
		processos.add(p);
		
		p = new Processo();
		p.setPid(4);
		p.setSolicitacaoES(0.9f);
		p.setTempoComputacao(1);
		p.setTempoES(1);
		
		processos.add(p);
		
		ShortJobFirst shortJobFirst = new ShortJobFirst(processos);
		Escalonador escalonador = new Escalonador(shortJobFirst);
		
		
		ExecutorService service = Executors.newFixedThreadPool(1);
		service.execute(escalonador);
		
		while(!escalonador.isTerminado()) {
			synchronized (escalonador) {
				try {
					escalonador.wait(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("-------------");				
				for(Processo processo : shortJobFirst.getProcessos()) {
					System.out.println(processo);
				}
				
				System.out.println("-------------");
				System.out.println();
				
			}
		}
		
		
		for(Processo processo : shortJobFirst.getTerminados()) {
			System.out.println(processo);
		}
		
		service.shutdown();
	}
}
