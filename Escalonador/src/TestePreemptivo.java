import java.util.ArrayList;
import escalonador.modelo.EstadoProcesso;
import escalonador.modelo.Processo;
import escalonador.modelo.algoritmos.Preemptivo;

public class TestePreemptivo {

	public static void main(String[] args) {
		ArrayList<Processo> processos = new ArrayList<Processo>();
		
		Processo p = new Processo();
		p.setSolicitacaoES(.09f);
		p.setTempoComputacao(10);
		p.setTempoES(1);
		
		p.setPrioridade(3);
		p.setEstado(EstadoProcesso.PRONTO);
		
		processos.add(p);
		
		p = new Processo();
		p.setSolicitacaoES(.59f);
		p.setTempoComputacao(4);
		p.setTempoES(2);
		
		p.setPrioridade(5);
		p.setEstado(EstadoProcesso.PRONTO);
		
		processos.add(p);
		
		p = new Processo();
		p.setSolicitacaoES(.1f);
		p.setTempoComputacao(5);
		p.setTempoES(7);
		
		p.setPrioridade(4);
		p.setEstado(EstadoProcesso.PRONTO);
		
		processos.add(p);
		
		p = new Processo();
		p.setSolicitacaoES(0.4f);
		p.setTempoComputacao(1);
		p.setTempoES(2);
		
		p.setPrioridade(1);
		p.setEstado(EstadoProcesso.PRONTO);
		processos.add(p);
		
		Preemptivo preemptivo = new Preemptivo(processos);
		
		preemptivo.escalonar();
	}
}
