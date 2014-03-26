package evoting.aspects.server;

import intertrust.aspects.aspectj.ElapsedTimeMonitoring;

public aspect ElapsedTimeMonitoring_EVotingServer extends ElapsedTimeMonitoring {

	protected pointcut monitorPoints();// execution(* intertrust.aspects.aspectj.encryption.Encryption.encrypt());
}
