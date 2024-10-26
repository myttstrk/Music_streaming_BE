package com.musicstreaming.repositories;

import com.musicstreaming.models.AlbumSong;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumSongRepository extends JpaRepository<AlbumSong,Long> {
}
