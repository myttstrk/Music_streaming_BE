package com.musicstreaming.repositories;

import com.musicstreaming.models.PlaylistFolder;
import com.musicstreaming.models.PlaylistSong;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistSongRepository  extends JpaRepository<PlaylistSong,Long> {
}
