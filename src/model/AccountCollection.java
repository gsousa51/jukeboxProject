//ClassName: AccountCollection
//Authors: Gary Sousa & Stephen Nolan
//Purpose: Keeps a list of the Accounts for the JukeBox and updates the Accounts as 
//necessary.

package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class AccountCollection implements Observer, Serializable {

	List<Account> accountList;
	Jukebox juke;
	Account currentUser;

	public AccountCollection(Jukebox juke) {
		this.juke = juke;
		accountList = new ArrayList<Account>();
		currentUser = null;
		createAccountList();
	}

	//Hardcode the 4 accounts given in spec.
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

	//Used by GUI. Sets the currentUser to account given in parameter
	//This signals that somebody has logged in.
	public void setCurrentUser(Account user) {
		currentUser = user;
	}

	//Returns the currently logged in user.
	public Account getCurrUser() {
		return currentUser;
	}

	//If user logged out, set the current user to null.
	public void loggedOut() {
		currentUser = null;
	}

	@Override
	// If the message isn't a Song, it's a String message saying "DayChanged"
	// Reset the songs played for every account.
	// Otherwise,the message is a Song object that was just played.
	//Decrement current user's account by length of song.
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
