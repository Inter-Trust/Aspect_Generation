package uma.caosd.AspectGenerationModule.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Set;

import javax.swing.JFileChooser;

import uma.caosd.AspectGenerationModule.AspectGeneration;
import uma.caosd.AspectGenerationModule.utils.XMLViewer;

public class AspectGenerationController implements ActionListener {
	private File newSDSFile;
	private AspectGeneration ag;
	private AspectGenerationView agView;
	private File sakFile;
	private XMLViewer xmlViewer;
	
	public AspectGenerationController(AspectGeneration ag, AspectGenerationView agView) {
		this.ag = ag;
		this.agView = agView;
		
		this.agView.registerController(this);
		newSDSFile = null;
		sakFile = null;
		xmlViewer = null;
	}
	
	public void provideSDSFile(File sdsFile) {
		newSDSFile = sdsFile;
	}
	
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals(AspectGenerationView.ACTION_VISUALIZEMAPPING)) {	
			//File maf = ag.getAspectualKnowledgeFile(); 
			//File msca = ag.getMappingSecurityConceptAspectFile();
			//new XMLViewer(maf);
			//new XMLViewer(msca);
		} else if (command.equals(AspectGenerationView.ACTION_GENERATEADAPTATION)) {
			// Cerramos el visor xml en caso de que siga abierto para que sobreescriba bien los ficheros.
			if (xmlViewer != null) {
				xmlViewer.dispose();
				xmlViewer = null;
			}
			//ag.updateSecurityDeploymentSpecification(newSDSFile);
			agView.setBtnVisualizeSecurityAdaptationEnabled(true);
			
			Set<String> nar = ag.getSecurityConceptsWithoutAdvisors();
			if (!nar.isEmpty()) {
				agView.setTextInformation("The Aspectual Knowledge is incompleted! There is not advisors associated for some security concepts.");
				agView.setBtnAdaptationRequestEnabled(false);
			} else {
				agView.setTextInformation("Notify aspect weaver!");
				agView.setBtnAdaptationRequestEnabled(true);
			}
		} else if (command.equals(AspectGenerationView.ACTION_VISUALIZEADAPTATION)) {
			//File sap = ag.getAdaptationPlanFile(); 
			//xmlViewer = new XMLViewer(sap);
		} else if (command.equals(AspectGenerationView.ACTION_ADAPTATIONREQUEST)) {
			//ag.sendToAMQP();
			agView.dispose();
		} else if (command.equals(AspectGenerationView.UPDATE_ASPECTUAL_KNOWLEDGE)) {
			if (sakFile != null) {
				//ag.updateAspectualKnowledge(sakFile);
				agView.setUpdateSAKPath(null);
				agView.setBtnUpdateSAKEnabled(false);
				agView.setTextInformation("Generate security adaptation plan again!");
				/*SecurityConcept sc = ag.getNewAspectsRequired().get(0);
				ag.incorporateNewAspect(sc, fileNewAspect);	
				agView.setNewAspectPath(null);
				agView.setBtnAddNewAspectEnabled(false);
				agView.setBtnExamineEnabled(false);
				agView.setTextInformation("Generate security adaptation plan again!");*/
			}
		} else if (command.equals(AspectGenerationView.EXAMINE_FILES)) {
			sakFile = getNewAspectFile();
			if (sakFile != null) {
				agView.setUpdateSAKPath(sakFile.getPath());
				agView.setBtnUpdateSAKEnabled(true);
			}
		} 
	}

	private File getNewAspectFile() {
		File file = null;
		JFileChooser fileChooser = new JFileChooser();
		int selection = fileChooser.showSaveDialog(agView);
		if (selection == JFileChooser.APPROVE_OPTION) {
		   file = fileChooser.getSelectedFile();
		}
		return file;
	}
	
	
}