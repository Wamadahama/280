import java.net.*;
import java.io.*;

public class Client {
	public Client() { }
	
	public DataInputStream Request(String option, double data) { 
		String serverName = "localhost";
		int port = 7778;
		
		try {
			Socket client = new Socket(serverName, port); 
			
			OutputStream serverStream = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(serverStream);
			out.writeUTF(option + " " + data);
			
			InputStream fromServer = client.getInputStream();
			DataInputStream recievedData = new DataInputStream(fromServer);
			
			return recievedData;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
