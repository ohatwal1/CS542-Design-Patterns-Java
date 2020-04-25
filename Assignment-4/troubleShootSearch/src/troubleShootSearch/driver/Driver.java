package troubleShootSearch.driver;

import java.io.File;
import java.util.ArrayList;

import troubleShootSearch.entities.Visitables;
import troubleShootSearch.util.FileProcessor;
import troubleShootSearch.util.MyLogger;
import troubleShootSearch.visitor.UserVisitor;

/**
 * @author Omkar Hatwalne
 *
 */
public class Driver {

	private static final int DEBUG_VALUE_MIN = 0;
	private static final int DEBUG_VALUE_MAX = 4;

	static StringBuilder sb = new StringBuilder();

	/**
	 * Validates and converts given string into an integer.
	 *
	 * @param arg
	 *            Input String
	 * @return Integer
	 */
	private static int validateInteger(String[] arg) {
		int result = -1;
		try {
			result = Integer.parseInt(arg[4]);
		} catch (NumberFormatException e) {
			MyLogger.writeMessage("Error, the fifth argument must be an integer.", MyLogger.DebugLevel.NO_OUTPUT);
			System.exit(1);
		} finally {
			assert true;
		}
		return result;
	}

	public static void main(String[] args) {

		if (validate(args)) {

			// Read CLI arguments into variables
			int debugValue = Integer.valueOf(args[4]);
			String outPutFilePath = args[3];

			// Set debug level for logger
			MyLogger.setDebugValue(debugValue);

			// Create necessary objects
			FileProcessor fileProcess = new FileProcessor();
			ArrayList<Visitables> dataMatchingStructures = fileProcess.getVisitables(args);
			ArrayList<String> userQueries = fileProcess.readDataFromUser(args);
			processQueries(userQueries, dataMatchingStructures);
			fileProcess.sendDataToProcessor(sb, outPutFilePath);

			// System.out.println("Success");
			
		} else {
			System.exit(0);
		}

	}

	private static void processQueries(ArrayList<String> userQueries, ArrayList<Visitables> dataMatchingStructures) {

		for (String userQuery : userQueries) {
			UserVisitor userVisitor = new UserVisitor(userQuery.split(" "));

			MyLogger.writeMessage("user input - " + userQuery.toString(), MyLogger.DebugLevel.SUCCESS);
			sb.append("user input - " + userQuery.toString() + "\n");

			for (Visitables dataStructure : dataMatchingStructures) {
				dataStructure.accept(userVisitor);
			}
//-----------------------------------------------display exact matched-------------------------------------------------------------------// 
			if (userVisitor.getExactMatchedSolutions().size() > 0) {
				MyLogger.writeMessage(
						"Exact Match\n-----------\n" + userVisitor.getExactMatchedSolutions().toString()
								.replace("=", ". ").replace("{", "").replace("}", "").replace("., ", ".\n") + "\n",
						MyLogger.DebugLevel.SUCCESS);

				sb.append("Exact Match\n-----------\n" + userVisitor.getExactMatchedSolutions().toString()
						.replace("=", ". ").replace("{", "").replace("}", "").replace("., ", ".\n") + "\n" + "\n");

			} else {
				MyLogger.writeMessage("Exact Match\n-----------\n" + "No exact match\n", MyLogger.DebugLevel.SUCCESS);
				sb.append("Exact Match\n-----------\n" + "No exact match\n" + "\n");

			}
			
			
//-----------------------------------------------------------------------------------------------------------------------------------------//			
//-----------------------------------------------display partial matched-------------------------------------------------------------------//
		
			
			ArrayList<String> partialMatchData = userVisitor.getPartialMatchedSolutions();
			if (partialMatchData.size() > 0) {
				String sol = partialMatchData.get(0);

				MyLogger.writeMessage("Naive Stemming Match\n--------------------\n" + "Word Count = " + sol.charAt(0) + "\n"
						+ "Line Numbers = " + sol.substring(1, sol.length()).replace(" ", "").replace("[", "")
								.replace("]", ",").replaceAll(",$", "")
						+ "\n", MyLogger.DebugLevel.SUCCESS);

				sb.append("Naive Stemming Match\n--------------------\n"
						+ "Word Count = " + sol.charAt(0) + "\n" + "Line Numbers = " + sol.substring(1, sol.length())
								.replace(" ", "").replace("[", "").replace("]", ",").replaceAll(",$", "")
						+ "\n" + "\n");

			} else
			{
				MyLogger.writeMessage("Naive Stemming Match\n--------------------\nNo naive stemming match\n",
						MyLogger.DebugLevel.SUCCESS);
				sb.append("Naive Stemming Match\n--------------------\nNo naive stemming match\n" + "\n");
			}	
			
//---------------------------------------------------------------------------------------------------------------------------------------//
//-----------------------------------------------display semantic matched----------------------------------------------------------------// 
			
			
			if (userVisitor.getSemanticMatchedSolutions().size() > 0) {
				MyLogger.writeMessage(
						"Semantic Match\n--------------\n" + userVisitor.getSemanticMatchedSolutions().toString()
								.replace("=", ". ").replace("{", "").replace("}", "").replace("., ", ".\n") + "\n",
						MyLogger.DebugLevel.SUCCESS);

				sb.append("Semantic Match\n--------------\n" + userVisitor.getSemanticMatchedSolutions().toString()
						.replace("=", ". ").replace("{", "").replace("}", "").replace("., ", ".\n") + "\n" + "\n");

			} else {
				MyLogger.writeMessage("Semantic Match\n--------------\n" + "No semantic match\n", MyLogger.DebugLevel.SUCCESS);
				sb.append("Semantic Match\n--------------\n" + "No semantic match\n" + "\n");

			}
//-----------------------------------------------------------------------------------------------------------------------------------------//
		}
	}

