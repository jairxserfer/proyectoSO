package explorer;

import static explorer.Explorer.tree;
import static explorer.Explorer.userTree;
import static explorer.Explorer.currentDir;
import static explorer.Explorer.usersContainer;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLManager {

    public void create(){
        try {
           DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
           DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

           Document document = documentBuilder.newDocument();
           document.setXmlVersion("1.0");
           document.setXmlStandalone(false);
           Element rootElement = document.createElement("directory");
           document.appendChild(rootElement);
           Attr attr = document.createAttribute("id");
           attr.setValue(String.valueOf(Explorer.getCurrentId()));
           rootElement.setAttributeNode(attr);

           TransformerFactory transformerFactory = TransformerFactory.newInstance();
           Transformer transformer = transformerFactory.newTransformer();
           DOMSource source = new DOMSource(document);
           StreamResult result = new StreamResult(new File("tree.xml"));
           Explorer.setCurrentId(Explorer.getCurrentId() + 1);
           tree = document;
           tree.getDocumentElement();
           Explorer.setCurrentId(currentDir.getChildNodes().getLength());
        } catch (ParserConfigurationException | TransformerException pce){
            System.err.print(pce.getMessage());
        }
    }

    public void createUserTable(){
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();
            document.setXmlVersion("1.0");
            document.setXmlStandalone(false);
            Element rootElement = document.createElement("users");
            document.appendChild(rootElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("users.xml"));
            transformer.transform(source,result);
            Explorer.setCurrentId(Explorer.getCurrentId() + 1);
            userTree = document;
            userTree.getDocumentElement().normalize();
            usersContainer = userTree.getDocumentElement();
        } catch (ParserConfigurationException | TransformerException pce){
            System.err.print(pce.getMessage());
        }
    }

    public void save(){
        try{
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(tree);
            StreamResult result = new StreamResult(new File("tree.xml"));
            transformer.transform(source, result);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void saveUser(){
        try{
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(Explorer.userTree);
            StreamResult result = new StreamResult(new File("users.xml"));
            transformer.transform(source, result);
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

}
