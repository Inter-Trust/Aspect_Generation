package evoting.aspects.client;

import intertrust.aspects.aspectj.Authentication_X509certificate;

public aspect Authentication_X509certificate_EVotingClient extends Authentication_X509certificate {

	protected pointcut authPoints(): execution(* evoting.core.client.EVotingInt.vote(..));
}
