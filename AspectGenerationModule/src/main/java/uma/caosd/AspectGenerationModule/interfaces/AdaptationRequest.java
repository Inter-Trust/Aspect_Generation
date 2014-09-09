package uma.caosd.AspectGenerationModule.interfaces;

import uma.caosd.SecurityDeploymentSpecification.Sds;

/**
 * This input interface is used by the Policy Interpreter module 
 * to request changes in the security policy being applied for a particular application. 
 * This information is provided through the Security Deployment Specification (SDS) xml file.
 * 
 * @author UMA
 * @date   12/09/2013
 *
 */
public interface AdaptationRequest {

	/**
	 * Receives a notification about a new security policy to be deployed and 
	 * dynamically generates an adaptation plan.
	 * 
	 * @param sds Security Deployment Specification.
	 */
	public void updateSecurityDeploymentSpecification(Sds sds);
}
