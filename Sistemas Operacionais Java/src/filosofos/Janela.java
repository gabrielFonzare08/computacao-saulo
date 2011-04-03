package filosofos;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Janela extends JFrame {

	private static final long serialVersionUID = 7845961579829139298L;
	private static Janela instance = null; 

	private Janela(final Object o) {
		super("Jantar dos Filósofos");
		setBackground(Color.WHITE);
		setSize(640, 480);
		setResizable(false);
				
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				synchronized (o) {
					o.notify();					
				}
			}
		});		
		
		setLocationRelativeTo(null);		
		setVisible(true);
	}
	
	public static Janela getInstance(final Object o) {
		if(instance == null) {
			instance = new Janela(o);
		}
		return instance;
	}
}
