package intertrust.modules.ag;

import intertrust.modules.ag.generic.SAKClasses.AdaptationPlan;

/**
 * 
 * @author UMA
 * @date   19/09/2013
 *
 */
public interface GenericAspectsAdaptationRequestInt {

	//public void provideGenericAdaptationPlan(AdaptationPlan ap);
	
	/**
	 * Generates the specific adaptation plan for the concrete AOP framework
	 * from the generic adaptation plan.
	 *  
	 * @param genericAP	Generic adaptation plan.
	 * @return			Specific adaptation plan.
	 */
	public AdaptationPlan getConcreteAdaptationPlan(AdaptationPlan genericAP);
}
