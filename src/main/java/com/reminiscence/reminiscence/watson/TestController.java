package com.reminiscence.reminiscence.watson;

import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.tone_analyzer.v3.model.ToneOptions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/watson")
    public String getTestWatson() {

        IamOptions options = new IamOptions.Builder()
                .apiKey("6qDP7NDHH7luqX1i5iNvnmnFruMAGosa96WbcCfpEyX9")
                .build();

        ToneAnalyzer toneAnalyzer = new ToneAnalyzer("2017-09-21", options);
        toneAnalyzer.setEndPoint("https://gateway.watsonplatform.net/tone-analyzer/api");

        String text = "hey, dad, I won football with the layups";

        ToneOptions toneOptions = new ToneOptions.Builder()
                .text(text)
                .build();
        ToneAnalysis tone = toneAnalyzer.tone(toneOptions).execute().getResult();
        System.out.println(tone);
        return "home";
    }
}