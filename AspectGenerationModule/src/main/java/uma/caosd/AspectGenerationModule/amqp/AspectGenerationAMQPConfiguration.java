package uma.caosd.AspectGenerationModule.amqp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AspectGenerationAMQPConfiguration {
	public static final String BROKER_URL_SDS = "brokerURL.sds";
	public static final String QUEUE_SDS = "queue.sds";
	public static final String BROKER_URL_SAK = "brokerURL.sak";
	public static final String QUEUE_SAK = "queue.sak";
	public static final String BROKER_URL_SAP = "brokerURL.sap";
	public static final String QUEUE_SAP = "queue.sap";
	public static final String BROKER_URL_ERRORS = "brokerURL.errors";
	public static final String QUEUE_ERRORS = "queue.errors";
	
	private Properties properties;
	
	public AspectGenerationAMQPConfiguration(String propertiesFilename) throws IOException {
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
	
	public String getSAKBrokerURL() {
		return properties.getProperty(BROKER_URL_SAK);
	}
	
	public String getSAKQueue() {
		return properties.getProperty(QUEUE_SAK);
	}
	
	public String getSAPBrokerURL() {
		return properties.getProperty(BROKER_URL_SAP);
	}
	
	public String getSAPQueue() {
		return properties.getProperty(QUEUE_SAP);
	}
	
	public String getErrorsBrokerURL() {
		return properties.getProperty(BROKER_URL_ERRORS);
	}
	
	public String getErrorsQueue() {
		return properties.getProperty(QUEUE_ERRORS);
	}
}
