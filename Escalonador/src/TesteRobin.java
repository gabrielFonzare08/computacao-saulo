import java.util.ArrayList;
import escalonador.modelo.Processo;
import escalonador.modelo.algoritmos.RoundRobin;


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
		p.setSolicitacaoES(.5f);
		p.setTempoComputacao(1);
		p.setTempoES(1);
		
		processos.add(p);
		
		RoundRobin roundRobin = new RoundRobin(processos);
		roundRobin.escalonar();
		
		
		System.out.println("Resultado:");
		for(Processo processo : roundRobin.getTerminados()) {
			System.out.println(processo);
		}
	}

}
