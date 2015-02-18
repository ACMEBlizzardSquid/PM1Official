/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soen487.xml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

import org.xmlsoap.schemas.wsdl.ObjectFactory;
import org.xmlsoap.schemas.wsdl.TBinding;
import org.xmlsoap.schemas.wsdl.TDefinitions;
import org.xmlsoap.schemas.wsdl.TDocumentation;
import org.xmlsoap.schemas.wsdl.TDocumented;
import org.xmlsoap.schemas.wsdl.TImport;
import org.xmlsoap.schemas.wsdl.TMessage;
import org.xmlsoap.schemas.wsdl.TOperation;
import org.xmlsoap.schemas.wsdl.TPortType;
import org.xmlsoap.schemas.wsdl.TService;
import org.xmlsoap.schemas.wsdl.TTypes;

/**
 *
 * @author Simon
 */
public class WSDLParser {

    JAXBElement<TDefinitions> definitions;
    ArrayList<TImport> imports;
    ArrayList<TTypes> types;
    ArrayList<TMessage> messages;
    ArrayList<TPortType> portTypes;
    ArrayList<TBinding> bindings;
    ArrayList<TOperation> operations;
    ArrayList<TService> services;

    public WSDLParser() {
        imports = new ArrayList<TImport>();
        types = new ArrayList<TTypes>();
        messages = new ArrayList<TMessage>();
        portTypes = new ArrayList<TPortType>();
        bindings = new ArrayList<TBinding>();
        operations = new ArrayList<TOperation>();
        services = new ArrayList<TService>();
    }

    public WSDLParser(URL url) {
        this();
        try {
            JAXBContext jc = JAXBContext.newInstance(TDefinitions.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            ObjectFactory objectFactory = new ObjectFactory();
            definitions = (JAXBElement<TDefinitions>) unmarshaller.unmarshal(url);
            parse();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public WSDLParser(String page) {
        this();
        try {
            JAXBContext jc = JAXBContext.newInstance(TDefinitions.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            ObjectFactory objectFactory = new ObjectFactory();
            InputStream is = new ByteArrayInputStream(page.getBytes(StandardCharsets.UTF_8));
            definitions = (JAXBElement<TDefinitions>) unmarshaller.unmarshal(is);
            parse();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void parse() {
        if (definitions.getValue() != null && definitions.getValue().getAnyTopLevelOptionalElement() != null) {
            for (TDocumented documented : definitions.getValue().getAnyTopLevelOptionalElement()) {
                switch (documented.getClass().getSimpleName()) {
                    case "TImport":
                        imports.add((TImport) documented);
                        break;
                    case "TTypes":
                        types.add((TTypes) documented);
                        break;
                    case "TMessage":
                        messages.add((TMessage) documented);
                        break;
                    case "TPortType":
                        portTypes.add((TPortType) documented);
                        break;
                    case "TBinding":
                        bindings.add((TBinding) documented);
                        break;
                    case "TOperation":
                        operations.add((TOperation) documented);
                        break;
                    case "TService":
                        services.add((TService) documented);
                        break;
                }
            }
        }
    }

    public String printServices() {
        StringBuilder sb = new StringBuilder();
        for (TService tService : services) {
            sb.append("service : ");
            if (tService.getDocumentation() != null) {
                sb.append("service : ");
                for (Object object : tService.getDocumentation().getContent()) {
                    sb.append("        documentation : ");
                    sb.append(object.toString());
                    sb.append("\r\n");
                }
            }
            if (tService.getPort() != null) {
                for (int i = 0; i < tService.getPort().size(); i++) {
                    sb.append("        port @ name: " + tService.getPort().get(i).getName() + "\r\n");
                }
            }
        }
        return sb.toString();
    }

    public TDocumentation getServiceDocumentation() {
        for (TService tService : services) {
            if (tService.getDocumentation() != null) {
                return tService.getDocumentation();
            }
        }
        return null;
    }

    public String printDocumentation(TDocumentation tDocumentation) {
        if (tDocumentation == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<wsdl:documentation>");
         for (Object object : tDocumentation.getContent()) {
            sb.append(object.toString());
        }
        sb.append("</wsdl:documentation>");
        return sb.toString();
    }

}
