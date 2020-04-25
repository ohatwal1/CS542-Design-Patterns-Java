package coursePlanner.state;

import java.util.ArrayList;
import java.util.Arrays;

import coursePlanner.planner.Student;

public class StateTwo implements CoursePlannerStateI {

	private ContextI context;
	final ArrayList<String> group2 = new ArrayList<>(Arrays.asList("E", "F", "G", "H"));

	StateTwo(ContextI contextIn){
		context = contextIn;
	}
	
	@Override
	public void updateState() {

	}
	
	public void setState(Student student) {
		student.setCurrState(2);
	}

	@Override
	public int getGroupCourse(Student student) {
		int count = 0;
		for (String course_assigned : student.getAssignedCourses()) {

			if (group2.contains(course_assigned)) {
				count = count + 1;
			}
		}

		return count;
	}

}
