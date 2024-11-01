package com.musicstreaming.services;

import com.musicstreaming.models.Playlist;
import com.musicstreaming.models.PlaylistSong;
import com.musicstreaming.models.Song;

import java.util.List;

public interface PlaylistService {

    Playlist changePlaylistStatus(Long playlistId,int status);
    Playlist createNewPlaylist(Playlist playlist);
    Playlist addSongToPlaylist(List<Song> songs,Long playlistId);
    Playlist removeSongFromPlaylist(List<Song>songs,Long playlistId);
    Playlist sharePlaylist(Long playlistId,String token);
    Playlist deletePlaylist(Long playlistId) throws Exception;
    List<Playlist> getAllPlaylistsByUser(Long userId);
    List<PlaylistSong> getPlaylistWithSongs(Long playlistId);
    String shareLinkPlaylist(Long playlistId);
}
