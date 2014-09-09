package uma.caosd.AspectGenerationModule.genericAdaptationPlan;

import uma.caosd.AspectGenerationModule.securityAspectualKnowledge.SecurityConfigurations;
import uma.caosd.AspectualKnowledge.Configuration;

public class SecurityConfigurationSelection {
	private SecurityConfigurations runtimeConfigurations;
	
	public SecurityConfigurationSelection(SecurityConfigurations runtimeConfigurations) {
		this.runtimeConfigurations = runtimeConfigurations;
	}
	
	public Configuration getCurrentSecurityConfiguration(String id) {
		return runtimeConfigurations.getConfiguration(id);
	}
}
