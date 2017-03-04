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
		songs = new ArrayDeque<Song>();
		songInProcess=false;
		
	}//end constructor
	
//	public static void main(String argv[]){
//		SongCollection songCollec = new SongCollection();
//		SongQueue song = new SongQueue(songCollec);
//		Object[] array = songCollec.getSongList();
//		for(int i=0; i<array.length;i++){
//			song.addToQueue((Song)array[i]);
//		}
//	}
	public void addToQueue(Song songToAdd){
		if(songs.isEmpty()&&!songInProcess){
			startPlaying(songToAdd);
		}
		else  songs.add(songToAdd);
	}
	
	private void startPlaying(Song songToAdd) {
		songs.add(songToAdd);
		SongPlayer.playFile(new SongWaiter(), songs.poll().getFileName());
		songInProcess=true;
		
	}

	@Override
	public void update(Observable arg0, Object message) {
		//Only have two update messages, if it isn't daychanged it's a song being chosen.
		if(!message.equals("DayChanged")){
			Song songToAdd = songCollection.getSong((String)message);
			if (songToAdd !=null){
				 this.addToQueue(songToAdd);
			}//end if
			else{
				System.out.println("Error in song name");
			}//end else
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
