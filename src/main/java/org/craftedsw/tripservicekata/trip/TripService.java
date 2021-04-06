package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;

import java.util.Collections;
import java.util.List;

public class TripService {

	private final TripDAO tripDAO;

	public TripService(final TripDAO tripDAO) {
		this.tripDAO = tripDAO;
	}

	public List<Trip> getTripsByUser(final User loggedUser, final User user) throws UserNotLoggedInException {
		this.ensureUserLogged(loggedUser);
		return (user.hasFriend(loggedUser))
				? this.tripDAO.findTrips(user)
				: Collections.emptyList();
	}

	private void ensureUserLogged(final User loggedUser) {
		if (loggedUser == null) {
			throw new UserNotLoggedInException();
		}
	}
}
