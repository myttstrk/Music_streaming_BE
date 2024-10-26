package com.musicstreaming.controllers;


import com.musicstreaming.models.Album;
import com.musicstreaming.repositories.AlbumRepository;
import com.musicstreaming.services.AlbumService;
import com.musicstreaming.utils.MessageKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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


}
