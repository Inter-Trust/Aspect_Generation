package uma.caosd.AspectGenerationModule.main;

import java.io.File;
import java.io.IOException;

import uma.caosd.AspectGenerationModule.AspectGeneration;
import uma.caosd.AspectGenerationModule.amqp.AspectGenerationAMQPConfiguration;

public class Main {
	public static final String ASPECT_GENERATION_AMQP_PROPERTIES_FILENAME = "/AMQPconfigAG.properties";

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Error! Wrong arguments number. Use:");
			System.out.println("    java -jar AspectGeneration.jar <AMQPconfig.properties.> <aspectualKnowledge.xml>");
			System.exit(0);
		}
		String propertiesFilename = args[0];
		String SAKFilename = args[1];
		
		try {
			File SAKFile = new File(SAKFilename);
			AspectGenerationAMQPConfiguration configAG = new AspectGenerationAMQPConfiguration(propertiesFilename);
			AspectGeneration aspectGeneration = new AspectGeneration(configAG, SAKFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
