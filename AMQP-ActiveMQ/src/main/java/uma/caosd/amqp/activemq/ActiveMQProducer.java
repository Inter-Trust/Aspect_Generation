package uma.caosd.amqp.activemq;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

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
		System.out.println(getClass().getSimpleName() + ">> connected to queue/topic " + queue + " in server " + brokerURL);
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
        //Destination destination = session.createQueue(queue);
        Destination destination = session.createTopic(queue);
        
        // Create a MessageProducer from the Session to the Topic or Queue
        producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
	}

	/**
	 * Send a message to the queue with the content specified.
	 * @param content			Content of the message.
	 * @throws JMSException
	 */
	public void send(String content) throws JMSException {
		TextMessage message = session.createTextMessage(content);
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
