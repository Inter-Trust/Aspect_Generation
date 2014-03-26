package intertrust.aspects.aspectj;


public abstract aspect Authentication_UserPassword extends IntertrustAspect {

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
	
	pointcut p1(): enabled() && authPoints();
	
	abstract protected pointcut authPoints();
	
	before(): p1() {
		authenticate();
	}

	public void authenticate() {
		//System.out.println("Authenticating using user+password...");
		writeConsole("Authenticating using user+password...\n");
	}

}
