package com.musicstreaming.services.impl;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.*;
import com.musicstreaming.components.InitializeStorage;
import com.musicstreaming.models.Playlist;
import com.musicstreaming.models.PlaylistSong;
import com.musicstreaming.models.Song;
import com.musicstreaming.repositories.PlaylistRepository;
import com.musicstreaming.repositories.PlaylistSongRepository;
import com.musicstreaming.repositories.SongRepository;
import com.musicstreaming.services.PlaylistService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlaylistImplService implements PlaylistService {
    private final PlaylistRepository playlistRepository;

    private final PlaylistSongRepository playlistSongRepository;
    private final SongRepository songRepository;
    private final Storage storage;
    private final String bucketName;

    @Autowired
    public PlaylistImplService(PlaylistSongRepository playlistSongRepository,PlaylistRepository playlistRepository,SongRepository songRepository, @Value("${gcp.credentials.file-path}") String credentialsPath,
                            @Value("${gcp.bucket.name}") String bucketName) {
        this.playlistSongRepository=playlistSongRepository;
        this.songRepository = songRepository;
        this.playlistRepository=playlistRepository;
        this.bucketName = bucketName;
        this.storage = InitializeStorage.initializeStorage(credentialsPath);
    }


    @Override
    public Playlist changePlaylistStatus(Long playlistId, int status) {
        Optional<Playlist> getOptPlaylist=playlistRepository.findById(playlistId);
        if(!getOptPlaylist.isEmpty()){
            Playlist getPlaylist=getOptPlaylist.get();
            getPlaylist.setStatus(status);
            return getPlaylist;
        }
        return null;
    }

    @Override
    public Playlist createNewPlaylist(Playlist playlist) {
        playlist.builder().user(playlist.getUser())
                .createdAt(LocalDateTime.now())
                .status(playlist.getStatus())
                .name(playlist.getName())
                .user(playlist.getUser())
                .rules(playlist.getRules())
                .folder(playlist.getFolder())
                .build();
        playlistRepository.save(playlist);
        return playlist;
    }

    @Override
    public Playlist addSongToPlaylist(List<Song> songs, Long playlistId) {
        Optional<Playlist>getOptplaylist=playlistRepository.findById(playlistId);
        if(getOptplaylist.isPresent()){
            Playlist playlist=getOptplaylist.get();
           List<PlaylistSong> playlistSongs= songs.stream().map(song ->PlaylistSong.builder()
                   .playlist(playlist)
                   .song(song)
                   .build()).collect(Collectors.toList());
                playlistSongRepository.saveAll(playlistSongs);
                playlist.setCreatedAt(LocalDateTime.now());
                playlistRepository.save(playlist);
                return playlist;
        } else {
            throw new EntityNotFoundException("Playlist not found with ID: " + playlistId);
        }
    }

    @Override
    public Playlist removeSongFromPlaylist(List<Song> songs, Long playlistId) {
        Optional<Playlist>getOpt=playlistRepository.findById(playlistId);
        if(getOpt.isPresent()){
            Playlist playlist=getOpt.get();
            List<PlaylistSong>playlistSongs=songs.stream().map(song
                    ->playlistSongRepository.findByPlaylistAndSong(playlist,song))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());

            playlistSongRepository.deleteAll(playlistSongs);

            playlist.setUpdatedAt(LocalDateTime.now());
            playlistRepository.save(playlist);
            return playlist;
        }else{
            throw new EntityNotFoundException("Playlist not found with ID: " + playlistId);
        }
    }

    @Override
    public Playlist sharePlaylist(Long playlistId,String token) {
        Optional<Playlist> getOpt = playlistRepository.findById(playlistId);

        if (getOpt.isPresent()) {
            Playlist playlist = getOpt.get();

            if (token.equals(playlist.getShareToken())) {
                return playlist;
            } else {
                throw new SecurityException("Invalid share token");
            }
        } else {
            throw new EntityNotFoundException("Playlist not found with ID: " + playlistId);
        }
    }

    @Override
    public String shareLinkPlaylist(Long playlistId) {
        Optional<Playlist> getOpt = playlistRepository.findById(playlistId);

        if (getOpt.isPresent()) {
            Playlist playlist = getOpt.get();

            // Tạo mã chia sẻ duy nhất nếu playlist chưa có mã
            if (playlist.getShareToken() == null) {
                String shareToken = UUID.randomUUID().toString();
                playlist.setShareToken(shareToken);
                playlist.setStatus(1);
                playlistRepository.save(playlist);
            }

            // Tạo URL chia sẻ với mã token
            String shareUrl = "https://musicstreaming.com/share/" + playlistId + "?token=" + playlist.getShareToken();
            return shareUrl;
        } else {
            throw new EntityNotFoundException("Playlist not found with ID: " + playlistId);
        }
    }

    @Override
    public Playlist deletePlaylist(Long playlistId) throws Exception {
        Optional<Playlist> playlistOptional= playlistRepository.findById(playlistId);
        if(playlistOptional.isPresent()){
            Playlist playlist=playlistOptional.get();
            playlistRepository.delete(playlist);
            return playlist;
        }else{
            throw new Exception("Not found");
        }
    }

    @Override
    public List<Playlist> getAllPlaylistsByUser(Long userId) {
        Optional<List<Playlist>> playlistOptional= playlistRepository.findByUserId(userId);
        if(playlistOptional.isPresent()){
            List<Playlist>playlistList=playlistOptional.get();
            return playlistList;
        }else{
            return null;
        }

    }

    @Override
    public List<PlaylistSong> getPlaylistWithSongs(Long playlistId) {
            Optional<Playlist>playlistOptional=playlistRepository.findById(playlistId);
            if(playlistOptional.isPresent()){
                Playlist playlist=playlistOptional.get();
                List<PlaylistSong> playlistSongs = playlistSongRepository.findByPlaylist(playlist);

                return playlistSongs;
            } else {
                throw new EntityNotFoundException("Playlist not found with ID: " + playlistId);
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
    public void deleteSongfromPlaylist(String gcsFilePath){
        BlobId blobId=BlobId.of(bucketName,gcsFilePath);
        storage.delete(blobId);
    }
    public void addSongToPlaylistGCS(List<Song> songs, String folderPath) throws IOException {
        Bucket bucket = storage.get(bucketName);

        if (bucket == null) {
            throw new IOException("Bucket not found: " + bucketName);
        }

        List<Song> updatedSongs = new ArrayList<>();

        songs.forEach(song -> {
            // Tạo UUID cho tên file
            String uuid = UUID.randomUUID().toString();
            String filename = folderPath + "/" + uuid + ".txt";

            // Tạo BlobId và BlobInfo với UUID
            BlobId blobId = BlobId.of(bucketName, filename);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                    .setContentType("audio/mpeg")
                    .setMetadata(Map.of(
                            "originalTitle", song.getTitle(),
                            "uuid", uuid
                    ))
                    .build();

            // Upload file với UUID

            // Cập nhật đường dẫn UUID trong đối tượng Song
            song.setAudioUrl(filename);
            updatedSongs.add(song);
        });

        // Lưu tất cả songs đã được cập nhật một lần
        if (!updatedSongs.isEmpty()) {
            songRepository.saveAll(updatedSongs);
        }
    }
}
