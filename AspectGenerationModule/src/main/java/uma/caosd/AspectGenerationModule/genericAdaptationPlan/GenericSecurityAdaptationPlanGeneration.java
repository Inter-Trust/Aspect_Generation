package uma.caosd.AspectGenerationModule.genericAdaptationPlan;

import java.io.File;

import uma.caosd.AspectGenerationModule.securityAspectualKnowledge.SecurityConfigurations;
import uma.caosd.AspectGenerationModule.utils.XMLViewer;
import uma.caosd.AspectualKnowledge.AdaptationPlan;
import uma.caosd.AspectualKnowledge.Configuration;
import uma.caosd.amqp.utils.XMLUtils;

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
	
	public GenericSecurityAdaptationPlanGeneration(Configuration securityConfiguration) {
		configSelection = new SecurityConfigurationSelection(securityConfiguration);
		configDifference = new SecurityConfigurationDifference();
		adaptationPlanGeneration = new AdaptationPlanGeneration();
	}
	
	public AdaptationPlan generateSecurityAdaptationPlan(Configuration currentConfig, Configuration newConfigDeploy, Configuration newConfigUndeploy) {
		String instanceID = newConfigDeploy.getInstance().getId();
		//Configuration currentConfig = configSelection.getCurrentSecurityConfiguration(instanceID); // id del usuario.
		//Configuration currentConfig = configSelection.getCurrentSecurityConfiguration();
		//System.out.println("currentConfig: " + currentConfig);
		if (currentConfig == null) {
			currentConfig = new Configuration();
		}
		
		/*File fcc = XMLUtils.writeTemp("currentConfiguration-calculateDifferent", currentConfig, Configuration.class);
		new XMLViewer(fcc);*/
		
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
