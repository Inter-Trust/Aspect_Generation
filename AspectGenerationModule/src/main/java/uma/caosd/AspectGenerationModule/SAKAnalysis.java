package uma.caosd.AspectGenerationModule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uma.caosd.AspectGenerationModule.exceptions.AspectualKnowledgeException;
import uma.caosd.AspectGenerationModule.utils.SetOperations;
import uma.caosd.AspectualKnowledge.Advice;
import uma.caosd.AspectualKnowledge.AdviceCategory;
import uma.caosd.AspectualKnowledge.Advices;
import uma.caosd.AspectualKnowledge.Advisor;
import uma.caosd.AspectualKnowledge.AdvisorCategory;
import uma.caosd.AspectualKnowledge.Advisors;
import uma.caosd.AspectualKnowledge.AspectualKnowledge;
import uma.caosd.AspectualKnowledge.Functionalities;
import uma.caosd.AspectualKnowledge.Functionality;
import uma.caosd.AspectualKnowledge.Pointcut;
import uma.caosd.AspectualKnowledge.Pointcuts;
import uma.caosd.errorHandling.DeploymentStatusSingleton;
import uma.caosd.errors.Module;
import uma.caosd.errors.Type;

/**
 * Provides access to the aspectual knowledge.
 * 
 * @author UMA
 * @date 22/04/2014
 * 
 */
public class SAKAnalysis {
	private AspectualKnowledge sak;
	
	public SAKAnalysis(AspectualKnowledge sak) {
		this.sak = sak;
	}
	
	/**
	 * Get all the pointcuts identifiers.
	 * @return								Pointcuts identifiers.
	 * @throws AspectualKnowledgeException 	There is not available pointcuts.
	 */
	public Set<String> getPointcutsID() throws AspectualKnowledgeException {
		Pointcuts pointcuts = sak.getPointcuts();
		if (pointcuts == null) {
			String desc = "There is not available pointcuts.";
			DeploymentStatusSingleton.getStatus().addError(desc, Module.ASPECT_GENERATION, Type.SAK);
			throw new AspectualKnowledgeException(desc);
		}
		
		HashSet<String> res = new HashSet<String>();
		for (Pointcut p : pointcuts.getPointcut())
			res.add(p.getId());
		return res;
	}
	
	/**
	 * Get the pointcut with the provided identifier
	 * @param id							Identifier.
	 * @return								Pointcut with the provided indentifier.
	 * @throws AspectualKnowledgeException	There is not available pointcut with the provided ID.
	 */
	public Pointcut getPointcut(String id) throws AspectualKnowledgeException {
		Pointcuts pointcuts = sak.getPointcuts();
		if (pointcuts == null) {
			String desc = "There is not available pointcuts.";
			DeploymentStatusSingleton.getStatus().addError(desc, Module.ASPECT_GENERATION, Type.SAK);
			throw new AspectualKnowledgeException(desc);
		}
			
		for (Pointcut p : pointcuts.getPointcut())
			if (p.getId().equals(id))
				return p;
		String desc = "There is not pointcut with ID '" + id + "'.";
		DeploymentStatusSingleton.getStatus().addError(desc, Module.ASPECT_GENERATION, Type.SAK);
		throw new AspectualKnowledgeException(desc);
	}
	
	/**
	 * Get all the advices identifiers.
	 * @return								Advices identifiers.
	 * @throws AspectualKnowledgeException 	There is not available advices.
	 */
	public Set<String> getAdvicesID() throws AspectualKnowledgeException {
		Advices advices = sak.getAdvices();
		if (advices == null) {
			String desc = "There is not available advices.";
			DeploymentStatusSingleton.getStatus().addError(desc, Module.ASPECT_GENERATION, Type.SAK);
			throw new AspectualKnowledgeException(desc);
		}
		
		HashSet<String> res = new HashSet<String>();
		for (AdviceCategory c : advices.getAdviceCategory())
			for (Advice a : c.adviceCategory())
				res.add(a.getId());
		return res;
	}
	
