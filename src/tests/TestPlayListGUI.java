
/**
 * An event-driven program with a GUI that allows users to add and remove ski resort names 
 */
package tests;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Jukebox;
import model.SongQueue;

public class TestPlayListGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		TestPlayListGUI window = new TestPlayListGUI();
		window.setVisible(true);
	}

	// All ski places that will be known in this example of event-driven
	// programming
	private SongQueue model;
	private Jukebox juke = new Jukebox();
	// The graphical view of any list that implements ListModel<E>. This
	// displayList
	// will store displayListModel as an instance variable with
	// setModel(ListModel<E>)
	private JList<String> displayList;
	private JButton addSong1 = new JButton("AddSong1");
	private JButton addSong2 = new JButton("AddSong2");
	
	// A single line editor used to add a new ski resort
	private JTextField resortInput = new JTextField("", 20);
	public TestPlayListGUI() {
		layoutGUI();
		registerListeners();
	}

	private void layoutGUI() {
		juke.getAccountCollection().setCurrentUser(juke.getAccountCollection().getAccount("River"));
		this.setTitle("Ski resorts");
		this.setSize(600, 600);
		this.setLocation(200, 20);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel buttonPane = new JPanel();
		buttonPane.add(resortInput);
		buttonPane.add(addSong1);
		buttonPane.add(addSong2);
		this.add(buttonPane, BorderLayout.NORTH);

		// TODO 1 First change SkiResorts.java (adapt SkiResorts to the
		// interface a JList expects)
		model = new SongQueue(juke.getSongCollection());
		
		/*FOR SOME GODDAMN REASON THIS WORKS IF WE INITIALIZE THE QUEUE TO HAVE SOMETHING BUT
		 * NOT WHEN IT'S EMPTY?
		 */
//		for(int i=0; i<10; i++){
//			model.addToQueue(juke.getSongCollection().getSong("Tada"));
//		}
		displayList = new JList<String>(model);
		
		this.add(displayList, BorderLayout.CENTER);
		model.setView(displayList);
//		fuckThisNoise();
		displayList.setVisibleRowCount(10);
		displayList.setVisible(true);
		
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
			System.out.println(model.toString());
			//

//			displayList.repaint();
			
		}
	}

	private class Song2Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			juke.songChosen(juke.getSongCollection().getSong("Tada"));
			System.out.println(model.toString());
			//
			
//			displayList.repaint();
		}
	}

}