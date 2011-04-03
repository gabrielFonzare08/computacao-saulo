package scheduler.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import scheduler.ProcessState;
import scheduler.controler.ProcessController;


public class EditProcessPanel extends JPanel {

	private static final long serialVersionUID = 1641314035135498303L;
	
	private JTextField priority;
	private JTextField computingTime;
	private JTextField ioRate;
	private JTextField ioRateTime;
	
	private JButton add;
	private JButton remove;
	private JButton simulate;
	private JComboBox algorithms; 
	
	
	private Janela janela;

	public EditProcessPanel(Janela janela) {
		this.janela = janela;
		initComponents();
		addEvents();
	}

	private void initComponents() {
		setPreferredSize(new Dimension(300, 400));
		priority = new JTextField();
		
		computingTime = new JTextField();
		ioRate = new JTextField();
		ioRateTime = new JTextField();
		
		add = new JButton("Add process");
		remove = new JButton("Delete selected process");
		simulate = new JButton("Simulate");
		
		algorithms = new JComboBox(new String[] {
			"Round Robin", "Short Job First", "Preemptive"
		});
		
		JTextField [] textFields = {
			priority, computingTime, ioRate, ioRateTime
		};
		
		String [] labels = {
			"Priority", "Computing Time", "IO Rate", "IO Rate Time"	
		};
		
		Dimension dimension = new Dimension(250, 32);
		Dimension labelDimension = new Dimension(150, 32);
		
		for(int i = 0; i < textFields.length; i++) {
			JLabel jLabel = new JLabel(labels[i]);
			add(jLabel);
			add(textFields[i]);
			
			jLabel.setPreferredSize(labelDimension);
			textFields[i].setPreferredSize(dimension);
		}
		
		add(add);
		add(remove);
		
		add(algorithms);
		add(simulate);
		
	}
	
	public String getAlgorithm() {
		return algorithms.getSelectedItem().toString();
	}
	
	public String getPriority() {
		return priority.getText();
	}
	
	public String getComputingTime() {
		return computingTime.getText();
	}
	
	public String getIoRate() {
		return ioRate.getText();
	}
	
	public String getIoRateTime() {
		return ioRateTime.getText();
	}

	private void addEvents() {
		add.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				ProcessController.getInstance(janela.getProcessPanel(), janela.getList()).add();
			}
		});
		
		remove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ProcessController.getInstance(janela.getProcessPanel(), janela.getList()).remove();
			}
		});
		
		simulate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ProcessController.getInstance(janela.getProcessPanel(), janela.getList()).simulate();
			}
		});
	}
}