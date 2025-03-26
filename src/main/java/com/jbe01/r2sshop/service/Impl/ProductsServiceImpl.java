package com.jbe01.r2sshop.service.Impl;

import com.jbe01.r2sshop.entity.Products;
import com.jbe01.r2sshop.handler.error.NotFoundException;
import com.jbe01.r2sshop.repository.ProductsRepository;
import com.jbe01.r2sshop.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    ProductsRepository productsRepository;

    public List<Products> findAll() {
        return productsRepository.findAll();
    }

    @Override
    public Products findById(long id) {
        return productsRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found" + id));
    }

    public List<Products> findProductsByCategoriesId(long id) {
        return productsRepository.findProductsByCategoriesId(id);
    }


}
