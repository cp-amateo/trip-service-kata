package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.trip.UserBuilder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserShould {

    private static final User TRISHA = new User();
    private static final User PAUL = new User();

    @Test
    void inform_when_friends_with_another_user() {
        User user = UserBuilder.aUser()
                .friendsWith(TRISHA)
                .build();
        
        assertThat(user.isFriendsWith(PAUL)).isFalse();
        assertThat(user.isFriendsWith(TRISHA)).isTrue();
    }

    @Test
    void return_false_when_null_user() {
        User anyUser = new User();
        assertThat(anyUser.isFriendsWith(null)).isFalse();
    }
}
