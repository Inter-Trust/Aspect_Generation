package intertrust.modules.aw.concrete.aspectj;

import intertrust.aspects.aspectj.EnablingDisablingInt;
import intertrust.modules.ag.generic.SAKClasses.AdaptationPlan;
import intertrust.modules.ag.generic.SAKClasses.Aspect;
import intertrust.modules.ag.generic.SAKClasses.Deploy;
import intertrust.modules.ag.generic.SAKClasses.Undeploy;
import intertrust.modules.aw.AspectWeaver;

import java.util.List;

/**
 * Concrete Aspect Weaver module for AspectJ.
 * This module enables and disables the woven aspects in the application at runtime.
 * 
 * @author UMA
 * @date   19/09/2013
 *
 */
public class AspectWeaver_AspectJ extends AspectWeaver {

	@Override
	public void adaptSecurityAspects(AdaptationPlan ap) {		
		Deploy deploy = ap.getDeploy();
		if (deploy != null) {
			enableAspects(deploy.getAspect(), true);
		}
		
		Undeploy undeploy = ap.getUndeploy();
		if (undeploy != null) {
			enableAspects(undeploy.getAspect(), false);
		}
	}
	
	/**
	 * Change the status of all the aspects.
	 * 
	 * @param aspects	Aspects.
	 * @param enabled	Status.
	 */
	private void enableAspects(List<Aspect> aspects, boolean enabled) {
		for (Aspect a : aspects) {
			String aspectName = a.getName();
			try {
				Class<?> aspectClass = Class.forName(aspectName);
				EnablingDisablingInt ed = (EnablingDisablingInt) aspectClass.newInstance();
				ed.setEnabled(enabled);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}
