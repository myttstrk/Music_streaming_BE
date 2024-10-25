package com.musicstreaming.services;

import com.musicstreaming.models.Song;

import java.util.List;

public interface AlbumService {

    public void createNewAlbum();
    public void addSongToAlbum(List<Song>songList);
    public void removeSongFromAlbum(List<Song>songList);
    public void deleteAlbum();


}
