package evoting.aspects.client;

import intertrust.aspects.aspectj.ElapsedTimeMonitoring;

public aspect ElapsedTimeMonitoring_EVotingClient extends ElapsedTimeMonitoring {

	protected pointcut monitorPoints(): execution(* intertrust.aspects.aspectj.encryption.Encryption.encrypt(..));
}
