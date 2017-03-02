//ClassName: SongCollection
//Author: Gary Sousa
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
	
	//WORK ON THIS METHOD NEED TO CHANGE THE ADD PART TO MAKE NEW SONG
	private void buildList(){
		String src = "./songfiles";
		songList.add(new Song("Kevin MacLeod", "Danse Macabre", src+"DanseMacabreViolinHook.mp3",34));
		songList.add(new Song("FreePlay Music", "Determined Tumbao",src+"DeterminedTumbao.mp3",20));
		songList.add(new Song("Sun Microsystems", "Flute", src+"flute.aif",6));
		songList.add(new Song("Unknown", "Space Music", src+"spacemusic.au",5));
		songList.add(new Song("FreePlay Muisc", "", src+"",));
		songList.add(new Song("", "", src+"",));	
		songList.add(new Song("", "", src+"",));
		songList.add(new Song("", "", src+"",));
	}

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
	@Override
	public void update(Observable arg0, Object message) {
		Iterator<Song> itr = songList.iterator();
		Song currSong;
		if(message == "DayChange"){
			while(itr.hasNext()){
				currSong=itr.next();
				currSong.newDay();
			}
		}
		else{
			String songName = (String)message;
			while(itr.hasNext()){
				currSong=itr.next();
				if(currSong.getSongName().equals(songName)){
					currSong.songPlayed();
					break;
				}
			}
		}//end else
	}//end update
	
	
}
