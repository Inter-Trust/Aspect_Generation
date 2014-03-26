package evoting.aspects.server;

import intertrust.WindowsTitleAspect;

public aspect WindowsTitleAspect_EVotingServer extends WindowsTitleAspect {

	private static final String TITLE = "EVoting Server";
	
	@Override
	public String getAppName() {
		return TITLE;
	}

}
