package asteroids.model.programs;

import asteroids.model.exceptions.IllegalExecutionTimeException;

public abstract class ProgramLine {
	
	public ProgramLine() {
		
	}
	
	public void setExecutionTime(double time) {
		if (time < 0) {
			throw new IllegalExecutionTimeException(time);
		}
		else
			this.executionTime = time;
	}
	
	public double getExecutionTime() {
		return this.executionTime;
	}
	
	private double executionTime = 0;
	
}
