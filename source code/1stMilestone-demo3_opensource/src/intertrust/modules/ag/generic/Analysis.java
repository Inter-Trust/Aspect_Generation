package intertrust.modules.ag.generic;

import intertrust.modules.ag.generic.SAKClasses.AdaptationPlan;
import intertrust.modules.ag.generic.SAKClasses.Aspect;
import intertrust.modules.ag.generic.SAKClasses.CandidateAspect;
import intertrust.modules.ag.generic.SAKClasses.Category;
import intertrust.modules.ag.generic.SAKClasses.Deploy;
import intertrust.modules.ag.generic.SAKClasses.ListAF;
import intertrust.modules.ag.generic.SAKClasses.MappingSCA;
import intertrust.modules.ag.generic.SAKClasses.Sds;
import intertrust.modules.ag.generic.SAKClasses.SecurityConcept;
import intertrust.modules.ag.generic.SAKClasses.Undeploy;
import intertrust.modules.ag.generic.SAKClasses.Undeploy.UndeploySecurityConcept;

import java.util.ArrayList;
import java.util.List;


/**
 * This component analyses the notified changes and the security aspectual knowledge
 * and determines whether the security aspects, currently instantiated in the application, 
 * fulfil the new security policy or some changes must be done in the current aspects.

 * @author UMA
 * @date 12/09/2013
 *
 */
public class Analysis {
	
	private ListAF maf;
	private MappingSCA msca;
	private AdaptationPlan currentAP;
	private AdaptationPlan newAP;
	
	public Analysis() {
		
	}

	public AdaptationPlan generateAdaptationPlan(Sds sds, MappingSCA msca) {
		Deploy deploy = new Deploy();
		if (sds.getDeploy() != null) {
			for (Category c : sds.getDeploy().getCategory()) {
				for (SecurityConcept sc : c.getSecurityConcept()) {
					List<Aspect> aspects = searchAspectsForSecurityConcept(sc.getId(), msca);
					deploy.getAspect().addAll(aspects);
				}
			}			
		}
		
		Undeploy undeploy = new Undeploy();
		if (sds.getUndeploy() != null) {
			for (UndeploySecurityConcept usc : sds.getUndeploy().getUndeploySecurityConcept()) {
				List<Aspect> aspects = searchAspectsForSecurityConcept(usc.getId(), msca);
				undeploy.getAspect().addAll(aspects);
			}	
		}
	
		AdaptationPlan ap = new AdaptationPlan();
		ap.setDeploy(deploy);
		ap.setUndeploy(undeploy);
		return ap;
	}

	private List<Aspect> searchAspectsForSecurityConcept(String id,	MappingSCA msca) {
		List<Aspect> aspects = new ArrayList<Aspect>();
		
		for (SecurityConcept sc : msca.getSecurityConcept()) {
			if (sc.getId().equals(id)) {
				for (CandidateAspect ca : sc.getCandidateAspect()) {
					aspects.addAll(ca.getAspect());
				}	
			}
		}
		return aspects;
	}
	
}
