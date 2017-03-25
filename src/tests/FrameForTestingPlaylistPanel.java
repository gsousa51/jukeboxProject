

package tests;
import java.awt.BorderLayout;
import java.awt.Dimension;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.AccountCollection;
import model.Jukebox;
import model.SongQueue;
import view.PlaylistPanel;

public class FrameForTestingPlaylistPanel extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		FrameForTestingPlaylistPanel window = new FrameForTestingPlaylistPanel();
		System.out.println("Got here...?");
		window.setVisible(true);
	}


	private JButton addSong1 = new JButton("AddSong1");
	private JButton addSong2 = new JButton("AddSong2");
	private Jukebox juke;
	private SongQueue queue;
	private AccountCollection accounts;

	public FrameForTestingPlaylistPanel() {
		promptUser();
		layoutGUI();
		registerListeners();
	}

	private void promptUser() {
		int userInput = JOptionPane.showConfirmDialog(null, "Use default list?");
		if (userInput == JOptionPane.YES_OPTION){
			juke = new Jukebox();
			queue = juke.getSongQueue();
			accounts = juke.getAccountCollection();
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
				juke.getSongQueue().userRestartedSavedJukebox();
//				queue= juke.getSongQueue();
//				accounts = juke.getAccountCollection();
//				System.out.println("We read in the objects successfully...");
//				System.out.println(juke.getSongCollection().getSong("Tada").getTimesPlayedToday());
//				System.out.println(accounts.getAccount("River").getSongsPlayedToday());
//				System.out.println("Songs played: " + queue.toString());
				
				input.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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

	// The controller that askes the user to save a persistent object or not
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
						queue.userClosedWindow();
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

	private void registerListeners() {
		addSong1.addActionListener(new Song1Listener());
		addSong2.addActionListener(new Song2Listener());
		this.addWindowListener(new ListenForWindowClose());
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