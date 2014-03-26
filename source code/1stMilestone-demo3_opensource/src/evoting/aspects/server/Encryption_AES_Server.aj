package evoting.aspects.server;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import javax.crypto.SecretKey;

import intertrust.aspects.aspectj.encryption.Encryption_AES;
import intertrust.aspects.aspectj.encryption.GenerateRepositoryKey;
import intertrust.aspects.aspectj.encryption.RepositoryKey;


public aspect Encryption_AES_Server extends Encryption_AES {

	public pointcut encryptPoints(String cleartext); 

	public pointcut decryptPoints(String ciphertext): call(public void evoting.core.server.EVotingServerInt.receiveVote(String)) && args(ciphertext);

	// Repository key
	private static final String KEY_STORE_NAME = GenerateRepositoryKey.KEY_STORE_NAME;
	private static final String PASSWORD = GenerateRepositoryKey.PASSWORD;
	private static final String SECRET_KEY_ALIAS = GenerateRepositoryKey.SECRET_KEY_ALIAS;
		
	private RepositoryKey rk;
	
	public Encryption_AES_Server() {
		try {
			rk = new RepositoryKey(RepositoryKey.TYPE_FOR_SECRET_KEYS, PASSWORD.toCharArray(), KEY_STORE_NAME);
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

	@Override
	public SecretKey getEncryptionKey() {
		try {
			return rk.getKey(SECRET_KEY_ALIAS);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnrecoverableEntryException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SecretKey getDecryptionKey() {
		try {
			return rk.getKey(SECRET_KEY_ALIAS);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnrecoverableEntryException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return null;
	}
}
