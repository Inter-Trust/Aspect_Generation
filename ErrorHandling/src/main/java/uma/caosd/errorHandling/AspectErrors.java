package uma.caosd.errorHandling;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import uma.caosd.errorHandling.xmlClasses.Alarm;
import uma.caosd.errorHandling.xmlClasses.Alarms;
import uma.caosd.errorHandling.xmlClasses.Element;
import uma.caosd.errorHandling.xmlClasses.Elements;
import uma.caosd.errorHandling.xmlClasses.Param;
import uma.caosd.errorHandling.xmlClasses.Params;


public class AspectErrors {
	public static List<Elements> errors = new ArrayList<Elements>();
	
	public static Element createElement(String id, String type, List<Alarm> alarms) {
		Element e = new Element();
		e.setId(id);
		e.setType(type);
		Alarms a = new Alarms();
		a.getAlarm().addAll(alarms);
		e.setAlarms(a);
		return e;
	}
	
	public static Alarm createAlarm(String id, String type, String state, List<Param> params) {
		Alarm a =  new Alarm();
		try {
			GregorianCalendar gcal = new GregorianCalendar();
			a.setId(id);
			a.setState(state);
			a.setType(type);
			Params p = new Params();
			p.getParam().addAll(params);
			a.setParams(p);
			XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);;
			a.setDate(date);
		} catch (DatatypeConfigurationException e) {
			a.setDate(null);
			e.printStackTrace();
		}
		return a;
	}
	
	public static Param createParam(String id, String value) {
		Param p = new Param();
		p.setId(id);
		p.setValue(value);
		return p;
	}
}
