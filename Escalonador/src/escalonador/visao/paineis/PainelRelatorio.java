package escalonador.visao.paineis;

import java.awt.Dimension;
import java.awt.FlowLayout;

//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
//import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import escalonador.controle.ControladorRelatorio;
import escalonador.visao.Janela;
import escalonador.visao.Painel;

public class PainelRelatorio extends Painel {
	
	private static final long serialVersionUID = 1L;
	
	private JList listaProcessos;
//	
//	private JTextField pid;
//	private JTextField prioridade;
//	private JTextField tempoComputacao;
//	private JTextField quantum;
//	private JTextField taxaES;
//	private JTextField tempoES;
	
	private JTextArea tempos;
	
//	private JTextField tempoPronto;
//	private JTextField tempoExecutando;
//	private JTextField tempoBloqueado;
		
	public PainelRelatorio(Janela janela) {
		super(janela);
		janela.setResizable(!false);
	}

	@Override
	public void initComponents() {
		setLayout(new FlowLayout(FlowLayout.CENTER));
//		
//		pid				= new JTextField();
//		prioridade		= new JTextField();
//		tempoComputacao	= new JTextField();
//		quantum			= new JTextField();
//		taxaES			= new JTextField();
//		tempoES			= new JTextField();
		
		tempos			= new JTextArea("", 5, 8);
		
		listaProcessos	= new JList();
		
		JScrollPane jScrollPane = new JScrollPane(listaProcessos);
		jScrollPane.setPreferredSize(new Dimension(430, 130));
		
//		JTextField [] campos = {
//			pid, prioridade, tempoComputacao, quantum, taxaES, tempoES
//		};
//		
//		String [] rotulos = {
//			"PID", "Prioridade", "Tempo de Computação", "Quantum*", "Taxa de E/S", "Tempo de E/S"	
//		};
//		
//		Dimension dimensaoCampoTexto = new Dimension(250, 32);
//		Dimension dimensaoRotulo = new Dimension(180, 32);
		
//		for(int i = 0; i < campos.length; i++) {
//			JLabel jLabel = new JLabel(rotulos[i]);
//			//add(jLabel);
//			//add(campos[i]);
//			
//			jLabel.setPreferredSize(dimensaoRotulo);
//			campos[i].setPreferredSize(dimensaoCampoTexto);
//		}
		
		JScrollPane jScrollPane2 = new JScrollPane(tempos);
		jScrollPane2.setPreferredSize(new Dimension(430, 200));
		
		add(jScrollPane);
		add(jScrollPane2);
		
	}
	
	public void setProcesso(String s) {
		
		
		tempos.setText(s);
	}
	
	public void setListaProcessos(Object [] objs) {
		listaProcessos.setListData(objs);
	}

	@Override
	public void addEvents() {		
		listaProcessos.addListSelectionListener(new ListSelectionListener() {			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				ControladorRelatorio.getInstance(PainelRelatorio.this).mostrarProcesso(listaProcessos.getSelectedIndex());
			}
		});
	}
}
