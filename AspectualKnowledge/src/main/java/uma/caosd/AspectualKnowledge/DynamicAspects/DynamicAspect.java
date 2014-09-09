package uma.caosd.AspectualKnowledge.DynamicAspects;

import uma.caosd.AspectualKnowledge.Advice;
import uma.caosd.AspectualKnowledge.Advisor;
import uma.caosd.AspectualKnowledge.Pointcut;

public interface DynamicAspect {
	public Advisor getAdvisor();
	public Pointcut getPointcut();
	public Advice getAdvice();
}
