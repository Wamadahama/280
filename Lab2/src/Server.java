/*
 * Elijah Ellis
 * 2/7/18
 * lab 2 server  
 */
import java.net.*;
import java.io.*;


public class Server extends Thread {
	private ServerSocket socket; 
	
	public Server(int port) throws IOException {  
		// Set up socket 
		socket = new ServerSocket(port);
		socket.setSoTimeout(100000);
	}
	
	public void run() {
		while(true) {
			try { 

				System.out.println("Waiting for client connection on port " + socket.getLocalPort());
				// Accept connection 
				Socket server = socket.accept();

				// Get server connection 
				System.out.println("Connected to " + server.getRemoteSocketAddress());
				DataInputStream in = new DataInputStream(server.getInputStream());

				// Read what the client sent us 
				String[] input = (in.readUTF()).split(" ");  
				
				String option = input[0];
				double data   = Double.parseDouble(input[1]);
				
				// Determine what function to use and then calculate area 
				double returnData = option.equals("circle") ? calculateCircleArea(data) : calculateSquareArea(data);

				// Prepare output 
				DataOutputStream out = new DataOutputStream(server.getOutputStream());

				// Send output 
				out.writeUTF(">> " + option + " area: " + returnData);
				
				// Close connection 
				server.close();

			} catch (SocketTimeoutException e) {
				System.out.println("Timeout!");
			} catch (IOException e) {
				System.out.println("IOException");
			} catch (NumberFormatException e) {
				System.out.println("Bad input!");
			}
		}
	}
	
	private double calculateCircleArea(double radius) {
		// Calculate circle area 
		System.out.println((Math.pow(radius, 2)) * Math.PI);
		return ((Math.pow(radius, 2)) * Math.PI);
	}
	private double calculateSquareArea(double length) {
		// Calculate square area
		return length * length;
	}
	
	public static void main(String[] args) {
		// setup server on port 7788 on a new thread  
		int port = 7788;
		try {
			Thread t = new Server(port);
			t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
