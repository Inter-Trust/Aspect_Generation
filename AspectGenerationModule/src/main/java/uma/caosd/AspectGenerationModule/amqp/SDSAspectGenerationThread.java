package uma.caosd.AspectGenerationModule.amqp;

import uma.caosd.AspectGenerationModule.AspectGeneration;
import uma.caosd.SecurityDeploymentSpecification.Sds;
import uma.caosd.amqp.utils.XMLUtils;

public class SDSAspectGenerationThread extends Thread {
	private String content;
	private AspectGeneration aspectGeneration;
	
	public SDSAspectGenerationThread(AspectGeneration ag, String c) {
		aspectGeneration = ag;
		content = c;
	}
	
	public void run() {
		System.out.println(getClass().getSimpleName() + ">> new security deployment specification (SDS) received.");
		
		Sds sds = XMLUtils.read(content, Sds.class);
		XMLUtils.writeTemp("SDS", sds, Sds.class);
		aspectGeneration.updateSecurityDeploymentSpecification(sds);

		System.out.println(getClass().getSimpleName() + ">> security deployment specification (SDS) deployed.");
    }

}
