package com.reminiscence.reminiscence.watson;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;

public class testToneGetters {

    Tone testTone;

    private Double testScore;
    private String tone;
    private String date;

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

        testTone = new Tone();
        testTone.setScore(78.8);
        String tone = "Confident";
        String date = "6/7/98";

    }


    @Test
    public void testGetScore() {

        assertEquals((Double) 78.9, testTone.getScore());

    }

    @Test
    public void testGetTone() {

    }

    @Test
    public void testGetEntry() {
    }

}