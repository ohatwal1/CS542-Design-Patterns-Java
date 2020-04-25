package loadbalancer.subject;

import java.util.ArrayList;

public interface SubjectI {

	public String addService(String serviceName, String URL, ArrayList<String> hostNames);

	public String removeService(String serviceName);

	public String clusterAddHost(String hostName);

	public String clusterRemoveHost(String hostName);

	public String addInstance(String serviceName, String hostName);

	public String removeInstance(String serviceName, String hostName);

	public String request(String serviceName);

}
