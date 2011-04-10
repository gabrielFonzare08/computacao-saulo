package escalonador.visao.paineis;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import escalonador.visao.Janela;
import escalonador.visao.Painel;

public class PainelSimulacao extends Painel {

	final long serialVersionUID = 1L;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JTextField textField1;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	private JList dataList1;
	private JList dataList2;
	private JPanel panel1;


	public PainelSimulacao(Janela janela) {
		super(janela);
		initComponents();
	}

	@Override
	public void initComponents() {
		// TODO Auto-generated method stub
		setSize(600, 480);
		setLayout(null);
		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		textField1 = new JTextField();
		dataList1 = new JList();
		dataList2 = new JList();
		scrollPane1 = new JScrollPane(dataList1);
		scrollPane2 = new JScrollPane(dataList2);
		panel1 = new JPanel();

		label1.setBounds(20, 30, 120, 100);
		label1.setText("Process in CPU:");

		textField1.setBounds(160, 72, 40, 20);
		textField1.setEnabled(false);

		label2.setBounds(308, 8, 100, 20);
		label2.setText("Blocked:");

		label3.setBounds(472, 8, 100, 20);
		label3.setText("Ready:");

		scrollPane1.setBounds(270, 40, 140, 380);
		scrollPane2.setBounds(430, 40, 140, 380);

		panel1.setBounds(10, 60, 200, 44);
		panel1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		panel1.setLayout(null);

		add(label1);
		add(label2);
		add(label3);
		add(textField1);
		add(scrollPane1);
		add(scrollPane2);
		add(panel1);
		setVisible(true);
	}
		



	@Override
	public void addEvents() {
		// TODO Auto-generated method stub

	}

}
