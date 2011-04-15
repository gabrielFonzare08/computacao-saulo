package escalonador.visao.paineis;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import escalonador.controle.ControladorPainelCriacaoProcesso;
import escalonador.visao.Janela;
import escalonador.visao.Painel;

public class PainelCriacaoProcesso extends Painel {
	
	private static final long serialVersionUID = -1337476581613013311L;
	
	private JTextField prioridade;
	private JTextField tempoComputacao;
	private JTextField quantum;
	private JTextField taxaES;
	private JTextField tempoES;
	
	private JButton adicionar;
	private JButton remover;
	private JButton simular;
	
	private JList listaProcessos;
	
	private JComboBox algoritmos;
	
	public PainelCriacaoProcesso(Janela janela) {
		super(janela);
	}
	
	@Override
	public void initComponents() {
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		prioridade		= new JTextField();
		tempoComputacao	= new JTextField();
		quantum			= new JTextField();
		taxaES			= new JTextField();
		tempoES			= new JTextField();
		
		adicionar		= new JButton("Adicionar Processo");
		remover			= new JButton("Deletar processo selecionado");
		simular			= new JButton("Simular");
		
		listaProcessos	= new JList();
		
		JScrollPane jScrollPane = new JScrollPane(listaProcessos);
		jScrollPane.setPreferredSize(new Dimension(430, 180));
		
		String [] nomeAlgoritmos = {
				"Preemptivo", "Round Robin", "Short Job First", "Escolha um algoritmo" 
		};
		
		algoritmos = new JComboBox(nomeAlgoritmos);
		
		JTextField [] campos = {
			prioridade, tempoComputacao, quantum, taxaES, tempoES
		};
		
		String [] rotulos = {
			"Prioridade", "Tempo de Computação", "Quantum*", "Taxa de E/S", "Tempo de E/S"	
		};
		
		Dimension dimensaoCampoTexto = new Dimension(250, 32);
		Dimension dimensaoRotulo = new Dimension(180, 32);
		
		for(int i = 0; i < campos.length; i++) {
			JLabel jLabel = new JLabel(rotulos[i]);
			add(jLabel);
			add(campos[i]);
			
			jLabel.setPreferredSize(dimensaoRotulo);
			campos[i].setPreferredSize(dimensaoCampoTexto);
		}
		
		
		add(jScrollPane);
		
		add(adicionar);
		add(remover);		
		
		JLabel escolha = new JLabel("Escolha um algoritmo: ");
		escolha.setPreferredSize(dimensaoRotulo);
		
		add(escolha);
		add(algoritmos);
		
		add(simular);
		
	}
	
	@Override
	public void addEvents() {
		
		final ControladorPainelCriacaoProcesso controlador = ControladorPainelCriacaoProcesso.getInstance(PainelCriacaoProcesso.this);
		
		adicionar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.adicionarNovoProcesso();
			}
		});
		
		remover.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				controlador.removerProcessoSelecionado();
				
			}
		});
		
		simular.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.simular();
			}
		});
	}
	
	public JList getListaProcessos() {
		return listaProcessos;
	}
	
	public String getPrioridadeProcesso() {
		return prioridade.getText();
	}
	
	public String getTempoComputacaoProcesso() {
		return tempoComputacao.getText();
	}
	
	public String getQuantumProcesso() {
		return quantum.getText();
	}
	
	public String getTaxaESProcesso() {
		return taxaES.getText();
	}
	
	public String getTempoESProcesso() {
		return tempoES.getText();
	}
	
	public String getAlgoritmo() {
		return algoritmos.getSelectedItem().toString();
	}
	
	public int getAlgoritmoId() {
		return algoritmos.getSelectedIndex();
	}
}