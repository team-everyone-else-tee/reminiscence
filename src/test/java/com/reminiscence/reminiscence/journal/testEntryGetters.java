package com.reminiscence.reminiscence.journal;

import com.reminiscence.reminiscence.account.AccountRepo;
import com.reminiscence.reminiscence.account.UserAccount;
import com.reminiscence.reminiscence.journal.Entry;
import com.reminiscence.reminiscence.journal.EntryRepo;
import com.reminiscence.reminiscence.watson.ToneRepo;
import com.reminiscence.reminiscence.watson.WatsonController;
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
public class testEntryGetters {

    private UserAccount user1;

    private Entry entry1;
    private Entry entry2;
    private Entry entry3;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    EntryRepo entryRepo;

    @Autowired
    ToneRepo toneRepo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    WatsonController watson;

    @Before
    public void setUp() {

        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
        // Make three accounts with different everything for use in below tests
        user1 = new UserAccount("testUser1", "password", this.encoder, "1994");
        accountRepo.save(user1);

        entry1 = new Entry("Hope?", "3/13/200", user1);
        entryRepo.save(entry1);
        watson.requestTones(entry1);

        entry2 = new Entry("Post 2", "8/14/2001", user1);
        entryRepo.save(entry2);
        watson.requestTones(entry2);

        entry3 = new Entry("Post 3", "6/28/2002", user1);
        entryRepo.save(entry3);
        watson.requestTones(entry3);
    }

    @Test
    public void testGetId() {

        assertNotNull("Id was not found", entry1.getId());
        assertNotNull("Id was not found", entry2.getId());
        assertNotNull("Id was not found", entry3.getId());
    }

    @Test
    public void testGetBody() {
        assertEquals("Post Body was Unexpected", "Hope?", entry1.getBody());
        assertEquals("Post Body was Unexpected", "Post 2", entry2.getBody());
        assertEquals("Post Body was Unexpected", "Post 3", entry3.getBody());
    }

    @Test
    public void getDate() {
        assertEquals("Post Date was Unexpected", "3/13/200", entry1.getDate());
        assertEquals("Post Date was Unexpected", "8/14/2001", entry2.getDate());
        assertEquals("Post Date was Unexpected", "6/28/2002", entry3.getDate());
    }

    @Test
    public void getUser() {
        assertEquals("Post User was Unexpected", user1, entry1.getUser());
        assertEquals("Post User was Unexpected", user1, entry2.getUser());
        assertEquals("Post User was Unexpected", user1, entry3.getUser());


    }

    //this test is basically useless.
    @Test
    public void getTones() {
        System.out.println("ITS ME YA SOUT BOI " + entry1.getTones());
        assertNotNull(entry1.getTones());
        assertSame("[]", entry1.getTones().toString());
    }

}