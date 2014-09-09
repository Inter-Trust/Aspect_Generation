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
			System.out.println("    java -jar SAKProducer.jar <AMQPconfig.properties> <SAKfile.xml>");
			System.exit(0);
		}
		String propertiesFilename = args[0];
		String sakFilename = args[1];
		
		try {
			File sakFile = new File(sakFilename);
			//AdaptationPlan sap = XMLUtils.read(sapFile, AdaptationPlan.class);
			
			ProducerAMQPConfiguration configAG = new ProducerAMQPConfiguration(propertiesFilename);
			ActiveMQProducer producer = new ActiveMQProducer(configAG.getSAKBrokerURL(), configAG.getSAKQueue());
			
			//String content = SerializationUtils.objectToString((Serializable) sap);
			String content = XMLUtils.readFile(sakFile);
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
