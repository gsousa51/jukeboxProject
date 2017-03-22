

package tests;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Jukebox;
import view.PlaylistPanel;

public class FrameForTestingPlaylistPanel extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		FrameForTestingPlaylistPanel window = new FrameForTestingPlaylistPanel();
		window.setVisible(true);
	}


	private JButton addSong1 = new JButton("AddSong1");
	private JButton addSong2 = new JButton("AddSong2");
	private Jukebox juke = new Jukebox();

	public FrameForTestingPlaylistPanel() {
		layoutGUI();
		registerListeners();
	}

	private void layoutGUI() {
		//Need to set the current account for testing
		juke.getAccountCollection().setCurrentUser(juke.getAccountCollection().getAccount("River"));
		
		this.setTitle("TestGUI");
		this.setSize(600,600);
		this.setLocation(200, 20);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Button pane for choosing two songs.
		JPanel buttonPane = new JPanel();
		buttonPane.add(addSong1);
		buttonPane.add(addSong2);
		this.add(buttonPane, BorderLayout.NORTH);

		//Our playlist panel 
		PlaylistPanel playlist = new PlaylistPanel(juke.getSongQueue());
		//add it to the center
		this.add(playlist, BorderLayout.CENTER);
		playlist.setSize(new Dimension(300,300));
	}


	private void registerListeners() {
		addSong1.addActionListener(new Song1Listener());
		addSong2.addActionListener(new Song2Listener());
	}

	/*Both of the listeners only access the jukebox. 
	 * This is important since our PlaylistPanel isn't going to have any access to the Jukebox
	 * Thus, this tests that our Panel is going to reset whether or not it's able to see the Jukebox
	 */
	
	/*NOTE: NEITHER LISTENER TESTS THE VALIDITY OF THE SONG BEING ABLE TO BE PLAYED
	 * 	    THIS WAS SO I COULD MAKE SURE THE LIST WOULD GET LONG 
	 *      (We may want to look into a scroll pane for the panel)
	 */
	private class Song1Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			juke.songChosen(juke.getSongCollection().getSong("Flute"));
			
		}
	}

	private class Song2Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			juke.songChosen(juke.getSongCollection().getSong("Tada"));

		}
	}

}