
/*
   Gary Sousa & Stephen Nolan
   CS 335
   Spring 2017
Instructor: Rick Mercer

Assignment: Project 5 - Jukebox
Due: Friday 10 March 2017 @ 10:00p


This class runs the Jukebox GUI for iteration 1

*/

package runner;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import view.LoginPanel;

// This Jukebox type extends Jframe
public class RunJukeboxGUI {


    // main method to run everything
    public static void main(String[] args) {

        // Set up nimbus to match Rick's GUI
        attemptToSetNimbusLookAndFeel();
        UIManager.put("control", Color.WHITE);

        // Pop the GUI
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(new LoginPanel());
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

        }          
    }
}
