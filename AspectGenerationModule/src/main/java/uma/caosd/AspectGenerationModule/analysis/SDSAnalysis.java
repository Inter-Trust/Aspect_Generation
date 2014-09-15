package uma.caosd.AspectGenerationModule.analysis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uma.caosd.AspectGenerationModule.exceptions.AnalysisException;
import uma.caosd.SecurityDeploymentSpecification.Category;
import uma.caosd.SecurityDeploymentSpecification.Deploy;
import uma.caosd.SecurityDeploymentSpecification.Functionality;
import uma.caosd.SecurityDeploymentSpecification.Sds;
import uma.caosd.SecurityDeploymentSpecification.SecurityFeature;
import uma.caosd.SecurityDeploymentSpecification.Target;
import uma.caosd.SecurityDeploymentSpecification.Undeploy;
import uma.caosd.SecurityDeploymentSpecification.UndeploySecurityFeature;
import uma.caosd.errorHandling.DeploymentStatusSingleton;
import uma.caosd.errors.Module;
import uma.caosd.errors.Type;

/**
 * Analyses a Security Deployment Specification (SDS) file.
 * 
 * @author UMA
 * @date 22/04/2014
 *
 */
public class SDSAnalysis {
	private Sds sds;
	
	/**
	 * Initialize a new analysis of a SDS file.
	 * @param sdsFile	SDS file.
	 */
	/*public SDSAnalysis(File sdsFile) {
		this.sds = XMLFile.read(sdsFile, Sds.class);
	}*/
	
	/**
	 * Initialize a new analysis of a SDS file.
	 * @param sdsFile	SDS file.
	 */
	public SDSAnalysis(Sds sds) {
		this.sds = sds;
	}
	
	/**
	 * Get all the categories identifiers of the security concepts to be deployed in the SDS.
	 * @return	Categories identifiers.
	 */
	public Set<String> getCategoriesID() {
		HashSet<String> res = new HashSet<String>();
		for (Category c : getCategories())
			res.add(c.getId());
		return res;
	}
	
	/**
	 * Get all the security concepts identifiers to be deployed in the SDS.
	 * @return	Categories identifiers.
	 */
	public Set<String> getSecurityConceptToBeDeployed() {
		List<SecurityFeature> securityConcepts = getSecurityConcepts();
		HashSet<String> res = new HashSet<String>();
		for (SecurityFeature sc : securityConcepts)
			res.add(sc.getId());
		return res;
	}
	
	/**
	 * Get all the security concepts identifiers to be undeployed in the SDS.
	 * @return	Categories identifiers.
	 */
	public Set<String> getSecurityConceptToBeUnDeployed() {
		HashSet<String> res = new HashSet<String>();
		Undeploy undeploy = sds.getUndeploy();
		if (undeploy != null) {
			for (UndeploySecurityFeature usc : undeploy.getUndeploySecurityFeature()) {
				res.add(usc.getId());
			}
		}
		return res;
	}
	
	/**
	 * Get all the functionalities identifiers of a security concept to be deployed in the SDS. 
	 * @param sc					Security concept identifier.
	 * @return						Functionalities identifiers.
	 * @throws AnalysisException	There is not any security concept with the specified identifier.
	 */
	public Set<String> getFunctionalitiesID(String securityConceptID) throws AnalysisException {
		SecurityFeature sc = getSecurityConcept(securityConceptID);
		HashSet<String> res = new HashSet<String>();
		for (Functionality f : sc.getFunctionality())
			res.add(f.getId());
		return res;
	}
	
	/**
	 * Get the target identifier of a security concept to be deployed in the SDS.
	 * @param sc					Security concept identifier.
	 * @return						Target identifier.
	 * @throws AnalysisException 	There is not any security concept with the specified identifier, or
	 * 								there is not 'target' for the specified security concept.
	 */
	public String getTargetID(String securityConceptID) throws AnalysisException {
		SecurityFeature sc = getSecurityConcept(securityConceptID);
		Target target = sc.getTarget();
		if (target == null) {
			// Error -> description,
			String desc = "There is not 'target' for security concept with ID '" + securityConceptID + "'.";
			DeploymentStatusSingleton.getStatus().addError(desc, Module.ASPECT_GENERATION, Type.SDS);
			throw new AnalysisException(desc);
		}
		return target.getId();
	}

	/**
	 * Get all the categories of the security concepts to be deployed in the SDS.
	 * @return	Categories.
	 */
	public List<Category> getCategories() {
		List<Category> res = new ArrayList<Category>();
		Deploy deploy = sds.getDeploy();
		if (deploy != null)
			res = deploy.getCategory();
		return res;
	}
	
	/**
	 * Get all the security concepts in the SDS.
	 * @return	Security concepts.
	 */
	public List<SecurityFeature> getSecurityConcepts() {
		List<SecurityFeature> res = new ArrayList<SecurityFeature>();
		for (Category c : getCategories())
			res.addAll(c.getSecurityFeature());
		return res;
	}
	
	/**
	 * Get a security concept from its identifiers.
	 * @param id					Identifier.
	 * @return						Security concept.
	 * @throws AnalysisException 	There is not any security concept with the specified identifier.
	 */
	public SecurityFeature getSecurityConcept(String id) throws AnalysisException {
		for (SecurityFeature sc : getSecurityConcepts())
			if (sc.getId().equals(id))
				return sc;
		String desc = "Security concept with ID '" + id + "' does not exist.";
		DeploymentStatusSingleton.getStatus().addError(desc, Module.ASPECT_GENERATION, Type.SDS);
		throw new AnalysisException(desc);
	}
	
	/**
	 * Get the configuration for the security concept with the identifier provided.
	 * @param id					Security concept identifier.
	 * @return						Configuration of the security concept.
	 * @throws AnalysisException 	There is not any security concept with the specified identifier.
	 */
	public uma.caosd.SecurityDeploymentSpecification.Configuration getSecurityConceptConfiguration(String id) throws AnalysisException {
		SecurityFeature sc = getSecurityConcept(id);
		return sc.getConfiguration();
	}
	
	/**
	 * !!! IMPORTANT !!!! NEED TO BE UPDATED WITH THE IDENTIFIER OR THE USER.
	 * Get the instance identifier (user/role/context/... identitier) associated with the SDS file.
	 * @return	Instance identifier.
	 */
	public String getInstanceID() {
		String id = null;
		/*if (sds.getInstance() != null) {
			id = sds.getInstance().getId();
		}*/
		return id;
	}
}
