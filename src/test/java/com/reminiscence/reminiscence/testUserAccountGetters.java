package com.reminiscence.reminiscence;

import com.reminiscence.reminiscence.account.UserAccount;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class testUserAccountGetters {

    @Autowired
    PasswordEncoder encoder;

    @Before
    public void setUp() {
        // Make three accounts with different everything for use in below tests
        UserAccount user1 = new UserAccount("testUser1", "password", this.encoder, "1994");
        UserAccount user2 = new UserAccount("testUser2", "farts", this.encoder, "2017-5-25");
        UserAccount user3 = new UserAccount("testUser3", "1234", this.encoder, "0000-1-1");
    }

    @Test
    public void testGetUserName() {
    }

    @Test
    public void testGetPassword() {
    }

    @Test
    public void testGetJoinDate() {
    }

    @Test
    public void testGetId() {
    }

    @Test
    public void testGetJournal() {
    }

}
