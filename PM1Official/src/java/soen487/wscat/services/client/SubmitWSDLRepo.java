
package soen487.wscat.services.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for submitWSDLRepo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="submitWSDLRepo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="repoURI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "submitWSDLRepo", propOrder = {
    "repoURI"
})
public class SubmitWSDLRepo {

    protected String repoURI;

    /**
     * Gets the value of the repoURI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepoURI() {
        return repoURI;
    }

    /**
     * Sets the value of the repoURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepoURI(String value) {
        this.repoURI = value;
    }

}
