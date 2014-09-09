package uma.caosd.amqp.activemq.objects;

import java.io.IOException;
import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * ActiveMQ producer agent.
 * Sends messages to a queue in a broker.
 * 
 * @author UMA
 *
 */
public class ActiveMQProducer {	
	private Connection connection;
	private Session session;
	private MessageProducer producer;
	
	/**
	 * Create a new producer agent initializing it with with the specified broker parameters: the broker url and the queue.
	 * @param brokerURL
	 * @param queue
	 * @throws IOException
	 * @throws JMSException
	 */
	public ActiveMQProducer(String brokerURL, String queue) throws IOException, JMSException {
		System.out.println(getClass().getSimpleName() + ">> initializing.");
		initialize(brokerURL, queue);
		System.out.println(getClass().getSimpleName() + ">> initialized.");
	}
	
	/**
	 * Initialize the producer agent with the specified broker parameters: the broker url and the queue.
	 * @param brokerURL
	 * @param queue
	 * @throws IOException
	 * @throws JMSException
	 */
	private void initialize(String brokerURL, String queue) throws IOException, JMSException {
		// Create a ConnectionFactory
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
		
        // Create a Connection
        connection = connectionFactory.createConnection();
        connection.start();
		
        // Create a Session
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
        // Create the destination (Topic or Queue)
        Destination destination = session.createQueue(queue);
        
        // Create a MessageProducer from the Session to the Topic or Queue
        producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
	}

	/**
	 * Send a message to the queue with the object as content.
	 * @param object		Serializable object.
	 * @throws JMSException
	 */
	public void send(Serializable object) throws JMSException {
		ObjectMessage message = session.createObjectMessage(object);
        producer.send(message);		
	}
	
	/**
	 * Closes the producer, the session, and the connection resources.
	 * @throws JMSException
	 */
	public void cleanUp() throws JMSException {
		producer.close();
		session.close();
        connection.close();
	}
}
