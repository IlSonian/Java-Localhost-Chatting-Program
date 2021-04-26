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
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.AbstractListModel;

public class GroupName extends JFrame {

    private JTextField messagetext;
    private JPanel contentPane;

    createGroup createG = new createGroup();

    public GroupName() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(100, 100, 743, 446);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Chat", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

        panel.setBounds(10, 24, 693, 324);

        contentPane.add(panel);
        panel.setLayout(null);

        JLabel nameTheGroup = new JLabel("Name the group");
        nameTheGroup.setFont(new Font("Tahoma", Font.BOLD, 14));

        nameTheGroup.setBounds(322, -55, 200, 200);

        panel.add(nameTheGroup);

        JLabel usersSelected = new JLabel("Participants: " + createG.selectedGroupUsers(createG.users));
        System.out.println(" The selected blah blah is: " + createG.selectedGroupUsers(createG.users)); // test out to see what's the output
        usersSelected.setFont(new Font("Tahoma", Font.PLAIN, 14));
        usersSelected.setBounds(100, 50, 200, 200);
        panel.add(usersSelected);

        messagetext = new JTextField();

        messagetext.setBounds(61, 269, 149, 28);

        panel.add(messagetext);

        messagetext.setColumns(2);

        JButton createBtn = new JButton("Create");
        createBtn.setBounds(217, 269, 112, 29);
        panel.add(createBtn);

        JButton backButton = new JButton("Back");
        backButton.setBounds(571, 358, 119, 31);
        contentPane.add(backButton);

        createBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //TODO: take the input of the user for group name
                if (e.getSource() == createBtn) {
                Messagelist msgList = new Messagelist();
                msgList.setVisible(true);

                dispose();
                }
            }

        });

        //simply returns to the previous page
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //creating object of Messagelist class
                createGroup obj = new createGroup(Login.array);
                obj.setVisible(true);
                //close current gui
                dispose();

            }
        });

    }

}