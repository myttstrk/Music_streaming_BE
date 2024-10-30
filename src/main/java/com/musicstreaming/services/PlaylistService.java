package com.musicstreaming.services;

import com.musicstreaming.models.Playlist;
import com.musicstreaming.models.Song;

import java.util.List;

public interface PlaylistService {

    Playlist changePlaylistStatus(Long playlistId,int status);
    Playlist createNewPlaylist(Playlist playlist);
    Playlist addSongToPlaylist(List<Song> songs,Long playlistId);
    Playlist removeSongFromPlaylist(List<Song>songs,Long playlistId);
    Playlist sharePlaylist(Long playlistId);
    Playlist deletePlaylist(Long playlistId);
    List<Playlist> getAllPlaylistsByUser(Long userId);
    Playlist getPlaylistWithSongs(Long playlistId);

}
