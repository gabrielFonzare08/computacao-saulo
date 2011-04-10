package escalonador.modelo.algoritmos;

import java.util.ArrayList;
import java.util.List;

import escalonador.modelo.Algoritmo;
import escalonador.modelo.EstadoProcesso;
import escalonador.modelo.Processo;

/**
 * Implementa&ccedil;&atilde;o do algoritmo RoundRobin.
 * O algoritmo deve obedecer as seguintes regras:
 * <ul>
 * 	<li>Baseado em fila circular.</li>
 * 	<li>Cada processo possui uma quantidade de tempo de execu&ccedil;&atilde;o, o seu quantum.</li>
 * 	<li>Ao final do quantum, se o processo ainda estiver em execu&ccedil;&atilde;o, sofrer&aacute; preemp&ccedil;&atilde;o.</li>
 * 	<li>Tamanho do quantum interfere no desempenho.</li>
 * </ul>
 * 
 * */
public class RoundRobin extends Algoritmo {
	
	public RoundRobin(List<Processo> processos) {
		super(processos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void escalonar() {

		while(!prontos.isEmpty()) {
			executando = prontos.remove(0);
			executando.setEstado(EstadoProcesso.EXECUTANDO);
			
			// testar ES
			if(executando.vaiFazerES()) {				
				executando.setEstado(EstadoProcesso.BLOQUEADO);
				executando.tempos.bloqueado += executando.getTempoES();
				
				// voltar pro fim da lista de prontos
				executando.setEstado(EstadoProcesso.PRONTO); // #1
				prontos.add(executando);
				continue;
			}
							
			executando.tempos.executando += executando.getQuantum();
			
			if(executando.tempos.executando >= executando.getTempoComputacao()) {
				executando.setEstado(EstadoProcesso.TERMINADO); // termina!
				terminados.add(executando);
			}
				
			for(Processo processo : prontos) {
				processo.tempos.pronto += executando.getQuantum();
			}
			
			if(!executando.isTerminado()) {
				executando.setEstado(EstadoProcesso.PRONTO);
				prontos.add(executando);
			}
		}
	}	
}