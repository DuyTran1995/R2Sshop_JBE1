package com.jbe01.r2sshop.service.Impl;

import com.jbe01.r2sshop.entity.Categories;
import com.jbe01.r2sshop.entity.Products;
import com.jbe01.r2sshop.handler.error.NotFoundException;
import com.jbe01.r2sshop.repository.CategoriesRepository;
import com.jbe01.r2sshop.service.CategoriesService;
import com.jbe01.r2sshop.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private ProductsService productsService;

    public List<Categories> findAll() {
        return categoriesRepository.findAll();
    }

    public Categories findById(long id) {
        return categoriesRepository.findById(id).orElseThrow(() -> new NotFoundException("Categories not found: " + id));
    }

    public Categories findByName(String name) {
        return null;
    }

    public Categories save(Categories categories) {
        return categoriesRepository.save(categories);
    }

    public void delete(Categories categories) {
        var foundCategory = this.findById(categories.getId());

        categoriesRepository.delete(foundCategory);
    }

    public void updateCategory(Categories categories) {
        var foundCategory = this.findById(categories.getId());

        foundCategory.setName(categories.getName());
        foundCategory.setDescription(categories.getDescription());

        categoriesRepository.save(foundCategory);

    }

    public boolean assignProductToCategory(Long productId, Long categoryId) {
        Products product = productsService.findById(productId);

        Categories category = this.findById(categoryId);

        category.getProducts().add(product);

        categoriesRepository.save(category);

        return true;
    }
}
