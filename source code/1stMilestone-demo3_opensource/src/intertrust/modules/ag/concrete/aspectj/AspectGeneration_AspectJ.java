package intertrust.modules.ag.concrete.aspectj;

import intertrust.modules.ag.ConcreteAspectGeneration;
import intertrust.modules.ag.generic.SAKClasses.AdaptationPlan;
import intertrust.modules.ag.generic.SAKClasses.Aspect;
import intertrust.modules.ag.generic.SAKClasses.Deploy;
import intertrust.modules.ag.generic.SAKClasses.SecurityConcept;
import intertrust.modules.ag.generic.SAKClasses.Undeploy;
import intertrust.modules.aw.AspectWeaver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Concrete Aspect Generation module for AspectJ.
 * 
 * @author UMA
 * @date   19/09/2013
 *
 */
public class AspectGeneration_AspectJ extends ConcreteAspectGeneration {
	
	/**
	 * Repository aspects for AspectJ's aspects.
	 * Complete name of the aspects (package + class).
	 */
	private List<String> repositoryAspects;
	
	
	/**
	 * Creates a new instance of the concrete aspect generation for AspectJ,
	 * with the indicated aspect weaver module for AspectJ.
	 * 
	 * @param concreteAW	Aspect weaver associated for AspectJ.
	 */
	public AspectGeneration_AspectJ(AspectWeaver concreteAW) {
		super(concreteAW);
		repositoryAspects = new ArrayList<String>();
	}

	/**
	 * 
	 * @return	Repository aspects in AspectJ.
	 */
	public List<String> getRepositoryAspects() {
		return repositoryAspects;
	}

	/**
	 * 
	 * @param repositoryAspects	New repository aspects.
	 */
	public void setRepositoryAspects(List<String> repositoryAspects) {
		this.repositoryAspects = repositoryAspects;
	}

	@Override
	public List<String> incorporateNewAspect(SecurityConcept sc, File newAspectsFile) {
		return new ArrayList<String>();
	}

	@Override
	public AdaptationPlan getConcreteAdaptationPlan(AdaptationPlan genericAP) {
		AdaptationPlan concreteAP = new AdaptationPlan();
		Deploy concreteDeploy = new Deploy();
		Undeploy concreteUndeploy = new Undeploy();
		
		Deploy deploy = genericAP.getDeploy();
		if (deploy != null) {
			for (Aspect a : deploy.getAspect()) {
				if (repositoryAspects.contains(a.getName())) {
					concreteDeploy.getAspect().add(a);
				}
			}
		}
		
		Undeploy undeploy = genericAP.getUndeploy();
		if (undeploy != null) {
			for (Aspect a : undeploy.getAspect()) {
				if (repositoryAspects.contains(a.getName())) {
					concreteUndeploy.getAspect().add(a);
				}
			}
		}
		
		concreteAP.setDeploy(concreteDeploy);
		concreteAP.setUndeploy(concreteUndeploy);
		return concreteAP;
	}
}
