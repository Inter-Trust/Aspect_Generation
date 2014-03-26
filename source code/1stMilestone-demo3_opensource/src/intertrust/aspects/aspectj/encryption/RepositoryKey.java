package intertrust.aspects.aspectj.encryption;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStore.ProtectionParameter;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import javax.crypto.SecretKey;

/**
 * 
 * @author UMA
 * @date   10/10/2013
 * @version 1.0
 *
 */
public class RepositoryKey {

	public static final String TYPE_FOR_SECRET_KEYS = "JCEKS";
	private KeyStore ks;
	private char[] password;
	private ProtectionParameter protectionParameter;
	
	/**
	 * Creates an empty keystore using the load method and by passing null as the InputStream argument. 
	 * 
	 * @param type
	 * @param password
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 */
	public RepositoryKey(String type, char[] password) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		ks = KeyStore.getInstance(type);

	    // get user password and file input stream
	    this.password = password;
	    protectionParameter = new KeyStore.PasswordProtection(password);
        ks.load(null, password);
        
	}
	
	/**
	 * Load an existing keystore.
	 * 
	 * @param type
	 * @param password
	 * @param keyStoreName
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 */
	public RepositoryKey(String type, char[] password, String keyStoreName) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		ks = KeyStore.getInstance(type);

	    // get user password and file input stream
		 this.password = password;
		 protectionParameter = new KeyStore.PasswordProtection(password);

	    java.io.FileInputStream fis = null;
	    try {
	        fis = new java.io.FileInputStream(keyStoreName);
	        ks.load(fis, password);
	    } finally {
	        if (fis != null) {
	            fis.close();
	        }
	    }
	}
	
	/**
	 * Get my secret key.
	 * 
	 * @param secretKeyAlias
	 * @return
	 * @throws KeyStoreException 
	 * @throws UnrecoverableEntryException 
	 * @throws NoSuchAlgorithmException 
	 */
	public SecretKey getKey(String secretKeyAlias) throws NoSuchAlgorithmException, UnrecoverableEntryException, KeyStoreException { 
	    KeyStore.SecretKeyEntry pkEntry = (KeyStore.SecretKeyEntry) ks.getEntry(secretKeyAlias, protectionParameter);
	    return pkEntry.getSecretKey();
	}
	
	/**
	 * Save my secret key.
	 * 
	 * @param mySecretKey
	 * @param secretKeyAlias
	 * @throws KeyStoreException
	 */
	public void saveKey(SecretKey mySecretKey, String secretKeyAlias) throws KeyStoreException {
	    KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry(mySecretKey);
	    ks.setEntry(secretKeyAlias, skEntry, protectionParameter);
	}
	
	/**
	 * Store away the keystore.
	 * 
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 */
	public void storage(String newKeyStoreName) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
	    java.io.FileOutputStream fos = null;
	    try {
	        fos = new java.io.FileOutputStream(newKeyStoreName);
	        ks.store(fos, password);
	    } finally {
	        if (fos != null) {
	            fos.close();
	        }
	    }
	}
	
}
