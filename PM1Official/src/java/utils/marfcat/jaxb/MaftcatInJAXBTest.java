/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.marfcat.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author shake0
 */
public class MaftcatInJAXBTest {
	public static void main(String[] args) throws JAXBException{
		// Entries
		final String fileTool = "1.0";
		final String findTool = "1.1";
		final String marfTool = "1.2";
		final int    file1Id  = 1;
		final int    file2Id  = 2;
		final String file1Path = "Path/1";
		final String file2Path = "Path/2";
		final String file1Type = "Type-1";
		final String file2Type = "Type-2";
		final int    fileLines = 300;
		final int    fileWords = 400;
		final int    fileBytes = 500;
		final String cve1      = "Hello";
		final String cve2      = "World";
		
		// Make structure
		DataSet     marfin      = new DataSet();
		Description description = new Description();
		File        file1       = new File();
		File        file2       = new File();
		List<File>  files       = new ArrayList<>();
		
		description.setFileTool(fileTool);
		description.setFindTool(findTool);
		description.setMarfTool(marfTool);
		file1.setId(file1Id);
		file1.setPath(file1Path);
		file1.setMeta(file1Type, fileLines, fileWords, fileBytes);
		file1.setLocation(cve1);
		file2.setId(file2Id);
		file2.setPath(file2Path);
		file2.setMeta(file2Type, fileLines, fileWords, fileBytes);
		file2.setLocation(cve2);
		
		// Build
		files.add(file1);
		files.add(file2);
		marfin.setDescription(description);
		marfin.setFiles(files);
		
		// Print
		JAXBContext context = JAXBContext.newInstance(DataSet.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(marfin, System.out);
	}
}
