package intertrust.modules.ag.generic;

import intertrust.modules.ag.generic.SAKClasses.Advice;
import intertrust.modules.ag.generic.SAKClasses.Aspect;
import intertrust.modules.ag.generic.SAKClasses.CandidateAspect;
import intertrust.modules.ag.generic.SAKClasses.Category;
import intertrust.modules.ag.generic.SAKClasses.Deploy;
import intertrust.modules.ag.generic.SAKClasses.Functionality;
import intertrust.modules.ag.generic.SAKClasses.ListAF;
import intertrust.modules.ag.generic.SAKClasses.MappingSCA;
import intertrust.modules.ag.generic.SAKClasses.Sds;
import intertrust.modules.ag.generic.SAKClasses.SecurityConcept;
import intertrust.utils.XMLFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This component represents a mapping table (ListAF and MappingSCA) with the information needed to relate 
 * the different security policies of an application with the aspects 
 * that need to be dynamically incorporated to the application 
 * in order to satisfy each negotiated security policy.
 * 
 * @author UMA
 * @date   12/09/2013
 *
 */
public class MappingPolicyAspects {

	//public static final String LAF_FILENAME = ".\\xmlSchemas\\securityAspectualKnowledge\\listAspectFunctionality.xml";
	//public static final String MSCA_FILENAME = ".\\xmlSchemas\\securityAspectualKnowledge\\mappingSecurityConceptAspect.xml";
	//public static final String MSCA_FILENAME = "." + File.separator + "xmlSchemas" + File.separator + "securityAspectualKnowledge" + File.separator + "mappingSecurityConceptAspect.xml";
	public static final String MSCA_FILENAME = "mappingSecurityConceptAspect";
	
	private Sds sds;			// Security Deployment Specification
	private ListAF laf;			// List of Aspect and their Functionality
	private MappingSCA msca;	// Mapping Security Concept Aspect

	private File sdsFile;
	private File lafFile;
	private File mscaFile;
	
	private List<SecurityConcept> newAspectsRequired;	// security concepts that don't have a mapped aspect.
	
	public MappingPolicyAspects(File sdsFile, File lafFile) {
		newAspectsRequired = new ArrayList<SecurityConcept>();
		provideKnowledge(sdsFile, lafFile);
	}

	public void provideKnowledge(File sdsFile, File lafFile) {
		sds = XMLFile.read(sdsFile, Sds.class);
		laf = XMLFile.read(lafFile, ListAF.class);
		msca = generateMappingSecurityConceptAspect(sds, laf);
		
		this.sdsFile = sdsFile;
		this.lafFile = lafFile;
		//this.mscaFile = writeMscaFile(msca, MSCA_FILENAME);	
		this.mscaFile = XMLFile.writeTemp(MSCA_FILENAME, msca, MappingSCA.class);
	}
	
	public Sds getSecurityDeploymentSpecification() {
		return sds;
	}
	
	public File getSecurityDeploymentSpecificationFile() {
		return sdsFile;
	}
	
	public ListAF getListAspectFunctionality() {
		return laf;
	}
	
	public MappingSCA getMappingSecurityConceptAspect() {
		return msca;
	}
	
	public File getListAspectFunctionalityFile() {
		return lafFile;
	}

	public File getMappingSecurityConceptAspectFile() {
		return mscaFile;
	}
		
	public void updateSecurityDeploymentSpecification(File sds) {
		sdsFile = sds;
		this.sds = XMLFile.read(sdsFile, Sds.class);
		msca = updateMappingSecurityConceptAspect(msca, this.sds, laf);
		this.mscaFile = XMLFile.writeTemp(MSCA_FILENAME, msca, MappingSCA.class);
	}
		
