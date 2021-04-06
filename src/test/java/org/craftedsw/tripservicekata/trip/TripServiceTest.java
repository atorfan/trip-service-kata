package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.empty;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

class TripServiceTest {

	@Test
	void GivenNotUserLoggedIn_WhenGetTripsByUser_ThenShouldRaiseException() {
		// Given
		final UserSession userSession = Mockito.mock(UserSession.class);
		given(userSession.getLoggedUser())
				.willReturn(null);

		final TripService tripService = new TripService(userSession);

		// When, Then
		assertThrows(UserNotLoggedInException.class, () ->
				tripService.getTripsByUser(new User())
		);
	}
}
