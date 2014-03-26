package evoting.aspects.client;

import intertrust.aspects.aspectj.Encryption_DSA;

public aspect Encryption_DSA_EVotingClient extends Encryption_DSA {

	protected pointcut encrypPoints(): execution(* evoting.core.client.EVotingInt.vote(..));
	
	protected pointcut decryptPoints();
}
