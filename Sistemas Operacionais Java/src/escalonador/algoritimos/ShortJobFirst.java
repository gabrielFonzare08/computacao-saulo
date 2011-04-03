package escalonador.algoritimos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import escalonador.Algorithm;
import escalonador.Process;

/**
 * Implementação da classe {@link Comparator} para tratar com processos.
 * O algoritmo simplemente compara os {@link Process#getComputingTime()} de cada processo.
 * */
class ProcessComparator implements Comparator<Process> {
	@Override
	public int compare(Process p1, Process p2) {
		return p1.getComputingTime() - p2.getComputingTime();
	}		
}

public class ShortJobFirst extends Algorithm {
	
	private ArrayList<Process> processes;
	
	
	public ShortJobFirst() {
		processes = new ArrayList<Process>();
	}
	
	public void addProcess(Process process) {
		processes.add(process);		
	}
	
	public void setProcess(int i, Process process) {
		processes.set(i, process);
	}

	@Override
	public void run() {
		Collections.sort(processes, new ProcessComparator());
		int terminatedProcess = 0;
		
		main: while(true) {
			
			for(Process process: processes) {
				if(process.isTerminated()) {
					terminatedProcess++;
				}
			}
			
			if(terminatedProcess == processes.size()) {
				break main;
			}
			
		}
	}
	
	public static void main(String[] args) {
		
	}
}