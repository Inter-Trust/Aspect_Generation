package evoting.aspects.server;

import intertrust.aspects.aspectj.Authentication_X509certificate;

public aspect Authentication_X509certificate_EVotingServer extends Authentication_X509certificate {

	protected pointcut authPoints(): execution(* evoting.core.server.EVotingServerInt.receiveVote(..));
}
