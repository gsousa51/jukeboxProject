
/*
   Gary Sousa & Stephen Nolan
   CS 335
   Spring 2017
Instructor: Rick Mercer

Assignment: Project 5 - Jukebox
Due: Friday 10 March 2017 @ 10:00p


This class runs the Jukebox GUI for iteration 2 - 
Gary is just fucking around with Iteration1's GUI
*/

package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import model.Jukebox;
import view.LoginPanel;
import view.PlaylistPanel;
import view.SongSelectionButtonsPanel;

// This Jukebox type extends Jframe
public class RunJukeBoxGUI_Iteration2 extends JFrame {

	private Jukebox juke;
    // main method to run everything
    public static void main(String[] args) {
    	RunJukeBoxGUI_Iteration2 view = new RunJukeBoxGUI_Iteration2();
    	
    } // method main

    public RunJukeBoxGUI_Iteration2(){
    	promptUser();
    	setUpGUI();
    }
    private void setUpGUI() {


        // Set up nimbus to match Rick's GUI
        attemptToSetNimbusLookAndFeel();


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Note: 3x1 to match look of Rick's GUI
        this.setLayout(null);
        this.setPreferredSize(new Dimension(750,750));
        this.getContentPane().setBackground(Color.cyan);
        this.setBackground(Color.CYAN);
//        JPanel newFrame = new JPanel();
//        newFrame.setBackground(Color.CYAN);
//        newFrame.setSize(new Dimension(750,750));
//        newFrame.setBackground(Color.CYAN);
//        this.add(newFrame);
        
        // Add the select-songs-with-buttons panel
        SongSelectionButtonsPanel selectionArea = 
            new SongSelectionButtonsPanel(juke);
        selectionArea.setBounds(300, 0, 200, 150);
        this.add(selectionArea);
 
        //Add the label above our playlist
        JLabel playListLabel = new JLabel();
        playListLabel.setText("Current song playing is on top of list");
        Font myFont = new Font("Arial", Font.TRUETYPE_FONT, 16);
        playListLabel.setFont(myFont);
        playListLabel.setSize(300,50);
        playListLabel.setLocation(27,0);
        this.add(playListLabel);
        
        //Add the playlist panel to the JFrame
        PlaylistPanel playlist = new PlaylistPanel(juke.getSongQueue());
        //Set its placement/size
        playlist.setBounds(0, 35, 300, 500);
        this.add(playlist);
        
        // Add the login panel
        LoginPanel loginArea = new LoginPanel(juke);
        loginArea.setBounds(0, 550, 300, 150);
        this.add(loginArea);
       
        // register panels as Jukebox observers
        juke.addObserver(selectionArea);
        juke.addObserver(loginArea);
        this.addWindowListener(new ListenForWindowClose());
        
        // Pop the GUI
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
	}

	private void promptUser() {
		int userInput = JOptionPane.showConfirmDialog(null, "Use default list?");
		if (userInput == JOptionPane.YES_OPTION){
			juke = new Jukebox();
	}	
		else{
			FileInputStream stream= null;
			ObjectInputStream input = null;
			try {
				stream = new FileInputStream("savedObjects.txt");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			try {
				input = new ObjectInputStream(stream);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				juke = (Jukebox) input.readObject();
				//This call gets the playlist to begin playing automatically
				juke.getSongQueue().userRestartedSavedJukebox();
				input.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
    
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
    
	private class ListenForWindowClose extends WindowAdapter {

		@Override
		public void windowClosing(WindowEvent e) {
			
			int userInput = JOptionPane.showConfirmDialog(null, "Save this playlist");
			if (userInput == JOptionPane.YES_OPTION){
					FileOutputStream stream = null;
					ObjectOutputStream output = null;
					try {
						stream = new FileOutputStream("savedObjects.txt");
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						output = new ObjectOutputStream(stream);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						//let the playlist know that we closed the window but saved the music.
						juke.getSongQueue().userClosedWindow();
						output.writeObject(juke);
//						output.writeObject(queue);
//						output.writeObject(accounts);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
	}
	}
}
