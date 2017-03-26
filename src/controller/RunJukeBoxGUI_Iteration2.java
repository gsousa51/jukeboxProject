
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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.plaf.basic.BasicArrowButton;

import model.Jukebox;
import model.Song;

import view.LoginPanel;
import view.PlaylistPanel;
import view.SongSelectionJTablePanel;

/*
 * This JFrame could totally run the program pretty damn well.
 * There's a few big changes that I want you to check out before you make the JTable.
 * First off, you'll notice "private Song songChosen"
 * This variable as written can be used for the ButtonListener.
 * As far as implementation, we could create the JTable in this Class when the user
 * clicks the button, we attempt to add the Song that is highlighted on the JTable
 * to the playlist using our old algorithm that we had in the SongSelectionPanel class
 * 
 */
// This Jukebox type extends Jframe
public class RunJukeBoxGUI_Iteration2 extends JFrame {

    private Song songChosen;
    private Jukebox juke;

    // to have access from inside of the listeners
    private SongSelectionJTablePanel songSelectionPanel;

    // main method to run everything
    public static void main(String[] args) {
        RunJukeBoxGUI_Iteration2 view = new RunJukeBoxGUI_Iteration2();

    } // method main

    public RunJukeBoxGUI_Iteration2() {

        // set nimbus for a nicer experience
        attemptToSetNimbusLookAndFeel();

        // ask if the user wants to start from previously saved state
        promptUser();

        // run the actual GUI
        setUpGUI();
    }

    private void setUpGUI() {

        // HOISTED to pre-GUI code
        // Set up nimbus to match Rick's GUI
        //attemptToSetNimbusLookAndFeel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Note: 3x1 to match look of Rick's GUI
        this.setLayout(new GridLayout(3, 2));
        //this.setPreferredSize(new Dimension(750, 750));
        // this.getContentPane().setBackground(Color.cyan);
        // this.setBackground(Color.CYAN);
        // JPanel newFrame = new JPanel();
        // newFrame.setBackground(Color.CYAN);
        // newFrame.setSize(new Dimension(750,750));
        // newFrame.setBackground(Color.CYAN);
        // this.add(newFrame);

        /*
         * NOTE: IF YOU WANT TO TEST THE FUNCTIONALITY OF THIS JFRAME JUST
         * UNCOMMENT THE CODE BENEATH THIS NOTE. ALSO, UNCOMMENT THE ADD
         * OBSERVER CODE FOR THE SONGSELECTIONPANEL
         */
        // Add the select-songs-with-buttons panel
        // SongSelectionButtonsPanel selectionArea =
        // new SongSelectionButtonsPanel(juke);
        // selectionArea.setBounds(300, 0, 200, 150);
        // this.add(selectionArea);

        /*
         * NOTE: The code below is just a rough shot of where the JTable might
         * go Obviously, modify this JPanel all you want. You're way better at
         * making this stuff pretty than I am.
         */



        // Add JTable-based panel for selecting and requesting songs
        this.songSelectionPanel = new SongSelectionJTablePanel(juke);
        // songSelectionPanel.setBackground(Color.BLUE);
        //songSelectionPanel.setSize(300, 500);
        //songSelectionPanel.setLocation(400, 35);
        this.add(songSelectionPanel);

        // Add the label above our playlist
        JLabel playListLabel = new JLabel();
        playListLabel.setText("Current song playing is on top of list");
        Font myFont = new Font("Arial", Font.TRUETYPE_FONT, 16);
        playListLabel.setFont(myFont);
        //playListLabel.setSize(300, 50);
        //playListLabel.setLocation(27, 0);
        this.add(playListLabel);

        // Add the playlist panel to the JFrame
        PlaylistPanel playlist = new PlaylistPanel(juke.getSongQueue());
        // Set its placement/size
        //playlist.setBounds(0, 35, 300, 500);
        this.add(playlist);

        // Add the login panel
        LoginPanel loginArea = new LoginPanel(juke);
        //loginArea.setBounds(0, 550, 300, 150);
        this.add(loginArea);

        // Add Arrow Button for Adding the Song
        BasicArrowButton addSongButton = new BasicArrowButton(BasicArrowButton.WEST);
        addSongButton.addActionListener(new ButtonListener());
        //addSongButton.setSize(40, 60);
        //addSongButton.setLocation(325, 225);
        this.add(addSongButton);

        // register panels as Jukebox observers
        // juke.addObserver(selectionArea);
        juke.addObserver(loginArea);
        this.addWindowListener(new ListenForWindowClose());

        // Pop the GUI
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void promptUser() {
        int userInput = JOptionPane.showConfirmDialog(null, "Start with previous saved state?");
        if (userInput == JOptionPane.NO_OPTION) {
            juke = new Jukebox();
        } else {
            FileInputStream stream = null;
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
                // This call gets the playlist to begin playing automatically
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

    // private button listener for the arrow buttons.
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            // nobody is logged in
            if (juke.getAccountCollection().getCurrUser() == null) {

                JOptionPane.showMessageDialog(null, "User must log in before selecting a song");
            }

            // someone is logged in, attempt song play
            else {
                /* IMPLEMENTATION DESCRIBED ABOVE
                 * songChosen= highlightedTextOnJTable
                 * 
                 */

                // capture the requested song
                int row = songSelectionPanel.getTable().getSelectedRow();
                Song requestedSong = (Song) juke.getSongCollection()
                    .getSong((String) songSelectionPanel.getTable().getValueAt(row, 1));

                // TODO delete - debug
                //System.out.println(row);
                //System.out.println(songSelectionPanel.getTable().getValueAt(row, 1));


                // TODO robust error checking - for example if no row is currently 
                // selected or row is outside valid range
                // if no column is selected, the jtable will return -1
                // when asked for selected row


                if (!juke.validPlay(requestedSong)) {
                    // user has already played 3 songs today - can not play any
                    // more
                    if (juke.getAccountCollection().getCurrUser().getSongsPlayedToday() > 2) {
                        JOptionPane.showMessageDialog(null,
                                juke.getAccountCollection().getCurrUser().getName() + " has reached the limit");
                    }

                    // user does not have enough minutes to play the song
                    else if (juke.getAccountCollection().getCurrUser().getTimeLeft() < requestedSong.getLength()) {

                        JOptionPane.showMessageDialog(null,juke.getAccountCollection().getCurrUser().getName()
                                + " does not have enough time credit for this song");
                    }

                    // song already played 3 times today, can not be played
                    // again
                    // today
                    else if (!requestedSong.canBePlayed()) {
                        JOptionPane.showMessageDialog(null,
                                requestedSong.getSongName() + " has been played 3 times today");
                    }
                }

                // user has enough time credit and song is able to be played
                // play song
                else {

                    // Request the song
                    juke.songChosen(requestedSong);

                }
            }
        }

    }

    
    private class ListenForWindowClose extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {

            int userInput = JOptionPane.showConfirmDialog(null, "Save data?");
            if (userInput == JOptionPane.YES_OPTION) {
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
                    // let the playlist know that we closed the window but saved
                    // the music.
                    juke.getSongQueue().userClosedWindow();
                    output.writeObject(juke);
                    // output.writeObject(queue);
                    // output.writeObject(accounts);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
    }
}
