//ClassName: Jukebox
//Author: Gary Sousa
//Purpose: JukeBox works as the main controller of the program.
//It allows other objects to access objects that aren't an instance variable 
//As well as verifying songs are valid to be played.

package model;

import java.time.LocalDate;
import java.util.Observable;

public class Jukebox extends Observable {

	private SongCollection songs;
	private AccountCollection accounts;
	private SongQueue queue;
	private int day;
	private int year;

	// Has AccountCollectionand SongCollection 
	//and eventually the GUIs as Observers
	public Jukebox() {
		songs = new SongCollection();
		accounts = new AccountCollection(this);
		queue = new SongQueue(songs);
		addObservers();
		LocalDate date = LocalDate.now();
		day = date.getDayOfYear();
		year = date.getYear();
	}

	private void addObservers() {
		addObserver(accounts);
		addObserver(songs);
		addObserver(queue);
	}

	//Method takes in a LocalDate object as a parameter.
	//Compares that value Jukebox's stored values for the date.
	public void checkDateChanged(LocalDate dateNow) {
		// create variable's holding the current year and day.
		int yearNow = dateNow.getYear();
		int dayNow = dateNow.getDayOfYear();
		// if the stored values of day or year differ from the values above, the
		// day has changed.
		if (dayNow > day || yearNow > year) {
			// notify observers of this change.
			setChanged();
			notifyObservers("DayChanged");
			day = dayNow;
			year = yearNow;
		}
	}
	
	public boolean validPlay(Song tune){
		return tune.canBePlayed()&& accounts.getCurrUser().canPlay(tune);
	}

	public void newDay(){
		setChanged();
		notifyObservers("DayChanged");
	}
	public SongCollection getSongCollection() {
		return songs;
	}

	public AccountCollection getAccountCollection(){
		return accounts;
	}
	
	// Method called by SongSelector.
	// Lets JukeBox know a song was chosen.
	// If the Song is valid, we update all necessary objects
	public boolean songChosen(Song tune) {
		setChanged();
		notifyObservers(tune);
		return true;
	}

	// Parameter: Song user tried to play
	// Purpose: Validates that account is allowed to play the song.
//	private boolean validSongChoice(Song tune) {
//		Account currUser = accounts.getCurrUser();
//		// User can only play 2 songs per day.
//		if (currUser.getSongsPlayedToday() >= 2) {
//			return false;
//		}
//		// If user's time left is less than length of song, return false
//		if (currUser.getTimeLeft() - tune.getLength() < 0) {
//			return false;
//		}
//		// If song has already been played five times today, return false
//		if (tune.getTimesPlayedToday() >= 5) {
//			return false;
//		}
//		// if we get here, it passes all conditions.
//		// we can play the song.
//		return true;
//	}



}
