package uma.caosd.AspectGenerationModule.securityAspectualKnowledge;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import uma.caosd.AspectualKnowledge.Configuration;

/**
 * Mapping between the users/roles/contexts and the security concepts 
 * currently deployed in the application.
 * 
 * @author UMA
 * @date 2/05/2014
 */
public class SecurityConfigurations {
	private Map<String, Configuration> configurations;
	
	public SecurityConfigurations() {
		configurations = new HashMap<String, Configuration>();
	}
	
	public Configuration getConfiguration(String id) {
		return configurations.get(id);
	}
	
	public Set<String> getInstancesID() {
		return configurations.keySet();
	}
	
	public void updateConfiguration(String id, Configuration configuration) {
		configurations.put(id, configuration);
	}
	
	public void removeConfiguration(String id) {
		configurations.remove(id);
	}
}
