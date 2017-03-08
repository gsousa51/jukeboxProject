
/*
Gary Sousa & Stephen Nolan
CS 335
Spring 2017
Instructor: Rick Mercer

Assignment: Project 5 - Jukebox
       Due: Friday 10 March 2017 @ 10:00p


This class creates a JPanel that allows Jukebox users select songs to play on a 
Jukebox

*/

package view;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Jukebox;
import model.Song;
import model.SongCollection;
import model.SongQueue;

// A JPanel, for modularity
public class SongSelectionButtonsPanel extends JPanel implements Observer {

    // Instance variables
    private Jukebox jukebox;

    // Constructor 
    public SongSelectionButtonsPanel(Jukebox jukebox) {

        // contain the jukebox
        this.jukebox = jukebox;

        // The panel containing the buttons
        // NOTE - 3x2 layout intentional to correctly layout buttons similar
        // to Rick's GUI
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2));

        // choose song1
        JButton selectSong1Button = new JButton("Select song 1");
        buttonPanel.add(selectSong1Button);
        selectSong1Button.addActionListener(event -> 
                requestSong1());

        // 'spacer' to manipulate layout
        buttonPanel.add(new JLabel(""));

        JButton selectSong2Button = new JButton("Select song 2");
        buttonPanel.add(selectSong2Button);
        selectSong2Button.addActionListener(event ->
                requestSong2());

        // 'spacer' to manipulate layout
        buttonPanel.add(new JLabel(""));

        // Optionally set GridLayout for this 'parent' Panel - stylistic choice
        // this.setLayout(new GridLayout(1, 2));
        this.add(buttonPanel);

    } // JukeboxGUI constructor

	@Override
	public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
        System.out.println("SongSelectionButtonsPanel received update message from jukebox.");

    }

    private boolean requestSong1() {

        if (jukebox.getAccountCollection().getCurrUser() == null) {
            JOptionPane.showMessageDialog(null, "Nobody is logged in.");
        }

        // TODO delete
        return false;
    }

    private boolean requestSong2() {

        if (jukebox.getAccountCollection().getCurrUser() == null) {
            JOptionPane.showMessageDialog(null, "Nobody is logged in.");
        }

        // TODO delete
        return false;
    }


}
