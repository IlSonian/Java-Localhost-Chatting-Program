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
import javax.swing.JTextArea;

public class Chat extends JFrame {

    // main background pane
    private JPanel contentPane;

    //text field to type message that to send
    private JTextField messagetext;


    //constructor of class containing gui code
    public Chat() {

        //action on close gui
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //setting x,y axis and width and height of mainpane
        setBounds(100, 100, 743, 446);

        //initailizing background panel
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //create titled panel for data
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Chat", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

        //setting x,y axis and width and height of title panel
        panel.setBounds(10, 24, 693, 324);
        //adding title panel to background main panel
        contentPane.add(panel);
        panel.setLayout(null);

        // label to show name of user
        JLabel label_userName = new JLabel("Elizbath");
        label_userName.setFont(new Font("Tahoma", Font.BOLD, 14));

        //setting x,y axis and width and height of user name label
        label_userName.setBounds(322, 31, 78, 23);

        //adding label to panel
        panel.add(label_userName);

        //creating textarea for user chat
        JTextArea chatArea = new JTextArea();

        //setting x,y axis and width and height of user chat text area
        chatArea.setBounds(61, 98, 268, 161);

        //adding textarea to title panel
        panel.add(chatArea);

        // initializing textfield where to type message
        messagetext = new JTextField();

        //setting x,y axis and width and height of textfield
        messagetext.setBounds(61, 269, 149, 28);

        // adding textfield to title panel
        panel.add(messagetext);

        //setting textfield size
        messagetext.setColumns(10);

        // create send button to send message
        JButton sendButton = new JButton("SEND");

        //setting x,y axis and width and height of send button
        sendButton.setBounds(217, 269, 112, 29);

        // add button to title panel
        panel.add(sendButton);

        // create export button
        JButton exportButton = new JButton("Export");

        //setting x,y axis and width and height of export button
        exportButton.setBounds(571, 358, 119, 31);

        //adding buttton to background pane
        contentPane.add(exportButton);

        // action of back button action
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //creating object of Messagelist class
                Messagelist obj = new Messagelist();
                obj.setVisible(true);
                //close current gui
                dispose();
            }
        });
        //setting x,y axis and width and height back Button
        backButton.setBounds(20, 363, 119, 31);
        //adding button to main pane
        contentPane.add(backButton);
    }
}
