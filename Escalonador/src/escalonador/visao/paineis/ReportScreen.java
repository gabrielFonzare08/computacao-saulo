package escalonador.visao.paineis;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ReportScreen extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JScrollPane scrollPane1;
	private JList dataList1;

	private JPanel panel1;

	public ReportScreen() {
		setSize(600, 480);
		setTitle("Report");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		initComponents();

		setVisible(true);
	}

	public void initComponents() {
		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		textField1 = new JTextField();
		textField2 = new JTextField();
		textField3 = new JTextField();

		dataList1 = new JList();
		scrollPane1 = new JScrollPane(dataList1);

		panel1 = new JPanel();

		label1.setBounds(40, 30, 100, 100);
		label1.setText("Running time:");

		textField1.setBounds(160, 72, 40, 20);
		textField1.setEnabled(false);

		label2.setBounds(40, 80, 100, 100);
		label2.setText("Blocked Time:");

		textField2.setBounds(160, 122, 40, 20);
		textField2.setEnabled(false);

		label3.setBounds(40, 130, 100, 100);
		label3.setText("Ready Time:");

		textField3.setBounds(160, 172, 40, 20);
		textField3.setEnabled(false);

		scrollPane1.setBounds(400, 40, 140, 380);

		panel1.setBounds(20, 40, 220, 200);
		panel1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		panel1.setLayout(null);

		add(label1);
		add(label2);
		add(label3);
		add(textField1);
		add(textField2);
		add(textField3);

		add(scrollPane1);

		add(panel1);
	}
}
