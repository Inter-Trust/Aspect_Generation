package intertrust.aspects.aspectj;


public abstract aspect Encryption_DSA extends IntertrustAspect {

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
		//System.out.println("Encrypting using DSA...");
		writeConsole("Encrypting using DSA...\n");
	}
	
	public void decrypt() {
		writeConsole("Decrypting using DSA...\n");
	}
}
