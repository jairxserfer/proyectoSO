package explorer;

import javax.swing.JLabel;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Directory extends explorer.Element {
    private boolean reading, edition;
    private String boss;

    public Directory(int id, String name, String type, boolean reading, boolean edition, String boss) {
        super(id,name, type);
        this.reading = reading;
        this.edition = edition;
        this.boss = boss;
    }


    public void add(){
        Element directory = Explorer.tree.createElement("directory");
        Explorer.currentDir.appendChild(directory);

        Attr attrName = Explorer.tree.createAttribute("name");
        //attrName.setValue(this.getName());
        directory.setAttributeNode(attrName);

        Attr attrReading = Explorer.tree.createAttribute("reading");
        attrReading.setValue( reading ? "Public" : "Private");
        directory.setAttributeNode(attrReading);

        Attr attrWriting = Explorer.tree.createAttribute("writing");
        attrWriting.setValue(edition ? "Public" : "Private");
        directory.setAttributeNode(attrWriting);

        Attr attrBoss = Explorer.tree.createAttribute("boss");
        //attrBoss.setValue(this.getBoss());
        directory.setAttributeNode(attrBoss);

        XMLManager xml = new XMLManager();
        xml.save();
    }

    public void edit(){
        JLabel elementName = (JLabel) ExplorerInterface.lastClick.getComponent(2);
        Element element = (Element) Directory.obtainSonNode(elementName.getText()).cloneNode(true);
        explorer.Element old = new explorer.Element(Integer.parseInt(element.getAttribute("id")), element.getAttribute("name"), "Directory");
        old.delete();

        element.setAttribute("id", String.valueOf(this.getId()));
        element.setAttribute("name", this.getName());
        element.setAttribute("boss", this.getBoss());
        element.setAttribute("reading", reading ? "Public" : "Private");
        element.setAttribute("writing", edition ? "Public" : "Private");

        Explorer.currentDir.appendChild(element);
        XMLManager xmlManager = new XMLManager();
        xmlManager.save();
    }

    public static Element obtainSonNode (String name){
        NodeList soons = Explorer.currentDir.getChildNodes();
        for(int i = 0; i < soons.getLength(); i++){
            if(soons.item(i).getNodeName().equals("directory") && soons.item(i).getAttributes().getNamedItem("name").getTextContent().equals(name))
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

    public String getBoss(){
        return boss;
    }
}
