package loadbalancer.observer;

import java.util.ArrayList;
import java.util.List;

import loadbalancer.entities.Machine;

public class ServiceManager implements ObserverI {
	private String key;

	// Information pertaining to the service.

	// {URL: [host1,host2,host3]}

	// Rest of the code.
	private String URL;
	private ArrayList<String> hostNames;
	private int curr_index_of_rr;

	public ServiceManager(String serviceName, String URL, ArrayList<String> hostNames) {
		this.URL = URL;
		this.hostNames = hostNames;
		this.key = serviceName;
		this.curr_index_of_rr = 0;

	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public ArrayList<String> getHostnames() {
		return hostNames;
	}

	public void setHostnames(ArrayList<String> hostNames) {
		this.hostNames = hostNames;
	}

	@Override
	public String update(String serviceName, String URL, ArrayList<String> hostName) {
		return "";
		// TODO Auto-generated method stub

	}

	public ArrayList<String> hostService() {

		ArrayList<String> hostNames = this.getHostnames();
		ArrayList<String> hostAndUrl = new ArrayList<>();
		hostAndUrl.add(hostNames.get(this.curr_index_of_rr));
		hostAndUrl.add(this.URL);

		this.curr_index_of_rr += 1;
		if (this.curr_index_of_rr >= hostNames.size()) {
			this.curr_index_of_rr = 0;
		}
		return hostAndUrl;

	}

	public ArrayList<String> getHostAndURL() {

		ArrayList<String> hostNames = this.getHostnames();
		if (hostNames == null) {
			return null;
		} else {
			ArrayList<String> hostAndUrl = this.hostService();
			return hostAndUrl;
		}
	}

	public boolean deleteServiceForHost(String hostName) {
		ArrayList<String> hostNames = this.getHostnames();
		if (hostNames == null || (!hostNames.contains(hostName))) {
			return false;
		} else {
			hostNames.remove(hostName);
			this.setHostnames(hostNames);
			return true;
		}
	}

}
