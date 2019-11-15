package com.reminiscence.reminiscence.watson;

import com.reminiscence.reminiscence.journal.Entry;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testToneSetters {
    private MockMvc mockMvc;
    private Tone tone;


    @Before
    public void setUp() {
        tone = new Tone();
    }

    @Test
    public void setScore() {
        assertNull("Tone unexpectedly was not null", tone.getScore());
        tone.setScore(1.0);
        assertEquals("Tone score was not 1.0", 1.0, tone.getScore(), .0001);
        tone.setScore(.23);
        assertEquals("Tone score was not .23", .23, tone.getScore(), .0001);
    }

    @Test
    public void setTone() {
        assertNull("Tone unexpectedly was not null", tone.getTone());
        tone.setTone("anger");
        assertEquals("Tone was not anger", "anger", tone.getTone());
        tone.setTone("Super Anger");
        assertEquals("Tone was not Super Anger", "Super Anger", tone.getTone());
    }

    @Test
    public void setEntry() {
        Entry entry1 = new Entry("fart noises");
        Entry entry2 = new Entry("double fart noises");
        assertNull("Tone entry was unexpectedly not null", tone.getEntry());
        tone.setEntry(entry1);
        assertEquals("Tone entry was not entry1", entry1, tone.getEntry());
        tone.setEntry(entry2);
        assertEquals("Tone entry was not entry1", entry2, tone.getEntry());
    }

}