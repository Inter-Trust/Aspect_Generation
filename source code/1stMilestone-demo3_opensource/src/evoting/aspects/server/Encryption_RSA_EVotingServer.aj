package evoting.aspects.server;

import intertrust.aspects.aspectj.Encryption_RSA;

public aspect Encryption_RSA_EVotingServer extends Encryption_RSA {

	protected pointcut encrypPoints(): execution(* evoting.core.server.EVotingServerInt.receiveVote(..));
	
	protected pointcut decryptPoints();
}
