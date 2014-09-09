package uma.caosd.AspectGenerationModule.concreteAG;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uma.caosd.AspectGenerationModule.securityAspectualKnowledge.SecurityAspectualKnowledge;
import uma.caosd.AspectualKnowledge.AOPType;
import uma.caosd.AspectualKnowledge.AdaptationPlan;
import uma.caosd.AspectualKnowledge.Advisor;

public class ConcreteSecurityAdaptationPlanGeneration {
	private SecurityAspectualKnowledge knowledge;
	private Map<AOPType, AdvisorInformation> advisorInformationWeavers;
	
	public ConcreteSecurityAdaptationPlanGeneration(SecurityAspectualKnowledge knowledge) {
		this.knowledge = knowledge;
		advisorInformationWeavers = new HashMap<AOPType, AdvisorInformation>();
		advisorInformationWeavers.put(AspectJAdvisorInformation.AOP_TYPE, new AspectJAdvisorInformation());
		advisorInformationWeavers.put(SpringAOPAdvisorInformation.AOP_TYPE, new SpringAOPAdvisorInformation());
	}
	
	public AdaptationPlan completeSecurityAdaptationPlan(AdaptationPlan genericSAP) {
		completeAdvisorInformation(genericSAP.getADD().getAdvisor());
		completeAdvisorInformation(genericSAP.getREMOVE().getAdvisor());
		completeAdvisorInformation(genericSAP.getCONFIGURE().getAdvisor());
		return genericSAP;
	}
	
	private void completeAdvisorInformation(List<Advisor> advisors) {
		for (Advisor a : advisors) {
			AdvisorInformation advInfoweaver = advisorInformationWeavers.get(a.getType());
			if (advInfoweaver != null)
				advInfoweaver.completeInformation(a, knowledge);	
		}
	}
}
