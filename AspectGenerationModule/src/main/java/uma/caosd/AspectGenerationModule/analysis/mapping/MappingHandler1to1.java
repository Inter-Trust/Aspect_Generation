package uma.caosd.AspectGenerationModule.analysis.mapping;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import uma.caosd.AspectGenerationModule.SAKAnalysis;
import uma.caosd.AspectGenerationModule.analysis.SDSAnalysis;
import uma.caosd.AspectGenerationModule.exceptions.AnalysisException;
import uma.caosd.AspectGenerationModule.exceptions.AspectualKnowledgeException;
import uma.caosd.AspectGenerationModule.exceptions.MappingException;
import uma.caosd.AspectualKnowledge.Advice;
import uma.caosd.AspectualKnowledge.Advisor;
import uma.caosd.AspectualKnowledge.AdvisorConfiguration;
import uma.caosd.AspectualKnowledge.ConfigurationParameters;
import uma.caosd.AspectualKnowledge.Parameter;
import uma.caosd.SecurityDeploymentSpecification.SecurityParameter;
import uma.caosd.SecurityDeploymentSpecification.SecurityParameters;
import uma.caosd.errorHandling.DeploymentStatusSingleton;
import uma.caosd.errors.Module;
import uma.caosd.errors.Type;

/**
 * Mapping between the security concepts of a Security Deployment Specification (SDS) and
 * the Aspectual Knowledge.
 * 
 * @author UMA
 * @date 22/04/2014
 *
 */
public class MappingHandler1to1 implements MappingSecurityConceptsAspects {
	private Map<String, Set<Advisor>> mapping;	// Security concepts ID -> advisors
	private SDSAnalysis sdsAnalysis;
	private SAKAnalysis sak;
	
	public MappingHandler1to1(SDSAnalysis sdsAnalysis, SAKAnalysis sak) throws MappingException {
		this.sdsAnalysis = sdsAnalysis;
		this.sak = sak;
		mapping = new HashMap<String, Set<Advisor>>();
		updateMapping(sdsAnalysis, sak);
	}
	
	public void updateMapping(SDSAnalysis sdsAnalysis, SAKAnalysis sak) throws MappingException {
		this.sdsAnalysis = sdsAnalysis;
		try {
			Set<String> securityConcepts = sdsAnalysis.getSecurityConceptToBeDeployed();
			for (String scID : securityConcepts) {
				Set<String> functionalities = sdsAnalysis.getFunctionalitiesID(scID);
				String target = sdsAnalysis.getTargetID(scID);
				Set<Advice> advices = sak.getAdvicesWithFunctionalities(functionalities);
				Set<Advisor> advisorsForSC = new HashSet<Advisor>();
				for (Advice advice : advices) {
					Set<Advisor> advisors = sak.getAdvisors(target, advice.getId());
					advisorsForSC.addAll(advisors);
				}
				mapping.put(scID, advisorsForSC);
			}	
		} catch (AnalysisException e) {
			String desc = "Error generating mapping: " + e.getMessage();
			DeploymentStatusSingleton.getStatus().addError(desc, Module.ASPECT_GENERATION, Type.MAPPING);
			throw new MappingException(desc);
		}
		
	}

	public Set<Advisor> getAdvisors(String securityConceptID) throws MappingException {
		if (!mapping.containsKey(securityConceptID)) {
			String desc = "There is not security concept with ID '" + securityConceptID + "'.";
			DeploymentStatusSingleton.getStatus().addError(desc, Module.ASPECT_GENERATION, Type.MAPPING);
			throw new MappingException(desc);
		}
		return mapping.get(securityConceptID);
	}

	// Be careful!! Description is an object in SDS and a String in the Configuration.
	public AdvisorConfiguration getSecurityConceptConfiguration(String securityConceptID) throws MappingException {
		AdvisorConfiguration config = null;
		try {
			uma.caosd.SecurityDeploymentSpecification.Configuration configSDS = sdsAnalysis.getSecurityConceptConfiguration(securityConceptID);
			if (configSDS != null) {
				//System.out.println("configSDS: " + configSDS);
				config = new AdvisorConfiguration();
				// Be careful!! Description is an object in SDS and a String in the Configuration.
				if (configSDS.getSecurityDescription() != null) {
					config.setDescription(configSDS.getSecurityDescription().toString());	
				}
	
				SecurityParameters paramSDS = configSDS.getSecurityParameters();
				if (paramSDS != null) {
					ConfigurationParameters parameters = new ConfigurationParameters();
					for (SecurityParameter p : paramSDS.getParameter()) {
						Parameter param = new Parameter();
						param.setName(p.getName());
						param.setValue(p.getValue());
						parameters.getParameter().add(param);
					}
					config.setConfigurationParameters(parameters);
				}
			}
		} catch (AnalysisException e) {
			config = null;
			throw new MappingException("There is not security concept with ID '" + securityConceptID + "'.");
		}
		return config;
	}

	/*public Configuration getAdvisorConfiguration(String advisorID) throws MappingException {
		for (String sc : mapping.keySet())
			for (Advisor a : mapping.get(sc))
				if (a.getId().equals(advisorID))
					return getSecurityConceptConfiguration(sc);
		throw new MappingException("There is not advisor with ID '" + advisorID + "'.");
	}*/

	public Set<String> getSecurityConcepts() {
		return mapping.keySet();
	}

	public Set<String> getSecurityConceptsWithoutAdvisors() {
		HashSet<String> res = new HashSet<String>();
		for (String s : mapping.keySet()) {
			if (mapping.get(s).isEmpty()) {
				res.add(s);
			}
		}
		return res;
	}
}
