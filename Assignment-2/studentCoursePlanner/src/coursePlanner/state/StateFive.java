package coursePlanner.state;

import java.util.ArrayList;
import java.util.Arrays;

import coursePlanner.planner.Student;

public class StateFive implements CoursePlannerStateI {
	private ContextI context;
	final ArrayList<String> group5 = new ArrayList<>(Arrays.asList("Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"));

	StateFive(ContextI contextIn){
		context = contextIn;
	}
	
	@Override
	public void updateState() {

	}
	
	public void setState(Student student) {
		student.setCurrState(5);
	}
	
	@Override
	public int getGroupCourse(Student student) {
		int count = 0;
		for (String course_assigned : student.getAssignedCourses()) {

			if (group5.contains(course_assigned)) {
				count = count + 1;
			}
		}

		return count;
	}

}
