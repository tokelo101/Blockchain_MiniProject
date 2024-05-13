package datacommunication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.Random;

/**
 * 
 * @author TM Monare 221022037
 * this class manages the process of uploading transaction and blockchain files from peers
 *
 */
public class Seeder {
	
	public Seeder() {
		try {
			System. out. println("Started Server");
			DatagramSocket serverSocket = new DatagramSocket (9876);
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			boolean running = true;
			
		while(running) {
		//Receive request from client
			DatagramPacket receivePacket =
			new DatagramPacket(receiveData , receiveData. length);
			serverSocket. receive(receivePacket);
			String request = new String( receivePacket. getData());
			String response = "";
		//Process request
		if (request. startsWith("HELLO")) {
		System. out. println("HELLO RECEIVED: " + request);
		response = "WELCOME";
		}
		else if (request. startsWith("RANDOM")) {
		System. out. println("RANDOM RECEIVED: " + request);
		Random r = new Random(System. currentTimeMillis());
		int max = Integer. parseInt(request. split(" ")[1]. trim());
		response = "NUMBER "+ r. nextInt(max);
		}
		else if (request. startsWith("QUIT")) {
		System. out. println("QUIT RECEIVED: " + request);
		response = "EXIT";
		running = false;
		}
		//Send reponse back to client
		InetAddress IPAddress = receivePacket . getAddress();
		int port = receivePacket . getPort();
		sendData = response. getBytes();
		DatagramPacket sendPacket =
		new DatagramPacket(sendData, sendData. length, IPAddress, port);
		serverSocket. send(sendPacket);
		
		}
		}
		catch(IOException io)
		{
		System. err. println(io. getMessage());
		}finally {

		}
		}
	
	public void seedTransaction() {
		
	}
	
	public void seedBlockchain() {
		
	}
}