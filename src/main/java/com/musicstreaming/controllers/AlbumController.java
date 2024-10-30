package com.musicstreaming.controllers;


import com.musicstreaming.models.Album;
import com.musicstreaming.models.Song;
import com.musicstreaming.repositories.AlbumRepository;
import com.musicstreaming.services.AlbumService;
import com.musicstreaming.utils.MessageKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/albums")

public class AlbumController {
    private final AlbumRepository albumRepository;
    private final AlbumService albumService;

    @PostMapping(value = "/createNewAlbum",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @Transactional
    public ResponseEntity<?>createNewAlbum(@RequestParam(value = "coverImage",required = false) MultipartFile coverImage,
                                           @RequestParam(value = "artistId",required = false) Integer artistId,
                                           @RequestParam(value = "albumName",required = false) String albumName)
                                            {
                try{
                    if(albumName== null){
                        return ResponseEntity.badRequest().body(MessageKeys.NAME_FIELD_EMPTY);
                    }
                    Album newAlbum= albumService.createNewAlbum(albumName,artistId,coverImage);
                    return ResponseEntity.ok().body(newAlbum);

                }catch (Exception e){
                    return ResponseEntity.badRequest().body(e.getMessage());
                }
    }
@PostMapping(value = "/addSongsToAlbum",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?>addSongToAlbum(@RequestParam("songs")MultipartFile[] songs,Long albumId){
            try{
                albumService.addSongToAlbum(songs,albumId);
                return ResponseEntity.ok().body(MessageKeys.ALBUM_ADDED_SONGS_SUCCESS);
            }catch (Exception e){
                return ResponseEntity.badRequest().body(e.getMessage());
            }
}
@DeleteMapping(value = "/removeSongsfromAlbum")
        public ResponseEntity<?>removeSongFromAlbum(@RequestParam("songs")List<Song>songs,Long albumId){
        try{
            if(songs.size()==0){
                return ResponseEntity.internalServerError().body(MessageKeys.ALBUM_SONG_EMPTY);
            }
            albumService.removeSongFromAlbum(songs,albumId);
                return ResponseEntity.ok().body(MessageKeys.DELETE_SONG_SUCCESS);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(MessageKeys.ERROR_MESSAGE);
        }
}
@DeleteMapping(value = "/deleteAlbum")
    public ResponseEntity<?>deleteAlbum (@RequestParam("albumId") Long albumId){
        try{
            Optional<Album> album =albumRepository.findById(albumId);
            if(!album.isEmpty()){
                Album getAlbum=album.get();
                albumService.deleteAlbum(albumId);
                return ResponseEntity.ok().body(MessageKeys.ALBUM_CREATED_SUCCESS);
            }
            return ResponseEntity.ofNullable("Not found with this id");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
}


}
