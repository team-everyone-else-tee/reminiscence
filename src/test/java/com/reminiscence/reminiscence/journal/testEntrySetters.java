package com.reminiscence.reminiscence.journal;

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
public class testEntrySetters {

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
    public void testEntrySetBody(){
        String newBody1 = "I can do the can-can. I can.";
        entry1.setBody(newBody1);
        assertEquals("Entry body was not successfully updated", newBody1, entry1.getBody());

        String newBody2 = "I am the lemon king. KING. How dare you stand in my court, bold as brass.";
        entry1.setBody(newBody2);
        assertEquals("Entry body was not successfully updated", newBody2, entry1.getBody());

        String newBody3 = "There was a man from mantucket. I dont know whats become of him.";
        entry1.setBody(newBody3);
        assertEquals("Entry body was not successfully updated", newBody3, entry1.getBody());
    }

    @Test
    public void testEntrySetDate(){
        String newDate1 = "1776-07-04";
        entry1.setDate(newDate1);
        assertEquals("Entry Date was not successfully updated", newDate1, entry1.getDate());

        String newDate2 = "3000-3000-3000";
        entry1.setDate(newDate2);
        assertEquals("Entry Date was not successfully updated", newDate2, entry1.getDate());

        String newDate3 = "The beginning of time";
        entry1.setDate(newDate3);
        assertEquals("Entry Date was not successfully updated", newDate3, entry1.getDate());
    }

    @Test
    public void testEntrySetUser(){
        entry1.setUser(user1);
        entry2.setUser(user1);
        entry3.setUser(user1);
        assertEquals("Entry User was not successfully updated", user1, entry1.getUser());
        assertEquals("Entry User was not successfully updated", user1, entry2.getUser());
        assertEquals("Entry User was not successfully updated", user1, entry3.getUser());
    }

    @Test
    public void testEntrySetEddited(){
        assertFalse("Entry is edited was not succesffuly updated", entry1.isEdited());
        entry1.setEdited(true);
        assertTrue("Entry is edited was not succesffuly updated", entry1.isEdited());
        entry1.setEdited(false);
        assertFalse("Entry is edited was not succesffuly updated", entry1.isEdited());
    }

    @Test
    public void testEntrySetTones(){
        assertNull("Entry tones were not as expected", entry1.getTones());
        List<Tone> tones = new ArrayList<>();
        Tone tone1 = new Tone("Joy");
        tones.add(tone1);
        entry1.setTones(tones);
        assertEquals("Entry tones were not as expected", tones ,entry1.getTones());
        Tone tone2 = new Tone("Super Anger");
        Tone tone3 = new Tone("normal Anger");
        tones.add(tone2);
        tones.add(tone3);
        entry1.setTones(tones);
        assertEquals("Entry tones were not as expected", tones ,entry1.getTones());
    }

}
