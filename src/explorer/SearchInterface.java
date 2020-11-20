package explorer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

import javafx.scene.layout.Pane;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SearchInterface extends javax.swing.JFrame implements MouseListener, ActionListener {
    private int resultNumber = 0;
    private String search;
    public static JPanel lastClick;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelResult;
    private javax.swing.JPanel jPanelContent;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel word;

    public SearchInterface(){
        initComponents();
    }

    public SearchInterface(String search){
        this.search = search.toUpperCase();
        initComponents();
        word.setText(" ' "+search+" ' ");
        paint();
        this.setVisible(true);
    }

    private void initComponents(){
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabelResult = new javax.swing.JLabel();
        jScrollPane = new javax.swing.JScrollPane();
        jPanelContent = new javax.swing.JPanel();
        word = new javax.swing.JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("File Explorer");
        setResizable(false);

        jLabel1.setText("Search:");
        jLabelResult.setText("0 result");

        jPanelContent.setLayout(new java.awt.GridLayout(0, 1));
        jScrollPane.setViewportView(jPanelContent);

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
                            .addComponent(jScrollPane)
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
                        .addComponent(jScrollPane, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(null);
    }

    public void paint(){
        System.out.println("Start paint");
        Node root = Explorer.tree.getDocumentElement();
        recursive_paint(root, "Finder");
    }

    public void recursive_paint(Node root, String direction){
        String auxName;
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++){
            auxName = nodeList.item(i).getAttributes().getNamedItem("name").getTextContent().toUpperCase();
            if (nodeList.item(i).getNodeName().equals("directory")){
                if(auxName.contains(search)){
                    Panel panel = new Panel(nodeList.item(i).getNodeName(), nodeList.item(i).getAttributes().getNamedItem("name").getTextContent());
                    JPanel element = panel.create(direction);
                    element.addMouseListener(this);
                    jPanelContent.add(element);
                    resultNumber++;
                    jLabelResult.setText(resultNumber + " result ");
                }
                recursive_paint(nodeList.item(i), direction+ "\\" + nodeList.item(i).getAttributes().getNamedItem("name").getTextContent());
            }
            else {
                if(nodeList.item(i).getNodeName().equals("file")){
                    if(auxName.contains(search)){
                        Panel panel = new Panel(nodeList.item(i).getNodeName(), nodeList.item(i).getAttributes().getNamedItem("name").getTextContent());
                        JPanel element = panel.create(direction);
                        element.addMouseListener(this);
                        jPanelContent.add(element);
                        resultNumber++;
                        jLabelResult.setText(resultNumber + "result");
                    }
                }
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            JLabel name = new JLabel(this.name);
            name.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel type = new JLabel(this.type);
            type.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel ubication = new JLabel(direction);
            ubication.setAlignmentX(Component.CENTER_ALIGNMENT);
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
            panel.add(ubication);
            panel.setBackground(Color.WHITE);
            return panel;
        }
    }
}
