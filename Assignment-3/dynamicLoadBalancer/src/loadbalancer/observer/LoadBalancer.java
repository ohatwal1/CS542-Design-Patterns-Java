package loadbalancer.observer;

import loadbalancer.entities.Machine;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class LoadBalancer implements ObserverI {
	// Index to find the URL and hostname for a given service name.
	//
	// Trie is optional for under-graduate students.
	// Graduate students have to use a Trie datastructure.
	private Trie ServiceURLAndHostnameFetcher;

	public LoadBalancer() {
		this.ServiceURLAndHostnameFetcher = new Trie();
	}

	public ServiceManager search(String serviceName) {
		return this.ServiceURLAndHostnameFetcher.search(serviceName);
	}

	@Override
	public String update(String serviceName, String URL, ArrayList<String> hostNames) {
		// TODO Auto-generated method stub
		ArrayList<String> result = new ArrayList<>();
		ServiceManager sm = this.ServiceURLAndHostnameFetcher.search(serviceName);

		if (sm != null) {
			return "Service already exists";
		} else {
			// add to Trie structure with mapped to its service manager
			ServiceManager newSm = new ServiceManager(serviceName, URL, hostNames);
			this.ServiceURLAndHostnameFetcher.insert(serviceName, newSm);
			return "Service Added";
		}
	}

	public String handleRequest(String serviceName, Map<String, Machine> cluster) {

		ServiceManager sm = this.ServiceURLAndHostnameFetcher.search(serviceName);

		if (sm == null) {
			return "Invalid service";
		} else {
			ArrayList<String> hostAndUrl = sm.getHostAndURL();
			if (hostAndUrl == null) {
				return "Service Inactive";
			}
			Machine machineInstance = cluster.get(hostAndUrl.get(0));
			machineInstance.setHostedServices(serviceName, hostAndUrl.get(1));
			return "Processed Request - Service_URL::" + hostAndUrl.get(1) + " Host::" + hostAndUrl.get(0);

		}

	}

	public boolean deleteService(String serviceName) {

		return this.ServiceURLAndHostnameFetcher.remove(serviceName);
	}

}
