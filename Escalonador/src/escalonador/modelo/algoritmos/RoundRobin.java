package escalonador.modelo.algoritmos;


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
	}
	
	@Override
	public void escalonar() {
		while(terminados.size() < processos.size()) {
			executando = null;
			esperar();
			
			try {
				executando = prontos.remove(0);
				
				if(executando.tempos.resposta == -1) {
					executando.tempos.resposta = executando.tempos.pronto;
				}				
				
				if(executando.vaiFazerES()) {
					executando.setEstado(EstadoProcesso.BLOQUEADO);
					executando.tempos.bloqueado += executando.getTempoES();
					executando.tempos.timeoutBloqueado = executando.getTempoES();
					bloqueados.add(executando);
					esperar();
					
				} else {
					
					executando.setEstado(EstadoProcesso.EXECUTANDO);
					executando.tempos.executando += executando.getQuantum();
					
					// terminou de computar
					if(executando.tempos.executando >= executando.getTempoComputacao()) {
						executando.setEstado(EstadoProcesso.TERMINADO);
						terminados.add(executando);
						executando = null;
					}
					
					// atualizar os tempos de pronto
					for(int i = 0; i < prontos.size(); i++) {
						Processo pronto = prontos.get(i);
						pronto.tempos.pronto += executando.getQuantum();					
					}
					
					// se n terminou, volta pros prontos
					if(!executando.isTerminado()) {
						prontos.add(executando);						
					}
				}
			}
			
			catch (Exception e) { 
				incrementaTempoCpuOciosa();
			}
			
			finally {
				
				// atualiza todos os processos bloqueados
				for(int i = 0; i < bloqueados.size(); i++) {
					Processo bloqueado = bloqueados.get(i);
					bloqueado.tempos.timeoutBloqueado -= (executando == null) ? 1 : executando.getQuantum();
					
					// se ele estiver terminado ES voltar para pronto!
					if(bloqueado.tempos.timeoutBloqueado <= 0) {
						bloqueado.tempos.timeoutBloqueado = 0;
						bloqueados.remove(i);
						bloqueado.setEstado(EstadoProcesso.PRONTO);
						prontos.add(bloqueado);
					}
				}				
			}
			
			incrementaTempoSimulacao();			
		}
		
		for(Processo p : prontos) {
			p.tempos.tempoRetorno = p.tempos.executando + p.tempos.bloqueado + p.tempos.pronto;
		}
	}	
}