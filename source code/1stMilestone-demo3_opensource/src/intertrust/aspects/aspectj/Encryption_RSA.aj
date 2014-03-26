package intertrust.aspects.aspectj;


public abstract aspect Encryption_RSA extends IntertrustAspect {

	// ENABLING/DISABLING
	private static boolean enabled = false;
	
	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	pointcut enabled(): if(enabled);
	
	pointcut p1(): enabled() && encrypPoints();
	pointcut p2(): enabled() && decryptPoints();
	
	abstract protected pointcut encrypPoints();
	abstract protected pointcut decryptPoints();
	
	before(): p1() {
		encrypt();
	}

	before(): p2() {
		decrypt();
	}
	
	public void encrypt() {
		//System.out.println("Encrypting using RSA...");
		writeConsole("Encrypting using RSA...\n");
	}
	
	public void decrypt() {
		//System.out.println("Decrypting using RSA...");
		writeConsole("Decrypting using RSA...\n");
	}
}
