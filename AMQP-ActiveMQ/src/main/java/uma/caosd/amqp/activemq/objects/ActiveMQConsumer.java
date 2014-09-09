package uma.caosd.amqp.activemq.objects;

import java.io.IOException;
import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * ActiveMQ consumer agent.
 * Listens for a message on a queue and executes the onMessageReceived method.
 * 
 * @author UMA
 *
 */
public abstract class ActiveMQConsumer implements ExceptionListener {
	private Connection connection;
	private Session session;
	private MessageConsumer consumer;
	
	/**
	 * Create a new consumer agent initializing it with with the specified broker parameters: the broker url and the queue.
	 * @param brokerURL
	 * @param queue
	 * @throws IOException
	 * @throws JMSException
	 */
	public ActiveMQConsumer(String brokerURL, String queue) throws IOException, JMSException {
		System.out.println(getClass().getSimpleName() + ">> initializing.");
		initialize(brokerURL, queue);
		System.out.println(getClass().getSimpleName() + ">> initialized.");
	}
	
	/**
	 * Initialize the consumer agent with the specified broker parameters: the broker url and the queue. 
	 * @param brokerURL
	 * @param queue
	 * @throws IOException
	 * @throws JMSException
	 */
	private void initialize(String brokerURL, String queue) throws IOException, JMSException {
		// Create a ConnectionFactory
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
		
        // Create a Connection
        connection = (ActiveMQConnection) connectionFactory.createConnection();
        connection.start();
		
        // Create a Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
        // Create the destination (Topic or Queue)
        Destination destination = session.createQueue(queue);
		
        // Create a MessageConsumer from the Session to the Topic or Queue
        consumer = session.createConsumer(destination);

        consumer.setMessageListener(new MessageListener() {
            public void onMessage(final Message message) {
            	try {
            		if (message instanceof ObjectMessage) {
            			ObjectMessage objectMessage = (ObjectMessage)message;
            			onMessageReceived(objectMessage.getObject());
            		} else {
            			System.out.println(getClass().getSimpleName() + ">> received a strange message: " + message.getClass().getName());
            		}
            	} catch (JMSException e) {
            		e.printStackTrace();
            	} catch (Exception e) {
            		e.printStackTrace();
            	}
            }
        });
	}
	
	/**
	 * Action to be performed when an object is received.
	 * @param o	Object.
	 */
	abstract protected void onMessageReceived(Serializable object);
	
	/**
	 * Closes the consumer, the session, and the connection resources.	
	 * @throws JMSException
	 */
	public void cleanUp() throws JMSException {
		consumer.close();
        session.close();
        connection.close();
	}

	@Override
	public void onException(JMSException arg) {
		 System.out.println("JMS Exception occured.  Shutting down client.");
	}
}
