package com.musicstreaming.controllers;


import com.musicstreaming.models.Category;
import com.musicstreaming.repositories.CategoryRepository;
import com.musicstreaming.responses.ApiResponse;
import com.musicstreaming.responses.user.CategoryResponse;
import com.musicstreaming.services.CategoryService;
import com.musicstreaming.services.impl.CategoryImplService;
import com.musicstreaming.utils.MessageKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepository categoryRepository;

    private final CategoryImplService categoryService;


    @GetMapping("/getAllCategories")
    public ResponseEntity<?>getAllCategories(){
    try{
        Optional<List<Category>> allCategories = categoryService.findAllCategory();
        if (allCategories.isPresent()) {
            List<CategoryResponse> categoryResponses = allCategories.get().stream()
                    .map(CategoryResponse::fromCategory)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(categoryResponses);
        } else {
            return ResponseEntity.ok().body(Collections.emptyList());
        }

    }catch (Exception e){
        return ResponseEntity.badRequest().body(MessageKeys.ERROR_MESSAGE);
    }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/createCategory")
    public ResponseEntity<?>createCategory(@RequestParam String categoryName){
        try{
        if(!categoryRepository.findByName(categoryName).isEmpty()){
            return ResponseEntity.badRequest().body(MessageKeys.CATEGORY_EXIST);
        }
        Category newCategory=categoryService.createCategory(categoryName);
        return ResponseEntity.ok(MessageKeys.CATEGORY_CREATED_SUCCESS);


        }catch (Exception e)
        {
    return ResponseEntity.internalServerError().body(MessageKeys.ERROR_MESSAGE);
        }
    }
}
