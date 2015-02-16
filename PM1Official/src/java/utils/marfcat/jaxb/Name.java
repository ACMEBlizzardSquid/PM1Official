/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.marfcat.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 *
 * @author shake0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Name {
	@XmlAttribute
	private String cweid;
	@XmlValue
	private String value;
	
	public Name(){
		this.cweid = "";
		this.value = "";
	}

	public String getCweid() {
		return cweid;
	}

	public void setCweid(String cweid) {
		this.cweid = cweid;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
