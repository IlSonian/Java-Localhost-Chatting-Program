import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.EtchedBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;

public class Chat extends JFrame implements DocumentListener{

	String[] array;
	Messagelist messagelist = new Messagelist();
	// main background pane
	private JPanel contentPane;
	//text field to type message that to send
	private JTextField messagetext;
	String sendDm;
	JButton sendButton;
	JButton exportButton;
	JButton importdata;


	static JTextArea chatArea;
	private static JScrollPane scrollPane;
	

	public void forceChange() {
		OutputStream output;
		try {
			output = ReceiverFromUser.socket.getOutputStream();
			PrintWriter writer = new PrintWriter(output, true);
			
			writer.println("**"+sendDm.trim() + "_" + chatArea.getText().replaceAll("[\\\t|\\\n|\\\r]","\\\\n"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	  public void insertUpdate(DocumentEvent e)
	  {
		  forceChange();
	  }
	  public void removeUpdate(DocumentEvent e)
	  {
		  forceChange();

	  }
	  public void changedUpdate(DocumentEvent e)
	  {
		  forceChange();

	  }
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
			if (e.getSource() == importdata) {
				String line = "";  

				try   
				{  

					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", "csv");
					chooser.setFileFilter(filter);
					int returnVal = chooser.showOpenDialog(null);
					if(returnVal == JFileChooser.APPROVE_OPTION) {
						System.out.println("You chose to open this file: " +
								chooser.getSelectedFile().getName());
					}
					//parsing a CSV file into BufferedReader class constructor  
					BufferedReader br = new BufferedReader(new FileReader(chooser.getSelectedFile().getAbsolutePath()));  
					while ((line = br.readLine()) != null)   //returns a Boolean value  
					{  
						// use comma as separator  
						setSendMessage(line.replace(",", " "));
					}  
				}   
				catch (IOException e1)   
				{  
					e1.printStackTrace();  
				}  




			}
			if (e.getSource() == exportButton) {
				try (PrintWriter writer = new PrintWriter(new File("data.csv"))) {

					StringBuilder sb = new StringBuilder();
					String headerForStuff = "Date, Time, User, Content";
					sb.append(headerForStuff);
					sb.append('\n');
					sb.append(chatArea.getText().replace(" ", ","));
					sb.append('\n');

					writer.write(sb.toString());

					JOptionPane.showMessageDialog(
							null, "Your file is at your current directory", "Done", JOptionPane.INFORMATION_MESSAGE);

				} catch (FileNotFoundException e3) {
					System.out.println(e3.getMessage());
				}

			}
		}
	};

	public static void setSendMessage(String display) {
		chatArea.append( display + "\n" );
	}
	//constructor of class containing gui code
	public Chat(String talktoUser) {
		sendDm = talktoUser;
		if(sendDm.substring(0,1).equals("{")) {
			sendDm = sendDm.replace("{", "[");
			sendDm = sendDm.replace("}", "]");
			sendDm = sendDm.replace(";", ",");
		}
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

		chatArea.setEditable(true);

		//setting x,y axis and width and height of user chat text area
		chatArea.setBounds(61, 60, 600, 200);
		chatArea.getDocument().addDocumentListener(this);

		//TODO: find out why the scroll pane is not working
		// scrollPane = new JScrollPane();

		scrollPane = new JScrollPane(chatArea);
		scrollPane.setBounds(61, 60, 600, 200);

		//add(scrollPane);
		panel.add(scrollPane, BorderLayout.CENTER);
		//adding textarea to title panel
		//panel.add(chatArea);


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

		importdata = new JButton("Import chat");


		importdata.setBounds(548, 269, 112, 29);


		importdata.addActionListener(actionListener);


		panel.add(importdata);


		//setting x,y axis and width and height of send button
		sendButton.setBounds(217, 269, 112, 29);
		sendButton.addActionListener(actionListener);
		// add button to title panel
		panel.add(sendButton);

		// create export button
		exportButton = new JButton("Export");

		//setting x,y axis and width and height of export button
		exportButton.setBounds(571, 358, 119, 31);

		//adding buttton to background pane
		contentPane.add(exportButton);

		exportButton.addActionListener(actionListener);
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

		OutputStream output;
		try {
			output = ReceiverFromUser.socket.getOutputStream();
			PrintWriter writer = new PrintWriter(output, true);
			writer.println("##"+talktoUser);
			System.out.println("##"+talktoUser);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	//creates a local method of the getClickedName, idk why (I was trying a bunch of things until this worked)
	public String getClickedName() {
		return messagelist.getClickedName();
	}
}