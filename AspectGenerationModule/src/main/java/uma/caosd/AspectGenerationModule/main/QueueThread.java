package uma.caosd.AspectGenerationModule.main;

import java.io.File;
import java.io.IOException;

import uma.caosd.AspectGenerationModule.AspectGeneration;
import uma.caosd.AspectGenerationModule.amqp.AspectGenerationAMQPConfiguration;

public class QueueThread extends Thread {
	private File propertiesFile;
	private File sakFile;
	
	public QueueThread(File propertiesFile, File sakFile) {
		this.propertiesFile = propertiesFile;
		this.sakFile = sakFile;
	}

	public void run() {
		try {
			AspectGenerationAMQPConfiguration configAG = new AspectGenerationAMQPConfiguration(propertiesFile.getPath());
			AspectGeneration aspectGeneration = new AspectGeneration(configAG, sakFile);
			System.out.println("AspectGenerationModule>>initialized.");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
