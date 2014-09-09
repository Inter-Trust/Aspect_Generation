package uma.caosd.AspectGenerationModule.analysis;

import java.util.HashSet;
import java.util.Set;

import uma.caosd.AspectGenerationModule.analysis.mapping.MappingSecurityConceptsAspects;
import uma.caosd.AspectGenerationModule.analysis.selectionAlgorithm.AspectsSelectionAlgorithm;
import uma.caosd.AspectGenerationModule.exceptions.AspectsSelectionException;
import uma.caosd.AspectGenerationModule.exceptions.MappingException;
import uma.caosd.AspectualKnowledge.Advisor;
import uma.caosd.AspectualKnowledge.AdvisorConfiguration;
import uma.caosd.AspectualKnowledge.Configuration;
import uma.caosd.AspectualKnowledge.Instance;

public class SecurityConfigurationAspectsGeneration {
	private MappingSecurityConceptsAspects mapping;
	private AspectsSelectionAlgorithm selectionAlgorithm;
	private SDSAnalysis sdsAnalysis;
	
	public SecurityConfigurationAspectsGeneration(SDSAnalysis sdsAnalysis, MappingSecurityConceptsAspects mapping, AspectsSelectionAlgorithm selectionAlgorithm) {
		this.sdsAnalysis = sdsAnalysis;
		this.mapping = mapping;
		this.selectionAlgorithm = selectionAlgorithm;
	}
	
	public Configuration generateSecurityConfigurationToBeDeployed() throws AspectsSelectionException, MappingException {
		Configuration sca = new Configuration();
		Set<Advisor> advisors = getAdvisorsFromMapping(sdsAnalysis.getSecurityConceptToBeDeployed());
		sca.getAdvisor().addAll(advisors);
		Instance instance = new Instance();
		instance.setId(sdsAnalysis.getInstanceID());
		sca.setInstance(instance);
		return sca;	
	}
	
	public Configuration generateSecurityConfigurationToBeUndeployed() throws AspectsSelectionException, MappingException {
		Configuration sca = new Configuration();
		Set<Advisor> advisors =  getAdvisorsFromMapping(sdsAnalysis.getSecurityConceptToBeUnDeployed());
		sca.getAdvisor().addAll(advisors);
		Instance instance = new Instance();
		instance.setId(sdsAnalysis.getInstanceID());
		sca.setInstance(instance);
		return sca;	
	}
	
	private Set<Advisor> getAdvisorsFromMapping(Set<String> ids) throws MappingException, AspectsSelectionException {
		Set<Advisor> resAdvisors = new HashSet<Advisor>();
		for (String scID : ids) {
			Set<Advisor> candidateAdvisors = mapping.getAdvisors(scID);
			Advisor advisor = selectionAlgorithm.selectAdvisor(candidateAdvisors);
			AdvisorConfiguration config = mapping.getSecurityConceptConfiguration(scID);
			if (config != null) {
				advisor.setConfiguration(mapping.getSecurityConceptConfiguration(scID));	
			}
			resAdvisors.add(advisor);
		}
		return resAdvisors;
	}
}
