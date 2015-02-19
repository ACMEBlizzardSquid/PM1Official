
package soen487.retriever.services.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soen487.retriever.services.client package. 
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

    private final static QName _RetrieveWSDLsResponse_QNAME = new QName("http://services.retriever.soen487/", "retrieveWSDLsResponse");
    private final static QName _IOException_QNAME = new QName("http://services.retriever.soen487/", "IOException");
    private final static QName _RetrieveWSDLs_QNAME = new QName("http://services.retriever.soen487/", "retrieveWSDLs");
    private final static QName _MalformedURLException_QNAME = new QName("http://services.retriever.soen487/", "MalformedURLException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soen487.retriever.services.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RetrieveWSDLs }
     * 
     */
    public RetrieveWSDLs createRetrieveWSDLs() {
        return new RetrieveWSDLs();
    }

    /**
     * Create an instance of {@link MalformedURLException }
     * 
     */
    public MalformedURLException createMalformedURLException() {
        return new MalformedURLException();
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link RetrieveWSDLsResponse }
     * 
     */
    public RetrieveWSDLsResponse createRetrieveWSDLsResponse() {
        return new RetrieveWSDLsResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveWSDLsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.retriever.soen487/", name = "retrieveWSDLsResponse")
    public JAXBElement<RetrieveWSDLsResponse> createRetrieveWSDLsResponse(RetrieveWSDLsResponse value) {
        return new JAXBElement<RetrieveWSDLsResponse>(_RetrieveWSDLsResponse_QNAME, RetrieveWSDLsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.retriever.soen487/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveWSDLs }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.retriever.soen487/", name = "retrieveWSDLs")
    public JAXBElement<RetrieveWSDLs> createRetrieveWSDLs(RetrieveWSDLs value) {
        return new JAXBElement<RetrieveWSDLs>(_RetrieveWSDLs_QNAME, RetrieveWSDLs.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MalformedURLException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.retriever.soen487/", name = "MalformedURLException")
    public JAXBElement<MalformedURLException> createMalformedURLException(MalformedURLException value) {
        return new JAXBElement<MalformedURLException>(_MalformedURLException_QNAME, MalformedURLException.class, null, value);
    }

}
