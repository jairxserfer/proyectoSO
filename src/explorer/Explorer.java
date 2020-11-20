package explorer;

import Interface.Starting;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Explorer implements Serializable {

    static LinkedList id_jumps = new LinkedList();
    private static int currentId = 1, currentLevel = 1;
    public static Document tree, userTree;
    public static Element currentDir, pasteElement, currentUser, usersContainer;
    public static boolean pasteState = false;
    public static String Direction = "Finder";

    public static String getDirection() {
        return Direction;
    }

    public static void setDirection(String direction) {
        Direction = direction;
    }

    public static LinkedList getId_jumps() {
        return id_jumps;
    }

    public static int getCurrentId() {
        return currentId;
    }

    public static void setCurrentId(int currentId) {
        Explorer.currentId = currentId;
    }

    public static int getCurrentLevel() {
        return currentLevel;
    }

    public static void setCurrentLevel(int currentLevel) {
        Explorer.currentLevel = currentLevel;
    }

    public static void inicializar(){

    }

    public static void main(String[] args) {
        inicializar();
        Starting starting = new Starting();

    }
}
