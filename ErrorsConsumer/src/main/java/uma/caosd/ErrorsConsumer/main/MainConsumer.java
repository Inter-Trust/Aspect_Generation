package uma.caosd.ErrorsConsumer.main;

import java.io.IOException;

import javax.jms.JMSException;


public class MainConsumer {
	public static final String BROKER_URL_ERRORS = "tcp://localhost:61616";
	public static final String QUEUE_ERRORS = "errorsQueue";
	
	public static void main(String[] args) {
		try {
			ErrorsAMQPConsumer consumerAMQP = new ErrorsAMQPConsumer(BROKER_URL_ERRORS, QUEUE_ERRORS);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
