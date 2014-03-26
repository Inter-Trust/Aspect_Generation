package intertrust.aspects.aspectj;

import intertrust.modules.pi_simulator.PolicyInterpreter_Simulator;

public interface IntertrustNotificationInt {

	public void setIntertrust(PolicyInterpreter_Simulator module);
	
	public void notifyIntertrust(String message);
}
