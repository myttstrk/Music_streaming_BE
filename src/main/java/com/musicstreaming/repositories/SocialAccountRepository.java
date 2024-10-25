package com.musicstreaming.repositories;

import com.musicstreaming.models.SocialAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialAccountRepository  extends JpaRepository<SocialAccount, Long> {
}
