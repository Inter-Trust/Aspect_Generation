package evoting.aspects.client;

import intertrust.aspects.aspectj.Authentication_UserPassword;

public aspect Authentication_UserPassword_EVotingClient extends Authentication_UserPassword {

	protected pointcut authPoints(): execution(* evoting.core.client.EVotingInt.vote(..));
}
