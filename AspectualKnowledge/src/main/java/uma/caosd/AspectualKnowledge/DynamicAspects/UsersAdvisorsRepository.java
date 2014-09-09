package uma.caosd.AspectualKnowledge.DynamicAspects;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import uma.caosd.AspectualKnowledge.AdvisorConfiguration;

/**
 * Repository of advisors (and its configurations) for each user.
 * 
 * @author UMA
 *
 */
public class UsersAdvisorsRepository {
	/**
	 * User ID -> Set of Advisors ID with its configurations
	 */
	private Map<String, Map<String, AdvisorConfiguration>> usersAdvisors;
	
	public UsersAdvisorsRepository() {
		usersAdvisors = new HashMap<String, Map<String, AdvisorConfiguration>>();
	}
	
	public void updateRepository(UsersAdvisorsRepository repository) {
		usersAdvisors = repository.usersAdvisors;
	}
	
	/**
	 * Add a new user.
	 * @param userID	Identifier of the user.
	 */
	public void addUser(String userID) {
		usersAdvisors.put(userID, new HashMap<String, AdvisorConfiguration>());
	}
	
	/**
	 * Remove a user.
	 * @param userID	Identifier of the user.
	 */
	public void removeUser(String userID) {
		usersAdvisors.remove(userID);
	}
	
	/**
	 * Add a new advisor for the user specified.
	 * @param userID		Identifier of the user.
	 * @param advisorID		Identifier of the advisor.
	 * @param configuration	Advisor's configuration.
	 */
	public void addAdvisorToUser(String userID, String advisorID, AdvisorConfiguration configuration) {
		Map<String, AdvisorConfiguration> advisors = usersAdvisors.get(userID);
		if (advisors == null) {
			addUser(userID);
			advisors = usersAdvisors.get(userID);
		}
		advisors.put(advisorID, configuration);
	}
	
	/**
	 * Delete an advisor for the user specified.
	 * @param userID		Identifier of the user.
	 * @param advisorID		Identifier of the advisor.
	 */
	public void removeAdvisorToUser(String userID, String advisorID) {
		Map<String, AdvisorConfiguration> advisors = usersAdvisors.get(userID);
		if (advisors != null) {
			advisors.remove(advisorID);
			if (advisors.isEmpty()) {
				removeUser(userID);
			}
		}
	}
	
	/**
	 * Get all the advisors for the user specified.
	 * @param userID	Identifier of the user.
	 * @return			Advisor of the users.
	 */
	public Map<String, AdvisorConfiguration> getAdvisorsOfUser(String userID) {
		return usersAdvisors.get(userID);
	}
	
	/**
	 * Get all the users.
	 * @return	Users.
	 */
	public Set<String> getUsers() {
		return usersAdvisors.keySet();
	}
	
	/**
	 * Check if the user specified contains the advisor specified.
	 * @param userID	Identifier of the user.
	 * @param advisorID	Identifier of the advisor.
	 * @return			True if the user has the advisor, false in other case.
	 */
	public boolean containsAdvisorOfUser(String userID, String advisorID) {
		Map<String, AdvisorConfiguration> advisors = usersAdvisors.get(userID);
		return advisors != null && advisors.containsKey(advisorID);
	}
	
	/**
	 * Get the configuration of the specified advisor for the specified user.
	 * @param userID	Identifier of the user.
	 * @param advisorID	Identifier of the advisor.
	 * @return			Advisor's configuration.
	 */
	public AdvisorConfiguration getConfiguration(String userID, String advisorID) {
		Map<String, AdvisorConfiguration> advisors = usersAdvisors.get(userID);
		if (advisors != null)
			return advisors.get(advisorID);
		return null;
	}
}
