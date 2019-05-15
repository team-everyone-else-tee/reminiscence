package com.reminiscence.reminiscence.journal;

import com.reminiscence.reminiscence.account.UserAccount;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Entry {
    @Id
    @GeneratedValue
    private long id;

    private String body;
    private String date;
    private boolean isEdited;

    @ManyToOne
    private UserAccount user;

    @OneToMany(mappedBy = "entry")


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
}
