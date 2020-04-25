package loadbalancer.entities;

import java.util.HashMap;
import java.util.Map;

public class Machine {
	private String hostName;
	// Service name to hosted services.
	private Map<String, Service> hostedServices;

	public Machine(String hostName) {
		this.hostName = hostName;
		this.hostedServices = new HashMap<String, Service>();
	}

	public String getHostname() {
		return hostName;
	}

	public void setHostname(String hostname) {
	}

	public Map<String, Service> getHostedServices() {
		return hostedServices;
	}

	public boolean setHostedServices(String serviceName, String url) {
		if (this.hostedServices.containsKey(serviceName)) {
			return false;
		}
		this.hostedServices.put(serviceName, new Service(serviceName, url));
		return true;
	}

	public void updateHostedServices(Map<String, Service> hostedServices) {
		this.hostedServices = hostedServices;

	}

}
