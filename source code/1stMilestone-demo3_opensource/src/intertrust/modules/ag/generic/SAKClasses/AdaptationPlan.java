//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2013.09.17 a las 11:51:22 AM CEST 
//


package intertrust.modules.ag.generic.SAKClasses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para adaptationPlan complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="adaptationPlan">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deploy" type="{http://inter-trust.eu/schema/interpreter/sds}deploy"/>
 *         &lt;element name="undeploy" type="{http://inter-trust.eu/schema/interpreter/sds}undeploy"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "adaptationPlan", propOrder = {
    "deploy",
    "undeploy"
})
@XmlRootElement
public class AdaptationPlan {

    @XmlElement(required = true)
    protected Deploy deploy;
    @XmlElement(required = true)
    protected Undeploy undeploy;

    /**
     * Obtiene el valor de la propiedad deploy.
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
     * Define el valor de la propiedad deploy.
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
     * Obtiene el valor de la propiedad undeploy.
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
     * Define el valor de la propiedad undeploy.
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
