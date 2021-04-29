import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Messagelist.java
 * <p>
 * message list class
 * <p>
 * a list of your sources of help (if any)
 *
 * @author Project 5 group
 * @version 4/29/2021
 */
public class Messagelist extends JFrame {

    static JList userlist;
    public int i;
    JPanel panel;
    String clickedName;
    JLabel userSeacrhLabel;
    JTextField txtSearch;
    JButton btnSearch;
    JButton btnDelete;


    private static String searchedUser; // to see the user that got searched in the search/dm feature


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

        ReceiverFromUser.removedList.add("____");
        // creating list that will contain all users
        ActionListener animation = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                model.removeAllElements();
                for (String s : (ReceiverFromUser.getAllUsers())) {
                    if (!ReceiverFromUser.removedList.contains(s))
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
                    String grouppy = (String) userlist.getSelectedValue();
                    if (grouppy != null)
                        if (grouppy.substring(0, 1).equals("{")) {
                            grouppy = grouppy.replace("{", "[");
                            grouppy = grouppy.replace("}", "]");
                            grouppy = grouppy.replace(";", ",");

                        }
                    Chat chat = new Chat(grouppy);
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

        userSeacrhLabel = new JLabel();
        userSeacrhLabel.setText("Send DM/delete chat");
        userSeacrhLabel.setBounds(80, 50, 170, 21);
        contentPane.add(userSeacrhLabel);

        txtSearch = new JTextField();
        txtSearch.setText("");
        txtSearch.setEditable(true);
        txtSearch.setBounds(80, 70, 106, 21);
        contentPane.add(txtSearch);

        btnSearch = new JButton();
        btnSearch.setText("Chat");
        btnSearch.setBounds(80, 94, 106, 21);
        contentPane.add(btnSearch);

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchedUser = txtSearch.getText();

                //	if (Arrays.asList(ReceiverFromUser.getAllUsers()).contains(searchedUser) || ReceiverFromUser.removedList.contains(searchedUser)) {
                Chat chat = new Chat(txtSearch.getText());
                chat.setVisible(true);
                dispose();

                //	} else JOptionPane.showMessageDialog(
                //			null, "Username does not exist", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnDelete = new JButton();
        btnDelete.setText("Remove user");
        btnDelete.setBounds(80, 120, 115, 25);
        contentPane.add(btnDelete);

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchedUser = txtSearch.getText();
                ReceiverFromUser.removedList.add(searchedUser);

                OutputStream output;
                try {
                    output = ReceiverFromUser.socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(output, true);

                    writer.println("*!" + searchedUser);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                if (Arrays.asList(ReceiverFromUser.getAllUsers()).contains(searchedUser)) {
                    int index = model.indexOf(searchedUser);

                    List<String> list = new ArrayList<>(Arrays.asList(ReceiverFromUser.array));
                    list.remove(index);
                    ReceiverFromUser.array = list.toArray(new String[0]);
                } else JOptionPane.showMessageDialog(
                        null, "Username does not exist to be removed", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //creating object of login class
                createGroup group = new createGroup(ReceiverFromUser.getAllUsers());
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
                ReceiverFromUser.in = false;
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
    public String getClickedName() {
        return (String) userlist.getSelectedValue();
    }

}
