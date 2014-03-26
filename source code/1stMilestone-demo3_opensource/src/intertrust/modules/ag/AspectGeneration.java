package intertrust.modules.ag;

import intertrust.aspects.aspectj.IntertrustAspect;
import intertrust.modules.ag.generic.GenericAspectGeneration;
import intertrust.modules.ag.generic.SAKClasses.AdaptationPlan;
import intertrust.modules.ag.generic.SAKClasses.ListAF;
import intertrust.modules.ag.generic.SAKClasses.SecurityConcept;
import intertrust.modules.ag.gui.AspectGenerationController;
import intertrust.modules.ag.gui.AspectGenerationView;
import intertrust.modules.aw.gui.AspectWeaverController;
import intertrust.modules.aw.gui.AspectWeaverView;
import intertrust.modules.pi_simulator.PolicyInterpreter_Simulator;

import java.io.File;
import java.util.List;

import javax.swing.JTextArea;

/**
 * Aspect Generation module.
 * 
 * @author UMA
 * @date   12/09/2013
 *
 */
public class AspectGeneration implements KnowledgeProvisionInt, AdaptationRequestInt, GenerationOfNewAspectRequestInt {

	private GenericAspectGeneration gag;
	private List<ConcreteAspectGeneration> concreteAGs;
	
	// GUI
	private AspectWeaverView awv;
	
	public AspectGeneration(List<ConcreteAspectGeneration> concreteAGs, File initialSdsFile, File listAFFile) {
		this.concreteAGs = concreteAGs;
		awv = new AspectWeaverView();
		gag = new GenericAspectGeneration(initialSdsFile, listAFFile);
		generateSecurityAdaptationPlan();
		
		//aw.adaptSecurityAspects(gag.getSecurityAdaptationPlan());
		for (ConcreteAspectGeneration cag : concreteAGs) {
			AdaptationPlan concreteAP = cag.getConcreteAdaptationPlan(gag.getSecurityAdaptationPlan());
			cag.getAspectWeaver().adaptSecurityAspects(concreteAP);
		}
	}
	
	// GUI
	private void makeGUI() {
		AspectGenerationView agv = new AspectGenerationView();
		AspectGenerationController agc = new AspectGenerationController(this, agv);
		agv.registerController(agc);
	}

	public File getListAspectsFunctionalitiesFile() {
		return gag.getListAspectsFunctionalitiesFile();
	}

	public File getMappingSecurityConceptAspectFile() {
		return gag.getMappingSecurityConceptAspectFile();
	}
	
	@Override
	public void updateSecurityDeploymentSpecification(File sds) {
		gag.updateSecurityDeploymentSpecification(sds);
		
		// GUI
		makeGUI();
	}
	
	@Override
	public void provideKnowledge(File sds, File laf) {
		gag.provideKnowledge(sds, laf);
	}
	
	public void generateSecurityAdaptationPlan() {
		gag.generateSecurityAdaptationPlan();
	}
	
	public List<SecurityConcept> getNewAspectsRequired() {
		return gag.getNewAspectsRequired();
	}
	
	public File getSecurityAdaptationPlanFile() {
		return gag.getSecurityAdaptationPlanFile();
	}
	
	public void adaptationRequest() {
		//aw.adaptSecurityAspects(gag.getSecurityAdaptationPlan());
		for (ConcreteAspectGeneration cag : concreteAGs) {
			AdaptationPlan concreteAP = cag.getConcreteAdaptationPlan(gag.getSecurityAdaptationPlan());
			cag.getAspectWeaver().adaptSecurityAspects(concreteAP);
		}
		
		// GUI
		awv.setVisible(true);
		AspectWeaverController awvC = new AspectWeaverController(concreteAGs, awv);
		awvC.adaptSecurityAspects(gag.getSecurityAdaptationPlan());
		
		PolicyInterpreter_Simulator policyInterpreterSimulator = new PolicyInterpreter_Simulator(this, PolicyInterpreter_Simulator.getSdss());
		IntertrustAspect.setIntertrustCommon(policyInterpreterSimulator);
	}
	
	public ListAF getListAspectsFunctionality() {
		return gag.getMappingAspectsFunctionality();
	}
	
	public JTextArea getTextConsole() {
		return awv.getTxtAreaConsole();
	}

	@Override
	public List<String> incorporateNewAspect(SecurityConcept sc, File newAspectsFile) {
		return null; // not open source
	}

	/*@Override
	public List<String> incorporateNewAspect(SecurityConcept sc, File newAspectsFile) {
		ConcreteAspectGeneration springAOPAG = getSpringAOPAG(concreteAGs);
		List<String> aspectsNames = springAOPAG.incorporateNewAspect(sc, newAspectsFile);
		for (String a : aspectsNames) {
			gag.updateListAspectFunctionality(sc, a);
		}
		return aspectsNames;
	}*/ // not open source
	/*
	private ConcreteAspectGeneration getSpringAOPAG(List<ConcreteAspectGeneration> ags) {
		ConcreteAspectGeneration res = null;
		Iterator<ConcreteAspectGeneration> iter = ags.iterator();
		while (res == null && iter.hasNext()) {
			ConcreteAspectGeneration ag = iter.next();
			if (ag instanceof AspectGeneration_SpringAOP) {
				res = ag;
			}
		}
		return res;
	}*/ // not open source
	
}
