package explorer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ExplorerInterface extends javax.swing.JFrame implements MouseListener, ActionListener {

    private javax.swing.JLabel direction;
    private javax.swing.JLabel elements;
    private javax.swing.JTextField searchText;
    private javax.swing.JButton back;
    private javax.swing.JButton shareButton;
    private javax.swing.JLabel binder;
    private javax.swing.JButton logoutSession;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanelContent;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPopupMenu jPopupMenu;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JTree jTree1;
    private javax.swing.JLabel user;



    public static JPanel lastClick;
    public ExplorerInterface(){
        initComponents();

    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new  CreateElementInterface("Directory");
        this.dispose();
    }

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new  CreateElementInterface("File");
        this.dispose();
    }

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JLabel elementName = (JLabel) lastClick.getComponent(2);
        JLabel elementType = (JLabel) lastClick.getComponent(1);
        if(elementType.getText().equals("file")){
            Element remove = File.obtainSoonNode(elementName.getText());
            if(remove.getAttribute("writing").equals("Public") || remove.getAttribute("boss").equals(Explorer.currentDir.getAttribute("name"))){
                File a = new File(Integer.parseInt(remove.getAttribute("id")), remove.getAttribute("name"), remove.getNodeName(), "Lorem", true, true, "adm");
                a.delete();
                jMenuItem6.setEnabled(false);
                Explorer.pasteState = false;
                lastClick = null;
                this.dispose();
                new ExplorerInterface();
            }else {
                JOptionPane.showMessageDialog(null, "You don't have permission for delete", "Denied Access", JOptionPane.ERROR_MESSAGE);
            }
        }else {
            Element remove = Directory.obtainSonNode(elementName.getText());
            if(remove.getAttribute("writing").equals("Public") || remove.getAttribute("boss").equals(Explorer.currentUser.getAttribute("name"))){
                Directory d = new Directory(Integer.parseInt(remove.getAttribute("id")), remove.getAttribute("name"), remove.getNodeName(), true, true, "admn");
                d.delete();
                jMenuItem6.setEnabled(false);
                Explorer.pasteState = false;
                lastClick = null;
                this.dispose();
                new ExplorerInterface();
            } else {
                JOptionPane.showMessageDialog(null, "Don't have permission for delete this element", "Denied Access", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JLabel elementName = (JLabel) lastClick.getComponent(2);
        JLabel elementType = (JLabel) lastClick.getComponent(1);
        if (elementType.getText().equals("file"))
            Explorer.pasteElement = File.obtainSoonNode(elementName.getText());
        else
            Explorer.pasteElement = Directory.obtainSonNode(elementName.getText());
        if (Explorer.pasteElement.getAttribute("writing").equals("Public") || Explorer.pasteElement.getAttribute("boss").equals(Explorer.currentUser.getAttribute("name"))){
            jMenuItem6.setEnabled(true);
            Explorer.pasteState = true;
        } else {
            JOptionPane.showMessageDialog(null, "Don't have permission to copy this element", "Denied Access", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JLabel elementName = (JLabel) lastClick.getComponent(2);
        JLabel elementType = (JLabel) lastClick.getComponent(1);
        if(elementType.getText().equals("file"))
            Explorer.pasteElement = File.obtainSoonNode(elementName.getText());
        else
            Explorer.pasteElement = Directory.obtainSonNode(elementName.getText());
        if(Explorer.pasteElement.getAttribute("writing").equals("Public") || Explorer.pasteElement.getAttribute("boss").equals(Explorer.currentUser.getAttribute("name"))){
            int id_remove = Integer.parseInt(Explorer.pasteElement.getAttribute("id"));
            explorer.Element remove = new explorer.Element(id_remove, elementName.getText(), elementType.getText());
            remove.delete();
            jMenuItem6.setEnabled(true);
            Explorer.pasteState = true;
        } else {
            JOptionPane.showMessageDialog(null, "Don't have permission for cut this element", "Denied Access", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.out.println(Explorer.pasteElement);
        if(Explorer.pasteElement.getNodeName().equals("file")){
            File file = new File(Explorer.getCurrentId(), Explorer.pasteElement.getAttribute("name"), "File", "Lorem", true, true, "adm");
            if(File.obtainSoonNode(Explorer.pasteElement.getAttribute("name")) == null){
                file.clonee();
                this.dispose();
                new ExplorerInterface();
            } else {
                JOptionPane.showMessageDialog(null, "This file it's duplicate", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            Directory directory = new Directory(Explorer.getCurrentId(), Explorer.pasteElement.getAttribute("name"), "directory",true,true,"adm");
            if(Directory.obtainSonNode(Explorer.pasteElement.getAttribute("name")) == null){
                directory.clonee();
                this.dispose();
                new ExplorerInterface();
            } else {
                JOptionPane.showMessageDialog(null, "This directory it's duplicate", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JLabel directoryName = (JLabel) lastClick.getComponent(2);
        JLabel elementType = (JLabel) lastClick.getComponent(1);
        Element last;
        if(elementType.getText().equals("directory")){
            last = Directory.obtainSonNode(directoryName.getText());
        } else {
            last = File.obtainSoonNode(directoryName.getText());
        }
        if(last.getAttribute("writing").equals("Public") || last.getAttribute("boss").equals(Explorer.currentUser.getAttribute("name"))){
            this.dispose();
            new EditElementInterface(elementType.getText());
        } else {
            JOptionPane.showMessageDialog(null, "You don't have permission for edit this element", "Access Denied", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.jMenuItem7ActionPerformed(evt);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       if(!searchText.getText().isEmpty()){
           new SearchInterface(searchText.getText());
           searchText.setText("");
       }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.jMenuItem3ActionPerformed(evt);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.jMenuItem2ActionPerformed(evt);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.jMenuItem1ActionPerformed(evt);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void shareButtonActionPerformed(java.awt.event.ActionEvent evt){
        ShareInterface shareInterface = new ShareInterface();
        shareInterface.share();
    }

    private void logoutSessionActionPerformed(java.awt.event.ActionEvent event){
        User.outSession();
        this.dispose();

    }

    private void backActionPerformed(java.awt.event.ActionEvent event){
        if(Explorer.getCurrentLevel() > 1){
            Explorer.currentDir = (Element) Explorer.currentDir.getParentNode();
            this.dispose();
            directionReduce();
            new ExplorerInterface();
            Explorer.setCurrentLevel(Explorer.getCurrentLevel() - 1);
        }
    }

    public void directionReduce(){
        int finalOcurrence = Explorer.Direction.lastIndexOf("\\");
        Explorer.Direction = (Explorer.Direction.substring(0,finalOcurrence));
        System.out.println(Explorer.Direction);
    }

    public void directionIncrease(){
        Explorer.Direction = (Explorer.Direction + "\\" + Explorer.currentDir.getAttributes().getNamedItem("name").getTextContent());
        System.out.println(Explorer.Direction);
    }

    private void initComponents(){
        jPopupMenu = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jPanelMenu = new javax.swing.JPanel();
        logoutSession = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        direction = new javax.swing.JLabel();
        back = new javax.swing.JButton();
        searchText = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        user = new javax.swing.JLabel();
        shareButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanelContent = new javax.swing.JPanel();
        binder = new javax.swing.JLabel();
        elements = new javax.swing.JLabel();

        jMenuItem1.setText("Nuevo Directorio");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent event){
                jMenuItem1ActionPerformed(event);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("New File");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                jMenuItem2ActionPerformed(e);
            }
        });
        jPopupMenu1.add(jMenuItem2);
        jPopupMenu1.add(jSeparator2);

        jMenuItem3.setText("Delete");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                jMenuItem3ActionPerformed(e);
            }
        });
        jPopupMenu1.add(jMenuItem3);
        jPopupMenu1.add(jSeparator3);

        jMenuItem4.setText("Copy");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent e){
                jMenuItem4ActionPerformed(e);
            }
        });
        jPopupMenu1.add(jMenuItem4);

        jMenuItem5.setText("Cut");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                jMenuItem5ActionPerformed(e);
            }
        });
        jPopupMenu1.add(jMenuItem5);

        jMenuItem6.setText("Paste");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                jMenuItem6ActionPerformed(e);
            }
        });
        jPopupMenu1.add(jMenuItem6);

        jMenuItem7.setText("Edit");
        jMenuItem7.addActionListener( new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                jMenuItem7ActionPerformed(e);
            }
        });
        jPopupMenu1.add(jMenuItem7);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Search File");
        setResizable(false);

        logoutSession.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
        logoutSession.setText("Logout");
        logoutSession.setMinimumSize(new java.awt.Dimension(30,30));
        logoutSession.setPreferredSize(new java.awt.Dimension(20,20));
        logoutSession.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                logoutSessionActionPerformed(e);
            }
        });

        jButton2.setText("Search");
        jButton2.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton2ActionPerformed(e);
            }
        });

        direction.setBackground(new java.awt.Color(255,255,255));
        direction.setText(" ");
        direction.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        direction.setOpaque(true);

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
        back.setText("Next Archive");
        back.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                backActionPerformed(e);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
        jButton1.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton1ActionPerformed(e);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
        jButton3.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton3ActionPerformed(e);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
        jButton4.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton4ActionPerformed(e);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
        jButton5.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton5ActionPerformed(e);
            }
        });

        user.setFont(new java.awt.Font("Tahoma",1,14));
        user.setHorizontalAlignment(SwingConstants.RIGHT);

        shareButton.setText("Sharing");
        shareButton.addActionListener(new java.awt.event.ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                shareButtonActionPerformed(e);
            }
        });

    }



    public void paint(){
        NodeList nodeList = Explorer.currentDir.getChildNodes();

        for( int i = 0; i < nodeList.getLength(); i++){
            Panel panel = new Panel(nodeList.item(i).getNodeName(), nodeList.item(i).getAttributes().getNamedItem("name").getTextContent());
            JPanel element = panel.create();
            element.addMouseListener(this);
            element.setComponentPopupMenu(jPopupMenu1);
            jPanelContent.add(element);
        }

    }

    public void tree_paint(){
        javax.swing.tree.DefaultMutableTreeNode soon;
        Node root = Explorer.tree.getDocumentElement();
        soon = paint_recursive_tree(root, "Finder");
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(soon));
    }

    public javax.swing.tree.DefaultMutableTreeNode paint_recursive_tree(Node root, String rootName){
        javax.swing.tree.DefaultMutableTreeNode father = new javax.swing.tree.DefaultMutableTreeNode(rootName);
        javax.swing.tree.DefaultMutableTreeNode soon;
        NodeList nodeList = root.getChildNodes();
        for(int i = 0; i < nodeList.getLength(); i++){
            if(nodeList.item(i).getNodeName().equals("directory")){
                soon = paint_recursive_tree(nodeList.item(i), nodeList.item(i).getAttributes().getNamedItem("name").getTextContent());
                father.add(soon);
            } else {
                soon = new javax.swing.tree.DefaultMutableTreeNode(nodeList.item(i).getAttributes().getNamedItem("name").getTextContent());
                father.add(soon);
            }
        }
        return father;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(lastClick != null)
            lastClick.setBackground(Color.WHITE);
        e.getComponent().setBackground(new Color(187,211,249));
        lastClick = (JPanel) e.getComponent();
        JLabel componentType =(JLabel) lastClick.getComponent(1);

        if(e.getClickCount() == 2 && componentType.getText().equals("directory")){
            JLabel nameDirectory = (JLabel) lastClick.getComponent(2);
            Element last = Directory.obtainSonNode(nameDirectory.getText());
            if(last.getAttribute("reading").equals("Public") || last.getAttribute("boos").equals(Explorer.currentUser.getAttribute("name"))){
                Explorer.currentDir = Directory.obtainSonNode(nameDirectory.getText());
                this.dispose();
                directionIncrease();
                new ExplorerInterface();
                Explorer.setCurrentLevel(Explorer.getCurrentLevel() + 1);
            } else {
                JOptionPane.showMessageDialog(null,"You don't have permission for open this file", "Access Deneied", JOptionPane.ERROR_MESSAGE);
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

        public JPanel create(){
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            JLabel name = new JLabel(this.name);
            name.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel type = new JLabel(this.type);
            type.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel imagen = new JLabel("");
            imagen.setAlignmentX(Component.CENTER_ALIGNMENT);
            imagen.setSize(60, 60);
            if(this.type.equals("directorio")){
                ImageIcon image_directorio = new ImageIcon(getClass().getResource("Images/folder.png"));
                ImageIcon img = new ImageIcon(image_directorio.getImage().getScaledInstance(imagen.getWidth(), imagen.getHeight(), Image.SCALE_DEFAULT));
                imagen.setIcon(img);
            }
            if(this.type.equals("file")){
                ImageIcon img_file = new ImageIcon(getClass().getResource("Images/document.png"));
                ImageIcon img = new ImageIcon(img_file.getImage().getScaledInstance(imagen.getWidth(), imagen.getHeight(), Image.SCALE_DEFAULT));
                imagen.setIcon(img);
            }

            panel.add(imagen);
            panel.add(type);
            panel.add(name);
            panel.setBackground(Color.WHITE);
            return panel;
        }
    }

}
