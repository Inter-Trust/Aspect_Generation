package intertrust.modules.pi_simulator;

import intertrust.modules.ag.AdaptationRequestInt;
import intertrust.utils.XMLViewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author UMA
 * @date   10/09/2013
 *
 */
public class PolicyInterpreter_Simulator implements ActionListener {

	private static List<File> sdss;
	private PolicyInterpreter_SimulatorPane piSimulatorPane;
	
	private AdaptationRequestInt adaptationRequest;
	
	public PolicyInterpreter_Simulator(AdaptationRequestInt ar) {
		adaptationRequest = ar;
		sdss = new ArrayList<File>();
		initialize();
	}
	
	public PolicyInterpreter_Simulator(AdaptationRequestInt ar, List<File> sdss) {
		adaptationRequest = ar;
		this.sdss = sdss;
		initialize();
	}

	public void setSdss(List<File> sdss) {
		this.sdss = sdss;
	}
	
	private void initialize() {
		piSimulatorPane = new PolicyInterpreter_SimulatorPane("Policy Interpreter Simulator");
		piSimulatorPane.setSDSs(sdss.toArray());
		piSimulatorPane.registerController(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals(PolicyInterpreter_SimulatorPane.ACTION_VISUALIZE)) {	
			//String filename = piSimulatorPane.getSelectedSDS();
			File file = piSimulatorPane.getSelectedSDS();
			new XMLViewer(file);
		} else if (command.equals(PolicyInterpreter_SimulatorPane.ACTION_NOTIFY)) {
			//String filename = piSimulatorPane.getSelectedSDS();
			File file = piSimulatorPane.getSelectedSDS();
			adaptationRequest.updateSecurityDeploymentSpecification(file);
			piSimulatorPane.dispose();
		}		
	}

	public static List<File> getSdss() {
		return sdss;
	}
	
	public void intertrustNotification(String message) {
		piSimulatorPane.writeNotifications(message);
	}
}
