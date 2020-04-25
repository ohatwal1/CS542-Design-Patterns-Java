package coursePlanner.state;

import java.util.ArrayList;
import java.util.Arrays;

import coursePlanner.planner.Student;

public class StateOne implements CoursePlannerStateI {

	final ArrayList<String> group1 = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));

	private ContextI context;

	StateOne(ContextI contextIn) {
		context = contextIn;
	}

	@Override
	public void updateState() {

	}

	public void setState(Student student) {
		student.setCurrState(1);
	}

	@Override
	public int getGroupCourse(Student student) {
		int count = 0;
		for (String course_assigned : student.getAssignedCourses()) {

			if (group1.contains(course_assigned)) {
				count = count + 1;
			}
		}

		return count;
	}

}
