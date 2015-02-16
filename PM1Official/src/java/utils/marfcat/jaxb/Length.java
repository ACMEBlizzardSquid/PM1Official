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

/**
 *
 * @author shake0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Length {
	@XmlAttribute
	private int lines;
	@XmlAttribute
	private int words;
	@XmlAttribute
	private int bytes;
	
	public Length(){}
	public Length(int lines, int words, int bytes){
		this.lines = lines;
		this.words = words;
		this.bytes = bytes;
	}

	// Auto-generated
	
	public int getLines() {
		return lines;
	}

	public void setLines(int lines) {
		this.lines = lines;
	}

	public int getWords() {
		return words;
	}

	public void setWords(int words) {
		this.words = words;
	}

	public int getBytes() {
		return bytes;
	}

	public void setBytes(int bytes) {
		this.bytes = bytes;
	}
	
	
}
