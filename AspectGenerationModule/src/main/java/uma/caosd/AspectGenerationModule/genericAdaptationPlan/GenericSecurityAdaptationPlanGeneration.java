package uma.caosd.AspectGenerationModule.genericAdaptationPlan;

import uma.caosd.AspectGenerationModule.securityAspectualKnowledge.SecurityConfigurations;
import uma.caosd.AspectualKnowledge.AdaptationPlan;
import uma.caosd.AspectualKnowledge.Configuration;

public class GenericSecurityAdaptationPlanGeneration {
	private SecurityConfigurationSelection configSelection;
	private SecurityConfigurationDifference configDifference;
	private AdaptationPlanGeneration adaptationPlanGeneration;
	private Configuration newSecurityConfiguration;
	
	public GenericSecurityAdaptationPlanGeneration() {
		
	}
	
	public GenericSecurityAdaptationPlanGeneration(SecurityConfigurations securityConfigurations) {
		configSelection = new SecurityConfigurationSelection(securityConfigurations);
		configDifference = new SecurityConfigurationDifference();
		adaptationPlanGeneration = new AdaptationPlanGeneration();
	}
	
	public AdaptationPlan generateSecurityAdaptationPlan(Configuration newConfigDeploy, Configuration newConfigUndeploy) {
		String instanceID = newConfigDeploy.getInstance().getId();
		Configuration currentConfig = configSelection.getCurrentSecurityConfiguration(instanceID); // id del usuario.
		if (currentConfig == null) {
			currentConfig = new Configuration();
		}
		
		configDifference.calculateConfigurationDifference(currentConfig, newConfigDeploy, newConfigUndeploy);
		
		Configuration configDeploy = configDifference.getDeployConfiguration();
		Configuration configUndeploy = configDifference.getUndeployConfiguration();
		Configuration remainConfig = configDifference.getRemainConfiguration();
		newSecurityConfiguration = configDifference.getTotalNewConfiguration(); 
		
		AdaptationPlan adaptationPlan = adaptationPlanGeneration.generateAdaptationPlan(configDeploy, configUndeploy, remainConfig);
		return adaptationPlan;
	}
	
	public Configuration getNewSecurityConfiguration() {
		return newSecurityConfiguration;
	}
}
