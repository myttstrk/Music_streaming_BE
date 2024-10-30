package com.musicstreaming.repositories;

import com.musicstreaming.models.Album;
import com.musicstreaming.models.AlbumSong;
import com.musicstreaming.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlbumSongRepository extends JpaRepository<AlbumSong,Long> {
    AlbumSong findByAlbumAndSong(Album album, Song song);

    List<AlbumSong> findAllByAlbum(Optional<Album> album);
}
