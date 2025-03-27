package com.jbe01.r2sshop.service;

import com.jbe01.r2sshop.entity.Products;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ProductsService {
    List<Products> findAll(PageRequest pageRequest);

    Products findById(long id);

    void delete(long id);

    void update(Products products, long id);

    List<Products> findProductsByCategoriesId(long id, PageRequest pageRequest);

    int countProductsByCategoriesId(long id);

    void save(Products products);

    int count();
}
