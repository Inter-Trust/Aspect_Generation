package uma.caosd.AspectGenerationModule.producer.main;

import java.io.File;
import java.io.IOException;

import javax.jms.JMSException;

import uma.caosd.amqp.activemq.ActiveMQProducer;
import uma.caosd.amqp.utils.XMLUtils;

public class MainProducer {
	
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Error! Wrong arguments number. Use:");
			System.out.println("    java -jar SDSProducer.jar <AMQPconfig.properties> <SDSfile.xml>");
			System.exit(0);
		}
		String propertiesFilename = args[0];
		String sdsFilename = args[1];
		
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
