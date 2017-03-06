
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
public class SongSelectionButtonsPanel extends JPanel {


    // Constructor 
    public SongSelectionButtonsPanel() {

        // The panel containing the buttons
        // NOTE - 3x2 layout intentional to correctly layout buttons similar
        // to Rick's GUI
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2));

        // choose song1
        JButton selectSong1Button = new JButton("Select song 1");
        buttonPanel.add(selectSong1Button);

        // 'spacer' to manipulate layout
        buttonPanel.add(new JLabel(""));

        JButton selectSong2Button = new JButton("Select song 2");
        buttonPanel.add(selectSong2Button);

        // 'spacer' to manipulate layout
        buttonPanel.add(new JLabel(""));

        // Optionally set GridLayout for this 'parent' Panel - stylistic choice
        // this.setLayout(new GridLayout(1, 2));
        this.add(buttonPanel);

    } // JukeboxGUI constructor
}
