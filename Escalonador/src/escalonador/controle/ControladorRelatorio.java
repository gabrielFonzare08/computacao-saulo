package escalonador.controle;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JOptionPane;

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
	
	public void gerarArquivo() {
		try {
			PrintWriter pw = new PrintWriter("relatorio" + System.currentTimeMillis() +  ".txt");
			
			pw.println("Simulação em: " + Calendar.getInstance(Locale.getDefault()).getTime().toString());
			pw.println("Algoritmo: " + ControladorSimulacao.getInstance(null, -1).getNomeAlgoritmo());
			
			pw.println("---------------------------------");
			pw.println("---------------------------------");
			
			for(Processo processo : processos) {
				pw.println("---------------------------------");
				
				pw.println("PID: " + processo.getPid());
				
				pw.format("Prioridade: %d", processo.getPrioridade());
				pw.println();
				
				pw.println("Tempo de Computação: " + processo.getTempoComputacao());
				
				pw.println("Quantum: "		+ processo.getQuantum());				
				pw.println("Taxa de E/S: "	+ processo.getSolicitacaoES());
				
				pw.format("Tempo de E/S: %d", processo.getTempoES());
				
				pw.println();
				pw.println();
				
				pw.println("Tempo resposta: "	+ processo.tempos.resposta);
				pw.println("Tempo bloqueado: "	+ processo.tempos.bloqueado);
				pw.println("Tempo Executando: "	+ processo.tempos.executando);
				pw.println("Tempo Pronto: "		+ processo.tempos.pronto);
				
				pw.format("Tempo bloqueado: %d", processo.tempos.bloqueado);
				pw.println("Tempo resposta: " + processo.tempos.resposta);
				pw.println("Tempo bloqueado: " + processo.tempos.bloqueado);
				pw.format("Tempo bloqueado: %d", processo.tempos.bloqueado);
				pw.format("Tempo bloqueado: %d", processo.tempos.bloqueado);
				pw.format("Tempo bloqueado: %d", processo.tempos.bloqueado);
				
				
				
				pw.println("---------------------------------");
				
				
				
			}
			
			pw.flush();
			pw.close();			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString(), "Erro ao gerar Relatório", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new ControladorRelatorio(null).gerarArquivo();
	}
}