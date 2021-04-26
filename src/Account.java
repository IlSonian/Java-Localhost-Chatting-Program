import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Account extends JFrame {

    // main background pane
    private JPanel contentPane;
    String[] array ;

    // constructor of class to run GUI

    

    public Account() {


        // on close GUI action
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setting x,y axis and width and height of main background panel
        setBounds(100, 100, 743, 446);
        // initialize backgroud pane
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        contentPane.setLayout(null);

        // creating title pane
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Account", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

        //setting x,y axis and width and height of title pane
        panel.setBounds(172, 68, 395, 230);
        contentPane.add(panel);
        panel.setLayout(null);


        // label for User name
        //TODO need to get the name of the registered user (tried multiple things, all gave me null)
        JLabel lblUserName = new JLabel(("username"));

        //setting x,y axis and width and height of label user
        lblUserName.setBounds(74, 43, 84, 33);

        // adding label to title pane
        panel.add(lblUserName);
        lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 12));

        // creating button "back"
        JButton backbutton = new JButton("BACK");

        //action of back button
        backbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                // creating object of class to change GUI
                Messagelist obj = new Messagelist();
                obj.setVisible(true);

                //close currrent GUi
                dispose();
            }
        });

        //setting x,y axis and width and height of back button
        backbutton.setBounds(74, 138, 224, 33);

        //adding buttton to title panel
        panel.add(backbutton);

        // create edit button
        JButton editbutton = new JButton("Edit");

        //setting x,y axis and width and height of edit button
        editbutton.setBounds(209, 44, 91, 33);

        // adding edit button to title panel
        panel.add(editbutton);

        // creating export button
        JButton btnImport = new JButton("Import");

        // adding action of import button
        btnImport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // cereating object of chat class
                //Chat obj = new Chat();
               // obj.setVisible(true);

                // close current GUI
                dispose();
            }
        });
        //setting x,y axis and width and height of button import
        btnImport.setBounds(256, 187, 129, 33);
        //adding import button to title panel
        panel.add(btnImport);
    }
}