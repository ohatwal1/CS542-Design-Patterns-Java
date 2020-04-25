package coursesRegistration.driver;

import coursesRegistration.scheduler.CourseSchedular;

/**
 * @author Omkar Hatwalne
 *
 */
public class Driver {
	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as argX, in case the argument value
		 * is not given java takes the default value specified in build.xml. To avoid
		 * that, below condition is used
		 */

		// Validate the parameters, if valid create new object of courseSchedule
		// Call schedule() and assignCourses()

		if (validate(args)) {
			
			CourseSchedular courseSchedule = new CourseSchedular();
			courseSchedule.schedule(args);
			courseSchedule.assignCourses();

			System.out.println("Success");
		} else {
			System.exit(0);
		}

//		System.out.println("Hello World! Lets get started with the assignment");

	}

	private static boolean validate(String[] args) {
		if (args == null || args.length != 3 || args[0].equals("${arg0}") || args[1].equals("${arg1}")
				|| args[2].equals("${arg2}")) {

			System.err.println("Error: Incorrect number of arguments. Program accepts 3 arguments.");
			return false;
		}
		return true;

	}
}
