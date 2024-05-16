package datacommunication;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

import acsse.csc03a3.Blockchain;
import acsse.csc03a3.Transaction;
import corelogic.Song;

/**
 * 
 * @author TM Monare
 * this class manages the process of downloading transaction and blockchain files from peers
 *
 */
public class Leecher {
	private Blockchain<Song> blockchain;
	private ArrayList<Transaction<Song>> transactions = new ArrayList<Transaction<Song>>();
	String publicAddress;
	public Leecher(String publicAddress) {
		this.publicAddress = publicAddress;
	}
	
	
	/**
	 * method for listening for a blockchain file from peers after a block has been addded to the blockchain
	 * Get the file and save it locally
	 */
	public void leechBlockchain() {
		try {
			boolean running = true;
			DatagramSocket clientSocket = new DatagramSocket();
			 while(running) {
				 
			 InetAddress IPAddress = InetAddress.getByName("localhost");
			 byte[] sendData = new byte[1024];
			 byte[] receiveData = new byte[1024];
			 
			 String blockchainString = receiveData.toString();
			 
			 //Convert BlockhainString to Blockchain<Song>
			 Blockchain<Song> blockchain = ConvertBlockchain(blockchainString);
			 
			 //Validation Status
			 sendData = String.valueOf(Validate(blockchain)).getBytes();
			 DatagramPacket sendPacket = new DatagramPacket(sendData , sendData.length, IPAddress, 9876);
			 
			 clientSocket.send(sendPacket);

			 }
			 clientSocket.close();
			 }
			 catch(IOException io) {
			 System.err.println(io.getMessage());
			 }
	}
	
	/**
	 * 
	 * @param blockchain blockchain object to be validated by peer
	 * @return validation status
	 */
	private boolean Validate(Blockchain<Song> blockchain) {
		boolean isValid;
		isValid = blockchain.isChainValid();
		return isValid;
	};
	
	private Blockchain<Song> ConvertBlockchain(String BlockchainString){
		Blockchain<Song> blockchain = new Blockchain<Song>();
		ArrayList<Transaction<Song>> transactions = new ArrayList<Transaction<Song>>();
		
		String[] blockchainData = BlockchainString.split(", ");
		
        // Creating and returning the Block object
        blockchain.addBlock(transactions);
		return blockchain;
	}
}
