package com.reminiscence.reminiscence.account;

import com.reminiscence.reminiscence.journal.Entry;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class UserAccount implements UserDetails {

    @Column(unique = true)
    private String username;
    private String password;
    private String joinDate;

    @OneToMany(mappedBy = "user")
    private List<Entry> journal;

    public UserAccount() {
        journal = new ArrayList<>();
    }

    @GeneratedValue
    @Id
    private long id;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password, PasswordEncoder encoder) {
        this.password = encoder.encode(password);
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public long getId() {
        return id;
    }

    public List<Entry> getJournal() {
        return journal;
    }

    public void setJournal(List<Entry> journal) {
        this.journal = journal;
    }

}
