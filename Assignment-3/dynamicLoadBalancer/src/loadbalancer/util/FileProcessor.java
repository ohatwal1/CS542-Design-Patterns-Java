package loadbalancer.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;



import loadbalancer.observer.LoadBalancer;
import loadbalancer.subject.Cluster;
import loadbalancer.subject.Operations_Clusters;

public class FileProcessor {



	static String outputFilePath = "";

	/**
	 * @param filePath
	 * 
	 *            Extract the data from input file and pass it to assignCourses
	 *            function.
	 */

	public ArrayList<String> fileRead(String[] filePath) {
		Scanner sc = null;
		ArrayList<String> allCommands = new ArrayList<String>();
		try {
			File file = new File(filePath[0]);
			sc = new Scanner(file);

			outputFilePath = filePath[1];
			// Start reading file
			while (sc.hasNextLine()) {
				allCommands.add(sc.nextLine());

			}
			// System.out.println(allCommands);
			// logger.info(allCommands);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			sc.close();
		}
		return allCommands;
	}

	public void executeCommands(ArrayList<String> allCommands) {

		Results result = new Results();
		StringBuilder sb = new StringBuilder();

		Cluster cluster = new Cluster();

		for (String commands : allCommands) {
			String command[] = commands.split(" ");

			String hostName = "";
			String serviceName = "";
			String URL = "";
			Operations_Clusters operations = Operations_Clusters.valueOf(command[0]);

			switch (operations) {
			case CLUSTER_OP__SCALE_UP:
				hostName = command[1];
				sb.append(cluster.clusterAddHost(hostName) + "\n");
				// logger.info(hostName);
				break;
			case CLUSTER_OP__SCALE_DOWN:
				hostName = command[1];
				sb.append(cluster.clusterRemoveHost(hostName) + "\n");
				break;
			case SERVICE_OP__ADD_SERVICE:
				serviceName = command[1];
				URL = command[2];
				ArrayList<String> hostNames = new ArrayList<>();
				hostNames.addAll(Arrays.asList(command[3].split(",")));
				sb.append(cluster.addService(serviceName, URL, hostNames) + "\n");
				break;
			case SERVICE_OP__REMOVE_SERVICE:
				serviceName = command[1];
				sb.append(cluster.removeService(serviceName) + "\n");
				break;
			case REQUEST:
				serviceName = command[1];
				sb.append(cluster.request(serviceName) + "\n");
				break;
			case SERVICE_OP__ADD_INSTANCE:
				hostName = command[2];
				serviceName = command[1];
				sb.append(cluster.addInstance(serviceName, hostName) + "\n");
				break;
			case SERVICE_OP__REMOVE_INSTANCE:
				hostName = command[1];
				serviceName = command[2];
				sb.append(cluster.removeInstance(serviceName, hostName) + "\n");
				break;
			default:
				System.out.println("Invalid arguments");
			}

		}
		// end of For
		result.writeOutput(sb, outputFilePath);
	}
}
