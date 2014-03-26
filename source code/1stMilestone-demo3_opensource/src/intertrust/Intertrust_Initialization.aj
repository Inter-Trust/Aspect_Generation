package intertrust;

import intertrust.aspects.aspectj.IntertrustAspect;
import intertrust.modules.ag.AspectGeneration;
import intertrust.modules.ag.ConcreteAspectGeneration;
import intertrust.modules.ag.concrete.aspectj.AspectGeneration_AspectJ;
import intertrust.modules.ag.generic.SAKClasses.Aspect;
import intertrust.modules.ag.generic.SAKClasses.ListAF;
import intertrust.modules.aw.concrete.aspectj.AspectWeaver_AspectJ;
import intertrust.modules.pi_simulator.PolicyInterpreter_Simulator;
import intertrust.utils.XMLFile;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

/**
 * Initialization aspect for Aspect Generation and Weaver modules.
 * It captures the initialization of the base program and, after that initialization,
 * creates the instances of the Aspect Generation and Weaver modules,
 * and the Policy Interpreter Simulator with its graphic user interface.
 * 
 * This aspects takes the necessary input .xml files.
 * 
 * @author UMA
 * @date   23/09/2013
 *
 */
public abstract aspect Intertrust_Initialization {

	private AspectGeneration ag;
	private JTextArea console;
	
	/**
	 * Captures the initialization of the base program.
	 */
	abstract public pointcut initialize();
	
	/**
	 * After the initialization of the base program initializes the
	 * Aspect Generation, Aspect Weaver and Simulator Policy Interpreter modules.
	 */
	after(): initialize() {
		console = getConsole();
		initializeIntertrust();
		initializeAspects();
	}
	
	private void initializeIntertrust() {
		// Create the concrete aspect weaver modules.
		AspectWeaver_AspectJ aspectjAW = new AspectWeaver_AspectJ();
		//AspectWeaver_SpringAOP springaopAW = new AspectWeaver_SpringAOP();
		
		// Create the concrete aspect generation modules.
		AspectGeneration_AspectJ aspectjAG = new AspectGeneration_AspectJ(aspectjAW);
		aspectjAG.setRepositoryAspects(getDefaultAspectNames(getListAspectFunctionalityFile()));
		
		//AspectGeneration_SpringAOP springaopAG = new AspectGeneration_SpringAOP(springaopAW);
		
		// List of concrete aspect generation modules.
		ArrayList<ConcreteAspectGeneration> ags = new ArrayList<ConcreteAspectGeneration>();
		ags.add(aspectjAG);
		//ags.add(springaopAG);
		
		// Create the generic aspect generation module.
		ag = new AspectGeneration(ags, getInitialSdsFile(), getListAspectFunctionalityFile());
		PolicyInterpreter_Simulator policyInterpreterSimulator = new PolicyInterpreter_Simulator(ag, getSecurityDeploymentSpecificationFiles());
		IntertrustAspect.setIntertrustCommon(policyInterpreterSimulator);
	}
	
	private void initializeAspects() {
		IntertrustAspect.setConsoleCommon(console);
	}
	
	private List<File> getSecurityDeploymentSpecificationFiles() {
		ArrayList<File> files = new ArrayList<File>();
		for (Object o : getSecurityDeploymentSpecificationFilesPath().toArray()) {
			URL url = (URL) o;
			File f = new File(url.getPath());
			files.add(f);
		}
		return files;
	}

	private File getInitialSdsFile() {
		return new File(getInitialSdsFilePath().getPath());
	}

	private File getListAspectFunctionalityFile() {
		return new File(getListAspectFunctionalityFilePath().getPath());
	}
	
	abstract public JTextArea getConsole();
	
	/**
	 * 
	 * @return	Paths to the security deployment specification files.
	 */
	abstract public List<URL> getSecurityDeploymentSpecificationFilesPath();
	
	/**
	 * 
	 * @return Path to the initial security deployment specification file. 
	 */
	abstract public URL getInitialSdsFilePath();
	
	/**
	 * 
	 * @return Path to the file with the list of aspects and their functionalities. 
	 */
	abstract public URL getListAspectFunctionalityFilePath();
	
	private List<String> getDefaultAspectNames(File lafFile) {
		ArrayList<String> aspectNames = new ArrayList<String>();
		ListAF laf = XMLFile.read(lafFile, ListAF.class);
		for (Aspect a : laf.getAspect()) {
			aspectNames.add(a.getName());
		}
		return aspectNames;
	}
}
