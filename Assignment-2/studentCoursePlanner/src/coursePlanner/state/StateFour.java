package coursePlanner.state;

import java.util.ArrayList;
import java.util.Arrays;

import coursePlanner.planner.Student;

public class StateFour implements CoursePlannerStateI {
	private ContextI context;
	final ArrayList<String> group4 = new ArrayList<>(Arrays.asList("M", "N", "O", "P"));

	StateFour(ContextI contextIn) {
		context = contextIn;
	}

	@Override
	public void updateState() {

	}
	
	public void setState(Student student) {
		student.setCurrState(4);
	}
	
	@Override
	public int getGroupCourse(Student student) {
		int count = 0;
		for (String course_assigned : student.getAssignedCourses()) {

			if (group4.contains(course_assigned)) {
				count = count + 1;
			}
		}

		return count;
	}

}
