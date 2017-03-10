
/*
   Gary Sousa & Stephen Nolan
   CS 335
   Spring 2017
Instructor: Rick Mercer

Assignment: Project 5 - Jukebox
Due: Friday 10 March 2017 @ 10:00p


This class runs the Jukebox GUI for iteration 1 - designed so as to closely 
model Rick's provided GUI

*/

package controller;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import model.Account;
import model.Jukebox;
import model.Song;
import view.LoginPanel;
import view.SongSelectionButtonsPanel;

// This Jukebox type extends Jframe
public class RunJukeboxGUI_Iteration1 {


    // main method to run everything
    public static void main(String[] args) {

        // TODO clean up - testing here
        // Set up model
        Jukebox jukebox = new Jukebox();
        // Song lopingString = jukebox.getSongCollection().getSong("Loping Sting");
        // Account user1 = jukebox.getAccountCollection().getAccount("Chris");

        // Set up nimbus to match Rick's GUI
        attemptToSetNimbusLookAndFeel();

        // Frame to hold panels
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Note: 3x1 to match look of Rick's GUI
        window.setLayout(new GridLayout(3, 1));

        // Add the select-songs-with-buttons panel
        SongSelectionButtonsPanel selectionArea = 
            new SongSelectionButtonsPanel(jukebox);
        window.add(selectionArea);

        // Add the login panel
        LoginPanel loginArea = new LoginPanel(jukebox);
        window.add(loginArea);

        // register panels as Jukebox observers
        jukebox.addObserver(selectionArea);
        jukebox.addObserver(loginArea);

        // Pop the GUI
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    } // method main


    // If the system has the [Nimbus] Look and Feel, turn it on
    private static void attemptToSetNimbusLookAndFeel() {

        try {


            for (LookAndFeelInfo lookAndFeel : UIManager.getInstalledLookAndFeels()) {

                if ("Nimbus".equals(lookAndFeel.getName())) {

                    UIManager.setLookAndFeel(lookAndFeel.getClassName());
                    break;
                }
            }
        } 

        // nimbus wasn't found
        catch (Exception e) {

            // system default L&F will be used
        }          
    }
}
