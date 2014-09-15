package uma.caosd.errorHandling;

import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import uma.caosd.errors.DeploymentStatus;
import uma.caosd.errors.Error;
import uma.caosd.errors.Errors;
import uma.caosd.errors.Module;
import uma.caosd.errors.Status;
import uma.caosd.errors.Type;

public class DeploymentStatusSingleton {
	private static DeploymentStatus status = null;
	private static DeploymentStatusSingleton s = null;
	private boolean errors;
	
	private DeploymentStatusSingleton() {
		errors = false;
		status = new DeploymentStatus();
	}
	
	public static DeploymentStatusSingleton getStatus() {
		if (s == null) {
			s = new DeploymentStatusSingleton();
		}
		return s;
	}
	
	public Errors getErrors() {
		return status.getErrors();
	}
	
	public void addError(String description, Module module, Type type) {
		errors = true;
		Errors errors = status.getErrors();
		if (errors == null) {
			errors = new Errors();
		}
		Error e = new Error();
		e.setDescription(description);
		e.setModule(module);
		e.setType(type);
		
		try {	// Provide date
			GregorianCalendar gcal = new GregorianCalendar();
			XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
			e.setDate(date);
		} catch (DatatypeConfigurationException e1) {
			// Not provide date.
		}
		
		errors.getError().add(e);
		status.setErrors(errors);
	}
	
	public void completeStatus() {
		if (status.getErrors() == null) {
			status.setStatus(Status.OK);
		} else {
			status.setStatus(Status.ERROR);
		}
	}
	
	public boolean hasErrors() {
		return status.getStatus() == Status.ERROR;
	}
	
	public void clear() {
		errors = false;
		status = new DeploymentStatus();
	}
	
	public DeploymentStatus getFinalStatus() {
		return status;
	}
}
