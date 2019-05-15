package com.reminiscence.reminiscence;

import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.tone_analyzer.v3.model.ToneOptions;
import com.reminiscence.reminiscence.watson.Tone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ReminiscenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReminiscenceApplication.class, args);

        /*printWat();*/
    }

    /*public static void printWat(){
        ToneAnalysis tone = Tone.getTestWatson();
        System.out.println("THIS IS THE TONE SOUT" + tone);
    }
*/


}
