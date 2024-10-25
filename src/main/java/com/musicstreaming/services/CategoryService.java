package com.musicstreaming.services;

import com.musicstreaming.models.Category;
import com.musicstreaming.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public Category createCategory(String categoryName) throws Exception;


    Optional<List<Category>> findAllCategory() throws Exception;
}
