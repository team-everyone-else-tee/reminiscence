package com.reminiscence.reminiscence.journal;

import com.reminiscence.reminiscence.account.AccountRepo;
import com.reminiscence.reminiscence.account.UserAccount;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class testEntryGetters {

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
    public void testGetId() {
    }

    @Test
    public void testGetBody() {
    }

    @Test
    public void getDate() {
    }

    @Test
    public void getUser() {

    }

    @Test
    public void getTones() {
    }

}