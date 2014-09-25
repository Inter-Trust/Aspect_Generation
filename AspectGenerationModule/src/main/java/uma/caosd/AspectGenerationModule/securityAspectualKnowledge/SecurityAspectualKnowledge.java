package uma.caosd.AspectGenerationModule.securityAspectualKnowledge;

import java.io.File;

import uma.caosd.AspectGenerationModule.SAKAnalysis;
import uma.caosd.AspectualKnowledge.AspectualKnowledge;
import uma.caosd.AspectualKnowledge.Configuration;
import uma.caosd.AspectualKnowledge.Instance;
import uma.caosd.amqp.utils.XMLUtils;

public class SecurityAspectualKnowledge {
	private AspectualKnowledge sak;
	//private SecurityConfigurations securityConfigurations;
	private Configuration securityConfiguration;
	private SAKAnalysis sakAnalysis;
	
	public SecurityAspectualKnowledge(File sakFile) {
		sak = XMLUtils.read(sakFile, AspectualKnowledge.class);
		sakAnalysis = new SAKAnalysis(sak);
		securityConfiguration = new Configuration();
		//securityConfigurations = new SecurityConfigurations();
	}
	
	public SAKAnalysis getSAKAnalysis() {
		return sakAnalysis;
	}
	
	public AspectualKnowledge getAspectualKnowledge() {
		return sak;
	}
	/*
	public SecurityConfigurations getSecurityConfigurations() {
		return securityConfigurations;
	}*/
	
	public Configuration getSecurityConfiguration() {
		return securityConfiguration;
	}
	
	public void updateSecurityConfigurations(Configuration currentConfiguration) {
		//Instance instance = currentConfiguration.getInstance();
		//String id = instance.getId();
		//securityConfigurations.updateConfiguration(id, currentConfiguration);
		securityConfiguration = currentConfiguration;
	}
	
	public void updateAspectualKnowledge(AspectualKnowledge sak) {
		SAKUpdate.updateAspectualKnowledge(sakAnalysis, this.sak, sak);
		//sak = XMLFile.read(sakFile, AspectualKnowledge.class);
		//sakAnalysis = new SAKAnalysis(sak);
	}
	
	/*
	public File getAspectualKnowledgeFile() {
		return XMLFile.writeTemp("sak", sak, AspectualKnowledge.class);
	}*/
}
