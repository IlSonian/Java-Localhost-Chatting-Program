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
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class Signup extends JFrame {

    static String[] array;
    JButton btn_signup;
    JButton btn_login;
    // constructor of class that will be called while making object of class
    Socket socket;
    // main backgroud pane
    private JPanel contentPane;
    //text field for username
    private JTextField txt_username;
    //password field for user passoword
    private JPasswordField txt_password;
    ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == btn_signup) {
                signUp();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                if (ReceiverFromUser.in) {
                Messagelist obj = new Messagelist();
                obj.setVisible(true);
                //close current gui
                dispose();
                }
                //creating object of messagelist class
            }
            if (e.getSource() == btn_login) {

                Login obj = new Login();
                obj.setVisible(true);
                //close current gui
                dispose();
                //creating object of messagelist class
            }
        }
    };

    public Signup(Socket socket) {
        this.socket = socket;
        // Action of end GUI
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 743, 446);

        //create background pane
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        contentPane.setLayout(null);

        // Title border panel
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Signup", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel.setBounds(172, 68, 395, 230);

        //add title pane to content pane
        contentPane.add(panel);
        panel.setLayout(null);

        // label for USER NAME
        JLabel lblUserName = new JLabel("USER NAME");

        //setting x,y axis and height and width of label
        lblUserName.setBounds(10, 43, 84, 33);

        //add label to Title panel
        panel.add(lblUserName);
        lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 12));

        // label for Password
        JLabel lblPassword = new JLabel("PASSWORD");

        //setting x,y axis and height and width of label
        lblPassword.setBounds(10, 92, 84, 33);

        //add label to Title panel
        panel.add(lblPassword);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));


        // textfield for user name
        txt_username = new JTextField();

        //setting x,y axis and height and width of textfield
        txt_username.setBounds(128, 47, 224, 27);

        //add textfield to title panel
        panel.add(txt_username);
        txt_username.setColumns(10);

        // Action of signup button
        btn_signup = new JButton("SIGN UP");
        btn_signup.addActionListener(actionListener);
        //setting x,y axis and height and width of button
        btn_signup.setBounds(246, 152, 106, 33);
        panel.add(btn_signup);


        // Action of signup button
        btn_login = new JButton("LOGIN");
        btn_login.addActionListener(actionListener);
        //setting x,y axis and height and width of button
        btn_login.setBounds(128, 152, 91, 33);
        panel.add(btn_login);


        //password textfield for user input password
        txt_password = new JPasswordField();
        txt_password.setBounds(128, 92, 224, 27);

        //adding password field to panel
        panel.add(txt_password);
    }

    static void importUserfromServer(String totalUser) {
        // adding dummy data to list
        totalUser = totalUser.replace("[", "");
        totalUser = totalUser.replace("]", "");
        System.out.println();
        array = totalUser.split(",");
        for (int i = 0; i < array.length; i++) {
            array[i].trim();
        }
    }

    public void signUp() {
        try {
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println("up " + txt_username.getText() + " " + String.valueOf(txt_password.getPassword()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}