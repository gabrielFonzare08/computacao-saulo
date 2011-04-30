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

public class PainelInicioSimulacao extends Painel{
private static final long serialVersionUID = -1337476581613013311L;
	
	private JButton adicionar;
	private JButton remover;
	private JButton simular;
	private JTextField quantidadeProcessos;
	
	private JList listaProcessos;
	
	private JComboBox algoritmos;
	private JComboBox tamanhoProcessos;
	
	public PainelInicioSimulacao(Janela janela) {
		super(janela);
	}
	
	@Override
	public void initComponents() {
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		adicionar		= new JButton("Adicionar Processo");
		remover			= new JButton("Deletar processo selecionado");
		simular			= new JButton("Simular");
		
		listaProcessos	= new JList();
		
		JScrollPane jScrollPane = new JScrollPane(listaProcessos);
		jScrollPane.setPreferredSize(new Dimension(430, 130));
		
		String [] nomeAlgoritmos = {
				"First-Fit", "Best-Fit", "Worst-Fit", "NextFit"
		};
		
			
		algoritmos = new JComboBox(nomeAlgoritmos);
		tamanhoProcessos = new JComboBox(TipoProcesso.values());
		
		
		add(jScrollPane);
		
		add(adicionar);
		add(remover);		
		
		JLabel escolhaAlgoritmo = new JLabel("Escolha um algoritmo: ");
		
		add(escolhaAlgoritmo);
		add(algoritmos);
		
		JLabel escolhaTamanho = new JLabel("Escolha o tamanho do processo: ");
		add(escolhaTamanho);
		add(tamanhoProcessos);
		
		JLabel escolhaQuantidade = new JLabel("Estipule a quantidade de processos: ");
		add(escolhaQuantidade);
		add(quantidadeProcessos);
		
		add(simular);
		
	}
	
	@Override
	public void addEvents() {
		
		final ControladorInicioSimulacao controlador =  ControladorInicioSimulacao.getInstance(PainelInicioSimulacao.this);
		
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
				controlador.notificarSimulacao();
			}
		});
	}
	
	public int getAlgoritmos() {
		return algoritmos.getSelectedIndex();
	}
	
	public String getQuantidadeProcessos() {
		return quantidadeProcessos.getText();
	}
	
	public int getTamanhoProcessos(){
		return tamanhoProcessos.getSelectedIndex();
	}

	public JList getListaProcessos() {
		return listaProcessos;
	}

	public void setListaProcessos(JList listaProcessos) {
		this.listaProcessos = listaProcessos;
	}
}
