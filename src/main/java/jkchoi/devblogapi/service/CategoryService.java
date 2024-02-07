package jkchoi.devblogapi.service;


import jkchoi.devblogapi.dto.CategoryDto;
import jkchoi.devblogapi.dto.PostDto;
import jkchoi.devblogapi.entity.Category;
import jkchoi.devblogapi.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryDto.ReadResponse> findCategories() {
        List<Category> findCategories = categoryRepository.findAll();
        List<CategoryDto.ReadResponse> findCategoriesDto = findCategories.stream()
                .map(category -> new CategoryDto.ReadResponse(category))
                .collect(Collectors.toList());

        return findCategoriesDto;
    }

}



