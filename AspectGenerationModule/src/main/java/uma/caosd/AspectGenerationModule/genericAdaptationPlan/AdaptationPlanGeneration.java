package uma.caosd.AspectGenerationModule.genericAdaptationPlan;

import java.util.HashSet;
import java.util.Set;

import uma.caosd.AspectualKnowledge.ADD;
import uma.caosd.AspectualKnowledge.AdaptationPlan;
import uma.caosd.AspectualKnowledge.Advisor;
import uma.caosd.AspectualKnowledge.CONFIGURE;
import uma.caosd.AspectualKnowledge.Configuration;
import uma.caosd.AspectualKnowledge.REMOVE;

public class AdaptationPlanGeneration {
	
	public AdaptationPlanGeneration() {
		
	}
	
	public AdaptationPlan generateAdaptationPlan(Configuration deployConfiguration,
												 Configuration undeployConfiguration,
												 Configuration remainConfiguration) {
		AdaptationPlan sap = new AdaptationPlan();
		sap.setInstance(deployConfiguration.getInstance());
		
		ADD add = new ADD();
		add.getAdvisor().addAll(deployConfiguration.getAdvisor());
		sap.setADD(add);
		
		REMOVE remove = new REMOVE();
		remove.getAdvisor().addAll(undeployConfiguration.getAdvisor());
		sap.setREMOVE(remove);
		
		CONFIGURE configure = new CONFIGURE();
		configure.getAdvisor().addAll(remainConfiguration.getAdvisor());
		configure.getAdvisor().addAll(getAdvisorThatNeedConfiguration(deployConfiguration));
		sap.setCONFIGURE(configure);
		
		return sap;
	}
	
	private Set<Advisor> getAdvisorThatNeedConfiguration(Configuration deployConfiguration) {
		Set<Advisor> resAdvisors = new HashSet<Advisor>();
		for (Advisor a : deployConfiguration.getAdvisor()) {
			if (a.getConfiguration() != null) {
				resAdvisors.add(a);
			}
		}
		return resAdvisors;
	}
}
