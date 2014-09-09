package uma.caosd.AspectGenerationModule.concreteAG;

import uma.caosd.AspectGenerationModule.securityAspectualKnowledge.SecurityAspectualKnowledge;
import uma.caosd.AspectualKnowledge.AOPType;
import uma.caosd.AspectualKnowledge.Advisor;

public interface AdvisorInformation {
	
	/**
	 * Complete the information of the provided advisor with the Aspectual Knowledge for a specific weaver.
	 * @param advisor	Advisor.
	 * @param knowledge	Aspectual knowledge.
	 */
	public void completeInformation(Advisor advisor, SecurityAspectualKnowledge knowledge);
	
	/**
	 * Kind of weaver.
	 * @return			Weaver.
	 */
	public AOPType weaverKind();
}
