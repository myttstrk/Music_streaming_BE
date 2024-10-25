package com.musicstreaming.repositories;

import com.musicstreaming.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdsRepository extends JpaRepository<Ad,Long> {
}
