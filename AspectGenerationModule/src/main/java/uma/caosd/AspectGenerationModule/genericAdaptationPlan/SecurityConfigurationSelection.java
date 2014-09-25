package uma.caosd.AspectGenerationModule.genericAdaptationPlan;

import uma.caosd.AspectGenerationModule.securityAspectualKnowledge.SecurityConfigurations;
import uma.caosd.AspectualKnowledge.Configuration;

public class SecurityConfigurationSelection {
	private SecurityConfigurations runtimeConfigurations;
	private Configuration runtimeConfiguration;
	
	
	public SecurityConfigurationSelection(SecurityConfigurations runtimeConfigurations) {
		this.runtimeConfigurations = runtimeConfigurations;
	}
	
	public SecurityConfigurationSelection(Configuration runtimeConfiguration) {
		this.runtimeConfiguration = runtimeConfiguration;
	}
	
	public Configuration getCurrentSecurityConfiguration(String id) {
		return runtimeConfigurations.getConfiguration(id);
	}
	
	public Configuration getCurrentSecurityConfiguration() {
		return runtimeConfiguration;
	}
}
