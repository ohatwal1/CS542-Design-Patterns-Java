package coursesRegistration.scheduler;

public class Course {

	public Course() {
	}

	@Override
	public String toString() {
		return "Course [courseName=" + courseName + ", capacity=" + capacity + ", classTiming=" + classTiming + "]";
	}

	private String courseName = "";
	private int capacity;
	private int classTiming;

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getClassTiming() {
		return classTiming;
	}

	public void setClassTiming(int classTiming) {
		this.classTiming = classTiming;
	}

}
