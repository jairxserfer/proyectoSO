package explorer;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class User extends explorer.Element {

    private String pass;
    private static int currentId = Explorer.usersContainer.getChildNodes().getLength();


    public User(int id, String name, String type, String pass) {
        super(id, name, type);
        this.pass = pass;
    }

    public String getPass(){
        return pass;
    }

    public static int getCurrentId(){
        return currentId;
    }

    public static void setCurrentId(int mCurrentId){
        currentId = mCurrentId;
    }

    public void add(){
        Element user = Explorer.userTree.createElement("user");
        Explorer.usersContainer.appendChild(user);

        Attr attr = Explorer.userTree.createAttribute("id");
        attr.setValue(String.valueOf(User.getCurrentId()));
        User.setCurrentId(User.getCurrentId() + 1);

        user.setAttributeNode(attr);

        Attr attrName = Explorer.userTree.createAttribute("name");
        attrName.setValue(this.getName());
        user.setAttributeNode(attrName);

        Attr attrContent = Explorer.userTree.createAttribute("pass");
        attrContent.setValue(this.getPass());
        user.setAttributeNode(attrContent);

        Attr attrAccount = Explorer.userTree.createAttribute("accountType");
        attrAccount.setValue(this.getType());
        user.setAttributeNode(attrAccount);

        XMLManager xmlManager = new XMLManager();
        xmlManager.saveUser();
    }

    public boolean existingUser(){
        NodeList user = Explorer.usersContainer.getChildNodes();
        for(int i = 0; i < user.getLength(); i++){
            if(user.item(i).getAttributes().getNamedItem("name").getTextContent().equals(this.getName())
            && user.item(i).getAttributes().getNamedItem("pass").getTextContent().equals(this.getPass()))
                return true;
        }
        return false;
    }

    public boolean existingName(){
        NodeList users = Explorer.usersContainer.getChildNodes();
        for (int i = 0; i < users.getLength(); i++){
            if (users.item(i).getAttributes().getNamedItem("name").getTextContent().equals(this.getName()))
                return true;
        }
        return false;
    }

    public void startSession(){
        NodeList users = Explorer.usersContainer.getChildNodes();
        for (int i = 0; i < users.getLength(); i++){
            if(users.item(i).getAttributes().getNamedItem("name").getTextContent().equals(this.getName())){
                Explorer.currentUser = (Element) users.item(i);
            }
        }
    }

    public static void outSession(){
        Explorer.currentUser = null;
        Explorer.pasteElement = null;
        Explorer.pasteState = false;
    }

}
