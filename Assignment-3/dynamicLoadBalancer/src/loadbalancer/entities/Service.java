package loadbalancer.entities;

import java.util.HashMap;
import java.util.Map;

public class Service {

	// Service URL.
	private String URL;
	// Service name.
	private String name;

	Map<String, String> serviceNameAndURL = new HashMap<String, String>();

	public Service(String serviceName, String url) {
		this.URL = url;
		this.name = serviceName;
	}

	public void getServiceDetails(String name, String URL) {
		serviceNameAndURL.put(name, URL);
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
