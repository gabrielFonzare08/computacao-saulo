package visao;
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

import controle.ControladorInicioSimulacao;

public class PainelInicioSimulacao extends Painel{
private static final long serialVersionUID = -1337476581613013311L;
	
	private JButton adicionar;
	private JButton remover;
	private JButton simular;
	private JTextField quantidadeProcessos;
	
	private JList listaProcessos;
	
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
		
		JScrollPane jScrollPane = new JScrollPane(listaProcessos, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setPreferredSize(new Dimension(440, 250));
		
		tamanhoProcessos = new JComboBox(TipoProcesso.values());		
		add(jScrollPane);
		
		add(adicionar);
		add(remover);
		
		JLabel escolhaTamanho = new JLabel("Escolha o tamanho do processo: ");
		add(escolhaTamanho);
		add(tamanhoProcessos);
		
		JLabel escolhaQuantidade = new JLabel("Estipule a quantidade de processos: ");
		add(escolhaQuantidade);
		
		quantidadeProcessos = new JTextField();
		quantidadeProcessos.setPreferredSize(new Dimension(200, 32));
		
		add(quantidadeProcessos);		
		add(simular);
		
	}
	
	@Override
	public void addEvents() {
		
		final ControladorInicioSimulacao controlador =  ControladorInicioSimulacao.getInstance(this);
		
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
