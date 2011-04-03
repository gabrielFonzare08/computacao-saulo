package scheduler.controler;

import java.util.Iterator;

import scheduler.Scheduler;

public class SchedulerController {
	private Scheduler scheduler;
	private Iterator<Process> processCreated;
	
	
	public SchedulerController(Iterator<Process> processCreated){
		this.processCreated = processCreated;
		
	}
	
	
	
}
