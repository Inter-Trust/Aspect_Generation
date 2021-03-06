//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.07 at 10:37:44 AM CEST 
//


package uma.caosd.SecurityDeploymentSpecification;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for configuration complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="configuration">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="securityParameters" type="{http://inter-trust.eu/schema/interpreter/sds}securityParameters"/>
 *         &lt;element name="securityDescription" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "configuration", propOrder = {
    "securityParameters",
    "securityDescription"
})
public class Configuration {

    protected SecurityParameters securityParameters;
    protected Object securityDescription;

    /**
     * Gets the value of the securityParameters property.
     * 
     * @return
     *     possible object is
     *     {@link SecurityParameters }
     *     
     */
    public SecurityParameters getSecurityParameters() {
        return securityParameters;
    }

    /**
     * Sets the value of the securityParameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityParameters }
     *     
     */
    public void setSecurityParameters(SecurityParameters value) {
        this.securityParameters = value;
    }

    /**
     * Gets the value of the securityDescription property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getSecurityDescription() {
        return securityDescription;
    }

    /**
     * Sets the value of the securityDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setSecurityDescription(Object value) {
        this.securityDescription = value;
    }

}
