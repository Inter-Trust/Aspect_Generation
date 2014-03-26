package intertrust.aspects.aspectj.encryption;

import intertrust.aspects.aspectj.IntertrustAspect;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * Advanced Encryption Standard (AES).
 * The aspect includes functionality for generating a key,
 * creating and initializing a cipher object,
 * encrypting a file, and then decrypting it. 
 * 
 * @author Jose-Miguel Horcas
 * @date   08/10/2013
 * @version 1.0
 * 
 */
public abstract aspect Encryption extends IntertrustAspect {
	
	private Cipher cipher;
	
	/**
	 * Aspect constructor.
	 * The constructor cannot take any arguments and 
	 * cannot throw any checked exceptions!
	 */
	public Encryption() {
		// You can initialize attributes and such here
		try {
			// Create the cipher
		    cipher = Cipher.getInstance(getTransformation());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}
	
	abstract public SecretKey getEncryptionKey();
	
	abstract public SecretKey getDecryptionKey();
	
	abstract public String getAlgorithm();
	
	abstract public String getMode();
	
	abstract public String getPadding();
	
	private String getTransformation() {
		return getAlgorithm() + "/" + getMode() + "/" + getPadding();
	}
	
	abstract public String getCharset();
	
	/**
	 * Pointcut for intercepting encryption points
	 * 
	 * @param cleartext	Clear text.
	 */
	abstract public pointcut encryptPoints(String cleartext);
	
	/**
	 * Pointcut for intercepting decryption points
	 * 
	 * @param ciphertext Cipher text.
	 */
	abstract public pointcut decryptPoints(String ciphertext);

	abstract pointcut enabled();
	
	/**
	 * Encrypt the clear text.
	 * Invoke the original method with the cipher text.
	 * If error, invoke the original method with the original clear text.
	 * 
	 * @param cleartext	Clear text.
	 * @return			Same as the original method.
	 */
	Object around(String cleartext): enabled() && encryptPoints(cleartext) {
		String text = encrypt(cleartext);
	    return proceed(text);
	}
	
	/**
	 * Decrypt the cipher text.
	 * Invoke the original method with the clear text.
	 * If error, invoke the original method with the original cipher text.
	 * 
	 * @param ciphertext	Cipher text.
	 * @return				Same as the original method.
	 */
	Object around(String ciphertext): enabled() && decryptPoints(ciphertext) {
		String text = decrypt(ciphertext);
	    return proceed(text);
	}
	
	private String encrypt(String cleartext) {
		writeConsole("Encrypting...\n");
		String text = cleartext;
		try {
	    	// Initialize the cipher for encryption
			cipher.init(Cipher.ENCRYPT_MODE, getEncryptionKey());
			// Encrypt the cleartext
			byte[] ciphertext = cipher.doFinal(cleartext.getBytes(getCharset()));
		    text = new String(ciphertext, getCharset());
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		return text;
	}
	
	private String decrypt(String ciphertext) {
		writeConsole("Decrypting...\n");
		String text = ciphertext;
		try {
			// Initialize the same cipher for decryption
		    cipher.init(Cipher.DECRYPT_MODE, getDecryptionKey());
		    // Decrypt the ciphertext
		    byte[] cleartext = cipher.doFinal(ciphertext.getBytes(getCharset()));
		    text = new String(cleartext, getCharset());
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return text;
	}
	 
}
