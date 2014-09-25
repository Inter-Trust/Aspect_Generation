package uma.caosd.AspectGenerationModule;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.jms.JMSException;

import uma.caosd.AspectGenerationModule.amqp.AspectGenerationAMQPConfiguration;
import uma.caosd.AspectGenerationModule.amqp.SAKAspectGenerationAMQPConsumer;
import uma.caosd.AspectGenerationModule.amqp.SDSAspectGenerationAMQPConsumer;
import uma.caosd.AspectGenerationModule.interfaces.AdaptationRequest;
import uma.caosd.AspectGenerationModule.interfaces.KnowledgeProvision;
import uma.caosd.AspectGenerationModule.securityAspectualKnowledge.SAKUpdate;
import uma.caosd.AspectGenerationModule.securityAspectualKnowledge.SecurityAspectualKnowledge;
import uma.caosd.AspectualKnowledge.AdaptationPlan;
import uma.caosd.AspectualKnowledge.AspectualKnowledge;
import uma.caosd.SecurityDeploymentSpecification.Sds;
import uma.caosd.amqp.activemq.ActiveMQProducer;
import uma.caosd.amqp.utils.XMLUtils;
import uma.caosd.errorHandling.DeploymentStatusSingleton;
import uma.caosd.errors.DeploymentStatus;

public class AspectGeneration implements AdaptationRequest, KnowledgeProvision {
	private SecurityAspectualKnowledge knowledge;
	private GenericAspectGeneration genericAspectGeneration;
	private ConcreteAspectGeneration concreteAspectGeneration;
	private AdaptationPlan adaptationPlan;
	private ActiveMQProducer SAPproducerAMQP;
	private SDSAspectGenerationAMQPConsumer SDSconsumerAMQP;
	private SAKAspectGenerationAMQPConsumer SAKconsumerAMQP;
	private AspectGenerationAMQPConfiguration configAG;
	private ActiveMQProducer producerAMQPErrors;
	
	public AspectGeneration(AspectGenerationAMQPConfiguration configAG, File sakFile) {
		System.out.println(getClass().getSimpleName() + ">> initializing...");
		this.configAG = configAG;
		knowledge = new SecurityAspectualKnowledge(sakFile);
		genericAspectGeneration = new GenericAspectGeneration(knowledge);
		concreteAspectGeneration = new ConcreteAspectGeneration(knowledge);
		
		try {
			SDSconsumerAMQP = new SDSAspectGenerationAMQPConsumer(configAG.getSDSBrokerURL(), configAG.getSDSQueue(), this);
			SAKconsumerAMQP = new SAKAspectGenerationAMQPConsumer(configAG.getSAKBrokerURL(), configAG.getSAKQueue(), this);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		System.out.println(getClass().getSimpleName() + ">> ready.");
	}
	/*
	private void provideInitialSecurityDeploymentSpecification(Sds sds) {
		updateSecurityDeploymentSpecification(sds);
	}*/
	
	public void updateSecurityDeploymentSpecification(Sds sds) {
		AdaptationPlan concreteSAP = null;
		
		AdaptationPlan genericSAP = genericAspectGeneration.adapts(sds);
		if (genericSAP != null) {
			concreteSAP = concreteAspectGeneration.adaptsAdaptationPlan(genericSAP);
			this.adaptationPlan = concreteSAP;
			System.out.println(getClass().getSimpleName() + ">>Security deployment specification (SDS) updated.");
		}
		
		//DeploymentStatusSingleton.getStatus().completeStatus();
		//sendToErrorsAMQP(DeploymentStatusSingleton.getStatus().getFinalStatus());
		
		DeploymentStatusSingleton.getStatus().completeStatus();
		if (DeploymentStatusSingleton.getStatus().hasErrors()) {
			sendToErrorsAMQP(DeploymentStatusSingleton.getStatus().getFinalStatus());
			DeploymentStatusSingleton.getStatus().clear();
		}
		
		if (concreteSAP != null) {
			sendToAMQP(concreteSAP);	
		}
	}
	
	private void sendToAMQP(AdaptationPlan sap) {
		System.out.println(getClass().getSimpleName() + ">> sending new security adaptation plan (SAP) ...");
		try {
			SAPproducerAMQP = new ActiveMQProducer(configAG.getSAPBrokerURL(), configAG.getSAPQueue());
			//String content = SerializationUtils.objectToString(sap);
			String content = XMLUtils.write(sap, AdaptationPlan.class);
			XMLUtils.writeTemp("sapAG", sap, AdaptationPlan.class);
			SAPproducerAMQP.send(content);
			SAPproducerAMQP.cleanUp();
			System.out.println(getClass().getSimpleName() + ">> new security adaptation plan (SAP) sent.");
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	private void sendToErrorsAMQP(DeploymentStatus errors) {
		System.out.println(getClass().getSimpleName() + ">> sending deployment status with errors ...");
		try {
			producerAMQPErrors = new ActiveMQProducer(configAG.getErrorsBrokerURL(), configAG.getErrorsQueue());
			//String content = SerializationUtils.objectToString(sap);
			String content = XMLUtils.write(errors, DeploymentStatus.class);
			XMLUtils.writeTemp("errorsStatus", errors, DeploymentStatus.class);
			producerAMQPErrors.send(content);
			producerAMQPErrors.cleanUp();
			System.out.println(getClass().getSimpleName() + ">> deployment status with errors sent.");
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public void updateAspectualKnowledge(AspectualKnowledge sak) {
		SAKUpdate.updateAspectualKnowledge(knowledge.getSAKAnalysis(), knowledge.getAspectualKnowledge(), sak);
	}
	
	/*public File getAspectualKnowledgeFile() {
		return knowledge.getAspectualKnowledgeFile();
	}*/
	
	public Set<String> getSecurityConceptsWithoutAdvisors() {
		return genericAspectGeneration.getSecurityConceptsWithoutAdvisors();
	}
	/*
	public File getAdaptationPlanFile() {
		return XMLFile.writeTemp("adaptationPlan", adaptationPlan, AdaptationPlan.class);
	}*/
}
