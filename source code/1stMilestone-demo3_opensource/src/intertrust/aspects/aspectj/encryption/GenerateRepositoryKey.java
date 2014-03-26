package intertrust.aspects.aspectj.encryption;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class GenerateRepositoryKey {

	public static final String SECRET_KEY_ALIAS = "Encryption_AES_KEY";
	public static final String KEY_STORE_NAME = "aesKeyStore";
	public static final String REPOSITORY_KEY_PATH = KEY_STORE_NAME;
	public static final String PASSWORD = "Encryption_AES_PASS";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
		    SecretKey aesKey = keygen.generateKey();
		    
			RepositoryKey rk = new RepositoryKey(RepositoryKey.TYPE_FOR_SECRET_KEYS, PASSWORD.toCharArray());
			rk.saveKey(aesKey, SECRET_KEY_ALIAS);
			rk.storage(REPOSITORY_KEY_PATH);
			System.out.println("done!");	
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
