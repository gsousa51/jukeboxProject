//ClassName: SongQueue 
//Author: Gary Sousa
//Purpose: Keeps a list of songs that need to be played and plays them in FIFO order.
package model;

import java.util.ArrayDeque;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

import songPlayerDemo.PlayASongWithEachButtonClick.SongWaiter;
import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

public class SongQueue implements Observer {

	SongCollection songCollection;
	private static Queue<Song> songs;
	private static boolean songInProcess;
	
	public SongQueue(SongCollection songCollection) {
		this.songCollection = songCollection;
		//Create a new ArrayDeque to hold the songs in the playlist
		songs = new ArrayDeque<Song>();
		//Boolean variable to track if SongPlayer is currently playing a song.
		songInProcess=false;
		
	}//end constructor
	
	//Main method used to test the SongQueue
//	public static void main(String argv[]){
//		SongCollection songCollec = new SongCollection();
//		SongQueue song = new SongQueue(songCollec);
//		Object[] array = songCollec.getSongList();
//		for(int i=0; i<array.length;i++){
//			song.addToQueue((Song)array[i]);
//		}
//	}
	//Parameter: Song to add to our playlist
	public void addToQueue(Song songToAdd){
		//If our list is empty and we aren't currently playing a song
		if(songs.isEmpty()&&!songInProcess){
			//Send the song to the startPlaying method
			startPlaying(songToAdd);
		}
		//Otherwise we're currently playing a song, just add the song
		//To the end of our playlist.
		else  songs.add(songToAdd);
	}
	
	//Parameter: Song to begin playing
	//Purpose: Method starts our SongPlayer, beginning with the song
	//         given as parameter
	private void startPlaying(Song songToAdd) {
		//Add song to list
		songs.add(songToAdd);
		//Begin song player and pop the first song in our list.
		SongPlayer.playFile(new SongWaiter(), songs.poll().getFileName());
		//Set our flag variable to true.
		songInProcess=true;
		
	}

	//Method is called by Jukebox
	//Two cases:
	//Case 1: message == "DayChanged"
	//     Action performed: Nothing.
	//Case 2 message == "<name of song chosen by user>"
	//		//Action: Find song by that name in the SongCollection
	//				  If valid song, send song to our addToQueue(Song) method.
	@Override
	public void update(Observable arg0, Object message) {

		//Only have two update messages, if it isn't daychanged 
		//it's the name of the song being chosen.
		if(!message.equals("DayChanged")){
			//Find the song from the SongCollection
			Song songToAdd = (Song)message;
				 this.addToQueue(songToAdd);
		}//end if
	}
	 public static class SongWaiter implements EndOfSongListener {

		public void songFinishedPlaying(EndOfSongEvent eosEvent) {
			
			if(!songs.isEmpty()){
				Song temp = songs.poll();
				if(temp!=null){
					SongPlayer.playFile(new SongWaiter(), temp.getFileName());
				}
				else songInProcess=false;
			}
		}
	}
	
	

}
