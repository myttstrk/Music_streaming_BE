package com.musicstreaming.responses.user;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.musicstreaming.models.Category;
import com.musicstreaming.models.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    @JsonProperty("category_id")
    private Long id;


    @JsonProperty("category_name")
    private String name;
    public static CategoryResponse fromCategory(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
