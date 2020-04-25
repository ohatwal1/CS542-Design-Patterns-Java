package loadbalancer.driver;

import loadbalancer.util.FileProcessor;

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

			FileProcessor fileProcessor = new FileProcessor();
			System.out.println("Success");
			fileProcessor.executeCommands(fileProcessor.fileRead(args));
		} else {
			System.exit(0);
		}

	}

	private static boolean validate(String[] args) {
		if (args == null || args.length != 2 || args[0].equals("${arg0}") || args[1].equals("${arg1}")) {

			System.err.println("Error: Incorrect number of arguments. Program accepts 2 arguments.");
			return false;
		}
		return true;

	}
}
