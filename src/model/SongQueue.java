//ClassName: SongQueue 
//Authors: Gary Sousa & Stephen Nolan
//Purpose: Keeps a list of songs that need to be played and plays them in FIFO order.
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

public class SongQueue implements Observer, ListModel<String>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SongCollection songCollection;
	private Jukebox juke;
	//This will hold the actual playlist of Songs
	private  List<Song> songs;
	//This will be used to hold the names of the songs in the playlist.
	private  List<String> playList;
	private  boolean songInProcess;
	private  JList<String> view;


	public SongQueue(SongCollection songCollection) {

		this.songCollection = songCollection;
		//Create a new ArrayDeque to hold the songs in the playlist
		songs = new ArrayList<Song>();
		//Boolean variable to track if SongPlayer is currently playing a song.
		songInProcess=false;
		juke=null;
		playList = new ArrayList<String>();

		
	}//end constructor

	//Parameter: Song to add to our playlist
	public void addToQueue(Song songToAdd){
		songs.add(songToAdd);
		//Add the name of the song to our String list of songs
		playList.add(songToAdd.getSongName()+ " , " + songToAdd.getArtistName());
		//If our list is empty and we aren't currently playing a song
		if(!songInProcess){
			//Begin song player and pop the first song in our list.
			SongPlayer.playFile(new SongWaiter(), songs.get(0).getFileName());
			//Set our flag variable to true.
			songInProcess=true;
		}
		view.repaint();
	}
	//This is used for testing purposes.
	@Override
	public String toString(){
		return playList.toString();
	}
	
	public int amountOfSongs(){
		return songs.size();
	}

	
	//Purpose: Lets the SongQueue object have access to its JList
	//This allows us to update it when a song ends.
	public void setView(JList<String> displayList){
		view = displayList;
	}
	@Override
	public void addListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getElementAt(int index) {
		return  playList.get(index);
	}

	@Override
	public int getSize() {
		return playList.size();
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	//Method is called by Jukebox
	//Two cases:
	//Case 1: message == "DayChanged"
	//     Action performed: Nothing.
	//Case 2 message == "<name of song chosen by user>"
	//		//Action: Find song by that name in the SongCollection
	//				  If valid song, send song to our addToQueue(Song) method.
	@Override
	public void update(Observable jukebox, Object message) {
		juke=(Jukebox)jukebox;
		//Only have two update messages, if it isn't daychanged 
		//it's the name of the song being chosen.
		if(message instanceof Song){
			//Find the song from the SongCollection
			Song songToAdd = (Song)message;
				 this.addToQueue(songToAdd);
		}//end if
	}
	public void userClosedWindow(){
		songInProcess = false;
	}
	public void userRestartedSavedJukebox(){
		if(!songs.isEmpty()){
			SongPlayer.playFile(new SongWaiter(), songs.get(0).getFileName());
			songInProcess = true;
		}
	}
	 public  class SongWaiter implements EndOfSongListener {

		public void songFinishedPlaying(EndOfSongEvent eosEvent) {
			//After song is over, check if the date has changed.
			if(juke!=null){
				juke.checkDateChanged(LocalDate.now());
			}
			//Remove the top of each of our lists
			songs.remove(0);
			playList.remove(0);
			//update the JList
			view.repaint();
			if(songs.size()>0){
					//Play the next song.
					SongPlayer.playFile(new SongWaiter(), songs.get(0).getFileName());
			}
			else{
				songInProcess=false;
			}
			System.out.println(playList.toString());
		}
	}
}
