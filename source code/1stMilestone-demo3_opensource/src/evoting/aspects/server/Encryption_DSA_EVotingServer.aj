package evoting.aspects.server;

import intertrust.aspects.aspectj.Encryption_DSA;

public aspect Encryption_DSA_EVotingServer extends Encryption_DSA {

	protected pointcut encrypPoints(): execution(* evoting.core.server.EVotingServerInt.receiveVote(..));
	
	protected pointcut decryptPoints();
}
