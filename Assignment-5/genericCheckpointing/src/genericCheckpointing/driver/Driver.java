package genericCheckpointing.driver;

import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.MyLogger;
import genericCheckpointing.util.MySpecialTypes;
import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;


public class Driver {

	/**
	 * Validates arguments
	 *
	 * @param args
	 *            arguments to be validated
	 */
	private static void validateArgs(String[] args) {

		// Validate number of arguments
		if (args == null || args.length != 4 || args[0].equals("${arg0}") || args[1].equals("${arg1}")
				|| args[2].equals("${arg2}") || args[3].equals("${arg3}")) {
			MyLogger.writeMessage("Error: Incorrect number of arguments. Program accepts 4 arguments."
					+ "Usage: <program_name> <mode> <N> <checkpoint_file_name>", MyLogger.DebugLevel.NO_OUTPUT);
			System.exit(1);
		}

		// Validate first argument - mode
		String mode = args[0];

		if (mode.equals("deserser")) {
			// Validate second argument - checkpointFileName
			// Checkpoint file should exist and be non-empty
			String checkpointFileName = args[1];
			File checkpointFile = new File(checkpointFileName);
			if (!checkpointFile.exists() || checkpointFile.length() == 0) {
				MyLogger.writeMessage("Error: Checkpoint file doesn't exist or empty : " + checkpointFileName,
						MyLogger.DebugLevel.NO_OUTPUT);
				System.exit(1);
			}


			// Validate fourth argument - Debug value
			if ((Integer.valueOf(args[3]) <= 0) || (Integer.valueOf(args[3]) > 4)) {
				MyLogger.writeMessage("Error, the foruth argument must be in between 1 to 4.",
						MyLogger.DebugLevel.NO_OUTPUT);
				System.exit(1);
			}

		} else {
			MyLogger.writeMessage("Error: Incorrect mode : " + mode, MyLogger.DebugLevel.NO_OUTPUT);
			System.exit(1);
		}
	}

	/**
	 * Reads a vector of SerializableObject type from the dynamic proxy of type
	 * StoreRestoreI.
	 *
	 * @param cpointRef
	 *            Dynamic proxy reference
	 * @param vector
	 *            Vector to be deserialized into
	 */
	private static void readVectorFromProxy(StoreRestoreI cpointRef, Vector<SerializableObject> vector) {
		SerializableObject myRecordRet = null;
		while(true)
		{
			myRecordRet = ((RestoreI) cpointRef).readObj("XML");
			if(myRecordRet == null)
				break;
			vector.add(myRecordRet);
		}
	}

	/**
	 * Writes the given vector of SerializableObject type into the dynamic proxy of
	 * type StoreRestoreI.
	 *
	 * @param cpointRef
	 *            Dynamic proxy reference
	 * @param vector
	 *            Vector to be serialized
	 */
	private static void writeVectorToProxy(StoreRestoreI cpointRef, Vector<SerializableObject> vector) {
		// Write the OldVector objects to file
		for (int i = 0; i < vector.size(); i++) {
			SerializableObject obj = vector.get(i);

			if (obj instanceof MyAllTypesFirst)
				((StoreI) cpointRef).writeObj((MyAllTypesFirst) obj, "XML");

			else if (obj instanceof MyAllTypesSecond)
				((StoreI) cpointRef).writeObj((MyAllTypesSecond) obj, "XML");

			else if (obj instanceof MySpecialTypes)
				((StoreI) cpointRef).writeObj((MySpecialTypes) obj, "XML");
		}
	}
	
	

	/**
	 * Calculates object mismatches between two vectors using equals() method.
	 * Prints number of mismatches
	 *
	 * @param inVector
	 *            First vector
	 * @param outVector
	 *            Second vector
	 */
	private static void printMismatches(Vector<SerializableObject> inVector, Vector<SerializableObject> outVector) {
		// Count and print mismatches
		if (inVector.size() != outVector.size()) {
			MyLogger.writeMessage("\n" + " In Vector and Out Vector size mismatched\n", MyLogger.DebugLevel.NO_OUTPUT);
			System.exit(1);
		}
		int mismatchedObjects = 0;
		for (int i = 0; i < inVector.size(); i++) {

			if (!inVector.get(i).equals(outVector.get(i))) {
				// Objects mismatched.
				mismatchedObjects++;
			}
		}
		MyLogger.writeMessage("\n" + mismatchedObjects + " objects mismatched\n", MyLogger.DebugLevel.NO_OUTPUT);
	}
	
	

	public static void main(String[] args) {

		validateArgs(args);


		// Read the input arguments
		// String mode = args[0];
		String checkpointFileName = args[1];
		String checkpointOutputFileName = args[2];
		int debugValue = Integer.valueOf(args[3]);

		
		ProxyCreator pc = new ProxyCreator();
		// create an instance of StoreRestoreHandler (which implements
		// the InvocationHandler
		StoreRestoreHandler handler = new StoreRestoreHandler();
		StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(new Class[] { StoreI.class, RestoreI.class }, handler);


		// Set the checkpoint file name and checkpoint-verify file name
		handler.setCheckpointInFileName(checkpointFileName);
		handler.setCheckpointOutFileName(checkpointOutputFileName);

		
		// initialize in and out vector will be used for reading from file(deserialize)
		// and write to verify file (Serialize)
		Vector<SerializableObject> inVector = new Vector<>();
		Vector<SerializableObject> outVector = new Vector<>();

		
		// Read the objects from checkpoint checkpoint file into inVector
		readVectorFromProxy(cpointRef, inVector);

		
		// Serialize inVector objects to checkpoint-verify file
		writeVectorToProxy(cpointRef, inVector);

		
		ProxyCreator pcVerify = new ProxyCreator();
		// create an instance of StoreRestoreHandler (which implements
		// the InvocationHandler
		StoreRestoreHandler handlerVerify = new StoreRestoreHandler();
		StoreRestoreI cpointRefVerify = (StoreRestoreI) pcVerify.createProxy(new Class[] { StoreI.class, RestoreI.class }, handlerVerify);
		handlerVerify.setCheckpointInFileName(checkpointOutputFileName);
		
		
		// Read the objects from checkpoint-verify file into outVector
		readVectorFromProxy(cpointRefVerify, outVector);

		
		// Count and print mismatches
		printMismatches(inVector, outVector);

	}
}
