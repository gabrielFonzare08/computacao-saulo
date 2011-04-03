package escalonador.view;

import java.awt.Dimension;

import javax.swing.JList;

public class ProcessList extends JList {

	private static final long serialVersionUID = -1070043748321509905L;
	
	public ProcessList() {
		setPreferredSize(new Dimension(100, 400));
		setValueIsAdjusting(true);
	}

}
