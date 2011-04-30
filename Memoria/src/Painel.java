
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;

public abstract class Painel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	protected final Janela janela;
	
	public Painel(final Janela janela) {
		this.janela = janela;
		
		setPreferredSize(new Dimension(440, 460));
		initComponents();
		addEvents();
		
		setVisible(true);
	}
	
	public Janela getJanela() {
		return janela;
	}
	
	public void porEmFoco() {
		for(Component component : janela.getContentPane().getComponents()) {
			component.setVisible(false);
		}
		
		this.setVisible(true);
	}
	
	public abstract void initComponents();
	public abstract void addEvents();

}
