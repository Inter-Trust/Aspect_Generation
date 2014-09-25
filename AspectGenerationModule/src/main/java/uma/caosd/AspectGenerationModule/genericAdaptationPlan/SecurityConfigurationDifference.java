package uma.caosd.AspectGenerationModule.genericAdaptationPlan;

import java.io.File;

import uma.caosd.AspectGenerationModule.utils.XMLViewer;
import uma.caosd.AspectualKnowledge.Configuration;
import uma.caosd.amqp.utils.XMLUtils;

/**
 * Calculates the security configuration difference between the 
 * current configuration deployed and the new configuration required for a user.
 * The resulting configuration need to be deployed.
 * 
 * @author UMA
 * @date 2/05/2014
 */
public class SecurityConfigurationDifference {
	private Configuration deployConfiguration;
	private Configuration undeployConfiguration;
	private Configuration remainConfiguration;
	private Configuration totalNewConfiguration;
	
	public SecurityConfigurationDifference() {
		
	}
	
	public void calculateConfigurationDifference(Configuration currentConfiguration, Configuration newConfigurationToBeDeployed, Configuration newConfigurationToBeUndeployed) {
		deployConfiguration = calculateDeployConfiguration(currentConfiguration, newConfigurationToBeDeployed);
		undeployConfiguration = calculateUndeployConfiguration(currentConfiguration, newConfigurationToBeUndeployed);
		remainConfiguration = calculateRemainConfiguration(currentConfiguration, newConfigurationToBeDeployed);
		totalNewConfiguration = calculateTotalNewConfiguration(deployConfiguration, currentConfiguration);
		/*
		File fcc = XMLUtils.writeTemp("currentConfiguration", currentConfiguration, Configuration.class);
		new XMLViewer(fcc);
		
		File f = XMLUtils.writeTemp("deployConfiguration", deployConfiguration, Configuration.class);
		new XMLViewer(f);
		
		File f2 = XMLUtils.writeTemp("undeployConfiguration", undeployConfiguration, Configuration.class);
		new XMLViewer(f2);
		
		File f3 = XMLUtils.writeTemp("remainConfiguration", remainConfiguration, Configuration.class);
		new XMLViewer(f3);
		
		File f4 = XMLUtils.writeTemp("totalNewConfiguration", totalNewConfiguration, Configuration.class);
		new XMLViewer(f4);
		*/
	}
	
	/**
	 * Security configuration with the aspects that need to be deployed because they are required and they are not in the current configuration.
	 * @param currentConfiguration
	 * @param newConfigurationToBeDeployed
	 * @return
	 */
	private Configuration calculateDeployConfiguration(Configuration currentConfiguration, Configuration newConfigurationToBeDeployed) {
		Configuration resConfiguration = new Configuration();
		resConfiguration.setInstance(newConfigurationToBeDeployed.getInstance());
		resConfiguration.getAdvisor().addAll(newConfigurationToBeDeployed.getAdvisor()); 
		resConfiguration.getAdvisor().removeAll(currentConfiguration.getAdvisor());
		return resConfiguration;
	}
	
	/**
	 * Security configuration with the aspects that need to be undeployed because they are not needed and they are in the current configuration.
	 * @param currentConfiguration
	 * @param newConfigurationToBeUndeployed
	 * @return
	 */
	private Configuration calculateUndeployConfiguration(Configuration currentConfiguration, Configuration newConfigurationToBeUndeployed) {
		Configuration resConfiguration = new Configuration();
		resConfiguration.setInstance(newConfigurationToBeUndeployed.getInstance());
		resConfiguration.getAdvisor().addAll(newConfigurationToBeUndeployed.getAdvisor());
		resConfiguration.getAdvisor().retainAll(currentConfiguration.getAdvisor());	
		return resConfiguration;
	}
	
	/**
	 * Security configuration with the aspects that need to be reconfigure because they are required, but they are already in the current configuration.
	 * @param currentConfiguration
	 * @param newConfigurationToBeDeployed
	 * @param newConfigurationToBeUndeployed
	 * @return
	 */
	private Configuration calculateRemainConfiguration(Configuration currentConfiguration, Configuration newConfigurationToBeDeployed) {
		Configuration resConfiguration = new Configuration();
		resConfiguration.setInstance(newConfigurationToBeDeployed.getInstance());
		resConfiguration.getAdvisor().addAll(newConfigurationToBeDeployed.getAdvisor());
		resConfiguration.getAdvisor().retainAll(currentConfiguration.getAdvisor());	
		return resConfiguration;
	}
	
	/**
	 * Calculate the total new configuration that will be deployed in the application.
	 * This configuration is only used to update the current configuration in the repository of configurations. 
	 * @param currentConfiguration
	 * @param newConfigurationToBeDeployed
	 * @return
	 */
	private Configuration calculateTotalNewConfiguration(Configuration newConfigurationToBeDeployed, Configuration currentConfiguration) {
		Configuration resConfiguration = new Configuration();
		resConfiguration.setInstance(newConfigurationToBeDeployed.getInstance());
		resConfiguration.getAdvisor().addAll(newConfigurationToBeDeployed.getAdvisor());
		resConfiguration.getAdvisor().addAll(currentConfiguration.getAdvisor());
		return resConfiguration;
	}
	
	public Configuration getDeployConfiguration() {
		return deployConfiguration;
	}

	public Configuration getUndeployConfiguration() {
		return undeployConfiguration;
	}

	public Configuration getRemainConfiguration() {
		return remainConfiguration;
	}
	
	public Configuration getTotalNewConfiguration() {
		return totalNewConfiguration;
	}
	
}
