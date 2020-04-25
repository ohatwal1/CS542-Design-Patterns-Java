package coursePlanner.planner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Student {

	private int studentId;
	private int currentSem;
	private int totalAssignedCourses = 0;
	private boolean graduated = false;
	private ArrayList<String> coursePreference = new ArrayList<>();
	private ArrayList<String> assignedCourses = new ArrayList<>();
	private ArrayList<String> waitList = new ArrayList<>();
	private int stateChangeCount = 0;
	private int currState = 1;
	
//	curr_state: int
//	state_change: int
//	wait_list: [] 
//	completed_courses: {[]}
//	preferred_courses: [] //input
//	curr_sem: int
//	student_id: int


	public int getCurrState() {
		return currState;
	}

	public void setCurrState(int currState) {
		this.currState = currState;
	}

	public int getStateChangeCount() {
		return stateChangeCount;
	}

	public void setStateChangeCount(int stateChangeCount) {
		this.stateChangeCount = stateChangeCount;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getCurrentSem() {
		return currentSem;
	}

	public void setCurrentSem(int currentSem) {
		this.currentSem = currentSem;
	}

	public ArrayList<String> getWaitList() {
		return waitList;
	}

	public void setWaitList(ArrayList<String> waitList) {
		this.waitList = waitList;
	}

	public Student() {
		// curr_state: int
		// state_change: int
		// wait_list: []
		// completed_courses: {[]}
		// preferred_courses: [] //input
		// curr_sem: int
		// student_id: int

	}

	// public int getbNumber() {
	// return studentId;
	// }

	public int getSemester() {
		return currentSem;
	}

	public void setSemester(int semester) {
		this.currentSem = semester;
	}

	public int getTotalAssignedCourses() {
		return totalAssignedCourses;
	}

	public void setTotalAssignedCourses(int totalAssignedCourses) {
		this.totalAssignedCourses = totalAssignedCourses;
	}

	public ArrayList<String> getAssignedCourses() {
		return assignedCourses;
	}

	public void setAssignedCourses(ArrayList<String> assignedCourses) {
		this.assignedCourses = assignedCourses;
	}

	// public void setbNumber(int bNumber) {
	// this.studentId = bNumber;
	// }

	public List<String> getCoursePreference() {
		return coursePreference;
	}

	public void setCoursePreference(ArrayList<String> coursePreference) {
		this.coursePreference = coursePreference;
	}

	public boolean isGraduated() {
		return graduated;
	}

	public void setGraduated(boolean graduated) {
		this.graduated = graduated;
	}

	@Override
	public String toString() {
		return "Student [bNumber=" + studentId + ", semester=" + currentSem + ", totalAssignedCourses="
				+ totalAssignedCourses + ", coursePreference=" + coursePreference + ", assignedCourses="
				+ assignedCourses + "]";
	}

}
