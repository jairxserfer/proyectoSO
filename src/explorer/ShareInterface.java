package explorer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

import javafx.scene.layout.Pane;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ShareInterface extends javax.swing.JFrame implements MouseListener, ActionListener {
    private int resultNumber = 0;
    private String share;
    public static  JPanel lastClick;

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelResult;
    private javax.swing.JPanel jPanelContent;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel word;


    public ShareInterface(){
        initComponents();
    }

    public ShareInterface(String shared){
        share = shared.toUpperCase();
        initComponents();
        word.setText(" ' "+share+" ' ");
        paint();
        this.setVisible(true);

    }

    public void share(){
        JFileChooser dig = new JFileChooser();
        int option = dig.showOpenDialog(this);

        if(option == JFileChooser.APPROVE_OPTION){
            String file = dig.getSelectedFile().getPath();
            System.out.println(file);
        }
    }

    public void paint(){
        System.out.println("Start paint");
        Node root = Explorer.tree.getDocumentElement();
        recursive_paint(root, "Finder: ");
    }

    public void recursive_paint( Node root, String direction){
        String auxName;
        NodeList nodeList = root.getChildNodes();
        for(int i = 0; i < nodeList.getLength(); i++){
            auxName = nodeList.item(i).getAttributes().getNamedItem("name").getTextContent().toUpperCase();
            if(nodeList.item(i).getNodeName().equals("directory")){
                if(auxName.contains(share)){
                    Panel panel = new Panel(nodeList.item(i).getNodeName(), nodeList.item(i).getAttributes().getNamedItem("name").getTextContent());
                    JPanel element = panel.create(direction);
                    element.addMouseListener(this);
                    jPanelContent.add(element);
                    resultNumber++;
                    jLabelResult.setText(resultNumber + "result");
                }
                recursive_paint(nodeList.item(i), direction+"\\"+nodeList.item(i).getAttributes().getNamedItem("name").getTextContent());
            }
            else {
                if(nodeList.item(i).getNodeName().equals("file")){
                    if(auxName.contains(share)){
                        Panel panel = new Panel(nodeList.item(i).getNodeName(), nodeList.item(i).getAttributes().getNamedItem("name").getTextContent());
                        JPanel element = panel.create(direction);
                        element.addMouseListener(this);
                        jPanelContent.add(element);
                        resultNumber++;
                        jLabelResult.setText(resultNumber + " result");
                    }
                }
            }
        }
    }

    private void initComponents(){
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabelResult = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanelContent = new javax.swing.JPanel();
        word = new javax.swing.JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Search File");
        setResizable(false);

        jLabel1.setText("Search:");
        jLabelResult.setText("0 result");

        jPanelContent.setLayout(new java.awt.GridLayout(0,1));
        jScrollPane1.setViewportView(jPanelContent);

        word.setText(" ");
        word.setToolTipText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator1)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1)
                                .addContainerGap())
                                .addGroup(layout.createSequentialGroup()
                                .addGap(20,20,20)
                                .addComponent(jLabel1)
                                .addGap(18,18,18)
                                .addComponent(word, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                                .addComponent(jLabelResult, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                .addGap(36,36,36))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGap(10,10,10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(jLabelResult)
                .addComponent(word))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(lastClick != null)
            lastClick.setBackground(Color.WHITE);
        e.getComponent().setBackground(new Color(187, 211,249));
        lastClick = (JPanel) e.getComponent();
        JLabel componentType = (JLabel) lastClick.getComponent(1);

        if(e.getClickCount() == 2 && componentType.getText().equals("directory")){
            JLabel directoryName = (JLabel) lastClick.getComponent(2);
            Element last = Directory.obtainSonNode(directoryName.getText());
            System.out.println();
            if(Directory.obtainSonNode(directoryName.getText()) != null) {
                if(last.getAttribute("reading").equals("Public") || last.getAttribute("boss").equals(Explorer.currentUser.getAttribute("name"))){
                    Explorer.currentDir = Directory.obtainSonNode(directoryName.getText());
                    this.dispose();
                    Explorer.Direction = (Explorer.Direction+"\\"+Explorer.currentDir.getAttributes().getNamedItem("name").getTextContent());
                    String[] levels = Explorer.Direction.split("\\\\");
                    Explorer.setCurrentLevel(levels.length);
                    new ExplorerInterface();
                } else {
                    JOptionPane.showMessageDialog(null, "You don't have reading permission in this file", "Access Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Unexpected error", "Open Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    class Panel{
        private final String type, name;

        public Panel(String type, String name){
            this.type = type;
            this.name = name;
        }

        public JPanel create(String direction){
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
            JLabel name = new JLabel(this.name);
            name.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel type = new JLabel(direction);
            type.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel location = new JLabel(direction);
            location.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel image = new JLabel("");
            image.setAlignmentX(Component.CENTER_ALIGNMENT);
            image.setSize(60,60);

            if(this.type.equals("directory")){
                ImageIcon image_directory = new ImageIcon(getClass().getResource(""));
                ImageIcon img = new ImageIcon(image_directory.getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_DEFAULT));
                image.setIcon(img);
            }

            if(this.type.equals("file")){
                ImageIcon image_file = new ImageIcon(getClass().getResource(""));
                ImageIcon img = new ImageIcon(image_file.getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_DEFAULT));
                image.setIcon(img);
            }
            panel.add(image);
            panel.add(type);
            panel.add(name);
            panel.add(location);
            panel.setBackground(Color.WHITE);
            return  panel;
        }
    }

}
