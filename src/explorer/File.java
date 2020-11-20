package explorer;

import javax.swing.JLabel;
import org.w3c.dom.Attr;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;


public class File extends explorer.Element {

    private String content, boos;
    private boolean reading, edition;

    public File(int id, String name, String type, String content, boolean reading, boolean edition, String boos){
        super(id, name, type);
        this.content = content;
        this.reading = reading;
        this.edition = edition;
        this.boos = boos;
    }

    public String getContent(){
        return content;
    }

    public void add(){
        Element file = Explorer.tree.createElement("file");
        Explorer.currentDir.appendChild(file);

        Attr attr = Explorer.tree.createAttribute("id");
        if(Explorer.id_jumps.isEmpty()){
            attr.setValue(String.valueOf(Explorer.getCurrentId()));
            Explorer.setCurrentId(Explorer.getCurrentId() + 1);
        } else {
            attr.setValue(String.valueOf(Explorer.id_jumps.get(0)));
            Explorer.id_jumps.remove(0);
        }
        file.setAttributeNode(attr);

        Attr attrName = Explorer.tree.createAttribute("name");
        attrName.setValue(this.getName());
        file.setAttributeNode(attrName);

        Attr attrBoos = Explorer.tree.createAttribute("boos");
        attrBoos.setValue(this.getBoos());
        file.setAttributeNode(attrBoos);

        Attr attrReading = Explorer.tree.createAttribute("reading");
        attrReading.setValue(reading ? "Public" : "Private");
        file.setAttributeNode(attrReading);

        Attr attrWriting = Explorer.tree.createAttribute("writing");
        attrWriting.setValue(edition ? "Public" : "Private");
        file.setAttributeNode(attrWriting);

        Attr attrContent = Explorer.tree.createAttribute("content");
        attrContent.setValue(this.getContent());
        file.setAttributeNode(attrContent);

        XMLManager xmlManager = new XMLManager();
        xmlManager.save();

    }

    public void edit(){
        JLabel elementName = (JLabel) ExplorerInterface.lastClick.getComponent(2);
        Element element = (Element) obtainSoonNode(elementName.getText()).cloneNode(true);
        explorer.Element old = new explorer.Element(Integer.parseInt(element.getAttribute("id")), element.getAttribute("name"), "Directory");
        old.delete();

        element.setAttribute("id", String.valueOf(this.getId()));
        element.setAttribute("name", this.getName());
        element.setAttribute("boos", this.getBoos());
        element.setAttribute("reading", reading ? "Public" : "Private");
        element.setAttribute("writing", edition ? "Public" : "Private");

        Explorer.currentDir.appendChild(element);
        XMLManager xmlManager = new XMLManager();
        xmlManager.save();
    }

    public static  Element obtainSoonNode(String name){
        NodeList soons = Explorer.currentDir.getChildNodes();
        for(int i = 0; i < soons.getLength(); i++){
            if(soons.item(i).getNodeName().equals("file") && soons.item(i).getAttributes().getNamedItem("name").getTextContent().equals(name))
                return (Element) soons.item(i);
        }
        return null;
    }

    public void clonee(){
        Explorer.currentDir.appendChild(Explorer.pasteElement.cloneNode(true));
        XMLManager xmlManager = new XMLManager();
        xmlManager.save();
    }

    public boolean isReading(){
        return reading;
    }

    public boolean isEdition(){
        return edition;
    }

    public String getBoos(){
        return boos;
    }
}
