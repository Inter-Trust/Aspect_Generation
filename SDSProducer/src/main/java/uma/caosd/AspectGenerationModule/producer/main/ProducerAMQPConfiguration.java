package uma.caosd.AspectGenerationModule.producer.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProducerAMQPConfiguration {
	public static final String BROKER_URL_SDS = "brokerURL.sds";
	public static final String QUEUE_SDS = "queue.sds";
	
	private Properties properties;
	
	public ProducerAMQPConfiguration(String propertiesFilename) throws IOException {
		// Load properties file
		File propertiesFile = new File(propertiesFilename);
		InputStream isPropertiesFile = new FileInputStream(propertiesFile);
		properties = new Properties();
		properties.load(isPropertiesFile);
	}
	
	public String getSDSBrokerURL() {
		return properties.getProperty(BROKER_URL_SDS);
	}
	
	public String getSDSQueue() {
		return properties.getProperty(QUEUE_SDS);
	}
}
