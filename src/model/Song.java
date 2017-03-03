package model;

public class Song {
	private String songName;
	private int length;
	private String artistName;
	private int timesPlayedToday;
	private String fileName;
	
	public Song(String artistName, String songName, String fileName, int length){
		this.songName= songName;
		this.artistName = artistName;
		this.length = length;
		this.fileName = fileName;
		timesPlayedToday = 0;
		
	}

	public String getSongName() {
		return songName;
	}


	public int getLength() {
		return length;
	}


	public String getArtistName() {
		return artistName;
	}
	
	public int getTimesPlayedToday(){
		return timesPlayedToday;
	}
	
	public void songPlayed(){
		timesPlayedToday++;
	}

	public String getFileName() {
		return fileName;
	}
	
	//Method used for NotifyObservers()
	//When the day changes, we reset the times play today
	//Seeing as it's a new day.
	public void newDay(){
		timesPlayedToday=0;
	}




}
