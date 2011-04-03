package escalonador;

public class Process {

	private int pid;
	private int priority;
	private ProcessState state;
	private int computingTime;
	private float IOrate;
	private float IOrateTime;
	private int[] times;

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public ProcessState getState() {
		return state;
	}

	public void setState(ProcessState state) {
		this.state = state;
	}

	public int getComputingTime() {
		return computingTime;
	}

	public void setComputingTime(int computingTime) {
		this.computingTime = computingTime;
	}

	public float getIOrate() {
		return IOrate;
	}

	public void setIOrate(float iOrate) {
		IOrate = iOrate;
	}

	public float getIOrateTime() {
		return IOrateTime;
	}

	public void setIOrateTime(float iOrateTime) {
		IOrateTime = iOrateTime;
	}

	public int[] getTimes() {
		return times;
	}

	public void setTimes(int[] times) {
		this.times = times;
	}
	
	public boolean isReady() {
		return state == ProcessState.READY;
	}
	
	public boolean isRunning() {
		return state == ProcessState.RUNNING;
	}
	
	public boolean isBlocked() {
		return state == ProcessState.BLOCKED;
	}
	
	public boolean isTerminated() {
		return state == ProcessState.TERMINATED;
	}
	
	@Override
	public String toString() {
		return computingTime + "";
	}
}