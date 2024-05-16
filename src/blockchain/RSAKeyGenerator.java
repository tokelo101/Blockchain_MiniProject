package blockchain;


import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * 
 * @author TM Monare
 *
 * @param <PUBLIC_KEY> publicKey
 * @param <PRIVATE_KEY> privateKey
 */
public class RSAKeyGenerator<PUBLIC_KEY,PRIVATE_KEY> {
        
	public KeyPairs<String,String> GenerateKeys(){
		KeyPairs<String,String> keys = null;
		
		try {
            // Generate RSA key pair
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            // Get public and private keys
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
            
            keys = new KeyPairs<String,String>(Base64.getEncoder().encodeToString(publicKey.getEncoded()) ,Base64.getEncoder().encodeToString(privateKey.getEncoded()));
    		
            System.out.println("Public key: " + publicKey.getEncoded().toString());
            System.out.println("Private key: " + privateKey.getEncoded().toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
		
		return keys;
	}
    
}