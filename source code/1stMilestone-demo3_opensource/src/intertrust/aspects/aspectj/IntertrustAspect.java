package intertrust.aspects.aspectj;

import intertrust.modules.pi_simulator.PolicyInterpreter_Simulator;

import javax.swing.JTextArea;

public abstract class IntertrustAspect implements EnablingDisablingInt, IntertrustNotificationInt, IntertrustAspectGUIInt {

	//********** NOTIFICATIONS **********
	private static PolicyInterpreter_Simulator module;
	
	@Override
	public void setIntertrust(PolicyInterpreter_Simulator intertrustModule) {
		module = intertrustModule;
	}

	@Override
	public void notifyIntertrust(String message) {
		module.intertrustNotification(message);		
	}
	
	public static void setIntertrustCommon(PolicyInterpreter_Simulator intertrustModule) {
		module = intertrustModule;
	}
	
	
	//********** GUI **********
	private static JTextArea console;
	
	@Override
	public void setConsole(JTextArea txtAreaConsole) {
		console = txtAreaConsole;
	}

	@Override
	public void writeConsole(String txt) {
		console.append(txt);
	}

	public static void setConsoleCommon(JTextArea txtAreaConsole) {
		console = txtAreaConsole;
	}
	
	
	//********** ENABLING/DISABLING **********
	//pointcut enabled(): if(EnablingDisablingDriver.isEnabled(thisJoinPoint.getTarget().getClass().getName()));
}
