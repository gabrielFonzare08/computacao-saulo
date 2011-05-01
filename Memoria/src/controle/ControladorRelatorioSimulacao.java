package controle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import modelo.Algoritmo;
import modelo.Processo;
import modelo.Segmento;
import modelo.algoritmos.BestFit;
import modelo.algoritmos.FirstFit;
import modelo.algoritmos.NextFit;
import modelo.algoritmos.WorstFit;

import visao.ListaProcessos;
import visao.PainelRelatorioSimulacao;

public class ControladorRelatorioSimulacao extends Controlador {
	
	private static ControladorRelatorioSimulacao instance;
	private PainelRelatorioSimulacao painel;
	
	public void setPainel(PainelRelatorioSimulacao painel) {
		this.painel = painel;
	}
	
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
				
		for(Algoritmo algoritmo : algoritmos) {			
			
			
			ArrayList<Processo> buffer = new ArrayList<Processo>(lista);
			
			while(algoritmo.getTerminados().size() < lista.size()) {
								
				algoritmo.fragmentacao += algoritmo.segmentosOcupados() / algoritmo.getMemoria().size();
				algoritmo.ciclos++;
				
				try {
					Thread.sleep(1);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				try {
					Processo p = buffer.remove(0);
					
					if(!algoritmo.adicionarProcesso(p)) {
						buffer.add(p);
					}					
				} catch (Exception e) {}
				
				
				
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
			 
		}
		
		gerarRelatorio(algoritmos);
		
	}
	
	public void gerarRelatorio(Algoritmo [] algoritmos) {
		painel.setListaProcessos(algoritmos[0].getTerminados().toArray());
		
		File file = new File("relatorio-memoria-" + System.currentTimeMillis() + ".txt");
		
		try {
			
			PrintWriter pw = new PrintWriter(file);
			
			
			for(Algoritmo algoritmo : algoritmos) {
				pw.println("Algoritmo: " + algoritmo.getClass().getSimpleName());
				pw.println("Número de iterações: " + algoritmo.ciclos);
				pw.println("Fragmentação total: " + algoritmo.fragmentacao);
				pw.println("Fragmentação média: " + (algoritmo.fragmentacao / algoritmo.ciclos));
				
				pw.println();				
			}
			
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("lendo...");
		Scanner sc;
		try {
			sc = new Scanner(file);
			while(sc.hasNext()) {
				painel.appendTexto(sc.nextLine());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
