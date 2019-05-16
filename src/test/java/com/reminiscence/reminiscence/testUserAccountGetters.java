package com.reminiscence.reminiscence;

import com.reminiscence.reminiscence.account.AccountController;
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
    AccountController accountController;


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
