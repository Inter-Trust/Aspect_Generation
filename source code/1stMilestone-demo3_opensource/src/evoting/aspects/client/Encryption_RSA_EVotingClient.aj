package evoting.aspects.client;

import intertrust.aspects.aspectj.Encryption_RSA;

public aspect Encryption_RSA_EVotingClient extends Encryption_RSA {

	protected pointcut encrypPoints(): execution(* evoting.core.client.EVotingInt.vote(..));
	
	protected pointcut decryptPoints();
}
