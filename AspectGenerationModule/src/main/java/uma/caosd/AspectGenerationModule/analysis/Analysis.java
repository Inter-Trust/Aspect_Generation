package uma.caosd.AspectGenerationModule.analysis;

import java.io.File;
import java.util.Set;

import uma.caosd.AspectGenerationModule.SAKAnalysis;
import uma.caosd.AspectGenerationModule.analysis.mapping.MappingHandler1to1;
import uma.caosd.AspectGenerationModule.analysis.mapping.MappingSecurityConceptsAspects;
import uma.caosd.AspectGenerationModule.analysis.selectionAlgorithm.AspectsSelectionAlgorithm;
import uma.caosd.AspectGenerationModule.analysis.selectionAlgorithm.SelectionAlgorithm1;
import uma.caosd.AspectGenerationModule.exceptions.AspectsSelectionException;
import uma.caosd.AspectGenerationModule.exceptions.MappingException;
import uma.caosd.AspectGenerationModule.utils.XMLViewer;
import uma.caosd.AspectualKnowledge.Configuration;
import uma.caosd.SecurityDeploymentSpecification.Sds;
import uma.caosd.amqp.utils.XMLUtils;

/**
 * This component analyses the notified changes and the security aspectual knowledge
 * and determines whether the security aspects, currently instantiated in the application, 
 * fulfil the new security policy or some changes must be done in the current aspects.

 * @author UMA
 * @date 12/09/2013
 *
 */
public class Analysis {
	private MappingSecurityConceptsAspects mapping;
	private SAKAnalysis sak;
	private Configuration securityConfigurationToBeDeployed;
	private Configuration securityConfigurationToBeUndeployed;
	
	public Analysis(SAKAnalysis sak) {
		this.sak = sak;
	}
	
	/**
	 * Generate the security configuration to be deployed and the security configuration to be undeployed from the SDS file. 
	 * @param sds	Security Deployment Specification file.
	 * @throws MappingException 
	 * @throws AspectsSelectionException 
	 */
	public void generateNewSecurityConfiguration(Sds sds) throws AspectsSelectionException, MappingException {
		SDSAnalysis sdsAnalysis = new SDSAnalysis(sds);
		if (mapping == null) {
			mapping = new MappingHandler1to1(sdsAnalysis, sak);
		} else {
			mapping.updateMapping(sdsAnalysis, sak);
		}
		AspectsSelectionAlgorithm selectionAlgorithm = new SelectionAlgorithm1();
		SecurityConfigurationAspectsGeneration configGeneration = new SecurityConfigurationAspectsGeneration(sdsAnalysis, mapping, selectionAlgorithm);
		securityConfigurationToBeDeployed = configGeneration.generateSecurityConfigurationToBeDeployed();
		securityConfigurationToBeUndeployed = configGeneration.generateSecurityConfigurationToBeUndeployed();
		
		/** PRUEBAS **/
		/*File f = XMLUtils.writeTemp("securityConfigurationToBeDeployed", securityConfigurationToBeDeployed, Configuration.class);
		new XMLViewer(f);*/
		/*
		File f2 = XMLFile.writeTemp("securityConfigurationToBeUndeployed", securityConfigurationToBeUndeployed, Configuration.class);
		new XMLViewer(f2);*/
	}

	public Configuration getSecurityConfigurationToBeDeployed() {
		return securityConfigurationToBeDeployed;
	}

	public Configuration getSecurityConfigurationToBeUndeployed() {
		return securityConfigurationToBeUndeployed;
	}
	
	public Set<String> getSecurityConceptsWithoutAdvisors() {
		return mapping.getSecurityConceptsWithoutAdvisors();
	}
/*
	private SecurityConfigurationAspects convertSDStoSCA(File sds) {
		return null;
	}
	
	public SecurityConfigurationAspects configurationDifference(SecurityConfigurationAspects currentSCA, SecurityConfigurationAspects newSCA) {
		return null;
	}
	
	
	private void updateCurrentConfiguration(SecurityConfigurationAspects newSCA) {
		currentSCA = newSCA;	
	}*/
}