	/**
	 * Get the advice with the provided identifier
	 * @param id							Identifier.
	 * @return								Advice with the provided indentifier.
	 * @throws AspectualKnowledgeException	There is not available advice with the provided ID.
	 */
	public Advice getAdvice(String id) throws AspectualKnowledgeException {
		Advices advices = sak.getAdvices();
		if (advices == null) {
			String desc = "There is not available advices.";
			DeploymentStatusSingleton.getStatus().addError(desc, Module.ASPECT_GENERATION, Type.SAK);
			throw new AspectualKnowledgeException(desc);
		}
		for (AdviceCategory c : advices.getAdviceCategory())
			for (Advice a : c.adviceCategory())
				if (a.getId().equals(id))
					return a;
		String desc = "There is not advice with ID '" + id + "'.";
		DeploymentStatusSingleton.getStatus().addError(desc, Module.ASPECT_GENERATION, Type.SAK);
		throw new AspectualKnowledgeException(desc);	
	}
	
	
	/**
	 * Get all the advisors identifiers.
	 * @return								Advisors identifiers.
	 * @throws AspectualKnowledgeException 	There is not available advisors.
	 */
	public Set<String> getAdvisorsID() throws AspectualKnowledgeException {
		Advisors advisors = sak.getAdvisors();
		if (advisors == null) {
			String desc = "There is not available advisors.";
			DeploymentStatusSingleton.getStatus().addError(desc, Module.ASPECT_GENERATION, Type.SAK);
			throw new AspectualKnowledgeException(desc);	
		}		
		HashSet<String> res = new HashSet<String>();
		for (AdvisorCategory c : advisors.getAdvisorCategory())
			for (Advisor a : c.getAdvisor())
				res.add(a.getId());
		return res;
	}
	
	/**
	 * Get the advisor with the provided identifier
	 * @param id							Identifier.
	 * @return								Advisor with the provided indentifier.
	 * @throws AspectualKnowledgeException	There is not available advisor with the provided ID.
	 */
	public Advisor getAdvisor(String id) throws AspectualKnowledgeException {
		Advisors advisors = sak.getAdvisors();
		if (advisors == null) {
			String desc = "There is not available advisors.";
			DeploymentStatusSingleton.getStatus().addError(desc, Module.ASPECT_GENERATION, Type.SAK);
			throw new AspectualKnowledgeException(desc);	
		}
		for (AdvisorCategory c : advisors.getAdvisorCategory())
			for (Advisor a : c.getAdvisor())
				if (a.getId().equals(id))
				return a;
		String desc = "There is not advisor with ID '" + id + "'.";
		DeploymentStatusSingleton.getStatus().addError(desc, Module.ASPECT_GENERATION, Type.SAK);
		throw new AspectualKnowledgeException(desc);		
	}
	
	/**
	 * Get all the advices that provide (each of them) the functionality specified.
	 * @param functionalityID	Functionality identifier.
	 * @return					Advices with the functionality.
	 */
	public Set<Advice> getAdvicesWithFunctionality(String functionalityID) {
		HashSet<Advice> advicesRes = new HashSet<Advice>();
		
		Advices advices = sak.getAdvices();
		if (advices != null) {
			for (AdviceCategory category : advices.getAdviceCategory()) {
				for (Advice advice : category.adviceCategory()) {
					if (adviceProvidesFunctionality(advice, functionalityID))
						advicesRes.add(advice);
				}
			}
		}
		return advicesRes;
	}
	
	/**
	 * Get all the advices that provide (each of them) all the functionalities specified.
	 * 
	 * @param functionalitiesID	Functionality identifiers.
	 * @return					Advices with the functionalities.
	 */
	public Set<Advice> getAdvicesWithFunctionalities(Set<String> functionalitiesID) {
		HashSet<Advice> advicesRes = new HashSet<Advice>();
		
		Advices advices = sak.getAdvices();
		if (advices != null) {
			for (AdviceCategory category : advices.getAdviceCategory()) {
				for (Advice advice : category.adviceCategory()) {
					if (adviceProvidesFunctionalities(advice, functionalitiesID))
						advicesRes.add(advice);
				}
			}
		}
		return advicesRes;
	}
	
