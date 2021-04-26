import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class Chat extends JFrame {

    String[] array;
    Messagelist messagelist = new Messagelist();
    // main background pane
    private JPanel contentPane;
    //text field to type message that to send
    private JTextField messagetext;
    String sendDm;
    JButton sendButton;
    static JTextArea chatArea;
    ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == sendButton) {
                //creating object of messagelist class]
            	chatArea.append( "You: "+ messagetext.getText() + "\n" );
            	OutputStream output;
				try {
					output = ReceiverFromUser.socket.getOutputStream();
					PrintWriter writer = new PrintWriter(output, true);
		            writer.println(sendDm.trim() + " " + messagetext.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
             
            }
        }
    };
    public static  void setSendMessage(String display) {
    	chatArea.append( display + "\n" );
	}
    //constructor of class containing gui code
    public Chat(String talktoUser) {
    	sendDm = talktoUser;
    	
    	if(sendDm.substring(0,1).equals("[")) {
    		sendDm = sendDm.replace("[", "");
    		sendDm = sendDm.replace("]", "");
 	        System.out.println(sendDm);
 	        array = sendDm.split(",");
 	        sendDm = "";
 	        for (int i = 0; i < array.length; i++) {
 	        	 array[i] =  array[i].trim();
 	        	 sendDm += "@" +  array[i] +" ";
 	        }
    	}else {
    		sendDm = "@" + sendDm.trim();
    	}
        //action on close gui
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //setting x,y axis and width and height of mainpane
        setBounds(100, 100, 743, 446);

        //initailizing background panel
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        contentPane.setLayout(null);

        //create titled panel for data
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Chat to "+talktoUser, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

        //setting x,y axis and width and height of title panel
        panel.setBounds(10, 24, 693, 324);
        //adding title panel to background main panel
        contentPane.add(panel);
        panel.setLayout(null);

        // label to show name of user
        JLabel label_userName = new JLabel(getClickedName());
        label_userName.setFont(new Font("Tahoma", Font.BOLD, 14));

        //setting x,y axis and width and height of user name label
        label_userName.setBounds(322, 31, 78, 23);

        //adding label to panel
        panel.add(label_userName);

        //creating textarea for user chat
        chatArea = new JTextArea();

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
        sendButton = new JButton("SEND");

        //setting x,y axis and width and height of send button
        sendButton.setBounds(217, 269, 112, 29);
        sendButton.addActionListener(actionListener);
        // add button to title panel
        panel.add(sendButton);

        // create export button
        JButton exportButton = new JButton("Export");

        //setting x,y axis and width and height of export button
        exportButton.setBounds(571, 358, 119, 31);

        //adding buttton to background pane
        contentPane.add(exportButton);

        // TODO: add action listener if user clicks on send button
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == sendButton) {
                    //add text to chatArea
                }
        }
    });
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

    //creates a local method of the getClickedName, idk why (I was trying a bunch of things until this worked)
    public String getClickedName() {
        return messagelist.getClickedName();
    }
}