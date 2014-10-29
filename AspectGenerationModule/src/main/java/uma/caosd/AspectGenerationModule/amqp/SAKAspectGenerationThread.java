package uma.caosd.AspectGenerationModule.amqp;

import uma.caosd.AspectGenerationModule.AspectGeneration;
import uma.caosd.AspectualKnowledge.AspectualKnowledge;
import uma.caosd.amqp.utils.XMLUtils;

public class SAKAspectGenerationThread extends Thread {

	private String content;
	private AspectGeneration aspectGeneration;
	
	public SAKAspectGenerationThread(AspectGeneration ag, String c) {
		aspectGeneration = ag;
		content = c;
	}
	
	public void run() {
		System.out.println(getClass().getSimpleName() + ">> new security aspectual knowledge (SAK) received.");
		
		//Sds sds = XMLUtils.read(content, Sds.class);
		AspectualKnowledge sak = XMLUtils.read(content, AspectualKnowledge.class);
		aspectGeneration.updateAspectualKnowledge(sak);
		
		System.out.println(getClass().getSimpleName() + ">> security aspectual knowledge (SAK) updated.");	
    }
}
