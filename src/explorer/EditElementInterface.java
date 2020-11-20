package explorer;

import static  explorer.ExplorerInterface.lastClick;
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
import org.w3c.dom.Element;

public class EditElementInterface extends JFrame implements ActionListener {

    private JLabel name, permission, reading, writing;
    private JButton change, cancel;
    private JPanel window, jPPermission, jPName, jPButton, division, division2, division3;
    private JTextField tName;
    private JComboBox jCBReading, jCBWriting;
    private static final String[] option = {"Public", "Private"};
    private String elementType;

    public EditElementInterface(String elementType){
        super("Edit element");
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
        window.setSize(x, y);

        name = new JLabel("Name");
        tName = new JTextField();
        jPName = new JPanel();
        jPName.setLayout(new GridLayout(1,2));
        jPName.add(name);
        jPName.add(tName);

        permission = new JLabel("Permission");

        jPPermission  = new JPanel();
        jPPermission.setLayout(new GridLayout(2,2));
        reading = new JLabel("Reading");
        writing = new JLabel("Writing");
        jCBReading = new JComboBox(option);
        jCBWriting = new JComboBox(option);

        JLabel name = (JLabel) lastClick.getComponent(2);
        Element element;
        if (this.elementType.equalsIgnoreCase("File"))
            element = File.obtainSoonNode(name.getText());
        else
            element = Directory.obtainSonNode(name.getText());

        tName.setText(element.getAttribute("name"));
        jCBReading.setSelectedItem(element.getAttribute("reading"));
        jCBWriting.setSelectedItem(element.getAttribute("writing"));

        jCBReading.addActionListener(this);
        jCBWriting.addActionListener(this);
        jPPermission.add(reading);
        jPPermission.add(jCBReading);
        jPPermission.add(writing);
        jPPermission.add(jCBWriting);

        jPButton = new JPanel();
        jPButton.setLayout(new GridLayout(1,2));
        jPButton.setMaximumSize(new Dimension(x/2, 20));
        change = new JButton("Edit");
        cancel = new JButton("Cancel");
        change.addActionListener(this);
        cancel.addActionListener(this);
        jPButton.add(change);
        jPButton.add(cancel);

        jPName.setMaximumSize(new Dimension(x, 25));
        jPPermission.setMaximumSize(new Dimension(x, 60));

        division.setMaximumSize(new Dimension(x, 10));
        division2.setMaximumSize(new Dimension(x, 10));
        division3.setMaximumSize(new Dimension(x, 10));

        window.add(jPName);
        window.add(division);
        window.add(permission);
        window.add(division2);
        window.add(jPPermission);
        window.add(division3);
        window.add(jPButton);

        this.add(window);

        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == change){
            String name = tName.getText();
            String reading = jCBReading.getSelectedItem().toString();
            String writing = jCBWriting.getSelectedItem().toString();
            if(!name.isEmpty() && !reading.isEmpty() && !writing.isEmpty()){
                if(this.elementType.equalsIgnoreCase("Directory")){
                    Directory directory = new Directory(Explorer.getCurrentId(), name, "Directory", reading.equals("Public"), writing.equals("Public"), Explorer.currentUser.getAttribute("name"));
                    directory.edit();
                } else {
                    File file = new File(Explorer.getCurrentId(), name, "File", "Lorem", reading.equals("Public"), reading.equals("Public"), Explorer.currentUser.getAttribute("name"));
                    file.edit();
                }
                this.dispose();
                new ExplorerInterface();
            } else {
                JOptionPane.showMessageDialog(null, "Complete the form", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == cancel){
            this.dispose();
            new ExplorerInterface();
        }
    }
}
