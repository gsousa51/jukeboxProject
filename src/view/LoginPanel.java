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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Jukebox;

// A JPanel, for modularity
public class LoginPanel extends JPanel implements Observer {

    // Instance Variables
    private Jukebox jukebox;

    // Constructor 
    public LoginPanel(Jukebox jukebox) {

        // contain the jukebox
        this.jukebox = jukebox;

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
        // sign out
        JButton signOutButton = new JButton("Sign out");
        signOutButton.addActionListener(event -> 
                attemptSignOut());
        this.add(signOutButton);

        // login
        JButton loginButton = new JButton("Attempt login");
        loginButton.addActionListener(event -> 
                attemptLogIn(loginName.getText(), loginPassword.getText()));
        this.add(loginButton);

        // TODO make sure to initialize this as per rick's demonstrated behavior
        // account information for logged in user
        this.add(new JLabel("Status: ", SwingConstants.RIGHT));
        this.add(new JLabel("X played, xx:xx:xx TODO"));

    } // JukeboxGUI constructor


    private boolean attemptLogIn(String name, String password) {

        // does the user exist in the account collection
        if (jukebox.getAccountCollection().getAccount(name) == null) {
            
            JOptionPane.showMessageDialog(null, "Invalid Account.");
            return false;
        }

        // the user exists in the collection, does the password match?
        else if (! jukebox.getAccountCollection().getAccount(name).getPassword().equals(password)) {

            JOptionPane.showMessageDialog(null, "Invalid Password.");
            return false;
        }
        
        // password matched for valid user found in account collection
        else {

            JOptionPane.showMessageDialog(null, "Welcome, " + name + ".");
            
            // set this user as the current user in the account collection
            jukebox.getAccountCollection().setCurrentUser(jukebox.getAccountCollection().getAccount(name));

            return false;

            // TODO fetch metrics and display
        }

    }



    private boolean attemptSignOut() {

        // nobody is currently signed in
        if (jukebox.getAccountCollection().getCurrUser() == null) {
            
            JOptionPane.showMessageDialog(null, "Nobody is signed in.");
        }

        // someone is signed in, sign them out
        else {

            JOptionPane.showMessageDialog(null, "Signing out: " + jukebox.getAccountCollection().getCurrUser().getName());
            jukebox.getAccountCollection().loggedOut();
        }

        // TODO delete
        return false;
    }


	@Override
	public void update(Observable o, Object arg) {
        
        // TODO delete
        System.out.println("LoginPanel received update message from jukebox.");
		
	}




}
