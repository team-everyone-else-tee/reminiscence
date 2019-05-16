package com.reminiscence.reminiscence.watson;

import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.tone_analyzer.v3.model.DocumentAnalysis;
import com.ibm.watson.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.tone_analyzer.v3.model.ToneOptions;
import com.ibm.watson.tone_analyzer.v3.model.ToneScore;
import com.reminiscence.reminiscence.journal.Entry;
import com.reminiscence.reminiscence.journal.EntryController;
import com.reminiscence.reminiscence.journal.EntryRepo;
import com.sun.jndi.ldap.EntryChangeResponseControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WatsonController {

    @Autowired
    ToneRepo toneRepo;


    @GetMapping("/watson")
    public void requestTones(Entry entry) {
        ToneAnalysis toneAnalysis = getToneAnalysis(entry.getBody());
        List<ToneScore> listTones = getToneList(toneAnalysis);
        saveTones(listTones, entry);
    }

    public void saveTones(List<ToneScore> listTones, Entry entry) {
        for (ToneScore tone : listTones) {
            Tone newTone = new Tone();
            newTone.setTone(tone.getToneName());
            newTone.setScore(tone.getScore());
            newTone.setEntry(entry);
            toneRepo.save(newTone);
        }
    }

    public List<ToneScore> getToneList(ToneAnalysis tone) {
        DocumentAnalysis documentTone = tone.getDocumentTone();
        List<ToneScore> parsedTones = documentTone.getTones();
        return parsedTones;
    }

    public ToneAnalysis getToneAnalysis(String entry) {

        IamOptions options = new IamOptions.Builder()
                .apiKey("6qDP7NDHH7luqX1i5iNvnmnFruMAGosa96WbcCfpEyX9")
                .build();

        ToneAnalyzer toneAnalyzer = new ToneAnalyzer("2017-09-21", options);
        toneAnalyzer.setEndPoint("https://gateway.watsonplatform.net/tone-analyzer/api");

        List<String> desired = new ArrayList<>();
        desired.add("social");
        ToneOptions toneOptions = new ToneOptions.Builder()
                .text(entry)
                .sentences(false)
                .tones(desired)
                .build();
        ToneAnalysis tone = toneAnalyzer.tone(toneOptions).execute().getResult();
        return tone;
    }
}