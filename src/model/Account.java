//ClassName: Account
//Author: Gary Sousa & Stephen Nolan
//Purpose: Account is used to track student activity with the juke box
// It keeps track of time left that student is allowed  to play songs with
// as well as how many songs are played by student each day.

package model;

public class Account {
	//how many minutes student is allowed to listen to music
	private static int TIME_ALLOWED = 1500;
	private int timeLeft;
	private int songsPlayedToday;
	private String name;
	private String password;
	
	public Account(String name, String password){
		//convert the minutes to seconds.
		timeLeft = TIME_ALLOWED*60;
		songsPlayedToday = 0;
		this.name = name;
		this.password = password;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPassword(){
		return password;
	}
	
	
	//Student played song
	//Subtract time of the song from their allowed minutes
	//As well as increment the songs they've played today.
	public void playedSong(Song tune){
		timeLeft -= tune.getLength();
		songsPlayedToday++;
	}//end playedSong
	
	public int getSongsPlayedToday(){
		return songsPlayedToday;
	}//end getSongsPlayedToday
	
	public int getTimeLeft(){
		return timeLeft;
	}//end getTimesPlayedToday
	
	//Method called by AccountCollection when the clock passes midnight
	//Simply resets the amount of songs played by user today
	//Since it's a new day now.
	public void newDay(){
		songsPlayedToday=0;
	}
	
	public boolean canPlay(Song tune){
		return (songsPlayedToday< 3 && timeLeft - tune.getLength()>=0);
	}
	
}
