/**
 * 
 */
package coursePlanner.state;

import coursePlanner.planner.Student;

/**
 * @author Omkar
 *
 */
public interface CoursePlannerStateI {

	public void updateState();

	public int getGroupCourse(Student student);
	
	public void setState(Student student);
	

}
