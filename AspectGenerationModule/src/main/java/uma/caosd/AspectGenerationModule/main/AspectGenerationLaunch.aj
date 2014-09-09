package uma.caosd.AspectGenerationModule.main;

import java.io.File;
import java.net.URL;

public abstract aspect AspectGenerationLaunch {
	abstract public pointcut initialize();
	
	after(): initialize() {
		/*try {
			AspectGenerationAMQPConfiguration configAG = new AspectGenerationAMQPConfiguration(getFileFromURL(getAMQPBrokerPropertiesFile()).getPath());
			AspectGeneration aspectGeneration = new AspectGeneration(configAG, getFileFromURL(getInitialAspectualKnowledgeFile()));
			System.out.println("AspectGenerationModule>>initialized.");
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		File propertiesFile = getFileFromURL(getAMQPBrokerPropertiesFile());
		File sakFile = getFileFromURL(getInitialAspectualKnowledgeFile());
		QueueThread t = new QueueThread(propertiesFile, sakFile);
		t.start();
	}
	
	abstract public URL getAMQPBrokerPropertiesFile();
	
	abstract public URL getInitialAspectualKnowledgeFile();
	
	private File getFileFromURL(URL url) {
		return new File(url.getPath());
	}
}
