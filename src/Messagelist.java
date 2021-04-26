import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.print.CancelablePrintJob;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.AbstractListModel;

public class Messagelist extends JFrame {

    static JList userlist;
    JPanel panel;
    public int i;
    String clickedName;

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
        setLocationRelativeTo(null);
        contentPane.setLayout(null);


        // creating title panel
        panel = new JPanel();
        panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Users", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        //setting x,y axis and width and height of panel
        panel.setBounds(243, 29, 206, 230);

        //adding that title panel to background panel
        contentPane.add(panel);
        panel.setLayout(null);

 
      
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String s : (ReceiverFromUser.getAllUsers())) {
          model.addElement(s);
        }
   
        userlist = new JList(model);
        
        // creating list that will contain all users
        ActionListener animation = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                 model.removeAllElements();
                for (String s : (ReceiverFromUser.getAllUsers())) {
                	
                    model.addElement(s);
                  }  
             
               repaint();
            }

        };
        Timer timer = new Timer(1000, animation);
        timer.start();
        
        //creates a Mouse Listener if the user gets clicked on
        userlist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 0) {
                    clickedName = (String) userlist.getSelectedValue();
                            System.out.println(userlist.getSelectedValue());
                            Chat chat = new Chat();
                            chat.setVisible(true);
                            dispose();

                }
            }
        });

        userlist.setFont(new Font("Tahoma", Font.PLAIN, 12));

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

        JButton btnNewButton = new JButton("Create Group");
        btnNewButton.setBounds(10, 25, 105, 21);
        panel.add(btnNewButton);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //creating object of login class
                createGroup group = new createGroup(Login.array);
                group.setVisible(true);

                //close current GUI
                dispose();
            }
        });

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


 

    // getClickedName method is to be able to call the name of the selected user from the list
    public String getClickedName(){
        return (String) userlist.getSelectedValue();
    }

}