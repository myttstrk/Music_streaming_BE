package com.musicstreaming.services.impl;

import com.google.cloud.storage.Storage;
import com.musicstreaming.components.InitializeStorage;
import com.musicstreaming.models.Playlist;
import com.musicstreaming.models.Song;
import com.musicstreaming.repositories.PlaylistRepository;
import com.musicstreaming.repositories.SongRepository;
import com.musicstreaming.services.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistImplService implements PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final SongRepository songRepository;
    private final Storage storage;
    private final String bucketName;

    @Autowired
    public PlaylistImplService(PlaylistRepository playlistRepository,SongRepository songRepository, @Value("${gcp.credentials.file-path}") String credentialsPath,
                            @Value("${gcp.bucket.name}") String bucketName) {
        this.songRepository = songRepository;
        this.playlistRepository=playlistRepository;
        this.bucketName = bucketName;
        this.storage = InitializeStorage.initializeStorage(credentialsPath);
    }


    @Override
    public Playlist changePlaylistStatus(Long playlistId, int status) {
        return null;
    }

    @Override
    public Playlist createNewPlaylist(Playlist playlist) {
        return null;
    }

    @Override
    public Playlist addSongToPlaylist(List<Song> songs, Long playlistId) {
        return null;
    }

    @Override
    public Playlist removeSongFromPlaylist(List<Song> songs, Long playlistId) {
        return null;
    }

    @Override
    public Playlist sharePlaylist(Long playlistId) {
        return null;
    }

    @Override
    public Playlist deletePlaylist(Long playlistId) {
        return null;
    }

    @Override
    public List<Playlist> getAllPlaylistsByUser(Long userId) {
        return null;
    }

    @Override
    public Playlist getPlaylistWithSongs(Long playlistId) {
        return null;
    }
}
