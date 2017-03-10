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
    private JLabel statusHeader;
    private JLabel statusData;
    private JPasswordField loginPassword;
    private JTextField loginName;

    // Constructor 
    public LoginPanel(Jukebox jukebox) {

        // contain the jukebox
        this.jukebox = jukebox;

        // set layout and background color to match Rick's GUI
        this.setLayout(new GridLayout(4, 2));
        this.setBackground(Color.WHITE);

        // account name entry
        this.add(new JLabel("Account Name: ", SwingConstants.RIGHT));
        this.loginName = new JTextField(15);
        this.add(loginName);

        // password entry
        this.add(new JLabel("Password: ", SwingConstants.RIGHT));
        this.loginPassword = new JPasswordField();
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
        this.statusHeader = new JLabel("Status: ", SwingConstants.RIGHT);
        this.statusData = new JLabel("Login First");
        this.add(this.statusHeader);
        this.add(this.statusData);

    } // JukeboxGUI constructor


    private boolean attemptLogIn(String name, String password) {

        // invalid user name
        if (jukebox.getAccountCollection().getAccount(name) == null) {
            
            // NOTE this is the behavior Rick decided
            this.loginName.setText("");
            this.loginPassword.setText("");
            return false;
        }

        // the user exists in the collection, does the password match?
        else if (! jukebox.getAccountCollection().getAccount(name).getPassword().equals(password)) {

            JOptionPane.showMessageDialog(null, "Invalid Password.");
            this.loginPassword.setText("");
            return false;
        }
        
        // password matched for valid user found in account collection
        else {

            System.out.println("[Logging in] : " + name);
            // set this user as the current user in the account collection
            jukebox.getAccountCollection().setCurrentUser(jukebox.getAccountCollection().getAccount(name));


            // TODO fetch metrics and display

            return true;
        }

    }



    private boolean attemptSignOut() {

        // nobody is currently signed in
        if (jukebox.getAccountCollection().getCurrUser() == null) {
            
            JOptionPane.showMessageDialog(null, "Nobody is signed in.");

            return false;
        }

        // someone is signed in, sign them out
        else {

            System.out.println("[Logging out] : " + jukebox.getAccountCollection().getCurrUser().getName());

            // log user out from model
            jukebox.getAccountCollection().loggedOut();

            // reset metrics display
            this.loginPassword.setText("");
            this.loginName.setText("");
            this.statusData.setText("Login First");

            return true;
        }
    }


	@Override
    public void update(Observable o, Object arg) {

        // TODO delete
        System.out.println("LoginPanel received update message from jukebox.");
        int userSongsLeft = 3 - jukebox.getAccountCollection().getCurrUser().getSongsPlayedToday();
        this.statusData.setText("" + userSongsLeft);

    }
}
