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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

    // main background pane
    private JPanel contentPane;

    //text field for username
    private JTextField txt_username;

    //password field for user password
    private JPasswordField txt_password;
    JButton btn_login;
    Socket socket;
    static boolean in = false;
    //main method to run the gui as thread

    public Login() {

        // Action of end GUI
    	try {
			socket =  new Socket("localhost", 8989);
			new ListenServer(socket, this).start();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
        panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Login", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
        JButton btn_signup = new JButton("SIGN UP");
        btn_signup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //creating object of signup class
                Signup obj = new Signup(socket);
                obj.setVisible(true);

                //close current gui
                dispose();
            }
        });

        //setting x,y axis and height and width of button
        btn_signup.setBounds(246, 152, 106, 33);
        panel.add(btn_signup);

        //Action of Login button
        btn_login = new JButton("LOGIN");
        btn_login.addActionListener(actionListener);

        //setting x,y axis and height and width of button
        btn_login.setBounds(128, 152, 91, 33);

        //adding button to title pane
        panel.add(btn_login);

        //password textfield for user input password
        txt_password = new JPasswordField();
        txt_password.setBounds(128, 92, 224, 27);

        //adding password field to panel
        panel.add(txt_password);
    }
    
	ActionListener actionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btn_login) {
			 	verifyServer();
                //creating object of messagelist class
			 	if(in) {
                Messagelist obj = new Messagelist();
                obj.setVisible(true);
                //close current gui
                dispose();
			 	}
			}
		}
	};
    
	
	static void logintrue(boolean login) {
		in = login;
	}
	
    void verifyServer() {
    	try {			
			OutputStream output = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(output, true);
			writer.println("in " + txt_username.getText() + " " + String.valueOf(txt_password.getPassword()));
            System.out.println(in);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }


    // constructor of class that will be called while making object of class

    public static void main(String[] args) {
        //thread
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    //	make object of Login class
                	
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}