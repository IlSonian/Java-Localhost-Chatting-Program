import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
public class Main extends JComponent implements Runnable {
	private Image image; // the canvas
	private Graphics2D graphics2D;  // this will enable drawing
	JTextArea jTextArea = new JTextArea();
	String username;
	JButton sendButton; // a button to change paint color
	JTextField chatField; // text field for input
	Socket socket;
	Main paint; // variable of the type ColorPicker
	/* action listener for buttons */
	String userName = "bob"+Math.random();

	ActionListener actionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == sendButton) {
				jTextArea.append( getUserName()+ ": "+ chatField.getText() + "\n" );
				getSendMessage();
			}
		}
	};

	int i = 0;

	public String getSendMessage() {
		try {

			OutputStream output = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(output, true);
			if (i == 0) {
				writer.println(userName);
				i++;
			}
			writer.println(chatField.getText());
		} catch (Exception e) {}
		return chatField.getText();
	}

	public void setSendMessage(String display) {
		jTextArea.append( display + "\n" );
	}

	public void send() {
		this.repaint();
	}


	public Main() {
		try {
			socket = new Socket("localhost", 8989);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new ReadThread(socket, this).start();
		setUserName(userName);
		//new SendThread(socket, this).start();

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Main());
	}

	public void run() {
		/* set up JFrame */
		JFrame frame = new JFrame("Chat stuff");
		Container content = frame.getContentPane();
		content.setLayout(new BorderLayout());
		paint = new Main();
		content.add(paint, BorderLayout.CENTER);

		sendButton = new JButton("Send");
		sendButton.addActionListener(actionListener);

		chatField = new JTextField(10);
		chatField.setText("");

		JPanel panel = new JPanel();
		panel.add(chatField);
		panel.add(sendButton);

		content.add(panel, BorderLayout.SOUTH);
		frame.add(jTextArea);
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}


	protected void paintComponent(Graphics g) {

	}


	public void setUserName(String userName) {
		username = userName;

	}


	public String getUserName() {
		return username;
	}
}