package intertrust.aspects.aspectj;

import java.util.Random;

public abstract aspect VoteRejection extends IntertrustAspect {

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

	abstract protected pointcut rejectPoints();
	
	pointcut p1(): enabled() && rejectPoints();
	
	before(): p1() {
		reject();
	}
	
	private static Random rnd = new Random();
	
	public void reject() {
		/*
		if (rnd.nextBoolean()) {
			writeConsole("VoteRejection: vote rejected.\n");
		} else {
			writeConsole("VoteRejection: vote accepted.\n");
		}
		*/
		writeConsole("VoteRejection: vote rejected.\n");
	}
}
