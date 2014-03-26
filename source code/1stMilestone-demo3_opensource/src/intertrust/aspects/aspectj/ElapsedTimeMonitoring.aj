package intertrust.aspects.aspectj;


public abstract aspect ElapsedTimeMonitoring extends IntertrustAspect {

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
	
	pointcut p1(): enabled() && monitorPoints();
	
	abstract protected pointcut monitorPoints();
	
	Object around(): p1() {
		time();
		Object o = proceed();
		time2();
		return o;
	}
	
	public void time() {
		//System.out.println("ElapsedTimeMonitorization, before.");
		writeConsole("ElapsedTimeMonitorization, before.\n");
		time = System.currentTimeMillis();
	}
	
	public void time2() {
		//System.out.println("ElapsedTimeMonitorization, after.");
		time = System.currentTimeMillis() - time;
		writeConsole("ElapsedTimeMonitorization, after. Execution time: " + time + " ms.\n");
		notifyIntertrust(">>'Elapsed Time Monitorization' aspect: The process is taking too much time.");
	}
	
	private long time;
}
