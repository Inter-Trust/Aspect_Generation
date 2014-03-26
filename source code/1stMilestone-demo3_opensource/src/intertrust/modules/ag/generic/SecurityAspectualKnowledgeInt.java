package intertrust.modules.ag.generic;

import intertrust.modules.ag.generic.SAKClasses.ListAF;
import intertrust.modules.ag.generic.SAKClasses.MappingSCA;
import intertrust.modules.ag.generic.SAKClasses.Sds;

import java.io.File;

/**
 * 
 * @author UMA
 * @date   11/09/2013
 *
 */
public interface SecurityAspectualKnowledgeInt {

	public Sds getSecurityDeploymentSpecification();
	
	public ListAF getListAspectFunctionality();
	
	public MappingSCA getMappingSecurityConceptAspect();
	
	public File getSecurityDeploymentSpecificationFile();
	
	public File getListAspectFunctionalityFile();
	
	public File getMappingSecurityConceptAspectFile();
	
}
