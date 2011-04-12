package escalonador.visao.paineis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import escalonador.visao.Janela;
import escalonador.visao.Painel;

public class PainelRelatorio extends Painel {
	
	private static final long serialVersionUID = 1L;
	
	private JList processos;
	
	private JTextField pid;
	private JTextField prioridade;
	private JTextField tempoComputacao;
	private JTextField quantum;
	private JTextField taxaES;
	private JTextField tempoES;
	
	private JTextField tempoPronto;
	private JTextField tempoExecutando;
	private JTextField tempoBloqueado;
		
	public PainelRelatorio(Janela janela) {
		super(janela);
		janela.setResizable(!false);
	}

	@Override
	public void initComponents() {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setPreferredSize(new Dimension(620, 420));
		
		
		pid = new JTextField();
		prioridade = new JTextField();
		tempoComputacao = new JTextField();
		quantum = new JTextField();
		taxaES = new JTextField();
		tempoES = new JTextField();
		
		tempoPronto = new JTextField();
		tempoExecutando = new JTextField();
		tempoBloqueado = new JTextField();
		
		processos = new JList();
		
		JTextField [] campos = {
			pid, prioridade, tempoComputacao, quantum, taxaES,
			tempoES, tempoPronto, tempoExecutando, tempoBloqueado
		};
		
		String [] rotulos = {
			"PID", "Prioridade", "Tempo de Computação",
			"Quantum", "Taxa de ES", "Tempo de ES",
			"Tempo de Pronto", "Tempo Executando", "Tempo bloqueado"
		};
		
		Dimension dimensaoCampoTexto = new Dimension(180, 32);
		Dimension dimensaoRotulo = new Dimension(180, 32);
		
		JScrollPane jScrollPane = new JScrollPane(processos);
		jScrollPane.setPreferredSize(new Dimension(200, 420));
		add(jScrollPane);
		
		for( int i = 0; i < rotulos.length; i++ ) {
			JLabel jLabel = new JLabel(rotulos[i]);
			jLabel.setPreferredSize(dimensaoRotulo);
			
			campos[i].setPreferredSize(dimensaoCampoTexto);
			
			//add(jLabel);
			add(campos[i]);			
		}
		
	}

	@Override
	public void addEvents() {
		// TODO Auto-generated method stub

	}

}
