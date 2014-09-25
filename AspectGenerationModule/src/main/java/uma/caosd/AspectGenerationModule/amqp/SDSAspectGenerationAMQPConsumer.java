package uma.caosd.AspectGenerationModule.amqp;

import java.io.IOException;

import javax.jms.JMSException;

import uma.caosd.AspectGenerationModule.AspectGeneration;
import uma.caosd.SecurityDeploymentSpecification.Sds;
import uma.caosd.amqp.activemq.ActiveMQConsumer;
import uma.caosd.amqp.utils.XMLUtils;

public class SDSAspectGenerationAMQPConsumer extends ActiveMQConsumer {
	private AspectGeneration aspectGeneration;
	
	public SDSAspectGenerationAMQPConsumer(String brokerURL, String queue, AspectGeneration aspectGeneration) throws IOException, JMSException {
		super(brokerURL, queue);
		this.aspectGeneration = aspectGeneration;
	}

	@Override
	protected void onMessageReceived(String content) {
		System.out.println(getClass().getSimpleName() + ">> new security deployment specification (SDS) received.");
		
		Sds sds = XMLUtils.read(content, Sds.class);
		XMLUtils.writeTemp("SDS", sds, Sds.class);
		aspectGeneration.updateSecurityDeploymentSpecification(sds);

		
		System.out.println(getClass().getSimpleName() + ">> security deployment specification (SDS) deployed.");	
	}
}