package com.musicstreaming.services.impl;

import com.google.api.gax.paging.Page;
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
import java.util.Arrays;
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
    public List<Album> getListAlbumFromArtist(Long artistId) {
        return null;
    }

    @Override
    public List<Album> getAllAlbums() {
        return null;
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
    public void addSongToAlbum(MultipartFile[] songFiles, Long albumId) throws Exception {
        Optional<Album> albumOptional = albumRepository.findById(albumId);

        if (albumOptional.isPresent()) {
            Album album = albumOptional.get();

            // Tạo danh sách AlbumSong từ các tệp trong songFiles
            List<AlbumSong> albumSongs = Arrays.stream(songFiles)
                    .map(songFile -> AlbumSong.builder().album(album).build())
                    .collect(Collectors.toList());

            // Đường dẫn thư mục trên GCS
            String folder = "Album/Artist/" + albumId;

            // Upload các tệp bài hát
            uploadSongToAlbumGCS(songFiles, folder);

            // Lưu danh sách albumSongs vào database
            albumSongRepository.saveAll(albumSongs);
        } else {
            throw new Exception("Album not found");
        }
    }


    @Override
    public void removeSongFromAlbum(List<Song> songList,Long albumId) throws Exception {
        Optional<Album> albumOptional = albumRepository.findById(albumId);
        if (albumOptional.isEmpty()) {
            throw new Exception("Album not found");
        }

        Album album = albumOptional.get();
        for(Song song: songList){
            AlbumSong albumSong=albumSongRepository.findByAlbumAndSong(album,song);
            if(albumSong!=null){
                String songPath = "Album/Artist/" + albumId + "/" + song.getId() + ".mp3";
                //remove song from Album at GCS
                deleteSongfromAlbum(songPath);

                //remove song from database
                albumSongRepository.delete(albumSong);
            }
        }
    }

    @Override
    public void deleteAlbum(Long albumId) {
        Optional<Album> album= albumRepository.findById(albumId);
        if(!album.isEmpty()){
            Album getAlbum=album.get();
            albumRepository.delete(getAlbum);
            List<AlbumSong> albumSongs = albumSongRepository.findAllByAlbum(album);
            albumSongs.stream().forEach(song->{
                Song song1=song.getSong();
                String songPath = "Album/Artist/" +getAlbum.getId() + "/" + song1.getId() + ".mp3";

                deleteFileFromGCS(songPath);
            });

            ///remove album folder from gcs
            String albumFolderPath = "Album/Artist/" + getAlbum.getId();
            deleteFolderFromGCS(albumFolderPath);

            //remove record
            albumSongRepository.deleteAll(albumSongs);
            albumRepository.delete(getAlbum);

        }

    }

    private void deleteFileFromGCS(String gcsFilePath) {
        BlobId blobId = BlobId.of(bucketName, gcsFilePath);
        storage.delete(blobId);

    }

    private void deleteFolderFromGCS(String folderPath) {
        Page<Blob> blobs = storage.list(bucketName, Storage.BlobListOption.prefix(folderPath + "/"));
        for (Blob blob : blobs.iterateAll()) {
            storage.delete(blob.getBlobId());
        }
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
    public void deleteSongfromAlbum(String gcsFilePath){
        BlobId blobId=BlobId.of(bucketName,gcsFilePath);
        storage.delete(blobId);
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
    public void uploadSongToAlbumGCS(MultipartFile[] songs,  String folderPath) throws IOException {
        Bucket bucket = storage.get(bucketName);

        if (bucket == null) {
            throw new IOException("Bucket not found: " + bucketName);
        }
            Arrays.asList(songs).stream().forEach(song ->{
            String filename = folderPath + "/" + song.getOriginalFilename();

            // Create BlobId and BlobInfo for each file
            BlobId blobId = BlobId.of(bucketName, filename);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("audio/mpeg").build();
                try {
                    storage.create(blobInfo, song.getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });


    }
}
