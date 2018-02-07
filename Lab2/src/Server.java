import java.net.*;
import java.io.*;


public class Server extends Thread {
	private ServerSocket socket; 
	
	public Server(int port) throws IOException {  
		socket = new ServerSocket(port);
		socket.setSoTimeout(1000000);
	}
	
	public void run() {
		while(true) {
			try { 
				System.out.println("Waiting for client connection on port " + socket.getLocalPort());
				Socket server = socket.accept(); 

				System.out.println("Connected to " + server.getRemoteSocketAddress());
				DataInputStream in = new DataInputStream(server.getInputStream());

				String[] input = (in.readUTF()).split(" ");  
				
				String option = input[0];
				double data   = Double.parseDouble(input[1]);
				
				System.out.println(option);
				System.out.println(option.trim() == "circle");
				System.out.println(data);
				
				double returnData = option.equals("circle") ? calculateCircleArea(data) : calculateSquareArea(data);

				DataOutputStream out = new DataOutputStream(server.getOutputStream());
				
				System.out.println(returnData);
				
				out.writeUTF(option + "area: " + returnData);
				
				server.close();

			} catch (SocketTimeoutException e) {
				System.out.println("Timeout!");
			} catch (IOException e) {
				System.out.println("IOException");
			}
		}
	}
	
	private double calculateCircleArea(double radius) {
		System.out.println((Math.pow(radius, 2)) * Math.PI);
		return ((Math.pow(radius, 2)) * Math.PI);
	}
	private double calculateSquareArea(double length) {
		return length * 2.0;
	}
	
	public static void main(String[] args) {
		int port = 7778;
		try {
			Thread t = new Server(port);
			t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
