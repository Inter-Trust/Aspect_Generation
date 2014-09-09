package uma.caosd.AspectualKnowledge.DynamicAspects;

import java.util.Set;

import uma.caosd.AspectualKnowledge.Advisor;
import uma.caosd.AspectualKnowledge.AdvisorConfiguration;

public abstract class DynamicWeaver {
	private DynamicRepository dynamicRepository;
	
	public DynamicWeaver(DynamicRepository dynamicRepository) {
		this.dynamicRepository = dynamicRepository;
	}
	
	public void weave(DynamicAspect aspect) {
		Advisor advisor = aspect.getAdvisor(); 
		dynamicRepository.getAdvisorRepository().addAdvisor(advisor.getId(), advisor.getConfiguration());
		dynamicRepository.getAdvisorRepositoryDISABLED().removeAdvisor(advisor.getId());
	}
	
	public void unweave(DynamicAspect aspect) {
		Advisor advisor = aspect.getAdvisor();
		dynamicRepository.getAdvisorRepository().removeAdvisor(advisor.getId());
		dynamicRepository.getAdvisorRepositoryDISABLED().addAdvisor(advisor.getId(), null);
	}
	
	public void configure(DynamicAspect aspect) {
		Advisor advisor = aspect.getAdvisor();
		dynamicRepository.getAdvisorRepository().addAdvisor(advisor.getId(), advisor.getConfiguration());
		dynamicRepository.getAdvisorRepositoryDISABLED().removeAdvisor(advisor.getId());
	}
	
	public void weaveForAllUsers(DynamicAspect aspect) {
		Advisor advisor = aspect.getAdvisor();
		String advisorID = advisor.getId();
		AdvisorConfiguration configuration = advisor.getConfiguration();
		Set<String> users = dynamicRepository.getUserAdvisorsRepository().getUsers();
		for (String u : users) {
			dynamicRepository.getUserAdvisorsRepository().addAdvisorToUser(u, advisorID, configuration);
			dynamicRepository.getUserAdvisorsRepositoryDISABLED().removeAdvisorToUser(u,  advisorID);
		}
	}
	
	public void unweaveForAllUsers(DynamicAspect aspect) {
		String advisor = aspect.getAdvisor().getId();
		Set<String> users = dynamicRepository.getUserAdvisorsRepository().getUsers();
		for (String u : users) {
			dynamicRepository.getUserAdvisorsRepository().removeAdvisorToUser(u, advisor);
			dynamicRepository.getUserAdvisorsRepositoryDISABLED().addAdvisorToUser(u,  advisor, null);
		}
	}
	
	public void configureForAllUsers(DynamicAspect aspect) {
		Advisor advisor = aspect.getAdvisor();
		String advisorID = advisor.getId();
		AdvisorConfiguration configuration = advisor.getConfiguration();
		Set<String> users = dynamicRepository.getUserAdvisorsRepository().getUsers();
		for (String u : users) {
			dynamicRepository.getUserAdvisorsRepository().addAdvisorToUser(u, advisorID, configuration);
			dynamicRepository.getUserAdvisorsRepositoryDISABLED().removeAdvisorToUser(u,  advisorID);
		}
	}
	
	public void weaveForUser(String userID, DynamicAspect aspect) {
		Advisor advisor = aspect.getAdvisor();
		dynamicRepository.getUserAdvisorsRepository().addAdvisorToUser(userID, advisor.getId(), advisor.getConfiguration());
		dynamicRepository.getUserAdvisorsRepositoryDISABLED().removeAdvisorToUser(userID,  advisor.getId());
	}
	
	public void unweaveForUser(String userID, DynamicAspect aspect) {
		Advisor advisor = aspect.getAdvisor();
		dynamicRepository.getUserAdvisorsRepository().removeAdvisorToUser(userID, advisor.getId());
		dynamicRepository.getUserAdvisorsRepositoryDISABLED().addAdvisorToUser(userID,  advisor.getId(), null);
	}
	
	public void configureForUser(String userID, DynamicAspect aspect) {
		Advisor advisor = aspect.getAdvisor();
		dynamicRepository.getUserAdvisorsRepository().addAdvisorToUser(userID, advisor.getId(), advisor.getConfiguration());
		dynamicRepository.getUserAdvisorsRepositoryDISABLED().removeAdvisorToUser(userID,  advisor.getId());
	}
}
