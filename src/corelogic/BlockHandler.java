package corelogic;

import java.util.List;

import acsse.csc03a3.Block;
import acsse.csc03a3.Blockchain;
import acsse.csc03a3.Transaction;

public class BlockHandler<T> {
	
	Blockchain<T> blockchain  = new Blockchain<T>();
	
	List<Transaction<T>> transactions = null;
	
	//read Blocks File
	
		//if there's no block, instantiate a Genesis Block
		//Block<T> block = new Block("", transactions);
	
	
	
	
	public boolean addTransaction(Transaction<T> transaction) {
		boolean status = false;
		
		transactions.add(transaction);
		
		//if there's more than 5 transactions //Create a Block
		if(transactions.size() >=5) {
			
			blockchain.addBlock(transactions);
			//Write the Blockchain to the Blockchain File
			
			//Send the latest Block Chain For Validation
			
			
		}
		
		return status;
	}
}
