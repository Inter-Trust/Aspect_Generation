package uma.caosd.AspectGenerationModule.amqp;

import java.io.IOException;

import javax.jms.JMSException;

import uma.caosd.AspectGenerationModule.AspectGeneration;
import uma.caosd.amqp.activemq.ActiveMQConsumer;

public class SAKAspectGenerationAMQPConsumer extends ActiveMQConsumer {
	private AspectGeneration aspectGeneration;
	
	public SAKAspectGenerationAMQPConsumer(String brokerURL, String queue, AspectGeneration aspectGeneration) throws IOException, JMSException {
		super(brokerURL, queue);
		this.aspectGeneration = aspectGeneration;
	}

	@Override
	protected void onMessageReceived(String content) {
		try {
			SAKAspectGenerationThread t = new SAKAspectGenerationThread(aspectGeneration, content);
			t.start();	
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}