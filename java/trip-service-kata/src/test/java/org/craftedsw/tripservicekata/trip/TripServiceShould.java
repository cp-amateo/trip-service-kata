package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.craftedsw.tripservicekata.trip.UserBuilder.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TripServiceShould {

    private static final User GUEST = null;
    private static final User ANY_USER = new User();
    private static final User REGISTERED_USER = new User();
    private static final Trip LONDON = new Trip();
    private static final User ANOTHER_USER = new User();
    private static final Trip BARCELONA = new Trip();

    @Mock
    private TripDAO tripDAO;

    private TripService tripService;

    @BeforeEach
    void setUp() {
        tripService = new TripService(tripDAO);
    }

    @Test
    void validate_the_logged_in_user() {
        assertThrows(UserNotLoggedInException.class, () -> tripService.getTripsByUser(ANY_USER, GUEST));
    }

    @Test
    void no_trips_when_users_are_not_friends() {
        User stranger = aUser()
                .friendsWith(ANOTHER_USER)
                .withTripsTo(LONDON)
                .build();

        List<Trip> trips = tripService.getTripsByUser(stranger, REGISTERED_USER);

        assertThat(trips).isEmpty();
    }

    @Test
    void return_trips_when_users_are_friends() {
        User friend = aUser()
                .friendsWith(ANOTHER_USER, REGISTERED_USER)
                .withTripsTo(LONDON, BARCELONA)
                .build();

        given(tripDAO.tripsBy(friend)).willReturn(friend.trips());

        List<Trip> trips = tripService.getTripsByUser(friend, REGISTERED_USER);

        assertThat(trips).containsExactlyInAnyOrder(LONDON, BARCELONA);
    }

}