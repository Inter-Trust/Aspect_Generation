package uma.caosd.AspectGenerationModule.analysis.selectionAlgorithm;

import java.util.Set;

import uma.caosd.AspectGenerationModule.exceptions.AspectsSelectionException;
import uma.caosd.AspectualKnowledge.Advisor;
import uma.caosd.errorHandling.DeploymentStatusSingleton;
import uma.caosd.errors.Module;
import uma.caosd.errors.Type;

/**
 * Select the firts advisor in the set of candidates.
 * 
 * @author UMA
 * @date 2/05/2014
 *
 */
public class SelectionAlgorithm1 implements AspectsSelectionAlgorithm {

	public Advisor selectAdvisor(Set<Advisor> advisors) throws AspectsSelectionException {
		if (advisors.isEmpty()) { 
			String desc = "There is not advisor candidates.";
			DeploymentStatusSingleton.getStatus().addError(desc, Module.ASPECT_GENERATION, Type.ASPECTS_SELECTION);
			throw new AspectsSelectionException(desc);
		}
		return advisors.iterator().next();
	}
	
}
