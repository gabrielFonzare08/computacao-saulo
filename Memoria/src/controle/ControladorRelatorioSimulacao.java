package controle;

import java.util.ArrayList;
import java.util.Arrays;

import modelo.Algoritmo;
import modelo.Processo;
import modelo.Segmento;
import modelo.algoritmos.BestFit;
import modelo.algoritmos.FirstFit;
import modelo.algoritmos.NextFit;
import modelo.algoritmos.WorstFit;

import visao.ListaProcessos;

public class ControladorRelatorioSimulacao extends Controlador {
	
	private static ControladorRelatorioSimulacao instance;
	
	public static ControladorRelatorioSimulacao getInstance() {
		if(instance == null) {
			instance = new ControladorRelatorioSimulacao();
		}
		return instance;
	}
	
	public void simular() {
		Algoritmo [] algoritmos = {
			new BestFit(),
			new FirstFit(),
			new NextFit(),
			new WorstFit()
		};
		
		ArrayList<Processo> lista = processos.getProcessos();
		
//		for(Algoritmo algoritmo : algoritmos) {
//			ArrayList<Processo> buffer = new ArrayList<Processo>(lista);
//			
//			while(!buffer.isEmpty()) {
//				System.out.print(buffer.size() + " ");
//				Processo p = buffer.remove(0);
//				System.out.println(buffer.size());
//				
//				if(!algoritmo.adicionarProcesso(p)) {
//					buffer.add(p);
//				}
//				
//				for(Segmento segmento : algoritmo.getMemoria()) {
//					if(segmento.isOcupado()) {
//						segmento.decrementarTempoExecucao();
//						
//						if(segmento.isTerminado()) {
//							algoritmo.removerProcesso(segmento);
//						}
//					}
//				}	
//			}
//			
//			
//		}
		
		
		ArrayList<Processo> buffer = processos.getProcessos();
		Algoritmo algoritmo = new BestFit();
		while(!buffer.isEmpty()) {
			Processo p = buffer.remove(0);
			
			if(!algoritmo.adicionarProcesso(p)) {
				buffer.add(p);
			}
			for(int i = 0; i < algoritmo.getMemoria().size(); i++) {
				Segmento segmento = algoritmo.getMemoria().get(i) ;
				if(segmento.isOcupado()) {
					segmento.decrementarTempoExecucao();
					
					if(segmento.isTerminado()) {
						algoritmo.removerProcesso(segmento);
					}
				}
			}	
		}
		
		for(Segmento segmento: algoritmo.getBuffer()) {
			System.out.println(segmento);		
			
		}
	}
	
	public void gerarRelatorio() {
		System.out.println(algoritmo.getBuffer());
		System.out.println("relatorio");
	}

}
