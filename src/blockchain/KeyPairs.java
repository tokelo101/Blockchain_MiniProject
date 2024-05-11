package blockchain;

public class KeyPairs<PUBLIC_KEY, PRIVATE_KEY> {
	private PUBLIC_KEY publicKey;
	private PRIVATE_KEY privateKey;
	
	public PUBLIC_KEY getPublicKey() {
		return publicKey;
	}
	
	public PRIVATE_KEY getPrivateKey() {
		return privateKey;
	}

	public void setPublicKey(PUBLIC_KEY publicKey) {
		this.publicKey = publicKey;
	}
	
	public void setPrivateKey(PRIVATE_KEY privateKey) {
		this.privateKey = privateKey;
	}
	
	
	public KeyPairs(PUBLIC_KEY publicKey, PRIVATE_KEY privateKey){
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}
}
