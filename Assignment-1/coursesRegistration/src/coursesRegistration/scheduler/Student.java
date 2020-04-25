package coursesRegistration.scheduler;

import java.util.ArrayList;
import java.util.List;

public class Student {

	public Student() {
	};

	@Override
	public String toString() {
		return "Student [bNumber=" + bNumber + ", year=" + year + ", coursePreferences=" + coursePreferences + "]";
	}

	private int bNumber;
	private StudentLevel year;
	private int totalAssignedCourses = 0;

	private List<Character> coursePreferences = new ArrayList<>();

	public int getTotalAssignedCourses() {
		return totalAssignedCourses;
	}

	public void setTotalAssignedCourses(int totalAssignedCourses) {
		this.totalAssignedCourses = totalAssignedCourses;
	}

	public int getbNumber() {
		return bNumber;
	}

	public void setbNumber(int bNumber) {
		this.bNumber = bNumber;
	}

	public StudentLevel getYear() {
		return year;
	}

	public void setYear(StudentLevel year) {
		this.year = year;
	}

	public List<Character> getCoursePreferences() {
		return coursePreferences;
	}

	public void setCoursePreferences(List<Character> coursePreferences) {
		this.coursePreferences = coursePreferences;
	}

}
