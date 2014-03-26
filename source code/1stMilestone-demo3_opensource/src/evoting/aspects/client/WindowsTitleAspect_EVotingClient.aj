package evoting.aspects.client;

import intertrust.WindowsTitleAspect;

public aspect WindowsTitleAspect_EVotingClient extends WindowsTitleAspect {

	private static final String TITLE = "EVoting Client";
	
	@Override
	public String getAppName() {
		return TITLE;
	}

}
