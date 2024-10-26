package com.musicstreaming.services;

import com.google.cloud.storage.Blob;
import com.musicstreaming.models.Album;
import com.musicstreaming.models.Song;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AlbumService {

    public Album createNewAlbum(String name, int artistID, MultipartFile coverImage) throws Exception;
    public void addSongToAlbum(List<Song>songList,Long albumId) throws Exception;


    public void removeSongFromAlbum(List<Song>songList,Long albumId);
    public void deleteAlbum();

    public void uploadCoverGCS(MultipartFile coverImage, String gcsFileName) throws IOException;

}
