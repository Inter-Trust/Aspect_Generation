package intertrust.modules.ag.generic;

import intertrust.modules.ag.generic.SAKClasses.ListAF;
import intertrust.modules.ag.generic.SAKClasses.MappingSCA;
import intertrust.modules.ag.generic.SAKClasses.Sds;
import intertrust.modules.ag.generic.SAKClasses.SecurityConcept;

import java.io.File;
import java.util.List;


/**
 * This component represents all the information that the Aspect Generation module 
 * needs in order to adapt the applications to changes on the security policies.
 * 
 * @author UMA
 * @date   11/09/2013
 * 
 */
public class SecurityAspectualKnowledge implements SecurityAspectualKnowledgeInt {

	private MappingPolicyAspects mappingPolicyAspects;
	private MappingTestsAspects mappingTestsAspects;
	private AspectualSecurityPatterns aspectualSecurityPatterns;
	
	public SecurityAspectualKnowledge(File initialSdsFile, File listAFFile) {
		mappingPolicyAspects = new MappingPolicyAspects(initialSdsFile, listAFFile);
		mappingTestsAspects = new MappingTestsAspects();
		aspectualSecurityPatterns = new AspectualSecurityPatterns();
	}

	@Override
	public Sds getSecurityDeploymentSpecification() {
		return mappingPolicyAspects.getSecurityDeploymentSpecification();
	}

	public void provideKnowledge(File sdsFile, File lafFile) {
		mappingPolicyAspects.provideKnowledge(sdsFile, lafFile);
	}
	
	@Override
	public ListAF getListAspectFunctionality() {
		return mappingPolicyAspects.getListAspectFunctionality();
	}
	
	@Override
	public MappingSCA getMappingSecurityConceptAspect() {
		return mappingPolicyAspects.getMappingSecurityConceptAspect();
	}

	@Override
	public File getSecurityDeploymentSpecificationFile() {
		return mappingPolicyAspects.getSecurityDeploymentSpecificationFile();
	}

	@Override
	public File getListAspectFunctionalityFile() {
		return mappingPolicyAspects.getListAspectFunctionalityFile();
	}

	@Override
	public File getMappingSecurityConceptAspectFile() {
		return mappingPolicyAspects.getMappingSecurityConceptAspectFile();
	}

	public void updateSecurityDeploymentSpecification(File sds) {
		mappingPolicyAspects.updateSecurityDeploymentSpecification(sds);
	}

	public void updateListAspectFunctionality(SecurityConcept sc, String aspectName) {
		mappingPolicyAspects.updateKnowledge(sc, aspectName);
	}

	public List<SecurityConcept> getNewAspectsRequired() {
		return mappingPolicyAspects.getNewAspectsRequired();
	}

}
