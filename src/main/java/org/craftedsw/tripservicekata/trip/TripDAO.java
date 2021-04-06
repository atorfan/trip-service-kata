package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.craftedsw.tripservicekata.user.User;

import java.util.List;

public class TripDAO {

	public List<Trip> findTrips(final User user) {
		return TripDAO.findTripsByUser(user);
	}

	public static List<Trip> findTripsByUser(final User user) {
		throw new CollaboratorCallException(
				"TripDAO should not be invoked on an unit test.");
	}
}