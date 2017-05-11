package asteroids.model.programs;

import asteroids.model.Ship;

public interface ProgramLine {
	
	public Program getProgram();
	public void setProgram(Program program);
	
	public default Ship getShip() {
		if (getProgram()!=null)
			return getProgram().getShip();
		else
			return null;
	}
	
}
