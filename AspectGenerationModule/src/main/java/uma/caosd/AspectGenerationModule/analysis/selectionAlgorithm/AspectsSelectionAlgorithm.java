package uma.caosd.AspectGenerationModule.analysis.selectionAlgorithm;

import java.util.Set;

import uma.caosd.AspectGenerationModule.exceptions.AspectsSelectionException;
import uma.caosd.AspectualKnowledge.Advisor;

/**
 * Selection algorithm for the candidate aspects.
 * 
 * @author UMA
 * @date 2/05/2014
 */
public interface AspectsSelectionAlgorithm {
	/**
	 * Select an advisor from a set of advisor candidates.
	 * @param advisors						Candidate advisors.
	 * @return								Advisor selected.
	 * @throws AspectsSelectionException	There is not advisor candidates.
	 */
	public Advisor selectAdvisor(Set<Advisor> advisors) throws AspectsSelectionException;
}
