package intertrust.modules.ag.generic;

import intertrust.modules.ag.generic.SAKClasses.AdaptationPlan;
import intertrust.modules.ag.generic.SAKClasses.Aspect;
import intertrust.modules.ag.generic.SAKClasses.Deploy;
import intertrust.modules.ag.generic.SAKClasses.Undeploy;
import intertrust.utils.Utils;

import java.util.Collection;


/**
 * This component generates a list of aspects and/or pointcuts 
 * that need to be added/deleted in order to satisfy the new security policy.
 * This component is independent from the AOP framework used to weave the aspects. 
 * The output of this component is the input to the concrete aspect generation sub-module.
 * 
 * @author UMA
 * @date 12/09/2013
 */
public class GenericSecurityAdaptationPlanGeneration {
	
	public GenericSecurityAdaptationPlanGeneration() {
		
	}
	
	/**
	 * Generate the action adaptation plan to be performed in the application.
	 * The action adaptation plan is the difference between the newAP and the currentAP (newAP - currentAP).
	 * 
	 * Deployed aspects = deployed aspect in newAP - deployedAspect in currentAP.
	 * Undeployed aspects = undeployed aspect in newAP - undeployedAspect in currentAP.
	 * 
	 * @param currentAP	Current adaptation plan deployed in the application.
	 * @param newAP		New adaptation plan for the application.
	 * @return			The action adaptation plan to be performed (newAP - currentAP).
	 */
	public AdaptationPlan generateActionSecurityAdaptationPlan(AdaptationPlan currentAP, AdaptationPlan newAP) {
		return SecurityAdaptationPlanOperations.difference(newAP, currentAP);
	}
	
	/**
	 * 
	 * Deployed aspects = deployed aspect in currentAP + deployed aspects in actionAP - undeployed aspects in actionAP.
	 * Undeployed aspects = undeployed aspect in currentAP + undeployed aspects in actionAP - deployed aspects in actionAP.
	 * 
	 * 
	 * @param currentAP
	 * @param newAP
	 * @return
	 */
	public AdaptationPlan generateCurrentSecurityAdaptationPlan(AdaptationPlan currentAP, AdaptationPlan actionAP) {
		AdaptationPlan c = SecurityAdaptationPlanOperations.or(currentAP, actionAP);
		
		Deploy deployC = c.getDeploy();
		Undeploy undeployC = c.getUndeploy();
		
		Deploy deployActionAP = actionAP.getDeploy();
		Undeploy undeployActionAP = actionAP.getUndeploy();
		
		if (undeployActionAP != null) {
			Collection<Aspect> deployAspects = Utils.difference(deployC.getAspect(), undeployActionAP.getAspect()); 
			deployC.getAspect().retainAll(deployAspects);
		}
		
		if (deployActionAP != null) {
			Collection<Aspect> undeployAspects = Utils.difference(undeployC.getAspect(), deployActionAP.getAspect()); 
			undeployC.getAspect().retainAll(undeployAspects);
		}
		
		return c;
		
	}
	
}
