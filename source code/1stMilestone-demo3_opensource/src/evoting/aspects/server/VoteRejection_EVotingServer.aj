package evoting.aspects.server;

import intertrust.aspects.aspectj.VoteRejection;

public aspect VoteRejection_EVotingServer extends VoteRejection {

	protected pointcut rejectPoints(): execution(* evoting.core.server.EVotingServerInt.receiveVote(..));

}
