package blockchain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import acsse.csc03a3.Blockchain;
import acsse.csc03a3.Transaction;
import corelogic.SongHandler;
import corelogic.users.User;
//import datacommunication.Seeder;

/**
 * 
 * @author TM Monare 221022037
 *
 * @param <T> the Transaction Data Type: Song
 */

public class BlockchainHandler<T> {
	
	private String filePath_blockchain = "data/blockchain.txt";
	private String filePath_transaction = "data/transactions.txt";
	private Blockchain<T> blockchain  = new Blockchain<T>();
	private User user;
	private List<Transaction<T>> transactions = new ArrayList<Transaction<T>>();
		
	/**
	 * 
	 * @param user the user in session
	 */
	public BlockchainHandler(User user) {
		this.user = user;
		blockchain.registerStake(user.getPublicKey(), user.getStake());
		
		//get transactions from a transaction file
		//The transactions file always has a max of 4 transactions. when the fifth transaction is made a block is created
		transactions = readTransactions();
		//read Blocks from blockchain file to runtime memory
		readBlockchain();
	}
	
	/**
	 * 
	 * @param transaction transaction to be added to the blockchain
	 * @return boolean value on whether the transaction was added successfully or not
	 */
	public boolean addTransaction(SongTransaction<T> transaction) {
		boolean status = false;
		
		transactions.add(transaction);
		//Update Transactions File
		UpdateTransactionFile();
		
		status = true;
		//if there's more than 5 transactions create a new block Block
		if(transactions.size() >=5) {
			
			//add Block to Blockchain
			blockchain.addBlock(transactions);
			
			//Write the Blockchain to the Blockchain File
			boolean updated = UpdateBlockChainFile();
			
			//Send the latest Block Chain For Validation
			if(updated) {
				SeedBlockchain(blockchain);
			}
			
			transactions.clear();
			//Clear Transactions File
			try {
				FileWriter fileWriter = new FileWriter(filePath_transaction);
	            PrintWriter printWriter = new PrintWriter(fileWriter);
	            printWriter.flush();
	            printWriter.close();
	            
			} catch (IOException e) {
	            e.printStackTrace();
	        }
			
			
		}
		System.out.println("Transaction Size: "+ transactions.size());
		return status;
	}
	
	/**
	 * Method for updating blockchain to a text file
	 * @return boolean value on whether the upload was successful or not
	 */
	private boolean UpdateBlockChainFile() {
		
		boolean status = false;
	
		try (FileWriter fileWriter = new FileWriter(filePath_blockchain, true)) {
			String blockchainString = blockchain.toString();    
            fileWriter.write(blockchainString);
            fileWriter.write(System.lineSeparator());
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
		return status;
	}
	
	/**
	 * Method for updating transactions to a text file
	 * @return boolean value on whether the upload was successful or not
	 */
	private boolean UpdateTransactionFile() {
		boolean status = false;
		try (FileWriter fileWriter = new FileWriter(filePath_transaction, true)) {
			String transactionString = transactions.toString();    
            fileWriter.write(transactionString);
            fileWriter.write(System.lineSeparator());
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the file: " + e.getMessage());
        }
		return status;
	}
	
	
	public List<Transaction<T>> readTransactions(){
		ArrayList<Transaction<T>> transactions = new ArrayList<Transaction<T>>();
		
		try (FileReader fileReader = new FileReader(filePath_transaction);
		         BufferedReader bufferedReader = new BufferedReader(fileReader)) {
				boolean notNull = true;
		        while(notNull) {
		            String transactionData = bufferedReader.readLine();
			        if (transactionData != null) {
			        String[] songFields = transactionData.split(",");
			        	if (songFields.length >= 5) {
			        		String songISRC = songFields[0];
			        		String transactionType = songFields[1];
			        		String sender = songFields[2];
			        		String receiver = songFields[3];
			        		long timeStamp = Long.parseLong(songFields[4]);
			        		
			        		SongHandler songhanlder = new SongHandler(user);
			        		Transaction<T> transaction = new SongTransaction<T>(songhanlder.ReadSong(songISRC),transactionType, sender, receiver,timeStamp);
			        		transactions.add(transaction);
			        	}
			        }else {
			        	notNull = false; 
			        }
		        }
		}
		catch (IOException e) {
            System.out.println("An error occurred while reading from the file: " + e.getMessage());
        }catch (Exception e) {
            //Clearing Transaction File
        	try {
				FileWriter fileWriter = new FileWriter(filePath_transaction);
	            PrintWriter printWriter = new PrintWriter(fileWriter);
	            printWriter.flush();
	            printWriter.close();
	            
			} catch (IOException e1) {
	            e1.printStackTrace();
	        }
        }
		
		return transactions;
	}
	
	
	/**
	 * 
	 * @param blockchain the blockchain Object to be validated by peers via UDP protocol
	 * @return
	 */
	private void SeedBlockchain(Blockchain<T> blockchain) {
	  
	}
	
	
	public Blockchain<T> readBlockchain() {
		Blockchain<T> blockchain = new Blockchain<T>();
		
		try (FileReader fileReader = new FileReader(filePath_blockchain);
		         BufferedReader bufferedReader = new BufferedReader(fileReader)) {
			String line;
            while ((line = bufferedReader.readLine()) != null) {
            	
                if (line.startsWith("Block{")) {
                	List<Transaction<T>> block = parseBlock(line);
                	blockchain.registerStake(user.getPublicKey(), user.getStake());
                    blockchain.addBlock(block);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
        return blockchain;
	}
	
	private List<Transaction<T>> parseBlock(String blockData) {
        try {
        	// Remove the "Block{" and "}" from the string
            String blockInfo = blockData.substring(6, blockData.length() - 1);

            // Split the block information into individual fields
            String[] fields = blockInfo.split(", ");

            // Parse the individual fields to create the Block object
            String previousHash = fields[0].split("=")[1];
            List<Transaction<T>> transactions = parseTransactions(fields[1]);

            return transactions;
        }catch(NumberFormatException nfe) {
        	System.err.println("Number Format Exception");
        	return transactions; 
        }
        catch(Exception nfe) {
        	System.err.println("Exception");
        	return transactions;
        }
    }
	
	private List<Transaction<T>> parseTransactions(String transactionsData) {
        List<Transaction<T>> transactions = new ArrayList<>();
        String[] transactionStrings = transactionsData.split("\\], ");

        for (String transactionString : transactionStrings) {
        	try {
                // Parse the individual transaction fields
                String[] fields = transactionString.split(", ");
                if (fields.length != 4) {
                    // Skip this transaction if the number of fields is not as expected
                    continue;
                }
            // Parse the individual transaction fields
//            String[] fields = transactionString.split(", ");
            String sender = fields[0].split("=")[1];
            String receiver = fields[1].split("=")[1];
            // Assuming the data field is a String representation of a corelogic.Song object
            String data = fields[2].split("=")[1];
            long timestamp = Long.parseLong(fields[3].split("=")[1]);

            Transaction<String> transaction = new Transaction<String>(sender, receiver, data);
            transactions.add((Transaction<T>) transaction);
        	} catch(ArrayIndexOutOfBoundsException | NumberFormatException e) {
            // Skip this transaction if there's an issue with the data format
            continue;
        	}
        }
        return transactions;
    }
}
