package uma.caosd.AspectGenerationModule.analysis.mapping;

import java.util.Set;

import uma.caosd.AspectGenerationModule.SAKAnalysis;
import uma.caosd.AspectGenerationModule.analysis.SDSAnalysis;
import uma.caosd.AspectGenerationModule.exceptions.MappingException;
import uma.caosd.AspectualKnowledge.Advisor;
import uma.caosd.AspectualKnowledge.AdvisorConfiguration;

/**
 * Mapping between the security concepts of a Security Deployment Specification (SDS) and
 * the Aspectual Knowledge.
 * Following the Strategy pattern.
 * 
 * @author UMA
 * @date 2/05/2014
 */
public interface MappingSecurityConceptsAspects {
	/**
	 * Get all the security concepts of the mapping.
	 * @return	Security concepts.
	 */
	public Set<String> getSecurityConcepts();
	
	/**
	 * Generate/Update the mapping between the security concepts and the advisors.
	 * @param sdsAnalysis			SDS Analysis.
	 * @param sak					SAK Analysis.
	 * @throws MappingException		Error generating the mapping.
	 */
	public void updateMapping(SDSAnalysis sdsAnalysis, SAKAnalysis sak) throws MappingException;
	
	/**
	 * Get the advisors associated with the security concept provided.
	 * @param securityConceptID		Security concept identifier.
	 * @return						Advisors associated with the security concept.
	 * @throws MappingException		There is not security concept in the mapping.
	 */
	public Set<Advisor> getAdvisors(String securityConceptID) throws MappingException;
	
	/**
	 * Get the configuration of the security concept provided.
	 * @param securityConceptID		Security concept identifier.
	 * @return						Configuration of the security concept.
	 * @throws MappingException		There is not security concept in the mapping.
	 */
	public AdvisorConfiguration getSecurityConceptConfiguration(String securityConceptID) throws MappingException;
	
	/**
	 * Get the security concepts in the mapping that have not advisors associated.
	 * @return	Security concepts identifiers.
	 */
	public Set<String> getSecurityConceptsWithoutAdvisors();
	
	/**
	 * Get the configuration of the advisor provided.
	 * @param advisorID				Advisor identifier.
	 * @return						Configuration of the advisor.
	 * @throws MappingException		There is not advisor in the mapping.
	 */
	//public Configuration getAdvisorConfiguration(String advisorID) throws MappingException;
}
