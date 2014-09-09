package uma.caosd.AspectGenerationModule.main;

import java.io.File;
import java.io.IOException;

import uma.caosd.AspectGenerationModule.AspectGeneration;
import uma.caosd.AspectGenerationModule.amqp.AspectGenerationAMQPConfiguration;

public class MainTesting {
	public static final String ASPECT_GENERATION_AMQP_PROPERTIES_FILENAME = "files" + File.separator + "AMQPconfigAG.properties";
	public static final String INITIAL_ASPECTUAL_KNOWLEDGE_FILENAME = "files" + File.separator + "sak.xml";

	public static void main(String[] args) {
		String propertiesFilename = ASPECT_GENERATION_AMQP_PROPERTIES_FILENAME;
		String SAKFilename = INITIAL_ASPECTUAL_KNOWLEDGE_FILENAME;
		
		try {
			File SAKFile = new File(SAKFilename);
			AspectGenerationAMQPConfiguration configAG = new AspectGenerationAMQPConfiguration(propertiesFilename);
			AspectGeneration aspectGeneration = new AspectGeneration(configAG, SAKFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
