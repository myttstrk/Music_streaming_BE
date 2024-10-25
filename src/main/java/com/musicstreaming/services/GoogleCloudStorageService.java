package com.musicstreaming.services;

import com.musicstreaming.models.Song;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public interface GoogleCloudStorageService {
        public ArrayList<Song> getAllSongs();
        public String uploadSongToGCS(InputStream inputStream, String songTitle,String category);

}
