package com.musicstreaming.responses.user;

import com.musicstreaming.models.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SongResponse {

    private Long songId;
    private String title;
    private String audioUrl;
    private String artistName;
    private List<String> categories;
}
