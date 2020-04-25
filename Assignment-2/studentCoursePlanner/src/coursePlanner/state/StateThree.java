package coursePlanner.state;

import java.util.ArrayList;
import java.util.Arrays;

import coursePlanner.planner.Student;

public class StateThree implements CoursePlannerStateI {
	private ContextI context;
	final ArrayList<String> group3 = new ArrayList<>(Arrays.asList("I", "J", "K", "L"));

	StateThree(ContextI contextIn) {
		context = contextIn;
	}

	@Override
	public void updateState() {

	}
	
	public void setState(Student student) {
		student.setCurrState(3);
	}

	@Override
	public int getGroupCourse(Student student) {
		int count = 0;
		for (String course_assigned : student.getAssignedCourses()) {

			if (group3.contains(course_assigned)) {
				count = count + 1;
			}
		}

		return count;
	}

}
