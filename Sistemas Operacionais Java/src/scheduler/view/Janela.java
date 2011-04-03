package scheduler.view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Janela extends JFrame {
	
	private static final long serialVersionUID = 6259000253167182868L;
	private ProcessList list;
	private EditProcessPanel processPanel;
	

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
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		list = new ProcessList();		
		list.setListData(new Object[] {
			"s", "a", "u", "l", "o", "alabama arckansas"
		});
		
		processPanel = new EditProcessPanel(this);
		
		add(processPanel);
		
		JScrollPane jScrollPane = new JScrollPane(list);
		jScrollPane.setPreferredSize(new Dimension(300, 400));
		add(jScrollPane);
	}
	
	public EditProcessPanel getProcessPanel() {
		return processPanel;
	}
	
	public ProcessList getList() {
		return list;
	}
	
	private void addEvents() {
		
	}
	
	public void setProcess() {
		
	}

	public static void main(String[] args) throws Exception {
		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new Janela();
	}
}
