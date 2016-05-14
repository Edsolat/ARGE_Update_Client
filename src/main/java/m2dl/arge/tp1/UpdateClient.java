package m2dl.arge.tp1;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;

public class UpdateClient {
	


	public static XmlRpcClient getClient(String ip, int port) {
		XmlRpcClient client = new XmlRpcClient();
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();

		try {
			config.setServerURL(new URL("http://" + ip + ":" + port + "/xmlrpc"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		config.setEnabledForExtensions(true);  
		config.setConnectionTimeout(60 * 1000);
		config.setReplyTimeout(60 * 1000);

		//client = new XmlRpcClient();

		// use Commons HttpClient as transport
		client.setTransportFactory(
				new XmlRpcCommonsTransportFactory(client));
		// set configuration
		client.setConfig(config);
		return client;
	}
	
	public static void main(String args[]) {
		int port = Integer.parseInt(args[2]);
		int n = Integer.parseInt(args[0]);
		
		Object[] params = {new Integer(n)};
		XmlRpcClient c = getClient(args[1], port);
		try {
			int i = (Integer) (c.execute("Update.update", params));
			System.out.println("Client updated : nbRequest is now " + n);
		} catch (XmlRpcException e) {
			e.printStackTrace();
		}
		
		
	}
}
