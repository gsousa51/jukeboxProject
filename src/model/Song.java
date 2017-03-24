/* Class Name: Song
 * Authors: Gary Sousa & Stephen Nolan
   Purpose: Creates an Object that stores all necessary information about a Song
   			This includes its length, song name, artist's name, times song was played today
   			and the filename used to access the song. 
 */

package model;

import java.io.Serializable;

public class Song implements Serializable {
	private String songName;
	private int length;
	private String artistName;
	private int timesPlayedToday;
	private String fileName;
	
	//Constructor for an instance of Song 
	public Song(String artistName, String songName, String fileName, int length){
		this.songName= songName;
		this.artistName = artistName;
		this.length = length;
		this.fileName = fileName;
		timesPlayedToday = 0;
		
	}

	//Returns the name of the song
	public String getSongName() {
		return songName;
	}

	//Returns the length of the Song
	public int getLength() {
		return length;
	}

	//Returns the artist's name 
	public String getArtistName() {
		return artistName;
	}
	
	//Returns times played today
	public int getTimesPlayedToday(){
		return timesPlayedToday;
	}
	
	//Tells the song that its been played.
	//Increments the times played variable.
	public void songPlayed(){
		timesPlayedToday++;
	}

	//Returns the filename of song.
	public String getFileName() {
		return fileName;
	}
	
	//Method used for NotifyObservers()
	//When the day changes, we reset the times play today
	//Seeing as it's a new day.
	public void newDay(){
		timesPlayedToday=0;
	}
	
	//Returns if song can be played or not.
	public boolean canBePlayed(){
		return timesPlayedToday < 3;
	}



}
