package explorer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreateElementInterface extends JFrame implements ActionListener {
    private JLabel name, authorization, reading, writing;
    private JButton create, cancel;
    private JPanel window, jPAuthorization, jPName, jPButton , division, division2 ,  division3;
    private JTextField tName;
    private JComboBox jCBReading, jCBWriting;
    private static final String[] options = {"Public", "Private"};
    public static String[] values;
    private String elementType;

    public CreateElementInterface(String elementType){
        super("New Element");
        this.elementType = elementType;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int xx = screenSize.width;
        int yy = screenSize.height;
        int x = 450;
        int y = 200;
        this.setSize(x, y);
        this.setLocation((xx-x)/2, (yy-y)/2);

        division = new JPanel();
        division2 = new JPanel();
        division3 = new JPanel();

        window = new JPanel();
        window.setLayout(new BoxLayout(window, BoxLayout.Y_AXIS));
        window.setSize(x,y);

        name = new JLabel("Name");
        tName = new JTextField();
        jPName = new JPanel();
        jPName.setLayout(new GridLayout(1,2));
        jPName.add(name);
        jPName.add(tName);

        authorization = new JLabel("authorization");

        jPAuthorization = new JPanel();
        jPAuthorization.setLayout(new GridLayout(2,2));
        reading = new JLabel("Reading");
        writing = new JLabel("Writing");
        jCBReading = new JComboBox(options);
        jCBWriting = new JComboBox(options);
        jCBReading.setSelectedIndex(0);
        jCBWriting.setSelectedIndex(0);
        jCBReading.addActionListener(this);
        jCBWriting.addActionListener(this);
        jPAuthorization.add(reading);
        jPAuthorization.add(jCBReading);
        jPAuthorization.add(writing);
        jPAuthorization.add(jCBWriting);

        jPButton = new JPanel();
        jPButton.setLayout(new GridLayout(1,2));
        jPButton.setMaximumSize(new Dimension(x/2,20));
        create = new JButton("Create");
        cancel = new JButton("Cancel");
        create.addActionListener(this);
        cancel.addActionListener(this);
        jPButton.add(create);
        jPButton.add(cancel);

        jPName.setMaximumSize(new Dimension(x, 25));
        jPAuthorization.setMaximumSize(new Dimension(x, 60));

        division.setMaximumSize(new Dimension(x, 10));
        division2.setMaximumSize(new Dimension(x,10));
        division3.setMaximumSize(new Dimension(x,10));

        window.add(jPName);
        window.add(division);
        window.add(authorization);
        window.add(division2);
        window.add(jPAuthorization);
        window.add(division3);
        window.add(jPButton);

        this.add(window);

        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == create){
            String name = tName.getText();
            String reading = jCBReading.getSelectedItem().toString();
            String writing = jCBWriting.getSelectedItem().toString();
            values = new String[3];
            if(!name.isEmpty() && !writing.isEmpty() && !reading.isEmpty()){
                values[0] = name;
                values[1] = reading;
                values[2] = writing;
                if (this.elementType.equalsIgnoreCase("File")){
                    Directory directory = new Directory(Explorer.getCurrentId(), name, "Directory", reading.equals("Public"), writing.equals("Public"), Explorer.currentUser.getAttribute("name"));
                    if(Directory.obtainSonNode(name) == null)
                        directory.add();
                    else
                        JOptionPane.showMessageDialog(null, "This directory will be duplicate", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    File file = new File(Explorer.getCurrentId(), name, "File", "Lorem", reading.equals("Public"),writing.equals("Public"), Explorer.currentUser.getAttribute("name"));
                    if(file.obtainSoonNode(name) == null)
                        file.add();
                    else
                        JOptionPane.showMessageDialog(null, "This directory will be duplicate", "Error", JOptionPane.ERROR_MESSAGE);
                }
                CreateElementInterface.values = null;
                this.dispose();
                new ExplorerInterface();
            } else {
                JOptionPane.showMessageDialog(null, "Add all sheets of the forms", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource() == cancel){
            this.dispose();
            new ExplorerInterface();
        }

    }
}
