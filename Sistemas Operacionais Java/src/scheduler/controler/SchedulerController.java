package scheduler.controler;

import scheduler.Algorithm;
import scheduler.Scheduler;

public class SchedulerController {
	private Scheduler scheduler;
	
	private static SchedulerController instance;
	
	
	public SchedulerController(Algorithm algorithm){
		scheduler = new Scheduler(algorithm);
		
	}
	
	public static SchedulerController getInstance(Algorithm algorithm) {
		if(instance == null) {
			instance = new SchedulerController(algorithm);
		}
		return instance;
	}
	
	
}
