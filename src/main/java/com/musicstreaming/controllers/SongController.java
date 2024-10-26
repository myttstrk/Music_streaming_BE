package com.musicstreaming.controllers;

import com.musicstreaming.models.Artist;
import com.musicstreaming.models.Song;
import com.musicstreaming.repositories.SongRepository;
import com.musicstreaming.responses.user.SongResponse;
import com.musicstreaming.services.GoogleCloudStorageService;
import com.musicstreaming.services.RedisCacheService.SongCacheService;
import com.musicstreaming.services.impl.SongsServiceImpl;
import com.musicstreaming.utils.MessageKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/songs")
public class SongController {
    private final SongCacheService songCacheService;

    private final SongsServiceImpl songsService;
    private final GoogleCloudStorageService googleCloudStorageService;
    private final SongRepository songRepository;
    @GetMapping("/getAllSongs")
    public ResponseEntity<ArrayList<Song>>getAllSongs(){
        return ResponseEntity.ok(googleCloudStorageService.getAllSongs());
    }

    @GetMapping("/getSongDetail")
    public ResponseEntity<?>getSongDetail(@RequestParam("songId")Long songId){
        try{
            SongResponse songResponse=songsService.getSongDetail(songId);
            return ResponseEntity.ok().body(songResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(MessageKeys.ERROR_MESSAGE);
        }
    }


    @DeleteMapping("/deleteSong")
    @Transactional
    public ResponseEntity<?>deleteSong(@RequestParam("songId")int songId){
        try{

            songRepository.deleteSongById(songId);
            return ResponseEntity.ok().body(MessageKeys.DELETE_SONG_SUCCESS);
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(MessageKeys.ERROR_MESSAGE);
        }

    }
    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadSong(@RequestParam("file") MultipartFile file,
                                             @RequestParam("title") String title,
                                             @RequestParam("artistId") Long artistId,
                                             @RequestParam("categoryId") Long categoryId) {
        try {
            // Upload file lên Google Cloud Storage
            String audioUrl = googleCloudStorageService.uploadSongToGCS(file.getInputStream()
                    ,file.getOriginalFilename()
                    ,categoryId.toString());

            // Tạo đối tượng Song mới
            Song newSong = Song.builder()
                    .title(title)
                    .artist(new Artist(artistId)) // Tạo một đối tượng Artist với ID
                    .audioUrl(audioUrl)
                    .createdAt(LocalDateTime.now())
                    .build();

            // Lưu bài hát vào cơ sở dữ liệu
            songRepository.save(newSong);

            return new ResponseEntity<>("Bài hát đã được tải lên thành công!", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Đã xảy ra lỗi khi tải lên bài hát.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/api/cache/popular-songs")
    public String cachePopularSongs(@RequestBody List<Song> songs) {
        songCacheService.cachePopularSongs(songs);
        return "Danh sách bài hát phổ biến đã được cache thành công!";
    }
    @PostMapping("/cache-latest-songs")
    public String cacheLatestSongs() {
        Pageable pageable = PageRequest.of(0, 5); // Lấy 5 bài hát mới nhất
        List<Song> latestSongs = songRepository.findLatestSongs(pageable);
        songCacheService.cacheLatestSongs(latestSongs);
        return "Danh sách bài hát mới nhất đã được cache thành công!";
    }
    @GetMapping("/latest-songs")
    public List<Song> getLatestSongs() {
        return songCacheService.getLatestSongs();
    }

}
