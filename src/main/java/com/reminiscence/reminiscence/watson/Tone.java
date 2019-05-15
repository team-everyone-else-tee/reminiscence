package com.reminiscence.reminiscence.watson;

import com.reminiscence.reminiscence.journal.Entry;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Tone {
    @Id
    @GeneratedValue
    private Long id;
    private Double score;
    private String tone;

    //    foreign key to entry
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

    public void setEntry(Entry entry) {
        this.entry = entry;
    }
}

