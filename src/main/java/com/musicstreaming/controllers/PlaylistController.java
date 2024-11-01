package com.musicstreaming.controllers;


import com.musicstreaming.models.Playlist;
import com.musicstreaming.models.PlaylistSong;
import com.musicstreaming.models.Song;
import com.musicstreaming.repositories.PlaylistRepository;
import com.musicstreaming.services.PlaylistService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/playlists")

public class PlaylistController {
        private final PlaylistRepository playlistRepository;
        private final PlaylistService playlistService;


        @PutMapping("/{playlistId}/status")
        public ResponseEntity <Playlist> changePlaylistStatus(
                @PathVariable Long playlistId,
                @RequestParam int status) {
                try {
                        Playlist updatedPlaylist = playlistService.changePlaylistStatus(playlistId, status);
                        return ResponseEntity.ok(updatedPlaylist);
                } catch (EntityNotFoundException e) {
                        return ResponseEntity.notFound().build();
                }
        }
        @PostMapping
        public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist) {
                try {
                        Playlist newPlaylist = playlistService.createNewPlaylist(playlist);
                        return ResponseEntity.status(HttpStatus.CREATED).body(newPlaylist);
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
        }
        @PostMapping("/{playlistId}/songs")
        public ResponseEntity<Playlist> addSongsToPlaylist(
                @PathVariable Long playlistId,
                @RequestBody List<Song> songs) {
                try {
                        Playlist updatedPlaylist = playlistService.addSongToPlaylist(songs, playlistId);
                        return ResponseEntity.status(HttpStatus.OK).body(updatedPlaylist);
                } catch (EntityNotFoundException e) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
                }
        }
        @DeleteMapping("/{playlistId}/songs")
        public ResponseEntity<Playlist> removeSongsFromPlaylist(
                @PathVariable Long playlistId,
                @RequestBody List<Song> songs) {
                try {
                        Playlist updatedPlaylist = playlistService.removeSongFromPlaylist(songs, playlistId);
                        return ResponseEntity.status(HttpStatus.OK).body(updatedPlaylist);
                } catch (EntityNotFoundException e) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
                }
        }
        @PostMapping("/{playlistId}/share")
        public ResponseEntity<Playlist> sharePlaylist(
                @PathVariable Long playlistId,
                @RequestParam String token) {
                try {
                        Playlist playlist = playlistService.sharePlaylist(playlistId, token);
                        return ResponseEntity.status(HttpStatus.OK).body(playlist);
                } catch (EntityNotFoundException e) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                } catch (SecurityException e) {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
                }
        }

        @GetMapping("/{playlistId}/share-link")
        public ResponseEntity<String> shareLinkPlaylist(@PathVariable Long playlistId) {
                try {
                        String shareUrl = playlistService.shareLinkPlaylist(playlistId);
                        return ResponseEntity.status(HttpStatus.OK).body(shareUrl);
                } catch (EntityNotFoundException e) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                }
        }

        @DeleteMapping("/{playlistId}")
        public ResponseEntity<Playlist> deletePlaylist(@PathVariable Long playlistId) {
                try {
                        Playlist deletedPlaylist = playlistService.deletePlaylist(playlistId);
                        return ResponseEntity.status(HttpStatus.OK).body(deletedPlaylist);
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                }
        }
        @GetMapping("/user/{userId}")
        public ResponseEntity<List<Playlist>> getAllPlaylistsByUser(@PathVariable Long userId) {
                List<Playlist> playlists = playlistService.getAllPlaylistsByUser(userId);
                if (playlists != null) {
                        return ResponseEntity.status(HttpStatus.OK).body(playlists);
                } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                }
        }
        @GetMapping("/{playlistId}/songs")
        public ResponseEntity<List<PlaylistSong>> getPlaylistWithSongs(@PathVariable Long playlistId) {
                try {
                        List<PlaylistSong> playlistSongs = playlistService.getPlaylistWithSongs(playlistId);
                        return ResponseEntity.status(HttpStatus.OK).body(playlistSongs);
                } catch (EntityNotFoundException e) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                }
        }

}
