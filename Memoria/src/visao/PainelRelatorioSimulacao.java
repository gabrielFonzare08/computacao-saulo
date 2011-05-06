package visao;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;



public class PainelRelatorioSimulacao extends Painel{
private static final long serialVersionUID = 1L;
	
	private JTextArea relatorio;
	
	public PainelRelatorioSimulacao(Janela janela) {
		super(janela);
		setPreferredSize(new Dimension(600, 460));
	}

	@Override
	public void initComponents() {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		relatorio			= new JTextArea("");
		relatorio.setEditable(false);
		
		JScrollPane jScrollPane2 = new JScrollPane(relatorio, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jScrollPane2.setPreferredSize(new Dimension(460, 400));
		
		add(jScrollPane2);
	}
	
	public void setProcesso(String s) {
		relatorio.setText(s);
	}
	
	public void appendTexto(String str) {
		relatorio.append(str + "\n");
	}
	
	@Override
	public void addEvents() {		
	}

}
