package com.musicstreaming.services.impl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.common.collect.Lists;
import com.musicstreaming.components.InitializeStorage;
import com.musicstreaming.models.Song;
import com.musicstreaming.repositories.SongRepository;
import com.musicstreaming.responses.user.SongResponse;
import com.musicstreaming.services.GoogleCloudStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service

public class SongsServiceImpl implements GoogleCloudStorageService {

    private final SongRepository songRepository;
    private final Storage storage;
    private final String bucketName;

    @Autowired
    public SongsServiceImpl(SongRepository songRepository, @Value("${gcp.credentials.file-path}") String credentialsPath,
                            @Value("${gcp.bucket.name}") String bucketName) {
        this.songRepository = songRepository;
        this.bucketName = bucketName;
        this.storage = InitializeStorage.initializeStorage(credentialsPath);
    }

    private Storage initializeStorage(String credentialsPath) {
        try {
            GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(credentialsPath))
                    .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
            return StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        } catch (IOException e) {
            throw new RuntimeException("Unable to initialize Google Cloud Storage", e);
        }
    }

    @Override
    public ArrayList<Song> getAllSongs() {
        ArrayList<Song> songList = new ArrayList<>();

        // Access the bucket in Google Cloud Storage
        Bucket bucket = storage.get(bucketName);
        if (bucket == null) {
            System.out.println("Bucket không tồn tại!");
            return songList;
        }

        // Get all objects in the bucket
        for (Blob blob : bucket.list().iterateAll()) {
            if (blob.getName().endsWith(".mp3") || blob.getName().endsWith(".wav")) {
                // Download the file content as a byte array
                byte[] fileContent = blob.getContent();

                // Create a new Song object with the file content and other details
                Song song = new Song();
                song.setTitle(blob.getName());
              //  song.setAudioData(fileContent);
                song.setAudioUrl("gs://" + bucketName + "/" + blob.getName());
                songList.add(song);
            }
        }

        return songList;
    }
    public SongResponse getSongDetail(Long songID){
        Song song =songRepository.findById(songID).orElse(null);
        List<String> categoryNames=song.getCategories().stream().map(category ->category.getName()).toList();
        return SongResponse.builder().songId(song.getId())
                .title(song.getTitle())
                .audioUrl(song.getAudioUrl())
                .artistName(song.getArtist().getName())
                .categories(categoryNames)
                .build();
    }
    public String uploadSongToGCS(InputStream inputStream, String songTitle,String category) {
        Bucket bucket = storage.get(bucketName);
        // Create metadata for the song
        Map<String, String> metadata = new HashMap<>();
        metadata.put("category", category);

        // Create BlobInfo with metadata
        BlobId blobId = BlobId.of(bucketName, songTitle);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType("audio/mpeg")
                .setMetadata(metadata)
                .build();

        // Upload the file with metadata
        Blob blob = storage.create(blobInfo, inputStream);
        // Trả về URL của bài hát trên GCS
        String httpUrl = "https://storage.googleapis.com/" + bucketName + "/" + blob.getName().replace(" ", "%20");

        return  httpUrl;
    }
    public Song getSongById(Long songId) {
        Optional<Song> song = songRepository.findById(songId);
        if (song.isPresent()) {
            System.out.println("Tên bài hát: " + song.get().getTitle());
            System.out.println("Đường dẫn đến tệp nhạc trên GCS: " + song.get().getAudioUrl());
        }
        return song.orElse(null);
    }
    public List<Song> getTop100Songs(){
        Optional<List<Song>>listTop100= Optional.ofNullable(songRepository.findTop100SongsByPlayCount());

        return listTop100.orElse(null);
    }


}
