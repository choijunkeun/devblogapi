package jkchoi.devblogapi.controller;


import jkchoi.devblogapi.dto.CategoryDto;
import jkchoi.devblogapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto.ReadResponse>> getCategories() {

        List<CategoryDto.ReadResponse> categories = categoryService.findCategories();

        return ResponseEntity.ok(categories);
    }
}
