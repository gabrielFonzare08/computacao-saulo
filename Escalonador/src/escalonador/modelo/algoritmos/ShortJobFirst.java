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
class ComparadorProcessoTempoComputacao implements Comparator<Processo> {
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
	
	private static final ComparadorProcessoTempoComputacao COMPARADOR = new ComparadorProcessoTempoComputacao();
	public ShortJobFirst(List<Processo> processos) {
		super(processos);
	}
	
	@Override
	public void escalonar() {
	
		
		Collections.sort(processos, COMPARADOR); // reorganiza-os
		
		while(!prontos.isEmpty()) {
			esperar();
			
			
			executando = prontos.remove(0); // pega o proximo processo pronto
			executando.setEstado(EstadoProcesso.EXECUTANDO);
			executando.tempos.resposta = executando.tempos.pronto;
			
			if(executando.vaiFazerES()) { // vai fazer es?
				executando.setEstado(EstadoProcesso.BLOQUEADO);				
				executando.tempos.bloqueado += executando.getTempoES(); // incrementar tempo de bloqueado
				incrementaTempoCpuOciosa();
				esperar();
			}
			
			
			executando.tempos.executando += executando.getTempoComputacao();
			
			for(Processo p : prontos) {
				p.tempos.pronto += executando.getTempoComputacao() + executando.tempos.bloqueado; // incrementar tempo de pronto em um ciclo; 
			}
			
			executando.setEstado(EstadoProcesso.TERMINADO);
			terminados.add(executando);
			incrementaTempoSimulacao();
		}
	}	
}