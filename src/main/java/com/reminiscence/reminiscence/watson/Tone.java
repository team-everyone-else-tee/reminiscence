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
    public String tone;

    @ManyToOne
    public Entry entry;

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
}

