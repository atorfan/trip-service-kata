package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.trip.Trip;

import java.util.ArrayList;
import java.util.List;

public class User {

	private final List<Trip> trips = new ArrayList<>();
	private final List<User> friends = new ArrayList<>();

	public List<User> getFriends() {
		return this.friends;
	}

	public void addFriend(final User user) {
		this.friends.add(user);
	}

	public void addTrip(final Trip trip) {
		this.trips.add(trip);
	}

	public List<Trip> trips() {
		return this.trips;
	}

	public boolean hasFriend(final User user) {
		return this.friends.contains(user);
	}
}
