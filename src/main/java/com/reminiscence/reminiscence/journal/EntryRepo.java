package com.reminiscence.reminiscence.journal;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepo extends JpaRepository<Entry, Long> {
}
