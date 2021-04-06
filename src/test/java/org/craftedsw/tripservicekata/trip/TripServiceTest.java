package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class TripServiceTest {

	@Test
	void GivenNotUserLoggedIn_WhenGetTripsByUser_ThenShouldRaiseException() {
		// Given
		final TripDAO tripDAO = Mockito.mock(TripDAO.class);
		final User loggedUser = null;
		final User user = new User();

		// When
		final TripService tripService = new TripService(tripDAO);

		// Then
		assertThrows(UserNotLoggedInException.class, () -> tripService.getTripsByUser(loggedUser, user));
	}

	@Test
	void GivenUserLoggedInAndUserWithoutFriends_WhenGetTripsByUser_ThenShouldReturnEmpty() {
		// Given
		final TripDAO tripDAO = Mockito.mock(TripDAO.class);
		final User loggedUser = new User();
		final User user = new User();

		// When
		final TripService tripService = new TripService(tripDAO);
		final List<Trip> tripsByUser = tripService.getTripsByUser(loggedUser, user);

		// Then
		assertThat(tripsByUser, empty());
	}

	@Test
	void GivenUserLoggedInWithOtherFriends_WhenGetTripsByUser_ThenShouldReturnEmpty() {
		// Given
		final TripDAO tripDAO = Mockito.mock(TripDAO.class);
		final User loggedUser = new User();

		final User user = new User();
		user.addFriend(new User());

		// When
		final TripService tripService = new TripService(tripDAO);
		final List<Trip> tripsByUser = tripService.getTripsByUser(loggedUser, user);

		// Then
		assertThat(tripsByUser, empty());
	}

	@Test
	void GivenUserLoggedInAsAFriendOfTheOther_WhenGetTripsByUser_ThenShouldReturnTrips() {
		// Given
		final TripDAO tripDAO = Mockito.mock(TripDAO.class);
		final User loggedUser = new User();

		final User user = new User();
		user.addFriend(loggedUser);
		final List<Trip> userTrips = Arrays.asList(new Trip(), new Trip());

		// When
		final TripService tripService = new TripService(tripDAO);
		given(tripDAO.findTrips(any(User.class)))
				.willReturn(userTrips);
		final List<Trip> tripsByUser = tripService.getTripsByUser(loggedUser, user);

		// Then
		assertThat(tripsByUser, not(empty()));
	}
}
