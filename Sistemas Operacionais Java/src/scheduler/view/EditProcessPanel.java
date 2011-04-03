package scheduler.view;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import scheduler.ProcessState;


public class EditProcessPanel extends JPanel {

	private static final long serialVersionUID = 1641314035135498303L;
	
	private JTextField priority;
	//private JComboBox processState;
	private JTextField computingTime;
	private JTextField ioRate;
	private JTextField ioRateTime;

	public EditProcessPanel() {
		initComponents();
		addEvents();
	}

	private void initComponents() {
		setPreferredSize(new Dimension(300, 400));
		priority = new JTextField();
		
		//processState = new JComboBox(ProcessState.values());
		//add(processState);
		computingTime = new JTextField();
		ioRate = new JTextField();
		ioRateTime = new JTextField();
		
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
		
	}

	private void addEvents() {

	}
}