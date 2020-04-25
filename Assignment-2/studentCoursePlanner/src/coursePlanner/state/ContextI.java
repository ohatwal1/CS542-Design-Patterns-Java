package coursePlanner.state;

import coursePlanner.planner.Student;

public interface ContextI {
	
	public void setGradStatus(Student student);
	public void updateState(Student student);

}
