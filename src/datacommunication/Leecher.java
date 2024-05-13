package datacommunication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 
 * @author TM Monare
 * this class manages the process of downloading transaction and blockchain files from peers
 *
 */
public class Leecher {
	
	public Leecher() {
		try {
			boolean running = true;
			DatagramSocket clientSocket = new DatagramSocket();
			 while(running) {
			 System. out. println("HELLO - Tests connection to server"+
			 "(responds with WELCOME)");
			 System. out. println("RANDOM M - Requests a random Integer,"+
			 " M is the maximum number(e. g. RANDOM 10 can respond with NUMBER 8)");
			 System. out. println("QUIT - Terminates the connection");
			 System. out. println("Enter Command: ");
			 BufferedReader inFromUser =
			 new BufferedReader(new InputStreamReader(System. in));
			 InetAddress IPAddress = InetAddress. getByName("localhost");
			 byte[] sendData = new byte[1024];
			 byte[] receiveData = new byte[1024];
			 String sentence = inFromUser. readLine();
			 sendData = sentence. getBytes();
			 DatagramPacket sendPacket =
			 new DatagramPacket(sendData , sendData. length, IPAddress, 9876);
			 clientSocket. send(sendPacket);
			 DatagramPacket receivePacket =
			 new DatagramPacket(receiveData , receiveData. length);
			 clientSocket. receive(receivePacket);
			 String modifiedSentence = new String(receivePacket . getData());
			 System. out. println("FROM SERVER: " + modifiedSentence);
			 }
			 clientSocket. close();
			 }
			 catch(IOException io) {
			 System. err. println(io. getMessage());
			 }
	}
	public void leechTransaction() {
		
	}
	
	public void leechBlockchain() {
		
	}
}
