/*
Gary Sousa & Stephen Nolan
CS 335
Spring 2017
Instructor: Rick Mercer

Assignment: Project 5 - Jukebox
       Due: Friday 10 March 2017 @ 10:00p


This class creates a Prototype GUI with some useful elements for interacting with a Jukebox

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

// This Jukebox type extends Jframe
public class JukeboxGUIPrototype extends JFrame {


    // main method to run everything
    public static void main(String[] args) {

        JFrame window = new JukeboxGUIPrototype();
        window.pack();
        window.setVisible(true);

        //TODO delete
        JOptionPane.showMessageDialog(null, "Just a prototype - not to be turned in with Iteration 1. Iteration 1 GUI will match specification and Rick's layout");

    } // method main


    // Constructor - GUI is built here
    public JukeboxGUIPrototype() {

        // place the JFrame on the screen
        this.setSize(900, 600);
        // this.setLocation(50, 50);

        // when user clicks 'x' on OS window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // title frame
        this.setTitle("Jukebox");

        // center on screen
        this.setLocationRelativeTo(null);

        // Set up login area and display of user data - MAIN PANEL #1
        JPanel userPanelMain = new JPanel();
        userPanelMain.setLayout(new GridLayout(0, 1));
        this.add(userPanelMain, BorderLayout.WEST);

        JPanel userPanel1 = new JPanel();
        userPanel1.setLayout(new GridLayout(0, 1));
        userPanelMain.add(userPanel1, BorderLayout.NORTH);

        JLabel loginLabel = new JLabel("Log In with your Name and Password");
        userPanel1.add(loginLabel);
        loginLabel.setForeground(Color.BLUE);

        userPanel1.add(new JLabel("Name: "));
        JTextField loginName = new JTextField();
        userPanel1.add(loginName);

        userPanel1.add(new JLabel("Password: "));
        JPasswordField loginPassword = new JPasswordField();
        userPanel1.add(loginPassword);

        userPanel1.add(Box.createRigidArea(new Dimension(0, 2)));

        JButton loginButton = new JButton("Login");
        userPanel1.add(loginButton);

        JButton logoutButton = new JButton("Logout");
        userPanel1.add(logoutButton);

        userPanel1.add(Box.createRigidArea(new Dimension(0, 2)));

        JPanel userPanel2 = new JPanel();
        userPanel2.setLayout(new GridLayout(0, 1));
        userPanelMain.add(userPanel2, BorderLayout.SOUTH);

        JLabel loginAccountDetails = new JLabel("Account Details: ");
        userPanel2.add(loginAccountDetails);

        JTextArea loginAccountDetailsText = new JTextArea();
        loginAccountDetailsText.setEditable(false);
        userPanel2.add(loginAccountDetailsText);

        loginAccountDetailsText.append("---TODO---");

        this.getContentPane().add(Box.createRigidArea(new Dimension(15, 15)), BorderLayout.CENTER);

        // Set up catalog and 'now playing' views - MAIN PANEL #2
        JPanel songPanelMain = new JPanel();
        songPanelMain.setLayout(new GridLayout(0, 1));
        this.add(songPanelMain, BorderLayout.EAST);

        // Now Playing label
        JLabel songNowPlayingLabel = new JLabel("Now Playing: ");
        songPanelMain.add(songNowPlayingLabel);

        // Now Playing View
        JTextArea nowPlayingText = new JTextArea();
        nowPlayingText.setEditable(false);
        songPanelMain.add(nowPlayingText);
        nowPlayingText.append("---TODO---");

        // Select and Play a Song:
        JLabel songSelectionLabel = new JLabel("Select a Song to Play and press \"Play Song\": ");
        songPanelMain.add(songSelectionLabel);

        // Song Selection Area
        JTextArea songSelectionArea = new JTextArea();
        songSelectionArea.setEditable(false);
        songPanelMain.add(songSelectionArea);
        songSelectionArea.append("---TODO---");

        // Button to choose songs
        JButton songPlayButton = new JButton("Play Song");
        songPanelMain.add(songPlayButton);

    } // JukeboxGUI constructor
}
