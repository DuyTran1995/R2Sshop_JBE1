package com.jbe01.r2sshop.service.Impl;

import com.jbe01.r2sshop.entity.Categories;
import com.jbe01.r2sshop.entity.Products;
import com.jbe01.r2sshop.handler.error.NotFoundException;
import com.jbe01.r2sshop.repository.CategoriesRepository;
import com.jbe01.r2sshop.service.CategoriesService;
import com.jbe01.r2sshop.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private ProductsService productsService;

    public List<Categories> findAll(PageRequest pageRequest) {
        return categoriesRepository.findAll(pageRequest).stream().toList();
    }

    public Categories findById(long id) {
        return categoriesRepository.findById(id).orElseThrow(() -> new NotFoundException("Categories not found: " + id));
    }

    public Categories save(Categories categories) {
        return categoriesRepository.save(categories);
    }

    public void delete(long id) {
        var foundCategory = this.findById(id);

        categoriesRepository.delete(foundCategory);
    }

    public void updateCategory(Categories categories, long id) {
        var foundCategory = this.findById(id);

        foundCategory.setName(categories.getName());
        foundCategory.setDescription(categories.getDescription());

        categoriesRepository.save(foundCategory);

    }

    public void assignProductToCategory(Long productId, Long categoryId) {
        Products product = productsService.findById(productId);

        Categories category = this.findById(categoryId);

        product.setCategories(category);

        productsService.save(product);
    }

    public int countCategories() {
        return (int) categoriesRepository.count();
    }
}
