package intertrust.modules.ag;

import java.io.File;

/**
 * This input interface is used by the rest of modules in the INTER-TRUST
 * framework architecture to provide/update the application dependent knowledge.
 * 
 * @author UMA
 * @date   12/09/2013
 *
 */
public interface KnowledgeProvisionInt {

	/**
	 * 
	 * @param sds Security Deployment Specification file.
	 */
	//public void updateSecurityDeploymentSpecification(File sds);
	

	/**
	 * 
	 * @param sds	Security Deployment Specification.
	 * @param laf	List of aspect and their functionalities.
	 */
	public void provideKnowledge(File sds, File laf);
	
	//public File getMappingAspectFunctionality();
	
	//public File getMappingSecurityConceptAspect();
	
	
	// Los siguientes metodos son de otra interfaz que debe implementar el componente SecurityAspectualKnowledge
	
	//public void updateMappingAspectsFunctionality();
	
	//public void updateMappingTestsAspects();
		
	//public void updateAspectualSecurityPatterns();
	
}
