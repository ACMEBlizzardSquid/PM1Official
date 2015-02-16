/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.marfcat.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author shake0
 */
@XmlRootElement
public class Description {
	private String fileTool;
	private String findTool;
	private String marfTool;

	@XmlElement(name="file-type-tool")
	public String getFileTool() {
		return fileTool;
	}

	public void setFileTool(String fileTool) {
		this.fileTool = fileTool;
	}

	@XmlElement(name="find-tool")
	public String getFindTool() {
		return findTool;
	}

	public void setFindTool(String findTool) {
		this.findTool = findTool;
	}

	@XmlElement(name="marf-tool")
	public String getMarfTool() {
		return marfTool;
	}

	public void setMarfTool(String marfTool) {
		this.marfTool = marfTool;
	}
}
