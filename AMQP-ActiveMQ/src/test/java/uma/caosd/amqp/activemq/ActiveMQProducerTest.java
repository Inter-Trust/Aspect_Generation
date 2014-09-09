package uma.caosd.amqp.activemq;

import uma.caosd.amqp.activemq.ActiveMQProducer;

public class ActiveMQProducerTest {
	
    public static void main(String[] args) throws Exception {
    	String m = "Mi mensaje";
    	ActiveMQProducer producer = new ActiveMQProducer("tcp://localhost:61616", "queue.test");
    	producer.send(m);
    }
}
