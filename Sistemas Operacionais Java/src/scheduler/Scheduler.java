package scheduler;

public class Scheduler {

	private Algorithm algorithm;
	private int timeSlice;
	
	public Scheduler(Algorithm algorithm, int timeSlice) {
		this.algorithm = algorithm;
		this.setTimeSlice(timeSlice);
	}

	public Algorithm getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(Algorithm algorithm) {
		this.algorithm = algorithm;
	}

	public void setTimeSlice(int timeSlice) {
		this.timeSlice = timeSlice;
	}

	public int getTimeSlice() {
		return timeSlice;
	}
}