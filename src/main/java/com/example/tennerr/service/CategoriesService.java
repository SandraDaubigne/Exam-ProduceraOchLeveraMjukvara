package com.example.tennerr.service;

import com.example.tennerr.entity.Categories;
import com.example.tennerr.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    public void saveCategories(Categories categories){
        categoriesRepository.save(categories);
    }
}
