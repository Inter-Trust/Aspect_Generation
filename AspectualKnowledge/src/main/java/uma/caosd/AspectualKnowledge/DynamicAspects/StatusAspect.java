package uma.caosd.AspectualKnowledge.DynamicAspects;



/**
 * Provides the status of the aspects in the repository of users and advisors.
 * It can be updated by the Aspect Weaver module in a synchronized way using a the AMQP protocol.
 * 
 * @author UMA
 *
 */
public class StatusAspect {
	private static DynamicRepository dynamicRepository = new DynamicRepository();
	
	/**
	 * Updates the repository of users and advisors.
	 * @param newDynamicRepository
	 */
	/*public final static synchronized void updateRepository(DynamicRepository newDynamicRepository) {
		dynamicRepository = newDynamicRepository;
	}*/
	
	// Actualización para SYCTL: se actualiza la tabla pero no se sobreescribe entera.
	public final static synchronized void updateRepository(DynamicRepository newDynamicRepository) {
		AdvisorsRepository aR = newDynamicRepository.getAdvisorRepository();
		UsersAdvisorsRepository uaR = newDynamicRepository.getUserAdvisorsRepository();
		
		// add advisors.
		for (String s : aR.getAdvisors()) {
			dynamicRepository.getAdvisorRepository().addAdvisor(s, aR.getConfiguration(s));
		}
		// add advisors of users.
		for (String s : uaR.getUsers()) {
			for (String a : uaR.getAdvisorsOfUser(s).keySet()) {
				dynamicRepository.getUserAdvisorsRepository().addAdvisorToUser(s, a, uaR.getConfiguration(s, a));	
			}
		}
		
		AdvisorsRepository arDISABLED = newDynamicRepository.getAdvisorRepositoryDISABLED();
		UsersAdvisorsRepository uarDISABLED = newDynamicRepository.getUserAdvisorsRepositoryDISABLED();
		// remove advisors.
		for (String s : arDISABLED.getAdvisors()) {
			dynamicRepository.getAdvisorRepository().removeAdvisor(s);
		}
		// remove advisors of users.
		for (String s : uarDISABLED.getUsers()) {
			for (String a : uarDISABLED.getAdvisorsOfUser(s).keySet()) {
				dynamicRepository.getUserAdvisorsRepository().removeAdvisorToUser(s, a);	
			}
		}
		//dynamicRepository = newDynamicRepository;
	}
	
	/**
	 * Checks if an advisor is enabled for an user.
	 * @param advisorID
	 * @param userID
	 * @return			True if the advisor is enabled for the user, false if it is disabled.
	 */
	public final static synchronized boolean isEnabled(String advisorID, String userID) {
		return dynamicRepository.getUserAdvisorsRepository().containsAdvisorOfUser(userID, advisorID);
	}
	
	/**
	 * Gets the configuration of the advisor for an user.
	 * @param advisorID
	 * @param userID
	 * @return			The configuration of the advisor for the user.
	 */
	public final static synchronized Object getConfiguration(String advisorID, String userID) {
		return dynamicRepository.getUserAdvisorsRepository().getConfiguration(userID, advisorID);
	}
	
	/**
	 * Checks if an advisor is enabled (independently from the user).
	 * @param advisorID
	 * @return			True if the advisor is enabled, false if it is disabled.
	 */
	public final static synchronized boolean isEnabled(String advisorID) {
		//System.out.println("isEnabled: " + advisorID + ": " + dynamicRepository.getAdvisorRepository().containsAdvisor(advisorID));
		return dynamicRepository.getAdvisorRepository().containsAdvisor(advisorID);
	}
	
	/**
	 * Gets the configuration of the advisor (independently from the user).
	 * @param advisorID
	 * @return			The configuration of the advisor.
	 */
	public final static synchronized Object getConfiguration(String advisorID) {
		return dynamicRepository.getAdvisorRepository().getConfiguration(advisorID);
	}
}
