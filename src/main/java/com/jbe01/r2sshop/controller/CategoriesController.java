package com.jbe01.r2sshop.controller;

import com.jbe01.r2sshop.dto.requests.CategoryRequestDto;
import com.jbe01.r2sshop.dto.responses.CategoriesResponseDto;
import com.jbe01.r2sshop.dto.responses.ProductsResponseDto;
import com.jbe01.r2sshop.handler.SuccessResponse;
import com.jbe01.r2sshop.mapper.CategoriesMapper;
import com.jbe01.r2sshop.mapper.ProductsMapper;
import com.jbe01.r2sshop.service.CategoriesService;
import com.jbe01.r2sshop.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private CategoriesMapper categoriesMapper;

    @Autowired
    private ProductsService productsService;

    @Autowired
    private ProductsMapper productsMapper;

    @Secured({ "ROLE_ADMIN" })
    @GetMapping("")
    public ResponseEntity<SuccessResponse<List<CategoriesResponseDto>>> getAllCategories() {
        var categories = categoriesService.findAll();

        return SuccessResponse.of(categoriesMapper.listCategoriesToListDto(categories)).toResponseEntity();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<CategoriesResponseDto>> getCategoriesById(@PathVariable long id) {
        var categories = categoriesService.findById(id);

        return SuccessResponse.of(categoriesMapper.categoriesToDto(categories)).toResponseEntity();
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<SuccessResponse<List<ProductsResponseDto>>> getProductsByCategoriesId(@PathVariable long categoryId) {
        var products = productsService.findProductsByCategoriesId(categoryId);

        return SuccessResponse.of(productsMapper.toListDto(products)).toResponseEntity();
    }

    @PostMapping("")
    public ResponseEntity<SuccessResponse<CategoriesResponseDto>> create(@RequestBody CategoryRequestDto categories) {
        var category = categoriesService.save(categoriesMapper.categoryRequestDtoToCategories(categories));

        return SuccessResponse.of(categoriesMapper.categoriesToDto(category)).toResponseEntity();
    }

    @PutMapping("")
    public void update(@RequestBody CategoryRequestDto categories) {
        categoriesService.updateCategory(categoriesMapper.categoryRequestDtoToCategories(categories));
    }

    @DeleteMapping("")
    public void delete(@RequestBody CategoryRequestDto categories) {
        categoriesService.delete(categoriesMapper.categoryRequestDtoToCategories(categories));
    }

    @PostMapping("/{productId}/{categoryId}")
    public void assignProductToCategory(@PathVariable long productId, @PathVariable long categoryId) {
        categoriesService.assignProductToCategory(productId, categoryId);
    }
}
