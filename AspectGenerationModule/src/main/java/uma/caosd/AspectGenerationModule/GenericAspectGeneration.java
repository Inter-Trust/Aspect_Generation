package uma.caosd.AspectGenerationModule;

import java.io.File;
import java.util.Set;

import uma.caosd.AspectGenerationModule.analysis.Analysis;
import uma.caosd.AspectGenerationModule.exceptions.AspectsSelectionException;
import uma.caosd.AspectGenerationModule.exceptions.MappingException;
import uma.caosd.AspectGenerationModule.genericAdaptationPlan.GenericSecurityAdaptationPlanGeneration;
import uma.caosd.AspectGenerationModule.securityAspectualKnowledge.SecurityAspectualKnowledge;
import uma.caosd.AspectGenerationModule.utils.XMLViewer;
import uma.caosd.AspectualKnowledge.AdaptationPlan;
import uma.caosd.AspectualKnowledge.Configuration;
import uma.caosd.SecurityDeploymentSpecification.Sds;
import uma.caosd.amqp.utils.XMLUtils;

public class GenericAspectGeneration {
	private SecurityAspectualKnowledge knowledge;
	private Analysis analysis;
	private GenericSecurityAdaptationPlanGeneration adaptationPlanGeneration;
	
	public GenericAspectGeneration(SecurityAspectualKnowledge knowledge) {
		this.knowledge = knowledge;
		analysis = new Analysis(knowledge.getSAKAnalysis());
		adaptationPlanGeneration = new GenericSecurityAdaptationPlanGeneration(knowledge.getSecurityConfiguration());
	}
	
	public AdaptationPlan adapts(Sds sds) {
		AdaptationPlan sap = null;
		try {
			analysis.generateNewSecurityConfiguration(sds);
			sap = adaptationPlanGeneration.generateSecurityAdaptationPlan(knowledge.getSecurityConfiguration(), analysis.getSecurityConfigurationToBeDeployed(), analysis.getSecurityConfigurationToBeUndeployed());
			knowledge.updateSecurityConfigurations(adaptationPlanGeneration.getNewSecurityConfiguration());
			
			/*File fcc = XMLUtils.writeTemp("currentConfiguration-adapts", adaptationPlanGeneration.getNewSecurityConfiguration(), Configuration.class);
			new XMLViewer(fcc);*/
			
		} catch (AspectsSelectionException e) {
			sap = null;
			System.out.println("AspectGeneration>> Error! See Deployment Status notification.");
			//e.printStackTrace();
		} catch (MappingException e) {
			sap = null;
			System.out.println("AspectGeneration>> Error! See Deployment Status notification.");
			//e.printStackTrace();
		}
		return sap;
	}
	
	public Set<String> getSecurityConceptsWithoutAdvisors() {
		return analysis.getSecurityConceptsWithoutAdvisors();
	}
}
