import javax.sound.midi.Soundbank;
import javax.swing.*;
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
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.AbstractListModel;

public class createGroup extends JFrame {

	StringBuilder selectedUsers = new StringBuilder();

	static JList allUsers;
	JPanel panel;
	static ArrayList<String> users = new ArrayList<>();
	private JPanel contentPane;


	public createGroup(String[] array) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 743, 446);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		// creating title panel
		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Choose Group Members.", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		//setting x,y axis and width and height of panel
		panel.setBounds(243, 29, 206, 230);

		contentPane.add(panel);
		panel.setLayout(null);

		allUsers = new JList(ReceiverFromUser.getAllUsers());


		allUsers.setFont(new Font("Tahoma", Font.BOLD, 12));

		allUsers.setBounds(10, 47, 186, 173);

		panel.add(allUsers);

		JButton btn_back = new JButton("Back");

		//setting x,y axis and width and height of button
		btn_back.setBounds(243, 292, 206, 33);

		contentPane.add(btn_back);

		//action of account button
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//creating object of account class
				Messagelist msg = new Messagelist();
				msg.setVisible(true);

				//close current GUI
				dispose();
			}
		});

		JButton addToGroup = new JButton("Add to group");
		addToGroup.setBounds(10, 25, 105, 21);
		panel.add(addToGroup);

		//get the selected users and open the next GUI that prompts the user for the group name

		addToGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("Selected users: ");
					List index = allUsers.getSelectedValuesList();
					test ( (ArrayList<String>)index);
					users = (ArrayList<String>) index;
					System.out.println(users); // this outputs the users selected (just to check, it is correct)

					//creating object of login class
					Chat chat = new Chat(users.toString());

					//GroupName groupName = new GroupName();
					chat.setVisible(true);

					//close current GUI
					dispose();
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(
							null, "Please select at least one participant", "Error", JOptionPane.ERROR_MESSAGE);
				}

			}

		});

	}
	static void test(ArrayList<String> index) {
		users = index;
	}

	public createGroup() {

	}

	//Method to get the users selected to create the group
	//TODO: figure out the issue as to why it's not converting the ArrayList to a String properly
	public String selectedGroupUsers(ArrayList<String> users) {

		for (String all : users) {
			selectedUsers.append(all).append("\t");
		}
		return selectedUsers.toString();
	}
}