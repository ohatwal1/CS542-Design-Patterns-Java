package coursePlanner.driver;

import coursePlanner.state.Context;
import coursePlanner.util.FileProcessor;

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

		if (validate(args)) {
			
//			Context context = new Context();
			FileProcessor fileProcessor = new FileProcessor();
			fileProcessor.getStudentDetails(args);
			
//			context.getInputData(args);

			System.out.println("Success");
		} else {
			System.err.println(" Invalid input");
			System.exit(0);
		}

		// System.out.println("Hello World! Lets get started with the assignment");

	}

	private static boolean validate(String[] args) {
		if (args == null || args.length != 2 || args[0].equals("${arg0}") || args[1].equals("${arg1}")) {

			System.err.println("Error: Incorrect number of arguments. Program accepts 2 arguments.");
			return false;
		}
		return true;

	}
}
