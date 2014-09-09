package uma.caosd.AspectGenerationModule.interfaces;

import uma.caosd.AspectualKnowledge.AspectualKnowledge;

/**
 * The knowledge required by the Aspect Generation module 
 * is provided when the framework is first instantiated using this interface. 
 * The knowledge can be updated later with the same interface at runtime. 
 * New aspects (pointcuts and/or advices) can also be incorporated to the Aspect Repository at runtime using this interface.
 * 
 * @author UMA
 * @date   12/09/2013
 *
 */
public interface KnowledgeProvision {

	/**
	 * Provides the initial aspectual knowledge.
	 * 
	 * @param sak	Security Aspectual Knowledge file.
	 */
	//public void provideAspectualKnowledge(File sak);
	
	/**
	 * Updates the aspectual knowledge.
	 *
	 * @param sak	Security Aspectual Knowledge file.
	 */
	public void updateAspectualKnowledge(AspectualKnowledge sak);
/*
	public List<DynamicAspect> listAspects();
	
	public void addNewAspect(DynamicAspect aspect);

	public void removeAspect(DynamicAspect aspect);
	*/
	//...
}
