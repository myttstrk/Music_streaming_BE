package com.musicstreaming.repositories;

import com.musicstreaming.models.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<Playlist,Long> {
    Optional<List<Playlist>> findByUserId(Long userId);
}
