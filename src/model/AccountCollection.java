//ClassName: AccountCollection
//Author: Gary Sousa
//Purpose: Keeps a list of the Accounts for the JukeBox and updates the Accounts as 
//necessary.

package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class AccountCollection implements Observer {

	List<Account> accountList;
	Jukebox juke;
	Account currentUser;

	public AccountCollection(Jukebox juke) {
		this.juke = juke;
		accountList = new ArrayList<Account>();
		currentUser = null;
		createAccountList();
	}

	private void createAccountList() {
		accountList.add(new Account("Chris", "1"));
		accountList.add(new Account("Devon", "22"));
		accountList.add(new Account("River", "333"));
		accountList.add(new Account("Ryan", "4444"));
	}

	// Parameter: The name of the account we're searching for
	// Return value: If we find an account with that name, returns the account
	// Else it returns a null value.
	public Account getAccount(String name) {
		Account temp = null;
		Iterator<Account> itr = accountList.iterator();
		while (itr.hasNext()) {
			temp = itr.next();
			if (temp.getName().equals(name)) {
				return temp;
			}
		}
		// If we don't have an account with that name, return null
		return null;
	}// end getAccount

	public void setCurrentUser(Account user) {
		currentUser = user;
	}

	public Account getCurrUser() {
		return currentUser;
	}

	public void loggedOut() {
		currentUser = null;
	}

	@Override
	// If the message is "DayChanged"
	// Reset the songs played for every account.
	// Otherwise, the message is the name of the song that was played.
	// We find the song matching the name
	// and decrement the time left on the current user's account
	// by the length of the song.
	public void update(Observable o, Object message) {
		if (message instanceof String) {
			Iterator<Account> itr = accountList.iterator();
			while (itr.hasNext()) {
				itr.next().newDay();
			} // end while
		} else {
			currentUser.playedSong((Song) message);

		}

	}

}
