package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

    private TripDAO tripDAO;

    public TripService() {
        this.tripDAO = new TripDAO();
    }

    public TripService(TripDAO tripDAO) {
        this.tripDAO = tripDAO;
    }

    public List<Trip> getTripsByUser(User user, User loggedInUser) throws UserNotLoggedInException {

        validateLoggedInUser(loggedInUser);

        return user.isFriendsWith(loggedInUser)
                ? tripsBy(user)
                : new ArrayList<>();
    }

    private void validateLoggedInUser(final User loggedInUser) {
        if (loggedInUser == null) {
            throw new UserNotLoggedInException();
        }
    }

    List<Trip> tripsBy(final User user) {
        return tripDAO.tripsBy(user);
    }

}
