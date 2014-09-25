package uma.caosd.ErrorsConsumer.main;

import java.io.IOException;

import javax.jms.JMSException;

import uma.caosd.amqp.activemq.ActiveMQConsumer;
import uma.caosd.amqp.utils.XMLUtils;
import uma.caosd.errors.DeploymentStatus;

public class ErrorsAMQPConsumer extends ActiveMQConsumer {
	
	public ErrorsAMQPConsumer(String brokerURL, String queue) throws IOException, JMSException {
		super(brokerURL, queue);
	}

	@Override
	protected void onMessageReceived(String content) {
		System.out.println(getClass().getSimpleName() + ">> deployment notification received.");
		
		DeploymentStatus errorsStatus = XMLUtils.read(content, DeploymentStatus.class);
		XMLUtils.writeTemp("errorsStatus", errorsStatus, DeploymentStatus.class);
		
		System.out.println(getClass().getSimpleName() + ">> deployment notification processed.");	
	}
}