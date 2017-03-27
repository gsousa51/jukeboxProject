
package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JPanel;

import model.SongQueue;
/*
*  **********************************************************
*  NOTE: THIS PANEL REQUIRES A SONGQUEUE IN ITS CONSTRUCTOR.
* ***********************************************************
 */
public class PlaylistPanel extends JPanel {

	private SongQueue queue;
	private JList<String> playlist;
	
	public PlaylistPanel(SongQueue queue){
		//Sets our local queue to the one sent as parameter
		this.queue = queue;
		//Make the playlist a new JList with queue as its model
		playlist = new JList<String>(queue);
		//Let queue know what "its view" is 
		//This is used for updating the JList
		queue.setView(playlist);
		//Initialize size of rows.
		playlist.setVisibleRowCount(5);
		playlist.setFixedCellWidth(20);
		playlist.setFixedCellHeight(20);
		playlist.setPreferredSize(new Dimension(300,550));
		this.setBackground(Color.CYAN);
		this.add(playlist);
		
		
	}
}
