
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
        

}

