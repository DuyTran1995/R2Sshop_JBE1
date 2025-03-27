package com.jbe01.r2sshop.repository;

import com.jbe01.r2sshop.entity.Products;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {
    List<Products> findProductsByCategoriesId(long id, PageRequest pageRequest);

    int countProductsByCategoriesId(long id);
}
