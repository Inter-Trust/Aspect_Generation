package intertrust.modules.ag;

import java.io.File;

/**
 * This input interface is used by the rest of modules in the INTER-TRUST framework 
 * to request an adaptation in the aspects being applied for a particular application. 
 * This information is provided in terms of changing that need to be done 
 * on the security policies or on the security testing.
 * 
 * @author UMA
 * @date   12/09/2013
 *
 */
public interface AdaptationRequestInt {

	/**
	 * 
	 * @param sds Security Deployment Specification file.
	 */
	public void updateSecurityDeploymentSpecification(File sds);
}
