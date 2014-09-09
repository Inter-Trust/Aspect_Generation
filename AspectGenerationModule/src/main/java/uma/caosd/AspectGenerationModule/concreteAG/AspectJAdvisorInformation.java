package uma.caosd.AspectGenerationModule.concreteAG;

import uma.caosd.AspectGenerationModule.SAKAnalysis;
import uma.caosd.AspectGenerationModule.exceptions.AspectualKnowledgeException;
import uma.caosd.AspectGenerationModule.securityAspectualKnowledge.SecurityAspectualKnowledge;
import uma.caosd.AspectualKnowledge.AOPType;
import uma.caosd.AspectualKnowledge.Advice;
import uma.caosd.AspectualKnowledge.Advisor;
import uma.caosd.AspectualKnowledge.Pointcut;

public class AspectJAdvisorInformation implements AdvisorInformation {
	public static final AOPType AOP_TYPE = AOPType.ASPECT_J;
	
	public void completeInformation(Advisor advisor, SecurityAspectualKnowledge knowledge) {
		try {
			SAKAnalysis sak = knowledge.getSAKAnalysis();
			String pointcutID = advisor.getPointcutRef();
			String adviceID = advisor.getAdviceRef();
			Pointcut pointcut = sak.getPointcut(pointcutID);
			Advice advice = sak.getAdvice(adviceID);
			advisor.setPointcut(pointcut);
			advisor.setAdvice(advice);
		} catch (AspectualKnowledgeException e) {
			e.printStackTrace();
		}
	}

	public AOPType weaverKind() {
		return AOP_TYPE;
	}

}
