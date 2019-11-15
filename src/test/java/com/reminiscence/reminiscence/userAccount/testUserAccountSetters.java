package com.reminiscence.reminiscence.userAccount;

import com.reminiscence.reminiscence.account.AccountRepo;
import com.reminiscence.reminiscence.account.UserAccount;
import com.reminiscence.reminiscence.journal.Entry;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testUserAccountSetters {

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
        user1 = new UserAccount();
        user2 = new UserAccount();
        user3 = new UserAccount();
    }

    @Test
    public void testSetUserName() {
        user2.setUsername("Chester");
        user3.setUsername("i am the one");
        assertNull("Username was unexpected", user1.getUsername());
        assertEquals("Username was unexpected", "Chester", user2.getUsername());
        assertEquals("Username was unexpected", "i am the one", user3.getUsername());
    }

    @Test
    public void testSetPassword() {
        user2.setPassword("the Cheetah", encoder);
        user3.setPassword("p4$$w0rd", encoder);
        assertNull("Password was unexpected", user1.getPassword());
        assertNotEquals("Password was unexpected", "the Cheetah", user2.getPassword());
        assertNotEquals("Password was unexpected", "p4$$w0rd", user3.getPassword());
    }

    @Test
    public void testSetJoinDate() {
        user2.setJoinDate("1776");
        user3.setJoinDate("0000-1-1");

        assertNull("Join Date was unexpected", user1.getJoinDate());
        assertEquals("Join Date was unexpected", "1776", user2.getJoinDate());
        assertEquals("Join Date was unexpected", "0000-1-1", user3.getJoinDate());
    }

    @Test
    public void testSetJournal() {
        List<Entry> journal = new ArrayList<>();
        assertEquals("Journal was unexpectedly not null", journal, user1.getJournal());

        Entry entry1 = new Entry();
        journal.add(entry1);
        user2.setJournal(journal);
        assertEquals("Journal was unexpectedly set", journal, user2.getJournal());

        Entry entry2 = new Entry();
        journal.add(entry2);
        Entry entry3 = new Entry();
        journal.add(entry3);
        user3.setJournal(journal);
        assertEquals("Journal was unexpectedly set", journal, user3.getJournal());
    }
}
