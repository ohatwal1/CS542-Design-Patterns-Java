package loadbalancer.observer;

import java.util.ArrayList;

public interface ObserverI {

	public String update(String serviceName, String URL, ArrayList<String> hostName);

}
