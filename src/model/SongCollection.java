//ClassName: SongCollection
//Authors: Gary Sousa & Stephen Nolan
//Purpose: Object keeps a list of object type Songs. Object observes the controller,
//Jukebox, and updates the songs as necessary. Object also allows accesss to songs in
//its list.

package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class SongCollection implements Observer{

	private List<Song> songList; 
	
	public SongCollection(){
		songList = new ArrayList<Song>();
		buildList();
	}
	
	private void buildList(){
		//Used to put in front of each filename parameter.
		String src = "./songfiles/";
		//add all of the songs to the songlist
		songList.add(new Song("Microsoft", "Tada", src+"tada.wav",2));	
		songList.add(new Song("Kevin MacLeod", "Danse Macabre", src+"DanseMacabreViolinHook.mp3",34));
		songList.add(new Song("FreePlay Music", "Determined Tumbao",src+"DeterminedTumbao.mp3",20));
		songList.add(new Song("Sun Microsystems", "Flute", src+"flute.aif",6));
		songList.add(new Song("Kevin Macleod", "Loping Sting", src+"LopingSting.mp3",5));
		songList.add(new Song("Unknown", "Space Music", src + "spacemusic.au", 6));
		// songList.add(new Song("Unknown", "Space Music", src + "spacemusic.au",1500*60));
		songList.add(new Song("FreePlay Music", "Swing Cheese", src+"SwingCheese.mp3",15));
		songList.add(new Song("Kevin Macleod", "The Curtain Rises", src+"TheCurtainRises.mp3",28));
		songList.add(new Song("Pierre Langer", "Untameable Fire", src+"UntameableFire.mp3",282));
	}
	
	public Object[] getSongList(){
		return songList.toArray();
	}
//buttonclick
	//validate
	//checkifQueue empty
	//update 
	//playSong if it was empty
	

	//Parameter is the name of the song to return
	//Return: Returns song that is requested or a null song.
	public Song getSong(String songName){
		Iterator<Song> itr = songList.iterator();
		while(itr.hasNext()){
			Song currSong = itr.next();
			if(currSong.getSongName().equals(songName)){
				return currSong;
			}
		}
		//If we get here, no song name matched the song in question.
		//Return null.
		return null;
	}
	
	//Method called by the Observable class (Jukebox)
	//Contains one of two messages for the message parameter
	//Case 1: message = "DayChanged", we reset timesPlayed variable for every song in the collection
	//Case 2: message = "<name of song>", we find the song in collection by that name
	//                   and increment the amount of times it was played today.
	@Override
	public void update(Observable arg0, Object message) {
		Iterator<Song> itr = songList.iterator();
		Song currSong;
		if(message instanceof String){
			while(itr.hasNext()){
				currSong=itr.next();
				currSong.newDay();
			}
		}
		else{
			((Song)message).songPlayed();
		}
			
	}//end update
	
	
}
