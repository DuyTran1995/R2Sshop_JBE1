package com.jbe01.r2sshop.service;

import com.jbe01.r2sshop.entity.Categories;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CategoriesService {
    public List<Categories> findAll(PageRequest pageRequest);

    public Categories findById(long id);

    public Categories save(Categories categories);

    public void delete(long id);

    public void updateCategory(Categories categories, long id);

    public void assignProductToCategory(Long productId, Long categoryId);

    public int countCategories();
}
