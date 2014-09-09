package uma.caosd.amqp.activemq;

import java.io.IOException;
import java.io.Serializable;

import javax.jms.JMSException;

import uma.caosd.amqp.activemq.ActiveMQConsumer;


public class ActiveMQConsumerTest extends ActiveMQConsumer {


	public ActiveMQConsumerTest(String brokerURL, String queue)	throws IOException, JMSException {
		super(brokerURL, queue);
	}

	@Override
	protected void onMessageReceived(String object) {
		System.out.println("Received: " + object);
	}

	public static void main(String[] args) {
		try {
			@SuppressWarnings("unused")
			ActiveMQConsumerTest consumer = new ActiveMQConsumerTest("tcp://localhost:61616", "queue.test");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
