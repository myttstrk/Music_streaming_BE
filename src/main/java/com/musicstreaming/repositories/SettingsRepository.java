package com.musicstreaming.repositories;

import com.musicstreaming.models.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingsRepository extends JpaRepository<Setting,Long> {
}
