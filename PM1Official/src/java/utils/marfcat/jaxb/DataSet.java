/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.marfcat.jaxb;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * MARFCAT-IN JAXB Structure
 * @author shake0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "dataset")
public class DataSet {
	private Description description;
	@XmlElement(name = "file")
	private List<File>  files;
	@XmlAttribute
	private String      generateBy;
	@XmlAttribute
	private String      generateOn;
	
	public DataSet(){
		this.generateBy="";
		this.generateOn="";
	}
	
	public void addFile(File f){
		if(this.files == null) this.files = new ArrayList<>();
		this.files.add(f);
	}
	
	// Auto generated

	public Description getDescription() {
		return description;
	}

	public void setDescription(Description description) {
		this.description = description;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public String getGenerateBy() {
		return generateBy;
	}

	public void setGenerateBy(String generateBy) {
		this.generateBy = generateBy;
	}

	public String getGenerateOn() {
		return generateOn;
	}

	public void setGenerateOn(String generateOn) {
		this.generateOn = generateOn;
	}
	
}
