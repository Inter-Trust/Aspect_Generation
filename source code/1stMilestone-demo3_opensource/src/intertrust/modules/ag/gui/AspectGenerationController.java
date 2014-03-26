package intertrust.modules.ag.gui;

import intertrust.modules.ag.AspectGeneration;
import intertrust.modules.ag.generic.SAKClasses.SecurityConcept;
import intertrust.utils.XMLViewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;

public class AspectGenerationController implements ActionListener {

	private AspectGeneration ag;
	private AspectGenerationView agView;
	private File fileNewAspect;
	private XMLViewer xmlViewer;
	
	public AspectGenerationController(AspectGeneration ag, AspectGenerationView agView) {
		this.ag = ag;
		this.agView = agView;
		
		fileNewAspect = null;
		xmlViewer = null;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals(AspectGenerationView.ACTION_VISUALIZEMAPPING)) {	
			File maf = ag.getListAspectsFunctionalitiesFile(); 
			File msca = ag.getMappingSecurityConceptAspectFile();
			new XMLViewer(maf);
			new XMLViewer(msca);
		} else if (command.equals(AspectGenerationView.ACTION_GENERATEADAPTATION)) {
			// Cerramos el visor xml en caso de que siga abierto para que sobreescriba bien los ficheros.
			if (xmlViewer != null) {
				xmlViewer.dispose();
				xmlViewer = null;
			}
			ag.generateSecurityAdaptationPlan();
			agView.setBtnVisualizeSecurityAdaptationEnabled(true);
			
			List<SecurityConcept> nar = ag.getNewAspectsRequired();
			if (!nar.isEmpty()) {
				agView.setTextInformation("Add new aspect for security concept with id=" + nar.get(0).getId() + ".");
				agView.setBtnExamineEnabled(true);
			} else {
				agView.setTextInformation("Notify aspect weaver!");
				agView.setBtnAdaptationRequestEnabled(true);
			}
		} else if (command.equals(AspectGenerationView.ACTION_VISUALIZEADAPTATION)) {
			File sap = ag.getSecurityAdaptationPlanFile(); 
			xmlViewer = new XMLViewer(sap);
		} else if (command.equals(AspectGenerationView.ACTION_ADAPTATIONREQUEST)) {
			ag.adaptationRequest();
			agView.dispose();
		} else if (command.equals(AspectGenerationView.ADD_NEW_ASPECT)) {
			if (fileNewAspect != null) {
				SecurityConcept sc = ag.getNewAspectsRequired().get(0);
				ag.incorporateNewAspect(sc, fileNewAspect);	
				agView.setNewAspectPath(null);
				agView.setBtnAddNewAspectEnabled(false);
				agView.setBtnExamineEnabled(false);
				agView.setTextInformation("Generate security adaptation plan again!");
			}
		} else if (command.equals(AspectGenerationView.EXAMINE_ASPECTS)) {
			fileNewAspect = getNewAspectFile();
			if (fileNewAspect != null) {
				agView.setNewAspectPath(fileNewAspect.getPath());
				agView.setBtnAddNewAspectEnabled(true);
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