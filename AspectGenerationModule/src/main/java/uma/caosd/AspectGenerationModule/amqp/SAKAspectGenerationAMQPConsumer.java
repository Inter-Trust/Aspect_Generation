package uma.caosd.AspectGenerationModule.amqp;

import java.io.IOException;

import javax.jms.JMSException;

import uma.caosd.AspectGenerationModule.AspectGeneration;
import uma.caosd.AspectualKnowledge.AspectualKnowledge;
import uma.caosd.amqp.activemq.ActiveMQConsumer;
import uma.caosd.amqp.utils.XMLUtils;

public class SAKAspectGenerationAMQPConsumer extends ActiveMQConsumer {
	private AspectGeneration aspectGeneration;
	
	public SAKAspectGenerationAMQPConsumer(String brokerURL, String queue, AspectGeneration aspectGeneration) throws IOException, JMSException {
		super(brokerURL, queue);
		this.aspectGeneration = aspectGeneration;
	}

	@Override
	protected void onMessageReceived(String content) {
		System.out.println(getClass().getSimpleName() + ">> new security aspectual knowledge (SAK) received.");
		
		//Sds sds = XMLUtils.read(content, Sds.class);
		AspectualKnowledge sak = XMLUtils.read(content, AspectualKnowledge.class);
		aspectGeneration.updateAspectualKnowledge(sak);
		
		System.out.println(getClass().getSimpleName() + ">> security aspectual knowledge (SAK) updated.");	
	}
}