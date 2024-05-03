package corelogic;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import acsse.csc03a3.Block;
import acsse.csc03a3.Blockchain;
import acsse.csc03a3.Transaction;

public class BlockHandler<T> {
	
	private String filePath = "data/blockchain_.txt";
	private Blockchain<T> blockchain  = new Blockchain<T>();
	
	private List<Transaction<T>> transactions = new ArrayList<Transaction<T>>();
	
	//read Blocks File
	
		//if there's no block, instantiate a Genesis Block
		//Block<T> block = new Block("", transactions);
	
	public BlockHandler(User user) {
		blockchain.registerStake(user.getAddress(), 10);
	}
	
	public boolean addTransaction(Transaction<T> transaction) {
		boolean status = false;
		
		transactions.add(transaction);
		status = true;
		//if there's more than 5 transactions create a new block Block
		if(transactions.size() >=5) {
			
			//add Block to Blockchain
			blockchain.addBlock(transactions);
			
			//Write the Blockchain to the Blockchain File
			UpdateBlockChainFile();
			
			//Send the latest Block Chain For Validation
			ValidateBlockchain(blockchain);
			
		}
		System.out.println("Transaction Size: "+ transactions.size());
		return status;
	}
	
	
	private boolean UpdateBlockChainFile() {
		
		boolean status = false;
		try (FileWriter fileWriter = new FileWriter(filePath, true)) {
			String blockchainString = blockchain.toString();    
            fileWriter.write(blockchainString);
            fileWriter.write(System.lineSeparator());
            System.out.println("User data written to the file successfully.");
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
		return status;
	}
	
	private boolean ValidateBlockchain(Blockchain<T> blockchain) {
		boolean isValid = false;
		
		isValid  = blockchain.isChainValid();
		System.out.println("Blockchain is Valid: "+ isValid);
		return isValid;
	}
}
