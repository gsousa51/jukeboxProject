/*
Gary Sousa & Stephen Nolan
CS 335
Spring 2017
Instructor: Rick Mercer

Assignment: Project 5 - Jukebox
       Due: Friday 10 March 2017 @ 10:00p


This class creates a JPanel that allows Jukebox users to authenticate. It also 
displays some information about the account if a user is logged in

** NOTE - design 'choices' here are to match Rick's provided GUI: labels, etc

*/

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

// A JPanel, for modularity
public class LoginPanel extends JPanel {


    // Constructor 
    public LoginPanel() {

        // set layout and background color to match Rick's GUI
        this.setLayout(new GridLayout(4, 2));
        this.setBackground(Color.WHITE);

        // account name entry
        this.add(new JLabel("Account Name: ", SwingConstants.RIGHT));
        JTextField loginName = new JTextField(15);
        this.add(loginName);

        // password entry
        this.add(new JLabel("Password: ", SwingConstants.RIGHT));
        JPasswordField loginPassword = new JPasswordField();
        this.add(loginPassword);

        // log in/out (wording and order to match Rick's GUI)
        JButton signOutButton = new JButton("Sign out");
        signOutButton.addActionListener(event -> 
                System.out.println("Sign out"));
        this.add(signOutButton);

        JButton loginButton = new JButton("Attempt login");
        loginButton.addActionListener(event -> {
                System.out.println("Attempt login:");
                System.out.print("[-]User: ");
                System.out.println("----");
                System.out.print("[-]Password: ");
                System.out.println("----");});
        this.add(loginButton);

        // account information for logged in user
        this.add(new JLabel("Status: ", SwingConstants.RIGHT));
        this.add(new JLabel("X played, xx:xx:xx TODO"));

    } // JukeboxGUI constructor
}
