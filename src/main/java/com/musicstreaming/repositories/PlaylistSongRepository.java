package com.musicstreaming.repositories;

import com.musicstreaming.models.Playlist;
import com.musicstreaming.models.PlaylistFolder;
import com.musicstreaming.models.PlaylistSong;
import com.musicstreaming.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlaylistSongRepository  extends JpaRepository<PlaylistSong,Long> {
    Optional<PlaylistSong> findByPlaylistAndSong(Playlist playlist, Song song);
    List<PlaylistSong> findByPlaylist(Playlist playlist);
}
