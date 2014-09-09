package uma.caosd.AspectGenerationModule;

import uma.caosd.AspectGenerationModule.concreteAG.ConcreteSecurityAdaptationPlanGeneration;
import uma.caosd.AspectGenerationModule.securityAspectualKnowledge.SecurityAspectualKnowledge;
import uma.caosd.AspectualKnowledge.AdaptationPlan;

public class ConcreteAspectGeneration {
	private SecurityAspectualKnowledge knowledge;
	private ConcreteSecurityAdaptationPlanGeneration concreteSAPGeneration;
	
	public ConcreteAspectGeneration(SecurityAspectualKnowledge knowledge) {
		this.knowledge = knowledge;
		concreteSAPGeneration = new ConcreteSecurityAdaptationPlanGeneration(knowledge);
	}
	
	public AdaptationPlan adaptsAdaptationPlan(AdaptationPlan genericSAP) {
		AdaptationPlan concreteSAP = concreteSAPGeneration.completeSecurityAdaptationPlan(genericSAP);
		return concreteSAP;
	}
}
