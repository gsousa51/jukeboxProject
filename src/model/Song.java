package model;

public class Song {
	private String songName;
	private int length;
	private String artistName;
	private int timesPlayedToday;
	
	public Song(String songName, String artistName, int length){
		this.songName= songName;
		this.artistName = artistName;
		this.length = length;
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
	
	public void newDay(){
		timesPlayedToday=0;
	}

}
