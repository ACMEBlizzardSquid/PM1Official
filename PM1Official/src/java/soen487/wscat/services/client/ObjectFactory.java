
package soen487.wscat.services.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soen487.wscat.services.client package. 
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

    private final static QName _IOException_QNAME = new QName("http://services.wscat.soen487/", "IOException");
    private final static QName _SAXException_QNAME = new QName("http://services.wscat.soen487/", "SAXException");
    private final static QName _SubmitWSDLToAnalyze_QNAME = new QName("http://services.wscat.soen487/", "submitWSDLToAnalyze");
    private final static QName _TrainOnFile_QNAME = new QName("http://services.wscat.soen487/", "trainOnFile");
    private final static QName _SubmitWSDLRepoResponse_QNAME = new QName("http://services.wscat.soen487/", "submitWSDLRepoResponse");
    private final static QName _TrainOnFileResponse_QNAME = new QName("http://services.wscat.soen487/", "trainOnFileResponse");
    private final static QName _InterruptedException_QNAME = new QName("http://services.wscat.soen487/", "InterruptedException");
    private final static QName _ParserConfigurationException_QNAME = new QName("http://services.wscat.soen487/", "ParserConfigurationException");
    private final static QName _SubmitWSDLRepo_QNAME = new QName("http://services.wscat.soen487/", "submitWSDLRepo");
    private final static QName _SubmitWSDLToAnalyzeResponse_QNAME = new QName("http://services.wscat.soen487/", "submitWSDLToAnalyzeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soen487.wscat.services.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SubmitWSDLToAnalyze }
     * 
     */
    public SubmitWSDLToAnalyze createSubmitWSDLToAnalyze() {
        return new SubmitWSDLToAnalyze();
    }

    /**
     * Create an instance of {@link TrainOnFile }
     * 
     */
    public TrainOnFile createTrainOnFile() {
        return new TrainOnFile();
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link SAXException }
     * 
     */
    public SAXException createSAXException() {
        return new SAXException();
    }

    /**
     * Create an instance of {@link SubmitWSDLRepo }
     * 
     */
    public SubmitWSDLRepo createSubmitWSDLRepo() {
        return new SubmitWSDLRepo();
    }

    /**
     * Create an instance of {@link SubmitWSDLToAnalyzeResponse }
     * 
     */
    public SubmitWSDLToAnalyzeResponse createSubmitWSDLToAnalyzeResponse() {
        return new SubmitWSDLToAnalyzeResponse();
    }

    /**
     * Create an instance of {@link TrainOnFileResponse }
     * 
     */
    public TrainOnFileResponse createTrainOnFileResponse() {
        return new TrainOnFileResponse();
    }

    /**
     * Create an instance of {@link SubmitWSDLRepoResponse }
     * 
     */
    public SubmitWSDLRepoResponse createSubmitWSDLRepoResponse() {
        return new SubmitWSDLRepoResponse();
    }

    /**
     * Create an instance of {@link InterruptedException }
     * 
     */
    public InterruptedException createInterruptedException() {
        return new InterruptedException();
    }

    /**
     * Create an instance of {@link ParserConfigurationException }
     * 
     */
    public ParserConfigurationException createParserConfigurationException() {
        return new ParserConfigurationException();
    }

    /**
     * Create an instance of {@link StackTraceElement }
     * 
     */
    public StackTraceElement createStackTraceElement() {
        return new StackTraceElement();
    }

    /**
     * Create an instance of {@link Throwable }
     * 
     */
    public Throwable createThrowable() {
        return new Throwable();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link MarfcatIn }
     * 
     */
    public MarfcatIn createMarfcatIn() {
        return new MarfcatIn();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.wscat.soen487/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SAXException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.wscat.soen487/", name = "SAXException")
    public JAXBElement<SAXException> createSAXException(SAXException value) {
        return new JAXBElement<SAXException>(_SAXException_QNAME, SAXException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubmitWSDLToAnalyze }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.wscat.soen487/", name = "submitWSDLToAnalyze")
    public JAXBElement<SubmitWSDLToAnalyze> createSubmitWSDLToAnalyze(SubmitWSDLToAnalyze value) {
        return new JAXBElement<SubmitWSDLToAnalyze>(_SubmitWSDLToAnalyze_QNAME, SubmitWSDLToAnalyze.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TrainOnFile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.wscat.soen487/", name = "trainOnFile")
    public JAXBElement<TrainOnFile> createTrainOnFile(TrainOnFile value) {
        return new JAXBElement<TrainOnFile>(_TrainOnFile_QNAME, TrainOnFile.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubmitWSDLRepoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.wscat.soen487/", name = "submitWSDLRepoResponse")
    public JAXBElement<SubmitWSDLRepoResponse> createSubmitWSDLRepoResponse(SubmitWSDLRepoResponse value) {
        return new JAXBElement<SubmitWSDLRepoResponse>(_SubmitWSDLRepoResponse_QNAME, SubmitWSDLRepoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TrainOnFileResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.wscat.soen487/", name = "trainOnFileResponse")
    public JAXBElement<TrainOnFileResponse> createTrainOnFileResponse(TrainOnFileResponse value) {
        return new JAXBElement<TrainOnFileResponse>(_TrainOnFileResponse_QNAME, TrainOnFileResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InterruptedException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.wscat.soen487/", name = "InterruptedException")
    public JAXBElement<InterruptedException> createInterruptedException(InterruptedException value) {
        return new JAXBElement<InterruptedException>(_InterruptedException_QNAME, InterruptedException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParserConfigurationException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.wscat.soen487/", name = "ParserConfigurationException")
    public JAXBElement<ParserConfigurationException> createParserConfigurationException(ParserConfigurationException value) {
        return new JAXBElement<ParserConfigurationException>(_ParserConfigurationException_QNAME, ParserConfigurationException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubmitWSDLRepo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.wscat.soen487/", name = "submitWSDLRepo")
    public JAXBElement<SubmitWSDLRepo> createSubmitWSDLRepo(SubmitWSDLRepo value) {
        return new JAXBElement<SubmitWSDLRepo>(_SubmitWSDLRepo_QNAME, SubmitWSDLRepo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubmitWSDLToAnalyzeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.wscat.soen487/", name = "submitWSDLToAnalyzeResponse")
    public JAXBElement<SubmitWSDLToAnalyzeResponse> createSubmitWSDLToAnalyzeResponse(SubmitWSDLToAnalyzeResponse value) {
        return new JAXBElement<SubmitWSDLToAnalyzeResponse>(_SubmitWSDLToAnalyzeResponse_QNAME, SubmitWSDLToAnalyzeResponse.class, null, value);
    }

}
