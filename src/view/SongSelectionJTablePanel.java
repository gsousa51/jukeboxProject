
/*
Gary Sousa & Stephen Nolan
CS 335
Spring 2017
Instructor: Rick Mercer

Assignment: Project 5 - Jukebox
Due: Friday 27 March 2017 @ 2:00p


This class creates a JPanel that allows Jukebox users select songs to play on a 
Jukebox

*/

package view;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.Jukebox;

// A JPanel, for modularity
public class SongSelectionJTablePanel extends JPanel {

	// Instance variables
	private Jukebox jukebox;
    private TableModel model;
    private JTable table;
    private JScrollPane scrollPane;
	// private Song song1;
	// private Song song2;

	// Constructor
	public SongSelectionJTablePanel(Jukebox jukebox) {

		// contain the jukebox
		this.jukebox = jukebox;

        // grab the model for the JTable
        this.model = jukebox.getSongCollection();

        // build a table from the model
        this.table = new JTable();
        table.setModel(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // build a JScrollPane to contain everything
        this.scrollPane = new JScrollPane(table);
        // only allow one row to be selcted at once
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Rowsorter for the Table
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);


		if (model.getColumnCount()>0)
			table.setRowSelectionInterval(0, 0);
        // finally add the scrollpane to this panel
        this.add(scrollPane);

		//this.setPreferredSize(new Dimension(300,550));
	} // JukeboxGUI constructor
    
    public JTable getTable() {

        return this.table;
    }
        






		// Save the songs in instance variables
		//song1 = jukebox.getSongCollection().getSong("Tada");
		//song2 = jukebox.getSongCollection().getSong("Space Music");

		// The panel containing the buttons
		// NOTE - 3x2 layout intentional to correctly layout buttons similar
		// to Rick's GUI
		// JPanel buttonPanel = new JPanel();
		//buttonPanel.setLayout(new GridLayout(3, 2));

		// choose song1
		//JButton selectSong1Button = new JButton("Select song 1");
		//buttonPanel.add(selectSong1Button);
		//selectSong1Button.addActionListener(event -> requestSong1());

		// 'spacer' to manipulate layout
		//buttonPanel.add(new JLabel(""));

		//JButton selectSong2Button = new JButton("Select song 2");
		//buttonPanel.add(selectSong2Button);
		//selectSong2Button.addActionListener(event -> requestSong2());

		// 'spacer' to manipulate layout
		//buttonPanel.add(new JLabel(""));

		// Optionally set GridLayout for this 'parent' Panel - stylistic choice
		//this.setLayout(new GridLayout(1, 2));
		//this.add(buttonPanel);



	// // attempt to play song1
	// private boolean requestSong1() {

	// 	// Force jukebox to look if date has changed
	// 	jukebox.checkDateChanged(LocalDate.now());

	// 	// nobody is logged in
	// 	if (jukebox.getAccountCollection().getCurrUser() == null) {

	// 		JOptionPane.showMessageDialog(null, "User must log in before selecting a song");
	// 		return false;
	// 	}

	// 	// someone is logged in, attempt song play
	// 	else {

	// 		System.out.println("request tada");
	// 		if (!jukebox.validPlay(song1)) {
	// 			// user has already played 3 songs today - can not play any more
	// 			if (jukebox.getAccountCollection().getCurrUser().getSongsPlayedToday() > 2) {
	// 				JOptionPane.showMessageDialog(null,
	// 						this.jukebox.getAccountCollection().getCurrUser().getName() + " has reached the limit");
	// 			}

	// 			// user does not have enough minutes to play the song
	// 			else if (this.jukebox.getAccountCollection().getCurrUser().getTimeLeft() < this.song1.getLength()) {

	// 				JOptionPane.showMessageDialog(null, this.jukebox.getAccountCollection().getCurrUser().getName()
	// 						+ " does not have enough time credit for this song");
	// 			}

	// 			// song already played 3 times today, can not be played again
	// 			// today
	// 			else if (!this.song1.canBePlayed()) {
	// 				JOptionPane.showMessageDialog(null, this.song1.getSongName() + " has been played 3 times today");
	// 			}
	// 		}

	// 		// user has enough time credit and song is able to be played
	// 		// play song
	// 		else {

	// 			jukebox.songChosen(this.song1);
	// 			return true;
	// 		}
	// 	}

	// 	// shouldn't ever be reached
	// 	return false;
	// }

	// // attempt to play song2
	// private boolean requestSong2() {

	// 	// Force jukebox to look if date has changed
	// 	jukebox.checkDateChanged(LocalDate.now());

	// 	// nobody is logged in
	// 	if (jukebox.getAccountCollection().getCurrUser() == null) {

	// 		JOptionPane.showMessageDialog(null, "User must log in before selecting a song");
	// 		return false;
	// 	}

	// 	// someone is logged in, attempt song play
	// 	else {

	// 		System.out.println("request spacemusic");
	// 		if (!jukebox.validPlay(song2)) {
	// 			// user has already played 3 songs today - can not play any more
	// 			if (jukebox.getAccountCollection().getCurrUser().getSongsPlayedToday() > 2) {
	// 				JOptionPane.showMessageDialog(null,
	// 						this.jukebox.getAccountCollection().getCurrUser().getName() + " has reached the limit");
	// 			}

	// 			// user does not have enough minutes to play the song
	// 			else if (this.jukebox.getAccountCollection().getCurrUser().getTimeLeft() < this.song2.getLength()) {

	// 				JOptionPane.showMessageDialog(null, this.jukebox.getAccountCollection().getCurrUser().getName()
	// 						+ " does not have enough time credit for this song");
	// 			}

	// 			// song already played 3 times today, can not be played again
	// 			// today
	// 			else if (!this.song2.canBePlayed()) {
	// 				JOptionPane.showMessageDialog(null, this.song2.getSongName() + " has been played 3 times today");
	// 			}
	// 		}
	// 		// user has enough time credit and song is able to be played
	// 		// play song

	// 		else {

	// 			jukebox.songChosen(this.song2);
	// 			return true;
	// 		}
	// 	}

	// 	// shouldn't ever be reached
	// 	return false;
	// }
}

