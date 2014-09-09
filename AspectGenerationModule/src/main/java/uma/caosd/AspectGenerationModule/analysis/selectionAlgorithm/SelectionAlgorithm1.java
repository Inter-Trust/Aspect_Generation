package uma.caosd.AspectGenerationModule.analysis.selectionAlgorithm;

import java.util.Set;

import uma.caosd.AspectGenerationModule.exceptions.AspectsSelectionException;
import uma.caosd.AspectualKnowledge.Advisor;

/**
 * Select the firts advisor in the set of candidates.
 * 
 * @author UMA
 * @date 2/05/2014
 *
 */
public class SelectionAlgorithm1 implements AspectsSelectionAlgorithm {

	public Advisor selectAdvisor(Set<Advisor> advisors) throws AspectsSelectionException {
		if (advisors.isEmpty()) 
			throw new AspectsSelectionException("There is not advisor candidates.");
		return advisors.iterator().next();
	}
	
}