	/**
	 * Check if the advice has the functionality specified.
	 * @param advice			Advice.
	 * @param functionalityID	Functionality identifier.
	 * @return					True if the advice has the functionality, otherwise false.
	 */
	public boolean adviceProvidesFunctionality(Advice advice, String functionalityID) {
		Functionalities functionalities = advice.getFunctionalities();
		if (functionalities == null) 
			return false;
		
		for (Functionality f : functionalities.getFunctionality()) {
			if (f.getId().equals(functionalityID))
				return true;
		}
		return false;
	}
	
	/**
	 * Check if the advice has all the functionalities specified.
	 * 
	 * @param advice			Advice.
	 * @param functionalitiesID	Functionality identifiers.
	 * @return					True if the advice has all the functionalities, otherwise false.
	 */
	public boolean adviceProvidesFunctionalities(Advice advice, Set<String> functionalitiesID) {
		for (String functionalityID : functionalitiesID) {
			if (!adviceProvidesFunctionality(advice, functionalityID))
				return false;
		}
		return true;
	}
	
	/**
	 * Get all the advices that provide (between them) all the functionalities specified.
	 * @param functionalitiesID	Functionality identifiers.
	 * @return					Advices that fulfill the functionalities.
	 */
	public Set<Set<Advice>> getAdvicesFulfillFunctionalities(Set<String> functionalitiesID) {
		List<Set<Advice>> advicesFunctionalities = new ArrayList<Set<Advice>>();
	
		for (String functionalityID : functionalitiesID) {
			Set<Advice> advices = getAdvicesWithFunctionality(functionalityID);
			advicesFunctionalities.add(advices);
		}
		Set<Set<Advice>> res = SetOperations.cartesianProduct(advicesFunctionalities);
		return res;
	}
	
	/**
	 * Get the advisors that has the specified pointcut and advice references.
	 * @param pointcutID	Pointcut identifier.
	 * @param adviceID		Advice identifier.
	 * @return				Advisors that has the pointcut and the advice references.
	 */
	public Set<Advisor> getAdvisors(String pointcutID, String adviceID) {
		HashSet<Advisor> advisorsRes = new HashSet<Advisor>();
		
		Advisors advisors = sak.getAdvisors();
		if (advisors != null) {
			for (AdvisorCategory category : advisors.getAdvisorCategory()) {
				for (Advisor a : category.getAdvisor()) {
					if (a.getPointcutRef().equals(pointcutID) &&
						a.getAdviceRef().equals(adviceID)) {
						advisorsRes.add(a);
					}
						
				}
			}
		}
		return advisorsRes;
	}
	
	/**
	 * Get all the available advices.
	 * @return	Available advices.
	 */
	public Set<Advice> getAllAdvices() {
		HashSet<Advice> res = new HashSet<Advice>();
	
		Advices advices = sak.getAdvices();
		if (advices != null) {
			for (AdviceCategory category : advices.getAdviceCategory()) {
				res.addAll(category.adviceCategory());
			}
		}
		return res;
	}
	
	/**
	 * Get all the available pointcuts.
	 * @return	Available pointcuts.
	 */
	public Set<Pointcut> getAllPointcuts() {
		HashSet<Pointcut> res = new HashSet<Pointcut>();
	
		Pointcuts pointcuts = sak.getPointcuts();
		if (pointcuts != null) {
			res.addAll(pointcuts.getPointcut());
		}
		return res;
	}
	
	/**
	 * Get all the available advisors.
	 * @return	Available advisors.
	 */
	public Set<Advisor> getAllAdvisors() {
		HashSet<Advisor> res = new HashSet<Advisor>();
	
		Advisors advisors = sak.getAdvisors();
		if (advisors != null) {
			for (AdvisorCategory category : advisors.getAdvisorCategory()) {
				res.addAll(category.getAdvisor());
			}
		}
		return res;
	}
}
