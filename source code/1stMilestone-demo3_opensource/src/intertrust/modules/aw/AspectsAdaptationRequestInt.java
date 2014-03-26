package intertrust.modules.aw;

import intertrust.modules.ag.generic.SAKClasses.AdaptationPlan;

/**
 * This interface is used to provide/receive information about
 * the list of aspects that need to be added/deleted by the aspect weaver.
 * 
 * @author UMA
 * @date   17/09/2013
 *
 */
public interface AspectsAdaptationRequestInt {

	public void adaptSecurityAspects(AdaptationPlan ap);
}
