package com.reminiscence.reminiscence.userAccount;

import com.reminiscence.reminiscence.account.AccountRepo;
import com.reminiscence.reminiscence.account.UserAccount;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testUserAccountGetters {

    private UserAccount user1;
    private UserAccount user2;
    private UserAccount user3;


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    PasswordEncoder encoder;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
        // Make three accounts with different everything for use in below tests
        user1 = new UserAccount("testUser1", "password", this.encoder, "1994");
        user2 = new UserAccount("testUser2", "farts", this.encoder, "2017-5-25");
        user3 = new UserAccount("testUser3", "1234", this.encoder, "0000-1-1");
    }

    @Test
    public void testGetUserName() {
        assertEquals("Username was unexpected", "testUser1", user1.getUsername());
        assertEquals("Username was unexpected", "testUser2", user2.getUsername());
        assertEquals("Username was unexpected", "testUser3", user3.getUsername());
    }

    @Test
    public void testGetPassword() {
        assertNotEquals("Password was unexpected", "password", user1.getPassword());
        assertNotEquals("Password was unexpected", "farts", user2.getPassword());
        assertNotEquals("Password was unexpected", "1234", user3.getPassword());
    }

    @Test
    public void testGetJoinDate() {
        assertEquals("Join Date was unexpected", "1994", user1.getJoinDate());
        assertEquals("Join Date was unexpected", "2017-5-25", user2.getJoinDate());
        assertEquals("Join Date was unexpected", "0000-1-1", user3.getJoinDate());
    }

    @Test
    public void testGetJournal() {
        assertNull("Journal was unexpectedly not null", user1.getJournal());
    }

    @Test
    public void testGetId() {
        UserAccount user4 = new UserAccount("testUser4", "danger", encoder, "2000");
        user4 = accountRepo.save(user4);

        assertEquals("Id was unexpected", 1, user4.getId());
    }


}
