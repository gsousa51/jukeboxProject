/*
Gary Sousa & Stephen Nolan
CS 335
Spring 2017
Instructor: Rick Mercer

Assignment: Project 5 - Jukebox
       Due: Friday 10 March 2017 @ 10:00p


This class creates a JPanel that allows Jukebox users to authenticate. It also 
displays some information about the account if a user is logged in

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

        // Set up login area and display of user data - MAIN PANEL #1
        // JPanel userPanelMain = new JPanel();
        // userPanelMain.setLayout(new GridLayout(0, 1));
        // this.add(userPanelMain, BorderLayout.WEST);

        this.setLayout(new GridLayout(4, 2));

        // // JPanel userPanel1 = new JPanel();
        // // userPanel1.setLayout(new GridLayout(0, 1));
        // this.add(userPanel1, BorderLayout.NORTH);

        // JLabel loginLabel = new JLabel("Log In with your Name and Password");
        // userPanel1.add(loginLabel);
        // loginLabel.setForeground(Color.BLUE);

        this.add(new JLabel("Account Name: ", SwingConstants.RIGHT));
        JTextField loginName = new JTextField(15);
        this.add(loginName);

        this.add(new JLabel("Password: ", SwingConstants.RIGHT));
        JPasswordField loginPassword = new JPasswordField();
        this.add(loginPassword);

        // userPanel1.add(Box.createRigidArea(new Dimension(0, 2)));

        JButton signOutButton = new JButton("Sign out");
        this.add(signOutButton);

        JButton loginButton = new JButton("Login");
        this.add(loginButton);

        this.add(new JLabel("Status: ", SwingConstants.RIGHT));
        this.add(new JLabel("X played, xx:xx:xx TODO"));
        // userPanel1.add(Box.createRigidArea(new Dimension(0, 2)));

//         JPanel userPanel2 = new JPanel();
//         userPanel2.setLayout(new GridLayout(0, 1));
//         this.add(userPanel2, BorderLayout.SOUTH);
// 
//         JLabel loginAccountDetails = new JLabel("Account Details: ");
//         userPanel2.add(loginAccountDetails);
// 
//         JTextArea loginAccountDetailsText = new JTextArea();
//         loginAccountDetailsText.setEditable(false);
//         userPanel2.add(loginAccountDetailsText);
// 
//         loginAccountDetailsText.append("---TODO---");
// 
        //this.getContentPane().add(Box.createRigidArea(new Dimension(15, 15)), BorderLayout.CENTER);

//         // Set up catalog and 'now playing' views - MAIN PANEL #2
//         JPanel songPanelMain = new JPanel();
//         songPanelMain.setLayout(new GridLayout(0, 1));
//         this.add(songPanelMain, BorderLayout.EAST);
// 
//         // Now Playing label
//         JLabel songNowPlayingLabel = new JLabel("Now Playing: ");
//         songPanelMain.add(songNowPlayingLabel);
// 
//         // Now Playing View
//         JTextArea nowPlayingText = new JTextArea();
//         nowPlayingText.setEditable(false);
//         songPanelMain.add(nowPlayingText);
//         nowPlayingText.append("---TODO---");
// 
//         // Select and Play a Song:
//         JLabel songSelectionLabel = new JLabel("Select a Song to Play and press \"Play Song\": ");
//         songPanelMain.add(songSelectionLabel);
// 
//         // Song Selection Area
//         JTextArea songSelectionArea = new JTextArea();
//         songSelectionArea.setEditable(false);
//         songPanelMain.add(songSelectionArea);
//         songSelectionArea.append("---TODO---");
// 
//         // Button to choose songs
//         JButton songPlayButton = new JButton("Play Song");
//         songPanelMain.add(songPlayButton);

    } // JukeboxGUI constructor
}
