package com.musicstreaming.repositories;

import com.musicstreaming.models.Album;
import com.musicstreaming.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album,Long> {


}
