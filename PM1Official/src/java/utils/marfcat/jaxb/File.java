/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.marfcat.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author shake0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class File {
	private Meta meta;
	private Location location;
	@XmlAttribute
	private Integer id;
	@XmlAttribute
	private String path;
	
	public File(){
		this.id = 0;
		this.path="";
	}
	
	public void setMeta(String type, int lines, int words, int bytes){
		this.meta = new Meta();
		this.meta.setType(type);
		this.meta.setLength(new Length(lines, words, bytes));
	}
	
	public void setLocation(String cve){
		this.location = new Location();
		this.location.setFragment("");
		this.location.setExplanation("");
		Meta m = new Meta();
		m.setCve(cve);
		m.setName(new Name());
		this.location.setMeta(m);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof File)
			return ((File) obj).id.equals(this.id);
		return super.equals(obj);
	}
	
	// Auto-generated

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
