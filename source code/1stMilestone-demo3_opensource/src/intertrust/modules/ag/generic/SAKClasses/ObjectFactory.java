//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2013.09.17 a las 11:51:22 AM CEST 
//


package intertrust.modules.ag.generic.SAKClasses;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the intertrust.modules.ag.generic.SAKClasses package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ListAF_QNAME = new QName("http://inter-trust.eu/schema/interpreter/sds", "listAF");
    private final static QName _AdaptationPlan_QNAME = new QName("http://inter-trust.eu/schema/interpreter/sds", "adaptationPlan");
    private final static QName _MappingSCA_QNAME = new QName("http://inter-trust.eu/schema/interpreter/sds", "mappingSCA");
    private final static QName _Sds_QNAME = new QName("http://inter-trust.eu/schema/interpreter/sds", "sds");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: intertrust.modules.ag.generic.SAKClasses
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Undeploy }
     * 
     */
    public Undeploy createUndeploy() {
        return new Undeploy();
    }

    /**
     * Create an instance of {@link Sds }
     * 
     */
    public Sds createSds() {
        return new Sds();
    }

    /**
     * Create an instance of {@link ListAF }
     * 
     */
    public ListAF createListAF() {
        return new ListAF();
    }

    /**
     * Create an instance of {@link AdaptationPlan }
     * 
     */
    public AdaptationPlan createAdaptationPlan() {
        return new AdaptationPlan();
    }

    /**
     * Create an instance of {@link MappingSCA }
     * 
     */
    public MappingSCA createMappingSCA() {
        return new MappingSCA();
    }

    /**
     * Create an instance of {@link SecurityParameters }
     * 
     */
    public SecurityParameters createSecurityParameters() {
        return new SecurityParameters();
    }

    /**
     * Create an instance of {@link Functionality }
     * 
     */
    public Functionality createFunctionality() {
        return new Functionality();
    }

    /**
     * Create an instance of {@link Deploy }
     * 
     */
    public Deploy createDeploy() {
        return new Deploy();
    }

    /**
     * Create an instance of {@link Category }
     * 
     */
    public Category createCategory() {
        return new Category();
    }

    /**
     * Create an instance of {@link Aspect }
     * 
     */
    public Aspect createAspect() {
        return new Aspect();
    }

    /**
     * Create an instance of {@link SecurityConcept }
     * 
     */
    public SecurityConcept createSecurityConcept() {
        return new SecurityConcept();
    }

    /**
     * Create an instance of {@link SecurityParameter }
     * 
     */
    public SecurityParameter createSecurityParameter() {
        return new SecurityParameter();
    }

    /**
     * Create an instance of {@link Target }
     * 
     */
    public Target createTarget() {
        return new Target();
    }

    /**
     * Create an instance of {@link CandidateAspect }
     * 
     */
    public CandidateAspect createCandidateAspect() {
        return new CandidateAspect();
    }

    /**
     * Create an instance of {@link Configuration }
     * 
     */
    public Configuration createConfiguration() {
        return new Configuration();
    }

    /**
     * Create an instance of {@link Advice }
     * 
     */
    public Advice createAdvice() {
        return new Advice();
    }

    /**
     * Create an instance of {@link Undeploy.UndeploySecurityConcept }
     * 
     */
    public Undeploy.UndeploySecurityConcept createUndeployUndeploySecurityConcept() {
        return new Undeploy.UndeploySecurityConcept();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListAF }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://inter-trust.eu/schema/interpreter/sds", name = "listAF")
    public JAXBElement<ListAF> createListAF(ListAF value) {
        return new JAXBElement<ListAF>(_ListAF_QNAME, ListAF.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdaptationPlan }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://inter-trust.eu/schema/interpreter/sds", name = "adaptationPlan")
    public JAXBElement<AdaptationPlan> createAdaptationPlan(AdaptationPlan value) {
        return new JAXBElement<AdaptationPlan>(_AdaptationPlan_QNAME, AdaptationPlan.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MappingSCA }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://inter-trust.eu/schema/interpreter/sds", name = "mappingSCA")
    public JAXBElement<MappingSCA> createMappingSCA(MappingSCA value) {
        return new JAXBElement<MappingSCA>(_MappingSCA_QNAME, MappingSCA.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sds }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://inter-trust.eu/schema/interpreter/sds", name = "sds")
    public JAXBElement<Sds> createSds(Sds value) {
        return new JAXBElement<Sds>(_Sds_QNAME, Sds.class, null, value);
    }

}
