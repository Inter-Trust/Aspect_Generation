package uma.caosd.AspectualKnowledge.DynamicAspects;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import uma.caosd.AspectualKnowledge.AdvisorConfiguration;

/**
 * Repository of advisors that are independent from the users.
 * 
 * @author UMA
 *
 */
public class AdvisorsRepository {
	/**
	 * Advisors ID -> configuration
	 */
	private Map<String, AdvisorConfiguration> advisors;
	
	public AdvisorsRepository() {
		advisors = new HashMap<String, AdvisorConfiguration>();
	}
	
	public void updateRepository(AdvisorsRepository repository) {
		advisors = repository.advisors;
	}
	
	/**
	 * Add a new advisor.
	 * @param advisorID		Identifier of the advisor.
	 * @param configuration	Advisor's configuration.
	 */
	public void addAdvisor(String advisorID, AdvisorConfiguration configuration) {
		advisors.put(advisorID, configuration);
	}
	
	/**
	 * Remove a advisor.
	 * @param advisorID	Identifier of the advisor.
	 */
	public void removeAdvisor(String advisorID) {
		advisors.remove(advisorID);
	}
	
	/**
	 * Get all the advisors.
	 * @return			Advisors.
	 */
	public Set<String> getAdvisors() {
		return advisors.keySet();
	}
	
	/**
	 * Get the configuration of the specified advisor.
	 * @param advisorID	Identifier of the advisor.
	 * @return			Advisor's configuration.
	 */
	public AdvisorConfiguration getConfiguration(String advisorID) {
		return advisors.get(advisorID);
	}
	
	/**
	 * Check if an advisor exists.
	 * @param advisorID	Identifier of the advisor.
	 * @return			True if the advisor exists, false in other case.
	 */
	public boolean containsAdvisor(String advisorID) {
		return advisors.containsKey(advisorID);
	}
}
