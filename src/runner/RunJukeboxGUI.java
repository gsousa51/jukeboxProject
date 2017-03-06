
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

package runner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import view.LoginPanel;
import view.SongSelectionButtonsPanel;

// This Jukebox type extends Jframe
public class RunJukeboxGUI {


    // main method to run everything
    public static void main(String[] args) {

        // Set up nimbus to match Rick's GUI
        attemptToSetNimbusLookAndFeel();

        // Frame to hold panels
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Note: 3x1 to match look of Rick's GUI
        window.setLayout(new GridLayout(3, 1));

        // Add the select-songs-with-buttons panel
        window.add(new SongSelectionButtonsPanel());

        // Add the login panel
        window.add(new LoginPanel());

        // Pop the GUI
        window.pack();
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
