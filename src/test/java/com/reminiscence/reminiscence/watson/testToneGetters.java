package com.reminiscence.reminiscence.watson;

import com.reminiscence.reminiscence.journal.Entry;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testToneGetters {
    private Entry entry1;
    private Tone tone1;
    private Tone tone2;
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    ToneRepo toneRepo;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();

        entry1 = new Entry("I suffer under the constant bludgeoning of time.");

        tone1 = new Tone("defeat");
        tone2 = new Tone("sadness");
        tone1.setEntry(entry1);
        tone2.setEntry(entry1);
    }

    @Test
    public void testGetScore() {
        assertEquals("Tone had an unexpected score", 0.49, tone1.getScore(), .001);
        assertEquals("Tone had an unexpected score", 0.49, tone2.getScore(), .001);
    }

    @Test
    public void testGetTone() {
        assertEquals("Tone had an unexpected Tone", "defeat", tone1.getTone());
        assertEquals("Tone had an unexpected Tone", "sadness", tone2.getTone());
    }

    @Test
    public void testGetEntry() {
        assertEquals("Tone entry foreign key was not as expected", entry1, tone1.getEntry());
        assertEquals("Tone entry foreign key was not as expected", entry1, tone2.getEntry());
    }

}