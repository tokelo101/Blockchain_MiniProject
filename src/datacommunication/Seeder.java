package datacommunication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Random;

import acsse.csc03a3.Blockchain;
import acsse.csc03a3.Transaction;
import corelogic.Song;

/**
 * 
 * @author TM Monare 221022037
 * this class manages the process of uploading transaction and blockchain files from peers
 *
 */
public class Seeder {
	private Blockchain<Song> blockchain;
	private ArrayList<Transaction<Song>> transactions = new ArrayList<Transaction<Song>>();
	private String publicAddress;
	
	public Seeder(String publicAddress) {
	this.publicAddress =publicAddress;
	}
	
	
	/**
	 * Method for sending the blockchain file to peers after a block has been added
	 * @param blockchain the blockchain object to be validated
	 * @return
	 */
	public void seedBlockchain(Blockchain<Song> blockchain) {
		this.blockchain = blockchain;
		try {
			System. out. println("Started Server");
			DatagramSocket serverSocket = new DatagramSocket (9876);
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			boolean running = true;
			
		while(running) {
		//Receive request from client
			DatagramPacket receivePacket = new DatagramPacket(receiveData , receiveData. length);
			serverSocket. receive(receivePacket);
			String request = new String( receivePacket. getData());
			String response = "";
			
			response = blockchain.toString() ;
			
		//Send validation status back to client
		InetAddress IPAddress = receivePacket.getAddress();
		int port = receivePacket.getPort();
		sendData = response.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData. length, IPAddress, port);
		serverSocket.send(sendPacket);
		
		}
		}
		catch(IOException io)
		{
		System. err. println(io. getMessage());
		}
	}
	

}