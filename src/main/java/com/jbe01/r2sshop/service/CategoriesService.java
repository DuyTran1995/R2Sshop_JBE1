package com.jbe01.r2sshop.service;

import com.jbe01.r2sshop.entity.Categories;

import java.util.List;

public interface CategoriesService {
    public List<Categories> findAll();

    public Categories findById(long id);

    public Categories findByName(String name);

    public Categories save(Categories categories);

    public void delete(Categories categories);

    public void updateCategory(Categories categories);

    public boolean assignProductToCategory(Long productId, Long categoryId);
}
