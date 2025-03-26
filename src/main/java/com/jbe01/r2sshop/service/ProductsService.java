package com.jbe01.r2sshop.service;

import com.jbe01.r2sshop.entity.Products;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ProductsService {
    List<Products> findAll();
    Products findById(long id);
    List<Products> findProductsByCategoriesId(long id);
}
