package com.musicstreaming.repositories;

import com.musicstreaming.models.Song;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song,Long> {
    Optional<Song> findById(int id);

    Optional<Song> deleteSongById(int id);
    @Query("SELECT s FROM Song s ORDER BY s.createdAt DESC")
    List<Song>findLatestSongs(Pageable pageable);

    @Query("SELECT s FROM Song s JOIN PlayCount pc ON s.id = pc.songID ORDER BY pc.playCount DESC")
    List<Song> findTop100SongsByPlayCount();


}
