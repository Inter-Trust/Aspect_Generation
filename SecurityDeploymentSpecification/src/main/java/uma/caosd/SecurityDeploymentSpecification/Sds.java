//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.07 at 10:37:44 AM CEST 
//


package uma.caosd.SecurityDeploymentSpecification;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Security Deployment Specification
 * 
 * <p>Java class for sds complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sds">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deploy" type="{http://inter-trust.eu/schema/interpreter/sds}deploy" minOccurs="0"/>
 *         &lt;element name="undeploy" type="{http://inter-trust.eu/schema/interpreter/sds}undeploy" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sds", propOrder = {
    "deploy",
    "undeploy"
})
public class Sds {

    protected Deploy deploy;
    protected Undeploy undeploy;

    /**
     * Gets the value of the deploy property.
     * 
     * @return
     *     possible object is
     *     {@link Deploy }
     *     
     */
    public Deploy getDeploy() {
        return deploy;
    }

    /**
     * Sets the value of the deploy property.
     * 
     * @param value
     *     allowed object is
     *     {@link Deploy }
     *     
     */
    public void setDeploy(Deploy value) {
        this.deploy = value;
    }

    /**
     * Gets the value of the undeploy property.
     * 
     * @return
     *     possible object is
     *     {@link Undeploy }
     *     
     */
    public Undeploy getUndeploy() {
        return undeploy;
    }

    /**
     * Sets the value of the undeploy property.
     * 
     * @param value
     *     allowed object is
     *     {@link Undeploy }
     *     
     */
    public void setUndeploy(Undeploy value) {
        this.undeploy = value;
    }

}
