package escalonador.modelo.algoritmos;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import escalonador.modelo.Algoritmo;
import escalonador.modelo.EstadoProcesso;
import escalonador.modelo.Processo;


/**
 * Implementa&ccedil;&atilde;o da classe {@link Comparator} para tratar com processos no algoritmo {@link ShortJobFirst}. <br />
 * O algoritmo simplemente compara os {@link Processo#getComputingTime()} de cada processo.
 * */
class ProcessComparator implements Comparator<Processo> {
	@Override
	public int compare(Processo p1, Processo p2) {
		return p1.getTempoComputacao() - p2.getTempoComputacao();
	}		
}

/**
 * Implementa&ccedil;&atilde;o do algoritmo ShortJobFirst.
 * O algoritmo deve obedecer as seguintes regras:
 * <ul>
 * 	<li>Fila priorizada pelo tempo de execu&ccedil;&atilde;o das tarefas.</li>
 *	<li>O tempo de execu&ccedil;&atilde;o deve ser conhecido previamente.</li>
 *	<li>Outra caracter&iacute;stica &eacute; que o algoritmo &eacute; n&atilde;o preemptivo.</li>
 * </ul>
 * 
 * */

public class ShortJobFirst extends Algoritmo {
	
	public ShortJobFirst(List<Processo> processos) {
		super(processos);
	}
	
	@Override
	public void escalonar() {
	
		ProcessComparator comparator = new ProcessComparator();
		
		Collections.sort(prontos, comparator); // reorganiza-os
		
		while(!prontos.isEmpty()) {
			
			executando = prontos.remove(0); // pega o proximo processo pronto
			executando.setEstado(EstadoProcesso.EXECUTANDO);
			
			if(executando.vaiFazerES()) { // vai fazer es?
				executando.setEstado(EstadoProcesso.BLOQUEADO);				
				executando.tempos.bloqueado += executando.getTempoES(); // incrementar tempo de bloqueado
				
			} 
			
			executando.tempos.executando += executando.getTempoComputacao(); // somar tempo de executando.
			executando.setEstado(EstadoProcesso.TERMINADO); // termina!
			
			for(Processo p : prontos) {
				p.tempos.pronto += 1; // incrementar tempo de pronto em um ciclo; 
			}
		}
	}
	
}
