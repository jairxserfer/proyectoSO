package explorer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class StartingInterface extends JFrame implements ActionListener{
    private JPanel window, nameLabel, passLabel, buttonLabel, divisor;
    private JLabel header, name, pass;
    private JButton adding, accountCreate, out;
    private JTextField nameTF;
    private JPasswordField passwordField;

    public StartingInterface(){
        super("Login");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int xx = screenSize.width;
        int yy = screenSize.height;
        int x = 600;
        int y = 200;
        this.setSize(x, y);
        this.setLocation((xx-x)/2, (yy-y)/2);

        window = new JPanel();
        window.setLayout(new BoxLayout(window, BoxLayout.Y_AXIS));
        window.setSize(x,y);

        header = new JLabel("Start Session");

        nameLabel = new JPanel(new GridLayout(1,2));
        nameLabel.setMaximumSize(new Dimension(x,20));
        name = new JLabel("User Name");
        nameTF = new JTextField();
        nameLabel.add(name);
        nameLabel.add(nameTF);

        passLabel = new JPanel(new GridLayout(1,3));
        passLabel.setMaximumSize(new Dimension(x,20));
        pass = new JLabel("Password");
        passwordField = new JPasswordField();
        passLabel.add(pass);
        passLabel.add(passwordField);

        divisor = new JPanel();
        divisor.setMaximumSize(new Dimension(x,40));

        adding = new JButton("Log In");
        accountCreate = new JButton("Create Account");
        out = new JButton("log out");
        adding.addActionListener(this);
        accountCreate.addActionListener(this);
        out.addActionListener(this);
        buttonLabel = new JPanel();
        buttonLabel.setLayout(new GridLayout(1,2));
        buttonLabel.setMaximumSize(new Dimension((int)(x*0.8),20));
        buttonLabel.add(adding);
        buttonLabel.add(accountCreate);
        buttonLabel.add(out);

        window.add(header);
        window.add(nameLabel);
        window.add(passLabel);
        window.add(divisor);
        window.add(buttonLabel);

        this.add(window);
        this.setResizable(false);
        this.setBackground(Color.LIGHT_GRAY);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameTF.getText();
        String pass = passwordField.getText();
        if(e.getSource() == adding){
            if(!name.isEmpty() && !pass.isEmpty()){
                User user = new User(User.getCurrentId(), name, "", pass);
                if(user.existingUser()){
                    user.startSession();
                    this.dispose();
                    new ExplorerInterface();
                }else {
                    JOptionPane.showMessageDialog(null, "This user don't exist", "The user doesn't exist", JOptionPane.ERROR_MESSAGE);
                }
            } else
                JOptionPane.showMessageDialog(null, "Write a name and password", "Incomplete data", JOptionPane.ERROR_MESSAGE);
        }
        if(e.getSource() == accountCreate){
            this.dispose();
            new RegisterInterface();
        }
        if(e.getSource() == out){
            this.dispose();
        }
    }
}
