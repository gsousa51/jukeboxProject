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

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Jukebox;

// A JPanel, for modularity
public class LoginPanel extends JPanel implements Observer {


    // Constructor 
    public LoginPanel(Jukebox jukebox) {

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
        loginPassword.setEchoChar('•');
        this.add(loginPassword);

        // log in/out (wording and order to match Rick's GUI)
        JButton signOutButton = new JButton("Sign out");
        signOutButton.addActionListener(event -> 
                attemptSignOut());
        this.add(signOutButton);

        JButton loginButton = new JButton("Attempt login");
        loginButton.addActionListener(event -> 
                attemptLogIn());
        this.add(loginButton);

        // account information for logged in user
        this.add(new JLabel("Status: ", SwingConstants.RIGHT));
        this.add(new JLabel("X played, xx:xx:xx TODO"));

    } // JukeboxGUI constructor


    private boolean attemptLogIn() {


        // TODO delete
        return false;
    }



    private boolean attemptSignOut() {

        // TODO delete
        return false;
    }


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}




}
