import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.AbstractListModel;

public class Messagelist extends JFrame {

    // background pane
    private JPanel contentPane;


    // constructor of class
    public Messagelist() {

        // on close action
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //setting x,y axis and width and height
        setBounds(100, 100, 743, 446);

        //initialize main background pane
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        // creating title panel
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Users", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        //setting x,y axis and width and height of panel
        panel.setBounds(243, 29, 206, 230);

        //adding that title panel to background panel
        contentPane.add(panel);
        panel.setLayout(null);

        // creating list that will contain all users
        JList userlist = new JList();
        userlist.setFont(new Font("Tahoma", Font.PLAIN, 12));

        // adding dummy data to list
        userlist.setModel(new AbstractListModel() {
            String[] values = new String[]{"John", "Elizbath", "Jonathan"};

            public int getSize() {
                return values.length;
            }

            public Object getElementAt(int index) {
                return values[index];
            }
        });

        //setting x,y axis and width and height of list
        userlist.setBounds(10, 47, 186, 173);

        //adding list to title panel
        panel.add(userlist);

        // creating combo box for searching any user
        JComboBox allusersdropdown = new JComboBox();

        //adding dummy data to dropdown
        allusersdropdown.setModel(new DefaultComboBoxModel(new String[]{"All", "John", "Elizbath", "Jonathan"}));
        //setting x,y axis and width and height of dropdown
        allusersdropdown.setBounds(145, 16, 51, 21);

        //adding dropdown to title panel
        panel.add(allusersdropdown);

        JButton btnNewButton = new JButton("Global");
        btnNewButton.setBounds(10, 16, 105, 21);
        panel.add(btnNewButton);

        //creating buttton "account"
        JButton btn_account = new JButton("ACCOUNT");

        //setting x,y axis and width and height of button
        btn_account.setBounds(243, 292, 206, 33);

        contentPane.add(btn_account);

        //action of account button
        btn_account.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //creating object of account class
                Account obj = new Account();
                obj.setVisible(true);

                //close current GUI
                dispose();
            }
        });
        //creating button "logout"
        JButton btnLogout = new JButton("logout");

        //action of logout button
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //creating object of login class
                Login obj = new Login();
                obj.setVisible(true);

                //close current GUI
                dispose();
            }
        });
        btnLogout.setBounds(623, 10, 96, 33);
        contentPane.add(btnLogout);

    }
}
