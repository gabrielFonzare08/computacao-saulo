package leitorescritor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Tela extends JFrame {
	
	private static final long serialVersionUID = 5752634039861677309L;

	public Tela() {
		super("Leitores & Escritores");
		
		setSize(640, 480);
		setResizable(false);				
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);		
		setVisible(true);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				synchronized (Tela.this) {
					Tela.this.notify();
				}
			}
		});
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Entidade.janelaInvisivel = true;
				Tela.this.dispose();
				super.windowClosing(e);
			}
		});
	}
}
