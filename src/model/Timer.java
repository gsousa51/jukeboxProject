//Class: Timer
//Author: Gary Sousa
//Purpose: Keep track of the time left in the day. Update the Jukebox when the day changes.

package model;

public class Timer {
	private static int TIME_IN_DAY = 24*60*60;
	//Stored in seconds
	private int timeLeftInDay;
	private Jukebox juke;
	
	public Timer(Jukebox juke){
		this.juke = juke;
		timeLeftInDay= 60*60*24;
	}
	
	public int getTimeLeft(){
		return timeLeftInDay;
	}
	
	public void songPlayed(Song tune){
		timeLeftInDay-=tune.getLength();
		if(timeLeftInDay<=0){
			newDay();
			int overLap = timeLeftInDay;
			timeLeftInDay= TIME_IN_DAY - overLap;
		}
	}

	private void newDay() {
		// TODO Auto-generated method stub
	}

}
