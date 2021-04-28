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
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;

public class Account extends JFrame {

    // main background pane
    private JPanel contentPane;
    String[] array ;
    JButton editbutton;
    JButton editbutton2;
    JButton change;

    JButton deleteAccount;

    JButton btnImport;
    JTextField usernametext;
    JTextField newUsernameTxt;
    JTextField optext;
    JTextField nptext;
    // constructor of class to run GUI
    ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == editbutton) {
                OutputStream output;
                try {
                    output = ReceiverFromUser.socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(output, true);
                    writer.println("!#"+usernametext.getText());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                ReceiverFromUser.myUsername = usernametext.getText();

            }

            if (e.getSource() == deleteAccount) {
                OutputStream output;
                try {
                    output = ReceiverFromUser.socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(output, true);
                    writer.println("!!");
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                Login obj = new Login();
                obj.setVisible(true);
                //close currrent GUi
                dispose();
            }

            if (e.getSource() == editbutton2) {
                if (optext.getText().equals(ReceiverFromUser.mypassword)) {
                    OutputStream output;
                    try {
                        output = ReceiverFromUser.socket.getOutputStream();
                        PrintWriter writer = new PrintWriter(output, true);
                        writer.println("$#"+nptext.getText());
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                    ReceiverFromUser.mypassword = nptext.getText();
                } else {
                    JOptionPane.showMessageDialog(
                            null, "Wrong password", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
            if (e.getSource() == btnImport) {

            }
        }
    };


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
        usernametext = new JTextField();
        newUsernameTxt = new JTextField();

        optext =  new JTextField();
        nptext =  new JTextField();

        JLabel lblUserName = new JLabel(("Username: "));


        JLabel password = new JLabel(("Old Password: "));
        password.setBounds(45, 73, 84, 30);

        optext.setBounds(130, 78, 91, 30);

        password.setFont(new Font("Tahoma", Font.PLAIN, 12));
        panel.add(password);
        panel.add(optext);
        JLabel newpassword = new JLabel(("New Password: "));
        newpassword.setBounds(45, 105, 90, 30);
        nptext.setBounds(130, 105, 91, 30);

        newpassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
        panel.add(newpassword);
        panel.add(nptext);


        usernametext.setText(ReceiverFromUser.myUsername);
        //setting x,y axis and width and height of label user
        lblUserName.setBounds(45, 43, 84, 33);
        usernametext.setBounds(130, 44, 91, 33);
        // adding label to title pane
        panel.add(usernametext);
        panel.add(lblUserName);
        lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 12));


        deleteAccount = new JButton("Delete Account");

        deleteAccount.setBounds(15, 187, 129, 33);

        panel.add(deleteAccount);


        deleteAccount.addActionListener(actionListener);
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
        editbutton = new JButton("Edit");
        editbutton2 = new JButton("Edit");
        //setting x,y axis and width and height of edit button
        editbutton.setBounds(230, 47, 91, 25);
        editbutton.addActionListener(actionListener);

        editbutton2.setBounds(230, 77, 91, 25);
        editbutton2.addActionListener(actionListener);
        // adding edit button to title panel
        panel.add(editbutton);
        panel.add(editbutton2);

        // creating export button
        btnImport = new JButton("Import");
        btnImport.addActionListener(actionListener);
        // adding action of import button
        /*
        btnImport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // cereating object of chat class
                //Chat obj = new Chat();
               // obj.setVisible(true);
                // close current GUI
                dispose();
            }
        });*/
        //setting x,y axis and width and height of button import
        btnImport.setBounds(256, 187, 129, 33);
        //adding import button to title panel
        panel.add(btnImport);
    }
}
