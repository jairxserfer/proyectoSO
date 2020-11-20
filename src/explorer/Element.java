package explorer;

import org.w3c.dom.NodeList;


public class Element {
    int id;
    String name;
    String type;

    public Element(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void delete(){

    }

}
