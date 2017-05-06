package asteroids.model.programs;

public class ProgramExpression extends ProgramLine {

	public Object getValue() {
		return this.value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	private Object value;
	
}
