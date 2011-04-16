package escalonador.controle;

import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.JOptionPane;

import escalonador.modelo.Algoritmo;
import escalonador.modelo.Processo;
import escalonador.visao.paineis.PainelRelatorio;

public class ControladorRelatorio extends Controlador {
	
	private static ControladorRelatorio instance;
	private PainelRelatorio painel;

	public ControladorRelatorio(PainelRelatorio painel) {
		this.painel = painel;
	}

	public static ControladorRelatorio getInstance(PainelRelatorio painel) {
		if (instance == null) {
			instance = new ControladorRelatorio(painel);
		}
		return instance;
	}

	public void setProcessos() {
		painel.setListaProcessos(processos.toArray());
	}

	public void mostrarProcesso(int i) {
		try {
			StringBuffer stringBuffer = new StringBuffer(processos.get(i)
					.toString());
			int indice = 0;

			while ((indice = stringBuffer.indexOf("[")) > 0) {
				stringBuffer.setCharAt(indice, '\n');
			}

			while ((indice = stringBuffer.indexOf("]")) > 0) {
				stringBuffer.setCharAt(indice, '\n');
			}

			while ((indice = stringBuffer.indexOf(",")) > 0) {
				stringBuffer.delete(indice, indice +1);
				stringBuffer.insert(indice, '\t');
				stringBuffer.insert(indice, '\n');
				
			}

			painel.setProcesso(stringBuffer.toString());
		} catch (Exception e) {
			
		}
	}
	
	public void gerarArquivo(Algoritmo algoritmo) {
		
		String arquivo = "relatorio" + System.currentTimeMillis() +  ".txt";
		
		try {
			
			PrintWriter pw = new PrintWriter(arquivo);
			
			pw.println("Simulação em: " + Calendar.getInstance(Locale.getDefault()).getTime().toString());
			pw.println("Algoritmo: " + ControladorSimulacao.getInstance(null, -1).getNomeAlgoritmo());
			
			pw.println("---------------------------------");
			pw.println("Métricas: ");
			
			
			List<Processo> processos = algoritmo.getProcessos(); 
			
			float cpuMedia = 0;
			for(Processo p : processos) {
				cpuMedia += (p.tempos.executando / ( p.tempos.resposta  == 0 ? 1 : p.tempos.resposta) );
			}
			
			float esperaMedia = 0;
			for(Processo p : processos) {
				esperaMedia += p.tempos.resposta;
			}
			
			float retornoMedia = 0;
			for(Processo p : processos) {
				retornoMedia += p.tempos.tempoRetorno;
			}
			
			float prontos = 0;
			for(Processo p : processos) {
				prontos += p.tempos.pronto;
			}
			
			List<Processo> terminados = algoritmo.getTerminados();
			
			Collections.sort(terminados, new Comparator<Processo>() {
				@Override
				public int compare(Processo o1, Processo o2) {
					return (int) (o2.tempos.tempoRetorno - o1.tempos.tempoRetorno); 
				}
			});
			
			pw.println("Vazão: "			+ (terminados.get(0).tempos.tempoRetorno / processos.size())); // fazendo
			pw.println("CPU médio: "		+ cpuMedia);
			pw.println("Espera médio: "		+ (esperaMedia / processos.size()));
			pw.println("Retorno médio: "	+ (retornoMedia / processos.size()));
			pw.println("Resposta médio: "	+ (prontos / processos.size()));
			
			pw.println();
			
			pw.println("---------------------------------");
			
			for(Processo processo : processos) {
				pw.println("---------------------------------");
				
				pw.println("PID: "					+ processo.getPid());				
				pw.println("Prioridade: "			+ processo.getPrioridade());
				pw.println("Tempo de Computação: "	+ processo.getTempoComputacao());
				pw.println("Quantum: "				+ processo.getQuantum());				
				pw.println("Taxa de E/S: " 			+ processo.getSolicitacaoES());
				pw.println("Tempo de E/S: "			+ processo.getTempoES());
				
				pw.println();
				pw.println();
				
				pw.println("Tempo Resposta: "	+ processo.tempos.resposta);
				pw.println("Tempo Bloqueado: "	+ processo.tempos.bloqueado);
				pw.println("Tempo Executando: "	+ processo.tempos.executando);
				pw.println("Tempo Pronto: "		+ processo.tempos.pronto);
				
				pw.println("---------------------------------");
				
				
				
			}
			
			pw.flush();
			pw.close();	
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString(), "Erro ao gerar Relatório", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		try {
			Scanner scanner = new Scanner(new File(arquivo));
			String str = "";
			
			while(scanner.hasNext()) {
				str += scanner.nextLine() + "\n";
			}
			
			painel.setProcesso(str);
			scanner.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString(), "Erro ao gerar Relatório", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	}
}