package evoting.aspects.client;

import intertrust.Intertrust_Initialization_EVotingClientFiles;

public aspect Intertrust_Initialization_EVotingClient extends Intertrust_Initialization_EVotingClientFiles {

	public pointcut initialize(): call(public static void evoting.core.client.EVotingClient.initializeGUI());
}
