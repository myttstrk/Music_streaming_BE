package com.musicstreaming.services.impl;

import com.google.cloud.storage.*;
import com.musicstreaming.components.InitializeStorage;
import com.musicstreaming.models.Album;
import com.musicstreaming.models.AlbumSong;
import com.musicstreaming.models.Artist;
import com.musicstreaming.models.Song;
import com.musicstreaming.repositories.AlbumRepository;
import com.musicstreaming.repositories.AlbumSongRepository;
import com.musicstreaming.repositories.ArtistRepository;
import com.musicstreaming.services.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlbumImplService implements AlbumService {

    private final AlbumRepository albumRepository;
    private final AlbumSongRepository albumSongRepository;
    private final ArtistRepository artistRepository;
    private final Storage storage;
    private final String bucketName;
    @Autowired
    public AlbumImplService(AlbumRepository albumRepository,AlbumSongRepository albumSongRepository,ArtistRepository artistRepository, @Value("${gcp.credentials.file-path}") String credentialsPath,
                            @Value("${gcp.bucket.name}") String bucketName) {
        this.albumRepository= albumRepository;
        this.artistRepository=artistRepository;
        this.albumSongRepository=albumSongRepository;
        this.bucketName = bucketName;
        this.storage = InitializeStorage.initializeStorage(credentialsPath);
    }
    @Override
    public Album createNewAlbum(String name, int artistID, MultipartFile coverImage) throws Exception {
        try{
            Artist artist = artistRepository.findById((long) artistID)
                    .orElseThrow(() -> new Exception("Artist not found with ID: " + artistID));

            Album newAlbum=new Album();
            newAlbum.setName(name);
            newAlbum.setArtist(artist);
            newAlbum.setCreatedAt(LocalDateTime.now());
            newAlbum = albumRepository.save(newAlbum);
            //upload coverImage to GCS
            String gcsFileName = "Cover/Album/"+artist.getId()+"/" + newAlbum.getId() + ".jpg";
            uploadCoverGCS(coverImage, gcsFileName);
            //create new album folder
            String folder="Album/Artist/"+artist.getId();
            createEmptyFolder(folder);
            String coverImageUrl = String.format("https://storage.googleapis.com/%s/%s", bucketName, gcsFileName);
            newAlbum.setCover(coverImageUrl);
            return albumRepository.save(newAlbum);
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }



    @Override
    public void addSongToAlbum(List<Song> songList, Long albumId) throws Exception {
        Optional<Album> albumOptional=albumRepository.findById(albumId);
        if(! albumOptional.isEmpty()){
            Album album1=albumOptional.get();
            List<AlbumSong> albumSongs=songList.stream()
                    .map(song->AlbumSong.builder().album(album1).song(song).build()).collect(Collectors.toList());
            albumSongRepository.saveAll(albumSongs);
        }else{
            throw new Exception("Album not found");
        }
    }

    @Override
    public void removeSongFromAlbum(List<Song> songList,Long albumId) {

    }

    @Override
    public void deleteAlbum() {

    }
    public void createEmptyFolder(String folderPath) throws IOException {

        String placeholderFilePath = folderPath + "/placeholder.txt";

        BlobId blobId = BlobId.of(bucketName, placeholderFilePath);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("text/plain").build();

        storage.create(blobInfo, new byte[0]);
      //  deleteFileInFolder(placeholderFilePath);
    }
    public void deleteFileInFolder(String filePath) {
        BlobId blobId = BlobId.of(bucketName, filePath);
        boolean deleted = storage.delete(blobId);
        if (deleted) {
            System.out.println("File deleted successfully: " + filePath);
        } else {
            System.out.println("File not found or could not be deleted: " + filePath);
        }
    }

    public String uploadCoverGCSURL(MultipartFile coverImage, String gcsFileName) throws IOException {
        Bucket bucket = storage.get(bucketName);
        // Create BlobInfo with metadata
        BlobId blobId = BlobId.of(bucketName, gcsFileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg").build();
        storage.create(blobInfo, coverImage.getBytes());

        String httpUrl = "https://storage.googleapis.com/" + bucketName + "/" + coverImage.getName().replace(" ", "%20");

        return  httpUrl;
    }
    public void uploadCoverGCS(MultipartFile coverImage, String gcsFileName) throws IOException {
        Bucket bucket = storage.get(bucketName);
        if (bucket == null) {
            throw new IOException("Bucket not found: " + bucketName);
        }
        BlobId blobId = BlobId.of(bucketName, gcsFileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg").build();

        storage.create(blobInfo, coverImage.getBytes());

    }
}
