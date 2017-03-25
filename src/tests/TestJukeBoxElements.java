package tests;

import static org.junit.Assert.*;

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
	//A lot of these tests aren't going to work because of how we have to 
	//Update the JList containing the PlayList...
	@Test
	public void testAccountCollection() {
		Jukebox juke = new Jukebox();
		Song temp;
		Account user1 = juke.getAccountCollection().getAccount("Chris");
		Account user2 = juke.getAccountCollection().getAccount("Devon");
		Account user3 = juke.getAccountCollection().getAccount("River");
		Account user4 = juke.getAccountCollection().getAccount("Ryan");
	//	assertEquals(null,juke.getAccountCollection().getCurrUser());
		juke.getAccountCollection().setCurrentUser(user1);
		assertEquals(0, user1.getSongsPlayedToday());
		System.out.println("User 1 time left before playing song: " +user1.getTimeLeft());
		assertEquals(0,juke.getSongCollection().getSong("Danse Macabre").getTimesPlayedToday());
		juke.songChosen(juke.getSongCollection().getSong("Danse Macabre"));
		assertEquals(1, user1.getSongsPlayedToday());
		assertEquals(1,juke.getSongCollection().getSong("Danse Macabre").getTimesPlayedToday());
		System.out.println("User 1 time left after playing song: " +user1.getTimeLeft());
		juke.newDay();
		assertEquals(0, user1.getSongsPlayedToday());
		assertEquals(0,juke.getSongCollection().getSong("Danse Macabre").getTimesPlayedToday());		
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
		assertFalse(juke.validPlay(lopingSting));
		//Make sure it can't play another song, either.
		assertFalse(juke.validPlay(songs.getSong("Tada")));
		

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
		assertFalse(juke.validPlay(songs.getSong("The Curtain Rises")));
		assertFalse(juke.validPlay(songs.getSong("Tada")));
		account.loggedOut();
		assertEquals(null,account.getCurrUser());
		account.setCurrentUser(user1);
		assertEquals(user1, account.getCurrUser());
		assertTrue(juke.validPlay(songs.getSong("Tada")));
		//Shouldn't be able to play, already has three plays
		assertFalse(juke.validPlay(songs.getSong("The Curtain Rises")));
		
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
	
	@Test
	public void testValidAccounts(){
		Jukebox juke = new Jukebox();
		AccountCollection account = juke.getAccountCollection();

		Account user1 = account.getAccount("Chris");
		Account user2 = account.getAccount("Devon");
		Account user3 = account.getAccount("River");
		Account user4 = account.getAccount("Ryan");
		
		assertTrue(juke.validAccount("Chris", "1"));
		assertTrue(juke.validAccount("Devon","22"));
		assertTrue(juke.validAccount("River", "333"));
		assertTrue(juke.validAccount("Ryan","4444"));
		assertFalse(juke.validAccount("RYan", "4444"));
		assertFalse(juke.validAccount("Ryan", "1"));
		assertFalse(juke.validAccount("Chris", "4444"));
		assertFalse(juke.validAccount("chris", "1"));
		assertFalse(juke.validAccount("RYAN", "4444"));
	}
	
	@Test
	public void makeAllSongsMaxedOut(){
		Jukebox juke = new Jukebox();
		AccountCollection account = juke.getAccountCollection();
		SongCollection songs = juke.getSongCollection();
		
		Account user1 = account.getAccount("Chris");
		Account user2 = account.getAccount("Devon");
		Account user3 = account.getAccount("River");
		Account user4 = account.getAccount("Ryan");
		
		Song song1 = songs.getSong("Tada");
		Song song2 = songs.getSong("Swing Cheese");
		Song song3 = songs.getSong("Flute");
		
		Song song4 = songs.getSong("Space Music");
		
		account.setCurrentUser(user1);
		assertTrue(juke.validPlay(song1));
		juke.songChosen(song1);
		account.loggedOut();
		account.setCurrentUser(user2);
		assertTrue(juke.validPlay(song1));
		juke.songChosen(song1);
		account.loggedOut();
		account.setCurrentUser(user3);
		assertTrue(juke.validPlay(song1));
		juke.songChosen(song1);
		assertFalse(juke.validPlay(song1));
		account.loggedOut();

		account.setCurrentUser(user1);
		assertTrue(juke.validPlay(song2));
		juke.songChosen(song2);
		account.loggedOut();
		account.setCurrentUser(user2);
		assertTrue(juke.validPlay(song2));
		juke.songChosen(song2);
		account.loggedOut();
		account.setCurrentUser(user3);
		assertTrue(juke.validPlay(song2));
		juke.songChosen(song2);
		assertFalse(juke.validPlay(song2));
		account.loggedOut();
		
		
		account.setCurrentUser(user1);
		assertTrue(juke.validPlay(song3));
		juke.songChosen(song3);
		account.loggedOut();
		account.setCurrentUser(user2);
		assertTrue(juke.validPlay(song3));
		juke.songChosen(song3);
		account.loggedOut();
		account.setCurrentUser(user3);
		assertTrue(juke.validPlay(song3));
		juke.songChosen(song3);
		account.loggedOut();
		//change user to a valid user for next test.
		account.setCurrentUser(user4);
		assertFalse(juke.validPlay(song3));
		account.loggedOut();
		
		//Now all of the accounts should be maxed out as well
		//We'll test with a "fresh" song
		account.setCurrentUser(user1);
		assertFalse(juke.validPlay(song4));
		account.setCurrentUser(user2);
		assertFalse(juke.validPlay(song4));
		account.setCurrentUser(user3);
		assertFalse(juke.validPlay(song4));
		
		//should reset all accounts and songs
		juke.newDay();
		
		
		account.setCurrentUser(user1);
		assertTrue(juke.validPlay(song4));
		account.setCurrentUser(user2);
		assertTrue(juke.validPlay(song4));
		account.setCurrentUser(user3);
		assertTrue(juke.validPlay(song4));
		
		account.setCurrentUser(user1);
		assertTrue(juke.validPlay(song1));
		account.setCurrentUser(user2);
		assertTrue(juke.validPlay(song1));
		account.setCurrentUser(user3);
		assertTrue(juke.validPlay(song1));
		
		account.setCurrentUser(user1);
		assertTrue(juke.validPlay(song2));
		account.setCurrentUser(user2);
		assertTrue(juke.validPlay(song2));
		account.setCurrentUser(user3);
		assertTrue(juke.validPlay(song2));
		
		account.setCurrentUser(user1);
		assertTrue(juke.validPlay(song3));
		account.setCurrentUser(user2);
		assertTrue(juke.validPlay(song3));
		account.setCurrentUser(user3);
		assertTrue(juke.validPlay(song3));
		
		
	}
	


	

}
