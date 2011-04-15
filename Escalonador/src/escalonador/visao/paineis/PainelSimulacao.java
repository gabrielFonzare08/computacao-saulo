package escalonador.visao.paineis;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import escalonador.controle.ControladorSimulacao;
import escalonador.visao.Janela;
import escalonador.visao.Painel;

public class PainelSimulacao extends Painel {

	private static final long serialVersionUID = 1L;
	
	private JList prontos;
	private JList bloqueados;
	private JList terminados;
	
	private JButton fim;
	
	private JTextField executando;

	public PainelSimulacao(Janela janela) {
		super(janela);
	}

	@Override
	public void initComponents() {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setPreferredSize(new Dimension(620, 450));
		
		executando = new JTextField();
		
		fim = new JButton("Terminar Simulação");
		
		prontos = new JList();
		bloqueados = new JList();
		terminados = new JList();
		
		
		JScrollPane [] jScrollPanes = {
				new JScrollPane(prontos),
				new JScrollPane(bloqueados),
				new JScrollPane(terminados)
		};
		
		String [] rotulos = {
			"Prontos", "Bloqueados", "Terminados"
		};
		
		Dimension dimensaonLista = new Dimension(200, 360);
		Dimension dimensaoRotulo = new Dimension(200, 32);
		
		executando.setPreferredSize(new Dimension(200, 32));
		executando.setEditable(false);
		
		JLabel jLabel = new JLabel("Processo executando na CPU");
		
		add(jLabel);
		add(executando);
		
		add(fim);
		
		for(JScrollPane jScrollPane : jScrollPanes) {
			jScrollPane.setPreferredSize(dimensaonLista);
			add(jScrollPane);			
		}
		
		for(String rotulo : rotulos) {
			JLabel label = new JLabel(rotulo);
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setPreferredSize(dimensaoRotulo);
			add(label);
		}
	}
	
	public void setProcessosProntos(Object [] processos) {
		prontos.removeAll();
		prontos.setListData(processos);
	}
	
	public void setProcessosBloqueados(Object [] processos) {
		bloqueados.removeAll();
		bloqueados.setListData(processos);
	}
	
	public void setProcessosTerminados(Object [] processos) {
		terminados.setListData(processos);
	}
	
	
	public void setExecutando(String pid) {
		executando.setText(pid);
	}
	
	@Override
	public void addEvents() {
		fim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorSimulacao.getInstance(PainelSimulacao.this, -1).terminar();
			}
		});
	}

}
