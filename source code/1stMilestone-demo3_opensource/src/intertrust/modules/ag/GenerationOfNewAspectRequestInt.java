package intertrust.modules.ag;

import intertrust.modules.ag.generic.SAKClasses.SecurityConcept;

import java.io.File;
import java.util.List;

/**
 * This interface is used by the rest of the modules 
 * in the INTER-TRUST framework to request the incorporation/generation
 * at runtime of a new aspect (this can be only a new pointcut or
 * both a poincut and an advice depending on the AOP weaver being
 * used in each instance of the framework) into the INTER-TRUST framework.

 * @author UMA
 * @date   17/09/2013
 *
 */
public interface GenerationOfNewAspectRequestInt {

	
	/**
	 * Incorporate new aspects at runtime.
	 * 
	 * @param sc				Security concept associated with the new aspects.
	 * @param newAspectsFile	File (.jar) with one or more new aspects.
	 * @return					Names of the new aspects.
	 */
	public List<String> incorporateNewAspect(SecurityConcept sc, File newAspectsFile);
	
	//public void addNewAspect(String filePath);
	
	//public void addNewAspect(File file);
}
