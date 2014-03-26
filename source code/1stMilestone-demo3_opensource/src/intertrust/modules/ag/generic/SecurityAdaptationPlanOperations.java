package intertrust.modules.ag.generic;

import intertrust.modules.ag.generic.SAKClasses.AdaptationPlan;
import intertrust.modules.ag.generic.SAKClasses.Aspect;
import intertrust.modules.ag.generic.SAKClasses.Deploy;
import intertrust.modules.ag.generic.SAKClasses.Undeploy;
import intertrust.utils.Utils;

import java.util.Collection;

/**
 * 
 * @author UMA
 * @date   13/09/2013
 *
 */
public class SecurityAdaptationPlanOperations {

	/**
	 * Calculate the union of the adaptation plans (a + b).
	 * 
	 * Deployed aspects = deployed aspect in a + deployed aspects in b.
	 * Undeployed aspects = undeployed aspect in a + undeployed aspects in b.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static AdaptationPlan or(AdaptationPlan a, AdaptationPlan b) {
		AdaptationPlan c = new AdaptationPlan();
		Deploy deployC = new Deploy();
		Undeploy undeployC = new Undeploy();
		
		Deploy deployA = a.getDeploy();
		Deploy deployB = b.getDeploy();
		if (deployA != null && deployB != null) {
			Collection<Aspect> deployAspects = Utils.or(deployA.getAspect(), deployB.getAspect()); 
			deployC.getAspect().addAll(deployAspects);
		} else if (deployA == null) {
			deployC.getAspect().addAll(deployB.getAspect());
		} else if (deployB == null) {
			deployC.getAspect().addAll(deployA.getAspect());
		}

		Undeploy undeployA = a.getUndeploy();
		Undeploy undeployB = b.getUndeploy();
		if (undeployA != null && undeployB != null) {
			Collection<Aspect> undeployAspects = Utils.or(undeployA.getAspect(), undeployB.getAspect()); 
			undeployC.getAspect().addAll(undeployAspects);
		} else if (undeployA == null) {
			undeployC.getAspect().addAll(undeployB.getAspect());
		} else if (undeployB == null) {
			undeployC.getAspect().addAll(undeployA.getAspect());
		}
		
		c.setDeploy(deployC);
		c.setUndeploy(undeployC);
		
		return c;
	}
	
	
	/**
	 * Calculate the difference between the adaptation plans (a - b).
	 * 
	 * Deployed aspects = deployed aspect in a - deployed aspects in b.
	 * Undeployed aspects = undeployed aspect in a - undeployed aspects in b.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static AdaptationPlan difference(AdaptationPlan a, AdaptationPlan b) {
		AdaptationPlan c = new AdaptationPlan();
		Deploy deployC = new Deploy();
		Undeploy undeployC = new Undeploy();
		
		Deploy deployA = a.getDeploy();
		Deploy deployB = b.getDeploy();
		if (deployA != null && deployB != null) {
			Collection<Aspect> deployAspects = Utils.difference(deployA.getAspect(), deployB.getAspect()); 
			deployC.getAspect().addAll(deployAspects);
		} else if (deployB == null) {
			deployC.getAspect().addAll(deployA.getAspect());
		}

		Undeploy undeployA = a.getUndeploy();
		Undeploy undeployB = b.getUndeploy();
		if (undeployA != null && undeployB != null) {
			Collection<Aspect> undeployAspects = Utils.difference(undeployA.getAspect(), undeployB.getAspect()); 
			undeployC.getAspect().addAll(undeployAspects);
		} else if (undeployB == null) {
			undeployC.getAspect().addAll(undeployA.getAspect());
		}
		
		c.setDeploy(deployC);
		c.setUndeploy(undeployC);
			
		return c;
	}
}
