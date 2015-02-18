
package soen487.wscat.services.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for trainOnFile complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="trainOnFile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="wsdlFile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wsdlURI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trainOnFile", propOrder = {
    "wsdlFile",
    "wsdlURI"
})
public class TrainOnFile {

    protected String wsdlFile;
    protected String wsdlURI;

    /**
     * Gets the value of the wsdlFile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWsdlFile() {
        return wsdlFile;
    }

    /**
     * Sets the value of the wsdlFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWsdlFile(String value) {
        this.wsdlFile = value;
    }

    /**
     * Gets the value of the wsdlURI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWsdlURI() {
        return wsdlURI;
    }

    /**
     * Sets the value of the wsdlURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWsdlURI(String value) {
        this.wsdlURI = value;
    }

}
