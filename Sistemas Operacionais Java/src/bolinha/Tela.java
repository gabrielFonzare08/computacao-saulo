package bolinha;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class Tela extends JFrame {
	
	private static final long serialVersionUID = 1L;
	Bolinha bolinha;
	
	public Tela() {
		
		
		super("Bolinha");
		setSize(640, 480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		addEventos();
		
	}
	
	
	private void addEventos() {
		addMouseListener(new MouseAdapter() {
			
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				if(bolinha == null) {// inicia a bolinha pela primeira vez!
					bolinha = new Bolinha(Tela.this, e.getPoint());
					synchronized (Tela.this) {
						Tela.this.notify();
					}
				}
				
				// a cada evento troca o estado da bolinha
				synchronized (bolinha) {
					bolinha.alternar(); 
					bolinha.notify();					
				}
			}
		});
	}
}