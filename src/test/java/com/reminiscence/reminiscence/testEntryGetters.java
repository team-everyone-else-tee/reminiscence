package com.reminiscence.reminiscence;

import com.reminiscence.reminiscence.account.AccountRepo;
import com.reminiscence.reminiscence.account.UserAccount;
import com.reminiscence.reminiscence.journal.Entry;
import com.reminiscence.reminiscence.journal.EntryRepo;
import com.reminiscence.reminiscence.watson.Tone;
import com.reminiscence.reminiscence.watson.ToneRepo;
import com.reminiscence.reminiscence.watson.WatsonController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
public class testEntryGetters {

    private MockMvc mockMvc;
    private UserAccount user1;
    private Entry entry1;
    private Entry entry2;
    private Entry entry3;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    EntryRepo entryRepo;

    @Autowired
    ToneRepo toneRepo;

    @Autowired
    WatsonController watsonController;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
        // Make three accounts with different everything for use in below tests
        user1 = new UserAccount();
        user1 = accountRepo.save(user1);
        entry1 = new Entry("I have journaled one time", "2019-08-08", user1);
        entry2 = new Entry("I have journaled two times", "1776-01-01", user1);
        entry3 = new Entry("I have journaled three times", "3000-3000-3000", user1);
        entry3.setEdited(true);
        entry1 = entryRepo.save(entry1);
        entry2 = entryRepo.save(entry2);
        entry3 = entryRepo.save(entry3);
    }

    @Test
    public void testEntryGetId() {
        assertNotNull("ID was unexpectedly null", entry1.getId());
        assertNotNull("ID was unexpectedly null", entry2.getId());
        assertNotNull("ID was unexpectedly null", entry3.getId());
    }

    @Test
    public void testEntryGetBody() {
        assertEquals("Body was unexpected", "I have journaled one time", entry1.getBody());
        assertEquals("Body was unexpected", "I have journaled two times", entry2.getBody());
        assertEquals("Body was unexpected", "I have journaled three times", entry3.getBody());
    }

    @Test
    public void testEntryGetDate() {
        assertEquals("Date was unexpected", "2019-08-08", entry1.getDate());
        assertEquals("Date was unexpected", "1776-01-01", entry2.getDate());
        assertEquals("Date was unexpected", "3000-3000-3000", entry3.getDate());
    }

    @Test
    public void testEntryGetUser() {
        assertEquals("user was unexpected", user1, entry1.getUser());
        assertEquals("user was unexpected", user1, entry2.getUser());
        assertEquals("user was unexpected", user1, entry3.getUser());
    }

    @Test
    public void testEntryGetTonesNull() {
        assertNull(entry1.getTones());
        assertNull(entry2.getTones());
        assertNull(entry3.getTones());
    }

    @Test
    public void testEntryGetTonesNotNull() throws Exception {
        List<Tone> tones = new ArrayList<>();
        Tone tone1 = new Tone("testjoy");
        Tone tone2 = new Tone("testfear");
        Tone tone3 = new Tone("testanger");
        tones.add(tone1);
        tones.add(tone2);
        tones.add(tone3);
        entry1.setTones(tones);
        assertEquals("Tones was unexpectedly not grabbed", tones, entry1.getTones());

    }

    @Test
    public void testEntryIsEdited() {
        assertFalse("Is eddited was unexpectedly True", entry1.isEdited());
        assertFalse("Is eddited was unexpectedly True", entry2.isEdited());
        assertTrue("Is eddited was unexpectedly False", entry3.isEdited());
    }

}
