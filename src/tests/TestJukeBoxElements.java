package tests;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Iterator;

import org.junit.Test;

import model.Account;
import model.AccountCollection;
import model.Jukebox;
import model.Song;
import model.SongCollection;

public class TestJukeBoxElements {

	
	@Test
	public void displayInventory(){
		Jukebox juke = new Jukebox();
		AccountCollection account = juke.getAccountCollection();
		SongCollection songs = juke.getSongCollection();
		Object[] songArray = songs.getSongList();
		
		for(int i=0; i<songArray.length; i++){
			System.out.println("Song name: " +((Song) songArray[i]).getSongName());
			System.out.println("	Artist name: " +((Song) songArray[i]).getArtistName());
			System.out.println("	File name: " +((Song) songArray[i]).getFileName());
			System.out.println("	Length: " +((Song) songArray[i]).getLength());
		}
	}
	@Test
	public void testAccountCollection() {
		Jukebox juke = new Jukebox();
		AccountCollection account = juke.getAccountCollection();
		SongCollection songs = juke.getSongCollection();
		Song temp;
		Account user1 = account.getAccount("Chris");
		Account user2 = account.getAccount("Devon");
		Account user3 = account.getAccount("River");
		Account user4 = account.getAccount("Ryan");
		assertEquals(null,account.getCurrUser());
		account.setCurrentUser(user1);
		assertEquals(0, user1.getSongsPlayedToday());
		System.out.println("User 1 time left before playing song: " +user1.getTimeLeft());
		assertEquals(0,songs.getSong("Danse Macabre").getTimesPlayedToday());
		juke.songChosen(songs.getSong("Danse Macabre"));
		assertEquals(1, user1.getSongsPlayedToday());
		assertEquals(1,songs.getSong("Danse Macabre").getTimesPlayedToday());
		System.out.println("User 1 time left after playing song: " +user1.getTimeLeft());
		juke.newDay();
		assertEquals(0, user1.getSongsPlayedToday());
		assertEquals(0,songs.getSong("Danse Macabre").getTimesPlayedToday());		
	}
	@Test
	public void testUser1RunningOutOfTime(){
		Jukebox juke = new Jukebox();
		AccountCollection account = juke.getAccountCollection();
		SongCollection songs = juke.getSongCollection();
		//This song runs for 5 seconds
		Song lopingSting = songs.getSong("Loping Sting");
		Account user1 = account.getAccount("Chris");
		assertEquals(null,account.getCurrUser());
		account.setCurrentUser(user1);
		assertEquals(user1,account.getCurrUser());
		assertEquals(90000,user1.getTimeLeft());
		assertEquals(0,lopingSting.getTimesPlayedToday());
		//play song 18000 times.
		//18000*5=1500*60 which is the total time on account
		for(int i=1; i<=18000;i++){
			juke.songChosen(lopingSting);
			if(i%3==0){
				//Every third iteration, say there's a new day
				//That way we can just drain the users account in one for loop
				juke.newDay();
			}
		}
		assertEquals(0,user1.getTimeLeft());
		//Once we get here, there should be a test that the user can't play the song.
		//@TO DO: create some tests here

		

	}
	
	@Test
	public void testDateChanger(){
		Jukebox juke = new Jukebox();
		LocalDate date = LocalDate.now();
		//create a date that's one ahead of the current date for jukebox
		date = date.plusDays(1);

		AccountCollection account = juke.getAccountCollection();
		SongCollection songs = juke.getSongCollection();
		
		Account user1 = account.getAccount("Chris");
		Account user2 = account.getAccount("Devon");
		Account user3 = account.getAccount("River");
		Account user4 = account.getAccount("Ryan");
		
		assertEquals(null,account.getCurrUser());
		
		account.setCurrentUser(user2);
		assertEquals(user2, account.getCurrUser());
		
		//Make user
		juke.songChosen(songs.getSong("The Curtain Rises"));
		juke.songChosen(songs.getSong("The Curtain Rises"));
		juke.songChosen(songs.getSong("The Curtain Rises"));
		
		assertEquals(3,songs.getSong("The Curtain Rises").getTimesPlayedToday());
		assertEquals(3,user2.getSongsPlayedToday());
		
		//@TO DO:
		// Maybe throw some tests in here to make sure user 2 can't play songs now?
		//Also check that the song "The Curtain Rises" can't be played
		account.loggedOut();
		assertEquals(null,account.getCurrUser());
		
		account.setCurrentUser(user1);
		assertEquals(user1, account.getCurrUser());
		
		juke.songChosen(songs.getSong("Untameable Fire"));
		juke.songChosen(songs.getSong("Untameable Fire"));
		juke.songChosen(songs.getSong("Untameable Fire"));
		
		assertEquals(3,songs.getSong("Untameable Fire").getTimesPlayedToday());
		assertEquals(3,user1.getSongsPlayedToday());
		
		//@TO DO:
		// Maybe throw some tests in here to make sure user1 can't play songs now?
		//Also check that the song "Untameable Fire" can't be played
		
		//We use the date that we made above.
		//Should reset all of the songs' and user's plays for today
		juke.checkDateChanged(date);
		
		assertEquals(0,user1.getSongsPlayedToday());
		assertEquals(0,user2.getSongsPlayedToday());
		assertEquals(0,songs.getSong("Untameable Fire").getTimesPlayedToday());
		assertEquals(0,songs.getSong("The Curtain Rises").getTimesPlayedToday());
	}//end tests
	
	

}
