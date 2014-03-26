package intertrust.modules.aw.gui;

import intertrust.modules.ag.ConcreteAspectGeneration;
import intertrust.modules.ag.concrete.aspectj.AspectGeneration_AspectJ;
import intertrust.modules.ag.generic.SAKClasses.AdaptationPlan;
import intertrust.modules.ag.generic.SAKClasses.Aspect;
import intertrust.modules.ag.generic.SAKClasses.Deploy;
import intertrust.modules.ag.generic.SAKClasses.Undeploy;

import java.util.List;

public class AspectWeaverController {

	private ConcreteAspectGeneration aspectjAG;
	private ConcreteAspectGeneration springaopAG;
	
	private AspectWeaverView awv;
	
	public AspectWeaverController(List<ConcreteAspectGeneration> ags, AspectWeaverView awv) {
		this.awv = awv;
		for (ConcreteAspectGeneration ag : ags) {
			if (ag instanceof AspectGeneration_AspectJ) {
				aspectjAG = ag;
			} /*else if (ag instanceof AspectGeneration_SpringAOP) {
				springaopAG = ag;
			}*/ // not open source
		}
	}
	
	// GUI
	public void adaptSecurityAspects(AdaptationPlan ap) {
		awv.writeTextConsole(">>Performing new adaptation plan...\n");
		
		if (aspectjAG != null) {
			AdaptationPlan apAspectJ = aspectjAG.getConcreteAdaptationPlan(ap);
			adaptSecurityAspects_AspectJ(apAspectJ);	
		}
		
		if (springaopAG != null) {
			AdaptationPlan apSpringAOP = springaopAG.getConcreteAdaptationPlan(ap);
			adaptSecurityAspects_SpringAOP(apSpringAOP);	
		}

		awv.writeTextConsole(">>Adaptation plan deployed.\n");
	}
	
	private void adaptSecurityAspects_AspectJ(AdaptationPlan ap) {
		awv.writeTextConsole(">>ASPECT-J weaver:\n");
		
		awv.writeTextConsole(">>>>Deploying aspects...\n");
		Deploy deploy = ap.getDeploy();
		if (deploy != null) {
			for (Aspect a : deploy.getAspect()) {
				awv.writeTextConsole(">>>>>>enabling " + a.getName() + "...\n");
			}
		}
		
		awv.writeTextConsole(">>>>Undeploying aspects...\n");
		Undeploy undeploy = ap.getUndeploy();
		if (undeploy != null) {
			for (Aspect a : undeploy.getAspect()) {
				awv.writeTextConsole(">>>>>>disabling " + a.getName() + "...\n");
			}
		}
	}
	
	private void adaptSecurityAspects_SpringAOP(AdaptationPlan ap) {
		awv.writeTextConsole(">>SPRING-AOP weaver:\n");
		
		awv.writeTextConsole(">>>>Deploying aspects...\n");
		Deploy deploy = ap.getDeploy();
		if (deploy != null) {
			for (Aspect a : deploy.getAspect()) {
				awv.writeTextConsole(">>>>>>adding " + a.getName() + "...\n");
			}
		}
		
		awv.writeTextConsole(">>>>Undeploying aspects...\n");
		Undeploy undeploy = ap.getUndeploy();
		if (undeploy != null) {
			for (Aspect a : undeploy.getAspect()) {
				awv.writeTextConsole(">>>>>>removing " + a.getName() + "...\n");
			}
		}
	}
}
