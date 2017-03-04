package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Account;
import model.AccountCollection;
import model.Jukebox;
import model.Song;
import model.SongCollection;

public class TestJukeBoxElements {

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

}
