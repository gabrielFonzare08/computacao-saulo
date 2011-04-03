package scheduler.view;

import java.awt.FlowLayout;

import javax.swing.JFrame;

public class Janela extends JFrame {
	
	private static final long serialVersionUID = 6259000253167182868L;

	public Janela() {
		super("Process Escalonator");
		setSize(640, 480);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		initComponents();
		addEvents();
		
		setVisible(true);
	}
	
	private void initComponents() {
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		ProcessList processList = new ProcessList();
		
		processList.setListData(new Object[]{
			"s", "a", "u", "l", "o", "alabama arckansas"
		});
		add(new EditProcessPanel());
		add(processList);
	}
	
	private void addEvents() {
		
	}

	public static void main(String[] args) throws Exception {
		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new Janela();
	}
}
