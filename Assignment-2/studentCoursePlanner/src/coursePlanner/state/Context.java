package coursePlanner.state;

import java.util.ArrayList;

import coursePlanner.planner.Student;

import coursePlanner.util.FileProcessor;

public class Context implements ContextI {

	private CoursePlannerStateI stateOne;
	private CoursePlannerStateI stateTwo;
	private CoursePlannerStateI stateThree;
	private CoursePlannerStateI stateFour;
	private CoursePlannerStateI stateFive;

	private CoursePlannerStateI currentState;

	public Context() {

		stateOne = new StateOne(this);
		stateTwo = new StateTwo(this);
		stateThree = new StateThree(this);
		stateFour = new StateFour(this);
		stateFive = new StateFive(this);

		// Default State
		currentState = stateOne;

	}

	public void setGradStatus(Student student) {
		if (student.getAssignedCourses().size() >= 10) {
			int group1 = stateOne.getGroupCourse(student);
			int group2 = stateTwo.getGroupCourse(student);
			int group3 = stateThree.getGroupCourse(student);
			int group4 = stateFour.getGroupCourse(student);
			int group5 = stateFive.getGroupCourse(student);
			if (group1 >= 2 && group2 >= 2 && group3 >= 2 && group4 >= 2 && group5 >= 2) {
				student.setGraduated(true);
			}
		}
	}

	@Override
	public void updateState(Student student) {
		int currState = student.getCurrState();
		ArrayList<Integer> allGroupCount = new ArrayList<>();
		allGroupCount.add(stateOne.getGroupCourse(student));
		allGroupCount.add(stateTwo.getGroupCourse(student));
		allGroupCount.add(stateThree.getGroupCourse(student));
		allGroupCount.add(stateFour.getGroupCourse(student));
		allGroupCount.add(stateFive.getGroupCourse(student));
		int maxCount = 0;
		int maxGroup = 1;
		for (int i = 0; i < allGroupCount.size(); i++) {
			if (allGroupCount.get(i) > maxCount) {
				maxCount = allGroupCount.get(i);
				maxGroup = i + 1;
			}
		}

		if (maxGroup != currState) {
			student.setStateChangeCount(student.getStateChangeCount() + 1);
			if (maxGroup == 1) {
				currentState = stateOne;
			} else if (maxGroup == 2) {
				currentState = stateTwo;
			} else if (maxGroup == 3) {
				currentState = stateThree;
			} else if (maxGroup == 4) {
				currentState = stateFour;
			} else if (maxGroup == 5) {
				currentState = stateFive;
			}
			currentState.setState(student);
		}
	}

	@Override
	public String toString() {
		return "Context [stateOne=" + stateOne + ", stateTwo=" + stateTwo + ", stateThree=" + stateThree
				+ ", stateFour=" + stateFour + ", stateFive=" + stateFive + ", currentState=" + currentState
				+ ", fileProcessor=" + "]";
	}

}
