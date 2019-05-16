package com.reminiscence.reminiscence.watson;

import com.reminiscence.reminiscence.journal.Entry;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
public class Tone {
    @Id
    @GeneratedValue
    private Long id;

    private Double score;
    private String tone;
    private String date;

    //    foreign key to entry
    @ManyToOne
    private Entry entry;

    public Tone() {
        this.score = score;
        this.tone = tone;
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        this.date = date;
    }

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

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public Entry getEntry() {
        return entry;
    }
}