	private MappingSCA generateMappingSecurityConceptAspect(Sds sds, ListAF laf) {
		newAspectsRequired.clear();
		Deploy deploySDS = sds.getDeploy();
		
		List<SecurityConcept> scs = new ArrayList<SecurityConcept>();
		
		for (Category c : deploySDS.getCategory()) {
			for (SecurityConcept sc : c.getSecurityConcept()) {
				List<Aspect> aspects = searchCandidateAspects(laf.getAspect(), sc.getFunctionality());
				
				if (aspects.isEmpty()) {
					newAspectsRequired.add(sc);
				} else {
					SecurityConcept newSC = new SecurityConcept();
					newSC.setId(sc.getId());
					for (Aspect a : aspects) {
						Aspect aspect = new Aspect();
						aspect.setId(a.getId());
						aspect.setName(a.getName());
					
						CandidateAspect ca = new CandidateAspect();
						ca.getAspect().add(aspect);
						newSC.getCandidateAspect().add(ca);	
					}
					scs.add(newSC);
				}
			}
		}
		MappingSCA msca = new MappingSCA();
		msca.getSecurityConcept().addAll(scs);
		
		return msca;
	}
	
	/**
	 * Busca todos los aspectos que cumplan todas las funcionalidades indicadas.
	 * 
	 * @param aspects
	 * @param functionalities
	 * @return
	 */
	private List<Aspect> searchCandidateAspects(List<Aspect> aspects, List<Functionality> functionalities) {
		List<Aspect> candidateAspects = new ArrayList<Aspect>();
		
		for (Aspect a : aspects) {
			if (isCandidate(a, functionalities)) {
				candidateAspects.add(a);
			}
		}
		return candidateAspects;
	}

	/**
	 * Comprueba si un aspecto tiene todas las funcionalidades indicadas.
	 * 
	 * @param a
	 * @param functionalities
	 * @return
	 */
	private boolean isCandidate(Aspect a, List<Functionality> functionalities) {
		boolean res = true;
		
		Iterator<Functionality> iterF = functionalities.iterator();		
		while (res && iterF.hasNext()) {
			Functionality f = iterF.next();
			res = isFulfilledFunctinality(f, a);
		}
		return res;
	}


	/**
	 * Comprueba si un aspecto tiene la funcionalidad indicada.
	 * 
	 * @param functionality
	 * @param a
	 * @return
	 */
	private boolean isFulfilledFunctinality(Functionality functionality, Aspect a) {
		String id = functionality.getId();
		for (Advice adv : a.getAdvice()) {
			for (Functionality f : adv.getFunctionality()) {
				if (f.getId().equals(id)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private MappingSCA updateMappingSecurityConceptAspect(MappingSCA msca, Sds sds, ListAF laf) {
		MappingSCA resMsca = msca;
		MappingSCA newMsca = generateMappingSecurityConceptAspect(sds, laf);
		
		for (SecurityConcept newSC : newMsca.getSecurityConcept()) {
			Iterator<SecurityConcept> iter = msca.getSecurityConcept().iterator();
			boolean isInMsca = false;
			while (!isInMsca && iter.hasNext()) {
				SecurityConcept sc = iter.next();
				isInMsca = sc.getId().equals(newSC.getId());
			}
			
			if (!isInMsca) {
				resMsca.getSecurityConcept().add(newSC);
			}
		}
		return resMsca;
	}

	public void updateKnowledge(SecurityConcept sc, String aspectName) {
		Aspect aspect = new Aspect();
		aspect.setId(String.valueOf(laf.getAspect().size()));
		aspect.setName(aspectName);
		
		Advice advice = new Advice();
		advice.setId("1");
		advice.setName(aspectName);
		for (Functionality f : sc.getFunctionality()) {
			advice.getFunctionality().add(f);
		}
		aspect.getAdvice().add(advice);
		
		laf.getAspect().add(aspect);
		msca = updateMappingSecurityConceptAspect(msca, sds, laf);	
	}

	public List<SecurityConcept> getNewAspectsRequired() {
		return newAspectsRequired;
	}
}
