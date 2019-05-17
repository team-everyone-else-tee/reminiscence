package com.reminiscence.reminiscence.watson;

import com.ibm.watson.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.tone_analyzer.v3.model.ToneScore;
import com.reminiscence.reminiscence.journal.Entry;
import com.reminiscence.reminiscence.journal.EntryRepo;
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

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testWatsonController {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    WatsonController watsonController;

    @Autowired
    EntryRepo entryRepo;

    @Autowired
    ToneRepo toneRepo;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void testGetToneAnalysis() {
        Entry entry = new Entry("Hope?");
        ToneAnalysis TA = watsonController.getToneAnalysis(entry.getBody());
        assertNotNull("TA was unexpectedly Null", TA);
        assertNull("sentences Tone was unexpectedly Not Null", TA.getSentencesTone());
    }

    @Test
    public void testGetToneList() {
        Entry entry = new Entry("Hope?");
        ToneAnalysis TA = watsonController.getToneAnalysis(entry.getBody());
        List<ToneScore> listTones = watsonController.getToneList(TA);

        assertNotNull("Tone list was unexpectedly null", listTones);
        assertEquals("Tone list had an unexpected size", 2, listTones.size());
        assertEquals("Expected listTones to contain 'Joy'", "joy", listTones.get(0).getToneId());
        System.out.println("ugly boy");
    }

    @Test
    public void saveTones() {
        Entry entry = new Entry("Hope?");
        entryRepo.save(entry);
        
        ToneAnalysis TA = watsonController.getToneAnalysis(entry.getBody());
        List<ToneScore> listTones = watsonController.getToneList(TA);

        watsonController.saveTones(listTones, entry);

        List<Tone> tones = toneRepo.findAll();
        assertEquals("tones had an unexpected size", 2, tones.size());

        System.out.println("ugly booy");
    }

}