package intertrust;

import intertrust.modules.pi_simulator.PolicyInterpreter_SimulatorPane;

import javax.swing.JFrame;

public abstract aspect WindowsTitleAspect {

	pointcut windows(JFrame window): this(window) && (execution(PolicyInterpreter_SimulatorPane.new(..))
												  || execution(intertrust.modules.ag.gui.AspectGenerationView.new(..))
												  || execution(intertrust.modules.aw.gui.AspectWeaverView.new(..))
												  || execution(intertrust.utils.XMLViewer.new(..)));
	
	after(JFrame window): windows(window) {
		System.out.println("windows aspect");
		window.setTitle(getAppName() + ": " + window.getTitle());
	}
	
	abstract public String getAppName();
}
