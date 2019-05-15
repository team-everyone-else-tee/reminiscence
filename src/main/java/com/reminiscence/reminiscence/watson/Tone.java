package com.reminiscence.reminiscence.watson;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.tone_analyzer.v3.model.DocumentAnalysis;
import com.ibm.watson.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.tone_analyzer.v3.model.ToneOptions;
import com.ibm.watson.tone_analyzer.v3.model.ToneScore;
import com.reminiscence.reminiscence.journal.Entry;
import com.reminiscence.reminiscence.journal.EntryRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Tone {

    @Autowired
    EntryRepo entryRepo;

    @Autowired
    ToneRepo toneRepo;

    @Id
    @GeneratedValue
    private Long id;
    private Double score;
    public String tone;


    public Tone (){}


   /*public List toneArray = new ArrayList<Tone>();*/

  /*  public static void populateArrayList(ArrayList toneArray, String tone) {

        toneArray.add(tone);
}*/

    @ManyToOne
    private Entry entry;


    public Long getId() {
        return id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getTone() {
        return tone;
    }

    public void setTone(String tone) {
        this.tone = tone;
    }

    public static ToneAnalysis getTestWatson(String entry) {

        IamOptions options = new IamOptions.Builder()
                .apiKey("6qDP7NDHH7luqX1i5iNvnmnFruMAGosa96WbcCfpEyX9")
                .build();

        ToneAnalyzer toneAnalyzer = new ToneAnalyzer("2017-09-21", options);
        toneAnalyzer.setEndPoint("https://gateway.watsonplatform.net/tone-analyzer/api");

        ToneOptions toneOptions = new ToneOptions.Builder()
                .text(entry)
                .sentences(false)
                .build();
        ToneAnalysis tone = toneAnalyzer.tone(toneOptions).execute().getResult();

        return tone;
    }

    public static List<ToneScore> parseTone(ToneAnalysis tone) {

        DocumentAnalysis docTone = tone.getDocumentTone();

        List<ToneScore> parsedTone = docTone.getTones();

       return parsedTone;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }
}
