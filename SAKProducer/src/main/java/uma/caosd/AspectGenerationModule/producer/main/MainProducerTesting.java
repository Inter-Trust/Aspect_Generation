package uma.caosd.AspectGenerationModule.producer.main;

import java.io.File;
import java.io.IOException;

import javax.jms.JMSException;

import uma.caosd.amqp.activemq.ActiveMQProducer;
import uma.caosd.amqp.utils.XMLUtils;

public class MainProducerTesting {
	public static final String ASPECT_GENERATION_AMQP_PROPERTIES_FILENAME = "files" + File.separator + "AMQPconfigProducerSAK.properties";
	public static final String SAK_FILE_EXAMPLE = "files" + File.separator + "sakUpdate.xml";
	
	public static void main(String[] args) {
		String propertiesFilename = ASPECT_GENERATION_AMQP_PROPERTIES_FILENAME;
		String sakFilename = SAK_FILE_EXAMPLE;
		
		try {
			File sakFile = new File(sakFilename);
			//AdaptationPlan sap = XMLUtils.read(sapFile, AdaptationPlan.class);
			
			ProducerAMQPConfiguration configAG = new ProducerAMQPConfiguration(propertiesFilename);
			ActiveMQProducer producer = new ActiveMQProducer(configAG.getSAKBrokerURL(), configAG.getSAKQueue());
			
			//String content = SerializationUtils.objectToString((Serializable) sap);
			String content = XMLUtils.readFile(sakFile);
			producer.send(content);
			System.out.println(MainProducerTesting.class.getSimpleName() + ">> SAK sent.");
			
			producer.cleanUp();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
