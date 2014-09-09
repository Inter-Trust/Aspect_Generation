package uma.caosd.AspectGenerationModule.securityAspectualKnowledge;

import java.util.HashSet;
import java.util.Set;

import uma.caosd.AspectGenerationModule.SAKAnalysis;
import uma.caosd.AspectGenerationModule.exceptions.AspectualKnowledgeException;
import uma.caosd.AspectualKnowledge.Advice;
import uma.caosd.AspectualKnowledge.AdviceCategory;
import uma.caosd.AspectualKnowledge.Advisor;
import uma.caosd.AspectualKnowledge.AdvisorCategory;
import uma.caosd.AspectualKnowledge.AspectualKnowledge;
import uma.caosd.AspectualKnowledge.Pointcut;

public class SAKUpdate {
	

	public static void updateAspectualKnowledge(SAKAnalysis sakAnalysis, AspectualKnowledge sak, AspectualKnowledge newSAK) {
		updatePointcuts(sakAnalysis, sak, newSAK);
		updateAdvices(sakAnalysis, sak, newSAK);
		updateAdvisors(sakAnalysis, sak, newSAK);
	}
	
	private static void updatePointcuts(SAKAnalysis sakAnalysis, AspectualKnowledge sak, AspectualKnowledge newSAK) {
		if (newSAK.getPointcuts() != null) {
			Set<String> existingPointcuts = new HashSet<String>();
			try {
				existingPointcuts = sakAnalysis.getPointcutsID();
			} catch (AspectualKnowledgeException e) {
				existingPointcuts = new HashSet<String>();
			}
			
			for (Pointcut p : newSAK.getPointcuts().getPointcut()) {
				if (!existingPointcuts.contains(p.getId())) {
					addPointcut(sak, p);
				} else {
					updatePointcut(sakAnalysis, p);
				}
			}
		}
	}
	
	private static void addPointcut(AspectualKnowledge sak, Pointcut p) {
		sak.getPointcuts().getPointcut().add(p);
	}
	
	private static void updatePointcut(SAKAnalysis sakAnalysis, Pointcut p) {
		try {
			Pointcut oldPointcut = sakAnalysis.getPointcut(p.getId());
			oldPointcut.setName(p.getName());
			oldPointcut.setExpression(p.getExpression());
		} catch (AspectualKnowledgeException e) {
			e.printStackTrace();
		}
	}

	private static void updateAdvices(SAKAnalysis sakAnalysis, AspectualKnowledge sak, AspectualKnowledge newSAK) {
		if (newSAK.getAdvices() != null) {
			Set<String> existingAdvices = new HashSet<String>();
			try {
				existingAdvices = sakAnalysis.getAdvicesID();
			} catch (AspectualKnowledgeException e) {
				existingAdvices = new HashSet<String>();
			}
			
			for (AdviceCategory ac : newSAK.getAdvices().getAdviceCategory()) {
				for (Advice a : ac.adviceCategory()) {
					if (!existingAdvices.contains(a.getId())) {
						addAdvice(sak, ac, a);
					} else {
						updateAdvice(sakAnalysis, a);
					}
				}
			}	
		}
	}
	
	private static void addAdvice(AspectualKnowledge sak, AdviceCategory ac, Advice advice) {
		AdviceCategory category = getAdviceCategory(sak, ac);
		if (category == null) {
			AdviceCategory newAC = new AdviceCategory();
			newAC.setId(ac.getId());
			newAC.adviceCategory().add(advice);
			sak.getAdvices().getAdviceCategory().add(newAC);
		} else {
			category.adviceCategory().add(advice);
		}
	}
	
	private static void updateAdvice(SAKAnalysis sakAnalysis, Advice advice) {
		try {
			Advice oldAdvice = sakAnalysis.getAdvice(advice.getId());
			oldAdvice.setClassname(advice.getClassname());
			oldAdvice.setFilepath(advice.getFilepath());
			oldAdvice.setFunctionalities(advice.getFunctionalities());
		} catch (AspectualKnowledgeException e) {
			e.printStackTrace();
		}
	}
	
	private static AdviceCategory getAdviceCategory(AspectualKnowledge sak, AdviceCategory ac) {
		AdviceCategory targetCategory = null;
		for (AdviceCategory adviceCategory : sak.getAdvices().getAdviceCategory()) {
			if (ac.getId().equals(adviceCategory.getId())) {
				targetCategory = adviceCategory;
			}
		}
		return targetCategory;
	}
	
	private static void updateAdvisors(SAKAnalysis sakAnalysis, AspectualKnowledge sak, AspectualKnowledge newSAK) {
		if (newSAK.getAdvisors() != null) {
			Set<String> existingAdvisors = new HashSet<String>();
			try {
				existingAdvisors = sakAnalysis.getAdvisorsID();
			} catch (AspectualKnowledgeException e) {
				existingAdvisors = new HashSet<String>();
			}
			
			for (AdvisorCategory ac : newSAK.getAdvisors().getAdvisorCategory()) {
				for (Advisor a : ac.getAdvisor()) {
					if (!existingAdvisors.contains(a.getId())) {
						addAdvisor(sak, ac, a);
					} else {
						updateAdvisor(sakAnalysis, a);
					}
				}
			}	
		}
	}

	private static void addAdvisor(AspectualKnowledge sak, AdvisorCategory ac, Advisor a) {
		AdvisorCategory category = getAdvisorCategory(sak, ac);
		if (category == null) {
			AdvisorCategory newAC = new AdvisorCategory();
			newAC.setId(ac.getId());
			newAC.getAdvisor().add(a);
			sak.getAdvisors().getAdvisorCategory().add(newAC);
		} else {
			category.getAdvisor().add(a);
		}	
	}
	
	private static void updateAdvisor(SAKAnalysis sakAnalysis, Advisor a) {
		try {
			Advisor oldAdvisor = sakAnalysis.getAdvisor(a.getId());
			oldAdvisor.setName(a.getName());
			oldAdvisor.setConfiguration(a.getConfiguration());
			oldAdvisor.setType(a.getType());
			oldAdvisor.setPointcut(a.getPointcut());
			oldAdvisor.setPointcutRef(a.getPointcutRef());
			oldAdvisor.setAdvice(a.getAdvice());
			oldAdvisor.setAdviceRef(a.getAdviceRef());
		} catch (AspectualKnowledgeException e) {
			e.printStackTrace();
		}
	}
	
	private static AdvisorCategory getAdvisorCategory(AspectualKnowledge sak, AdvisorCategory ac) {
		AdvisorCategory targetCategory = null;
		for (AdvisorCategory advisorCategory : sak.getAdvisors().getAdvisorCategory()) {
			if (ac.getId().equals(advisorCategory.getId())) {
				targetCategory = advisorCategory;
			}
		}
		return targetCategory;
	}
	
}
