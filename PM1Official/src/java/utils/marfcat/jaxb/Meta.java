/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.marfcat.jaxb;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author shake0
 */
@XmlRootElement
@XmlType(propOrder = {"type","length","cve","name"})
public class Meta {
	private String type;
	private Length length;
	private String cve;
	private Name   name;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Length getLength() {
		return length;
	}

	public void setLength(Length length) {
		this.length = length;
	}

	public String getCve() {
		return cve;
	}

	public void setCve(String cve) {
		this.cve = cve;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}
	
	
}
