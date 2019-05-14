package com.reminiscence.reminiscence.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<UserAccount, Long> {
    UserAccount findByUsername(String username);
}
