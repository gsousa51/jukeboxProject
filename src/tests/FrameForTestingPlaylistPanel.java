
/**
 * An event-driven program with a GUI that allows users to add and remove ski resort names 
 */
package tests;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import model.Jukebox;
import model.SongQueue;
import view.PlaylistPanel;

public class FrameForTestingPlaylistPanel extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		TestPlayListGUI window = new TestPlayListGUI();
		window.setVisible(true);
	}


	// The graphical view of any list that implements ListModel<E>. This
	// displayList
	// will store displayListModel as an instance variable with
	// setModel(ListModel<E>)
	private JButton addSong1 = new JButton("AddSong1");
	private JButton addSong2 = new JButton("AddSong2");
	private Jukebox juke = new Jukebox();

	public FrameForTestingPlaylistPanel() {
		layoutGUI();
		registerListeners();
	}

	private void layoutGUI() {
		juke.getAccountCollection().setCurrentUser(juke.getAccountCollection().getAccount("River"));
		this.setTitle("TestGUI");
		this.setSize(600, 600);
		this.setLocation(200, 20);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel buttonPane = new JPanel();
		buttonPane.add(addSong1);
		buttonPane.add(addSong2);
		this.add(buttonPane, BorderLayout.NORTH);

		PlaylistPanel playlist = new PlaylistPanel(juke.getSongQueue());
		this.add(playlist, BorderLayout.CENTER);
	}

//	private void fuckThisNoise(){
//		DefaultListModel<String> listModel = new DefaultListModel<>();
//        listModel.addElement("USA");
//        listModel.addElement("India");
//        listModel.addElement("Vietnam");
//        listModel.addElement("Canada");
//        listModel.addElement("Denmark");
//        listModel.addElement("France");
//        listModel.addElement("Great Britain");
//        listModel.addElement("Japan");
//        displayList.setModel(listModel);
//        
//	}
	private void registerListeners() {
		// TODO 3: Implement the ActionListeners below
		addSong1.addActionListener(new Song1Listener());
		addSong2.addActionListener(new Song2Listener());
	}

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