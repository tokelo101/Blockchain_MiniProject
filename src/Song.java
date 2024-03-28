import java.util.List;

import acsse.csc03a3.Block;

@SuppressWarnings("rawtypes")
public class Song extends Block{

    @SuppressWarnings("unchecked")
	public Song(String previousHash, List transactions) {
        super(previousHash, transactions);
        System.out.println("Created Song");
    }
    
}
