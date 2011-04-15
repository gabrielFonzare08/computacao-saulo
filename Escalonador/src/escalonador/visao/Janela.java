package escalonador.visao;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import escalonador.visao.paineis.PainelCriacaoProcesso;
import escalonador.visao.paineis.PainelRelatorio;
import escalonador.visao.paineis.PainelSimulacao;

public class Janela extends JFrame {
	
	private static final long serialVersionUID = 6259000253167182868L;
	private PainelCriacaoProcesso painelCriacaoProcesso;
	private PainelSimulacao painelSimulacao;
	private PainelRelatorio painelRelatorio;
	
	private Painel [] paineis;

	public Janela() {
		super("Simulador de Escalonador de Processos");
		
		initComponents();
		addEvents();
		
		setVisible(true);
	}
	
	private void initComponents() {
		setSize(640, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setJMenuBar(new JMenuBar());
		
		getJMenuBar().add(new JMenu("Arquivo"));
		getJMenuBar().add(new JMenu("Ajuda"));
		
		getJMenuBar().getMenu(0).add(new JMenuItem("Sair"));
		getJMenuBar().getMenu(1).add(new JMenuItem("Sobre"));
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		painelCriacaoProcesso = new PainelCriacaoProcesso(this);
		painelSimulacao = new PainelSimulacao(this);
		painelRelatorio = new PainelRelatorio(this);
		
		add(painelCriacaoProcesso);
		add(painelSimulacao);
		add(painelRelatorio);
		
		paineis = new Painel[] {
			painelCriacaoProcesso,
			painelSimulacao,
			painelRelatorio
		};
	}
	
	public PainelCriacaoProcesso getPainelCriacaoProcesso() {
		return painelCriacaoProcesso;
	}
	
	public PainelSimulacao getPainelSimulacao() {
		return painelSimulacao;
	}
	
	public PainelRelatorio getPainelRelatorio() {
		return painelRelatorio;
	}
	
	public void trocarPainel(int i) {
		for(Painel painel : paineis) {
			painel.setVisible(false);
		}
		
		painelCriacaoProcesso.setVisible(true);
	}
	
	private void addEvents() {
		getJMenuBar().getMenu(0).getItem(0).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		getJMenuBar().getMenu(1).getItem(0).addActionListener(new ActionListener() {
			static final String mensagem = "Desenvolvido por: \nJardel Rodrigues <jardel.ifce@gmail.com> \nSaulo Freitas <saulo.ifet@gmail.com>";
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, mensagem, "Sobre", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}	
}