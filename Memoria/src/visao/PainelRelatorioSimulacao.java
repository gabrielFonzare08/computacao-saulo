package visao;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;



public class PainelRelatorioSimulacao extends Painel{
private static final long serialVersionUID = 1L;
	
	private JList listaProcessos;
	private JTextArea tempos;
	
	public PainelRelatorioSimulacao(Janela janela) {
		super(janela);
		janela.setResizable(!false);
	}

	@Override
	public void initComponents() {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		tempos			= new JTextArea("", 5, 8);
		tempos.setEditable(false);
		
		listaProcessos	= new JList();
		
		JScrollPane jScrollPane = new JScrollPane(listaProcessos);
		jScrollPane.setPreferredSize(new Dimension(430, 130));
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
	}

}
