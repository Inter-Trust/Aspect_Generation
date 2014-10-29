package uma.caosd.AspectGenerationModule.producer.main;

import java.io.File;
import java.io.IOException;

import javax.jms.JMSException;

import uma.caosd.amqp.activemq.ActiveMQProducer;
import uma.caosd.amqp.utils.XMLUtils;

public class MainProducerTesting {
	public static final String ASPECT_GENERATION_AMQP_PROPERTIES_FILENAME = "files" + File.separator + "AMQPconfigProducerSDS.properties";
	public static final String SDS_FILE_EXAMPLE = "files" + File.separator + "SDSexampleSpringAOP.xml";
	
	public static void main(String[] args) {
		String propertiesFilename = ASPECT_GENERATION_AMQP_PROPERTIES_FILENAME;
		String sdsFilename = SDS_FILE_EXAMPLE;
		
		try {
			File sdsFile = new File(sdsFilename);
			//AdaptationPlan sap = XMLUtils.read(sapFile, AdaptationPlan.class);
			
			ProducerAMQPConfiguration configAG = new ProducerAMQPConfiguration(propertiesFilename);
			ActiveMQProducer producer = new ActiveMQProducer(configAG.getSDSBrokerURL(), configAG.getSDSQueue());
			
			//String content = SerializationUtils.objectToString((Serializable) sap);
			String content = XMLUtils.readFile(sdsFile);
			producer.send(content);
			System.out.println(MainProducerTesting.class.getSimpleName() + ">> SDS sent.");
			
			producer.cleanUp();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
