/*
 * Elijah Ellis
 * 2/7/18
 * Client 
 */
import java.net.*;
import java.io.*;

public class Client {

	public Client() { } 
	
	public DataInputStream Request(String option, double data) { 

		String serverName = "localhost";
		int port = 7788;
		
		try {
			// Create socket connection 
			Socket client = new Socket(serverName, port); 
			
			// Send server data 
			OutputStream serverStream = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(serverStream);
			out.writeUTF(option + " " + data);
			
			// Get the input stream 
			InputStream fromServer = client.getInputStream();
			DataInputStream recievedData = new DataInputStream(fromServer);
			
			// Return the InputStream 
			return recievedData;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
