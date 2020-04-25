package loadbalancer.subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import loadbalancer.entities.Machine;
import loadbalancer.entities.Service;
import loadbalancer.observer.LoadBalancer;
import loadbalancer.observer.ServiceManager;
import loadbalancer.util.FileProcessor;

public class Cluster implements SubjectI {

	public Cluster() {
		loadBalance = new LoadBalancer();
	}

	LoadBalancer loadBalance;
	// Hostnames to corresponding machine instances.
	private Map<String, Machine> cluster = new HashMap<String, Machine>();

	@Override
	public String addService(String serviceName, String URL, ArrayList<String> hostNames) {

		return loadBalance.update(serviceName, URL, hostNames);
	}

	@Override
	public String removeService(String serviceName) {

		// Remove service from machines on which it is currently running
		ServiceManager serviceManager = loadBalance.search(serviceName);
		if (serviceManager == null) {
			return "Service Invalid";
		}
		ArrayList<String> hostNames = serviceManager.getHostnames();
		while (hostNames.size() > 0) {
			this.removeInstance(serviceName, hostNames.get(0));
		}

		// remove service from cluster/loadbalancer/Trie structure
		if (loadBalance.deleteService(serviceName)) {
			return "Service Removed";
		}
		return "Service Invalid";
	}

	@Override
	public String clusterAddHost(String hostName) {
		if (!cluster.containsKey(hostName)) {
			Machine machine = new Machine(hostName);
			cluster.put(hostName, machine);
			return "Cluster Scaled Up - " + hostName;
		} else {


			return "Hostname already exist - " + hostName;
		}

	}

	@Override
	public String clusterRemoveHost(String hostName) {
		// remove hostname from service manager
		if (!cluster.containsKey(hostName)) {
			return "No machine with the given hostname exists";
		}
		Machine machineInstance = cluster.get(hostName);
		Map<String, Service> hostedServices = machineInstance.getHostedServices();
		for (Map.Entry<String, Service> entry : hostedServices.entrySet()) {
			this.removeInstance(entry.getKey(), hostName);
		}
		// remove machine from cluster
		cluster.remove(hostName);
		return "Cluster Scaled Down - " + hostName;
	}

	@Override
	public String addInstance(String serviceName, String hostName) {
		// search service and get hostNames (extract from trie structure)
		ServiceManager serviceManager = loadBalance.search(serviceName);
		if (serviceManager == null) {
			return "Service not added previouly using ADD_SERVICE";
		}
		ArrayList<String> hostNames = serviceManager.getHostnames();
		if (cluster.containsKey(hostName)) {
			if (hostNames.contains(hostName)) {
				return "Service already exist";
			} else {
				hostNames.add(hostName);
				serviceManager.setHostnames(hostNames);
				return "Instance Added";
			}
		} else {
			return "Invalid Machine";
		}
	}

	@Override
	public String removeInstance(String serviceName, String hostName) {

		ServiceManager serviceManager = loadBalance.search(serviceName);
		if (serviceManager == null) {
			return "Service name is invalid";
		}

		if (serviceManager.deleteServiceForHost(hostName)) {
			Machine machineInstance = cluster.get(hostName);
			Map<String, Service> hostedServices = machineInstance.getHostedServices();
			hostedServices.remove(serviceName);
			machineInstance.updateHostedServices(hostedServices);

			return "Instance Removed";
		}

		return "No instance of service is present";

	}

	/**
	 * @return the machines
	 */
	public Map<String, Machine> getHost() {
		return cluster;
	}

	/**
	 * @param machines
	 *            the machines to set
	 */
	public void setHost(Map<String, Machine> machines) {
		this.cluster = machines;
	}

	@Override
	public String request(String serviceName) {

		return loadBalance.handleRequest(serviceName, cluster);
	}

}
