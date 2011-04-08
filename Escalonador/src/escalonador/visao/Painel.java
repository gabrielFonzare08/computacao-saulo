package escalonador.visao;

import java.awt.Component;

import javax.swing.JPanel;

public abstract class Painel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private final Janela janela;
	
	public Painel(final Janela janela) {
		this.janela = janela;
		initComponents();
		addEvents();
		setVisible(true);
	}
	
	public void porEmFoco() {
		for(Component component : janela.getComponents()) {
			component.setVisible(false);
		}
		
		this.setVisible(true);
	}
	
	public abstract void initComponents();
	public abstract void addEvents();

}
