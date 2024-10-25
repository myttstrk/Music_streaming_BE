package com.musicstreaming.services.impl;

import com.musicstreaming.models.Category;
import com.musicstreaming.repositories.CategoryRepository;
import com.musicstreaming.services.CategoryService;
import com.musicstreaming.utils.MessageKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryImplService implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(String categoryName) throws Exception {
        Optional<Category>category =categoryRepository.findByName(categoryName);
        if(!category.isEmpty()){
            throw new Exception(MessageKeys.ERROR_MESSAGE);
        }
        Category category1=new Category();
        category1.setName(categoryName);
        return categoryRepository.save(category1);
    }

    @Override
    public Optional<List<Category>> findAllCategory() throws Exception {
        try {
                Optional<List<Category>> listCategory= Optional.of(categoryRepository.findAll());

                return Optional.of(categoryRepository.findAll());

        } catch (Exception e) {

            throw new Exception(e.getMessage());
        }
    }
}
