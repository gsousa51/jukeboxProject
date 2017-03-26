
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
import java.time.LocalDate;
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
	private Song song1;
	private Song song2;

	// Constructor
	public SongSelectionButtonsPanel(Jukebox jukebox) {

		// contain the jukebox
		this.jukebox = jukebox;

		// Save the songs in instance variables
		song1 = jukebox.getSongCollection().getSong("Tada");
		song2 = jukebox.getSongCollection().getSong("Space Music");

		// The panel containing the buttons
		// NOTE - 3x2 layout intentional to correctly layout buttons similar
		// to Rick's GUI
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(3, 2));

		// choose song1
		JButton selectSong1Button = new JButton("Select song 1");
		buttonPanel.add(selectSong1Button);
		selectSong1Button.addActionListener(event -> requestSong1());

		// 'spacer' to manipulate layout
		buttonPanel.add(new JLabel(""));

		JButton selectSong2Button = new JButton("Select song 2");
		buttonPanel.add(selectSong2Button);
		selectSong2Button.addActionListener(event -> requestSong2());

		// 'spacer' to manipulate layout
		buttonPanel.add(new JLabel(""));

		// Optionally set GridLayout for this 'parent' Panel - stylistic choice
		this.setLayout(new GridLayout(1, 2));
		this.add(buttonPanel);

	} // JukeboxGUI constructor

	@Override
	public void update(Observable o, Object arg) {

		// System.out.println("SongSelectionButtonsPanel received update message
		// from jukebox.");
	}

	// attempt to play song1
	private boolean requestSong1() {

		// Force jukebox to look if date has changed
		jukebox.checkDateChanged(LocalDate.now());

		// nobody is logged in
		if (jukebox.getAccountCollection().getCurrUser() == null) {

			JOptionPane.showMessageDialog(null, "User must log in before selecting a song");
			return false;
		}

		// someone is logged in, attempt song play
		else {

			System.out.println("request tada");
			if (!jukebox.validPlay(song1)) {
				// user has already played 3 songs today - can not play any more
				if (jukebox.getAccountCollection().getCurrUser().getSongsPlayedToday() > 2) {
					JOptionPane.showMessageDialog(null,
							this.jukebox.getAccountCollection().getCurrUser().getName() + " has reached the limit");
				}

				// user does not have enough minutes to play the song
				else if (this.jukebox.getAccountCollection().getCurrUser().getTimeLeft() < this.song1.getLength()) {

					JOptionPane.showMessageDialog(null, this.jukebox.getAccountCollection().getCurrUser().getName()
							+ " does not have enough time credit for this song");
				}

				// song already played 3 times today, can not be played again
				// today
				else if (!this.song1.canBePlayed()) {
					JOptionPane.showMessageDialog(null, this.song1.getSongName() + " has been played 3 times today");
				}
			}

			// user has enough time credit and song is able to be played
			// play song
			else {

				jukebox.songChosen(this.song1);
				return true;
			}
		}

		// shouldn't ever be reached
		return false;
	}

	// attempt to play song2
	private boolean requestSong2() {

		// Force jukebox to look if date has changed
		jukebox.checkDateChanged(LocalDate.now());

		// nobody is logged in
		if (jukebox.getAccountCollection().getCurrUser() == null) {

			JOptionPane.showMessageDialog(null, "User must log in before selecting a song");
			return false;
		}

		// someone is logged in, attempt song play
		else {

			System.out.println("request spacemusic");
			if (!jukebox.validPlay(song2)) {
				// user has already played 3 songs today - can not play any more
				if (jukebox.getAccountCollection().getCurrUser().getSongsPlayedToday() > 2) {
					JOptionPane.showMessageDialog(null,
							this.jukebox.getAccountCollection().getCurrUser().getName() + " has reached the limit");
				}

				// user does not have enough minutes to play the song
				else if (this.jukebox.getAccountCollection().getCurrUser().getTimeLeft() < this.song2.getLength()) {

					JOptionPane.showMessageDialog(null, this.jukebox.getAccountCollection().getCurrUser().getName()
							+ " does not have enough time credit for this song");
				}

				// song already played 3 times today, can not be played again
				// today
				else if (!this.song2.canBePlayed()) {
					JOptionPane.showMessageDialog(null, this.song2.getSongName() + " has been played 3 times today");
				}
			}
			// user has enough time credit and song is able to be played
			// play song

			else {

				jukebox.songChosen(this.song2);
				return true;
			}
		}

		// shouldn't ever be reached
		return false;
	}
}
