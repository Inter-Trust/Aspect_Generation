package intertrust.aspects.aspectj;

/**
 * Provides access to the status (enabled/disabled) of an aspect. 
 * 
 * @author UMA
 * @date   18/09/2013
 *
 */
public interface EnablingDisablingInt {

	/**
	 * 
	 * @return Current status of the aspect.
	 */
	public boolean isEnabled();
	
	/**
	 * Change the current state of the aspect.
	 * 
	 * @param enabled	New state of the aspect.
	 */
	public void setEnabled(boolean enabled);
}