	/*
	 * As the build.xml specifies the arguments as argX, in case the argument value
	 * is not given java takes the default value specified in build.xml. To avoid
	 * that, below condition is used
	 */

	private static boolean validate(String[] args) {
		if (args == null || args.length != 5 || args[0].equals("${arg0}") || args[1].equals("${arg1}")
				|| args[2].equals("${arg2}") || args[3].equals("${arg3}") || args[4].equals("${arg4}")) {

			MyLogger.writeMessage(
					"Error: Incorrect number of arguments. Program accepts 5 arguments."
							+ "Usage: technicalInfo.txt userInput.txt synonyms.txt output.txt <debug_value>",
					MyLogger.DebugLevel.NO_OUTPUT);
			return false;
		}

		// Validate the technical info file exists
		String technicalInfoFilePath = args[0];
		File inputTechnicalInfoFile = new File(technicalInfoFilePath);
		if (!inputTechnicalInfoFile.exists() || inputTechnicalInfoFile.isDirectory()) {
			MyLogger.writeMessage("Error: Technical Info File not found : " + technicalInfoFilePath,
					MyLogger.DebugLevel.NO_OUTPUT);
			System.exit(1);
		}

		// Validate the User input file exists
		String userInputFilePath = args[1];
		File inputUserFile = new File(userInputFilePath);
		if (!inputUserFile.exists() || inputUserFile.isDirectory()) {
			MyLogger.writeMessage("Error: User Input File not found : " + userInputFilePath,
					MyLogger.DebugLevel.NO_OUTPUT);
			System.exit(1);
		}

		// Validate the User input file exists
		String synonymFilePath = args[2];
		File inputSynonymFile = new File(userInputFilePath);
		if (!inputSynonymFile.exists() || inputSynonymFile.isDirectory()) {
			MyLogger.writeMessage("Error: Synonym File not found : " + synonymFilePath, MyLogger.DebugLevel.NO_OUTPUT);
			System.exit(1);
		}


		// Validate that DEBUG_VALUE is between 0 and 4
		int debugValue = validateInteger(args);
		if (debugValue < DEBUG_VALUE_MIN || debugValue > DEBUG_VALUE_MAX) {
			MyLogger.writeMessage(
					"Error, debug value can only be between " + DEBUG_VALUE_MIN + " and " + DEBUG_VALUE_MAX,
					MyLogger.DebugLevel.NO_OUTPUT);
			System.exit(1);
		}

		return true;

	}
}
