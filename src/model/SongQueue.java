//ClassName: SongQueue 
//Author: Gary Sousa
//Purpose: Keeps a list of songs that need to be played and plays them in FIFO order.
package model;

import java.util.ArrayDeque;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

public class SongQueue implements Observer{

	SongCollection songCollection;
	Queue<Song> songs;
	
	public SongQueue(SongCollection songCollection) {
		this.songCollection = songCollection;
		songs = new ArrayDeque<Song>();
	}//end constructor

	@Override
	public void update(Observable arg0, Object message) {
		//Only have two update messages, if it isn't daychanged it's a song being chosen.
		if(!message.equals("DayChanged")){
			Song songToAdd = songCollection.getSong((String)message);
			if (songToAdd !=null){
				songs.add(songToAdd);
			}//end if
			else{
				System.out.println("Error in song name");
			}//end else
		}//end if
	}
	
	

}
