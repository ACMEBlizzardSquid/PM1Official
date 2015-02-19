
package soen487.wscat.services.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for trainOnWSDL complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="trainOnWSDL">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="wsdlFile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trainOnWSDL", propOrder = {
    "wsdlFile"
})
public class TrainOnWSDL {

    protected String wsdlFile;

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

}
