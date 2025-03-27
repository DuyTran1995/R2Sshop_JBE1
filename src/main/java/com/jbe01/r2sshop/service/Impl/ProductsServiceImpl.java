package com.jbe01.r2sshop.service.Impl;

import com.jbe01.r2sshop.entity.Products;
import com.jbe01.r2sshop.handler.error.NotFoundException;
import com.jbe01.r2sshop.repository.ProductsRepository;
import com.jbe01.r2sshop.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    ProductsRepository productsRepository;

    public List<Products> findAll(PageRequest pageRequest) {
        return productsRepository.findAll(pageRequest).stream().toList();
    }

    public Products findById(long id) {
        return productsRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found" + id));
    }

    public void delete(long id) {
        this.findById(id);
    }

    public void update(Products products, long id) {
        var foundProduct = this.findById(id);

        Products newProduct = Products.builder()
                .price(foundProduct.getPrice())
                .name(foundProduct.getName())
                .description(foundProduct.getDescription())
                .stockQuantity(foundProduct.getStockQuantity())
                .build();

        productsRepository.save(newProduct);
    }

    public List<Products> findProductsByCategoriesId(long id, PageRequest pageRequest) {
        return productsRepository.findProductsByCategoriesId(id, pageRequest);
    }

    public int countProductsByCategoriesId(long id) {
        return productsRepository.countProductsByCategoriesId(id);
    }

    public void save(Products products) {
        productsRepository.save(products);
    }

    public int count() {
        return (int) productsRepository.count();
    }
}
