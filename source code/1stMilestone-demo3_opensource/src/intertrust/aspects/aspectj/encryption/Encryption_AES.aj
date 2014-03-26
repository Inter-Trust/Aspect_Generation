package intertrust.aspects.aspectj.encryption;



public abstract aspect Encryption_AES extends Encryption {

	// AES Algorithm
	private static final String CHARSET = "ISO-8859-1";
	private static final String ALGORITHM = "AES";
	private static final String MODE = "ECB";
	private static final String PADDING = "PKCS5Padding";

	@Override
	public String getAlgorithm() {
		return ALGORITHM;
	}

	@Override
	public String getMode() {
		return MODE;
	}

	@Override
	public String getPadding() {
		return PADDING;
	}

	@Override
	public String getCharset() {
		return CHARSET;
	}
	
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

}
