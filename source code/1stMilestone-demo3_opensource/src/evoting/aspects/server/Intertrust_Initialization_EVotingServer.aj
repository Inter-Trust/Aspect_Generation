package evoting.aspects.server;

import intertrust.Intertrust_Initialization_EVotingServerFiles;


public aspect Intertrust_Initialization_EVotingServer extends Intertrust_Initialization_EVotingServerFiles {

	public pointcut initialize(): call(public static void evoting.core.server.EVotingServer.initializeGUI());
}
