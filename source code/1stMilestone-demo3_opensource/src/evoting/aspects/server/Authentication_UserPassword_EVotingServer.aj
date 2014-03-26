package evoting.aspects.server;

import intertrust.aspects.aspectj.Authentication_UserPassword;

public aspect Authentication_UserPassword_EVotingServer extends Authentication_UserPassword {

	protected pointcut authPoints(): execution(* evoting.core.server.EVotingServerInt.receiveVote(..));
}
