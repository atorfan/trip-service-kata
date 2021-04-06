package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

	private final UserSession userSession;

	public TripService(final UserSession userSession) {
		this.userSession = userSession;
	}

	public List<Trip> getTripsByUser(final User user) throws UserNotLoggedInException {
		final User loggedUser = this.userSession.getLoggedUser();
		if (loggedUser == null) {
			throw new UserNotLoggedInException();
		}
		List<Trip> tripList = new ArrayList<>();
		boolean isFriend = false;
		for (final User friend : user.getFriends()) {
			if (friend.equals(loggedUser)) {
				isFriend = true;
				break;
			}
		}
		if (isFriend) {
			tripList = TripDAO.findTripsByUser(user);
		}
		return tripList;
	}
}
