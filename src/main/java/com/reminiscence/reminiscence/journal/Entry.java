package com.reminiscence.reminiscence.journal;

import com.reminiscence.reminiscence.account.UserAccount;
import com.reminiscence.reminiscence.watson.Tone;
import com.reminiscence.reminiscence.watson.ToneRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Entry {

    @Autowired
    ToneRepo toneRepo;

    @Autowired
    EntryRepo entryRepo;

    @Id
    @GeneratedValue
    private long id;

    private String body;
    private String date;
    private boolean isEdited;

    @ManyToOne
    private UserAccount user;

    @OneToMany(mappedBy = "entry")
    private List<Tone> tone = new ArrayList<>();

    public Entry() {
    }

    public Entry(String body) {
        this.body = body;
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public boolean isEdited() {
        return isEdited;
    }

    public void setEdited(boolean edited) {
        isEdited = edited;
    }

    public List<Tone> getTone() {
        return tone;
    }
}
