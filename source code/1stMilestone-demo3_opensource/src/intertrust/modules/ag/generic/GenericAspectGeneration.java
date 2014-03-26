package intertrust.modules.ag.generic;

import intertrust.modules.ag.generic.SAKClasses.AdaptationPlan;
import intertrust.modules.ag.generic.SAKClasses.ListAF;
import intertrust.modules.ag.generic.SAKClasses.SecurityConcept;
import intertrust.utils.XMLFile;

import java.io.File;
import java.util.List;

/**
 * This module is in charge of deciding which aspects need to be added/deleted 
 * to an application in order to deploy a new security policy 
 * or to adapt a previously deployed security policy. 
 * Its input are: 
 * (1) the information needed to generate the security adaptation plan 
 * (KnowledgeProvisionInt interface),
 * (2) requests to adapt the security policy or the security tests
 * (AdaptationRequestInt interface), and
 * (3) requests to add new aspects into the aspect repository 
 * (GenerationNewAspectRequestInt interface).
 * Its output is a generic security adaptation plan with the list of
 * aspects that need to be added/deleted, without including
 * any implementation details of the AOP framework being used 
 * (GenericAspectsAdaptationRequestInt interface)
 * These implementation details will be later added by the 
 * Concrete Aspect Generation sub-module in order to complete 
 * the security adaptation plan. It is composed by three internal components:
 * Security Aspectual Knowledge, Analysis and Generic Security Adaptation Plan Generation.

 * @author UMA
 * @date   12/09/2013
 *
 */
public class GenericAspectGeneration {

	public static final String AP_FILENAME = "adaptationPlan";
	//public static final String AP_FILENAME = ".\\temp\\adaptationPlan.xml";
	//public static final String AP_FILENAME = "." + File.separator + "temp" + File.separator + "adaptationPlan.xml";
	
	private SecurityAspectualKnowledge securityAspectualKnowledge;
	private Analysis analysis;
	private GenericSecurityAdaptationPlanGeneration gsap;
	
	private AdaptationPlan currentAP;	// Current adaptation plan in application.
	private AdaptationPlan newAP;		// New proposed adaptation plan for application.
	private AdaptationPlan actionAP;	// Action plan for application (difference between currentAP and newAP).
	private File adaptationPlanFile;
	
	public GenericAspectGeneration(File initialSdsFile, File listAFFile) {
		securityAspectualKnowledge = new SecurityAspectualKnowledge(initialSdsFile, listAFFile);
		analysis = new Analysis();
		gsap = new GenericSecurityAdaptationPlanGeneration();
		currentAP = new AdaptationPlan();
		//currentAP = analysis.generateAdaptationPlan(securityAspectualKnowledge.getSecurityDeploymentSpecification(), securityAspectualKnowledge.getMappingSecurityConceptAspect());
		//adaptationPlanFile = XMLFile.write(AP_FILENAME, currentAP, AdaptationPlan.class);
	}
	
	public void updateSecurityDeploymentSpecification(File sds) {
		securityAspectualKnowledge.updateSecurityDeploymentSpecification(sds);
	}
	
	public void provideKnowledge(File sds, File laf) {
		securityAspectualKnowledge.provideKnowledge(sds, laf);
	}
	
	public File getListAspectsFunctionalitiesFile() {
		return securityAspectualKnowledge.getListAspectFunctionalityFile();
	}

	public File getMappingSecurityConceptAspectFile() {
		return securityAspectualKnowledge.getMappingSecurityConceptAspectFile();
	}

	// only for GUI
	public GenericSecurityAdaptationPlanGeneration getGenericSecurityAdaptationPlanGeneration() {
		return gsap;
	}
	
	/**
	 * Generate a new adaptation plan specified in the SDS file (newAP).
	 * Generate the adaptation plan to be performed in the application (actionAP).
	 * Update the current adaptation plan deployed in the application (currentAP).
	 */
	public void generateSecurityAdaptationPlan() {
		newAP = analysis.generateAdaptationPlan(securityAspectualKnowledge.getSecurityDeploymentSpecification(), securityAspectualKnowledge.getMappingSecurityConceptAspect());
		actionAP = gsap.generateActionSecurityAdaptationPlan(currentAP, newAP);
		currentAP = gsap.generateCurrentSecurityAdaptationPlan(currentAP, actionAP);
		adaptationPlanFile = XMLFile.writeTemp(AP_FILENAME, actionAP, AdaptationPlan.class);
	}
	
	public File getSecurityAdaptationPlanFile() {
		return adaptationPlanFile;
	}

	public AdaptationPlan getSecurityAdaptationPlan() {
		return actionAP;
	}
	
	public ListAF getMappingAspectsFunctionality() {
		return securityAspectualKnowledge.getListAspectFunctionality();
	}
	
	public List<SecurityConcept> getNewAspectsRequired() {
		return securityAspectualKnowledge.getNewAspectsRequired();
	}

	public void updateListAspectFunctionality(SecurityConcept sc, String Aspectname) {
		securityAspectualKnowledge.updateListAspectFunctionality(sc, Aspectname);
		
	}
}
