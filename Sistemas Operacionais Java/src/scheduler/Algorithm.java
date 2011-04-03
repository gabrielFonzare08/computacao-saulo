package scheduler;

import java.util.Iterator;
import java.util.List;

import scheduler.algorithms.Preemptive;
import scheduler.algorithms.RoundRobin;
import scheduler.algorithms.ShortJobFirst;

/**
 * It's just a abstract class to represent a Algoritm.
 * The concrete classes who extends {@link Algorithm} are:
 * <ul>
 * 	<li></li>
 * </ul>
 * */
public abstract class Algorithm implements Runnable {
	
	protected List<Process> processes;
	
	public void addProcess(Process process) {
		processes.add(process);		
	}
	
	public void setProcess(int index, Process process) {
		processes.set(index, process);
	}
	
	public void remove(int index) {
		processes.remove(index);
	}
	
	public static Algorithm getAlgorithmByName(String algorithmName) {
		if(algorithmName.equalsIgnoreCase("round robin")) {
			return new RoundRobin();
		} else if(algorithmName.equalsIgnoreCase("short job first")) {
			return new ShortJobFirst();
		} else {
			return new Preemptive();
		}
	}
}

