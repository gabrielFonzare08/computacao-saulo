package scheduler.controler;

import java.util.ArrayList;

import scheduler.view.EditProcessPanel;
import scheduler.view.ProcessList;
import scheduler.Process;
import scheduler.ProcessState;

public class ProcessController {
	
	private EditProcessPanel editProcessPanel;
	private ProcessList list;
	
	private ArrayList<Process> processes;
	private static int pid = 0;
	
	private static ProcessController instance;
	
	public static ProcessController getInstance(EditProcessPanel editProcessPanel, ProcessList list) {
		if(instance == null) {
			instance = new ProcessController(editProcessPanel, list);
		}
		return instance;
	}
	
	public ProcessController(EditProcessPanel editProcessPanel, ProcessList list) {
		this.editProcessPanel = editProcessPanel;
		this.list = list;
		processes = new ArrayList<Process>();
	}
	
	public void add() {
		Process p = new Process();
		
		try {
			
			p.setPid(++pid);
			p.setState(ProcessState.READY);
			
			int priority = Integer.parseInt(editProcessPanel.getPriority());
			p.setPriority(priority);
			
			int computingTime = Integer.parseInt(editProcessPanel.getComputingTime());
			p.setComputingTime(computingTime);
			
			float iOrate = Float.parseFloat(editProcessPanel.getIoRate());
			p.setIOrate(iOrate);
			
			float iOrateTime = Float.parseFloat(editProcessPanel.getIoRateTime());
			p.setIOrateTime(iOrateTime);
			
		} catch (Exception e) {
			return;
		}
		
		int index = processes.size();
		
		processes.add(p);
		updateList();
		list.setSelectedIndex(index);
	}
	
	private void updateList() {
		list.setListData(processes.toArray());
	}
	
	public void remove() {
		if(!processes.isEmpty()) {
			int index = list.getSelectedIndex();
			processes.remove(index);
			updateList();		
			list.setSelectedIndex(--index > 0 ? index : 0);			
		}
	}
	
	public void simulate() {
		System.out.println("simulate");
	}
}