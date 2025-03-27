package com.jbe01.r2sshop.controller;

import com.jbe01.r2sshop.dto.requests.CategoryRequestDto;
import com.jbe01.r2sshop.dto.responses.CategoriesResponseDto;
import com.jbe01.r2sshop.dto.responses.ProductsResponseDto;
import com.jbe01.r2sshop.handler.SuccessResponse;
import com.jbe01.r2sshop.mapper.CategoriesMapper;
import com.jbe01.r2sshop.mapper.ProductsMapper;
import com.jbe01.r2sshop.model.Metadata;
import com.jbe01.r2sshop.service.CategoriesService;
import com.jbe01.r2sshop.service.ProductsService;
import com.jbe01.r2sshop.util.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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

    @GetMapping("")
    public ResponseEntity<SuccessResponse<List<CategoriesResponseDto>>> getAllCategories(
            @RequestParam(defaultValue = "0", required = false, name = "page") int page,
            @RequestParam(defaultValue = "10", required = false, name = "size") int size,
            @RequestParam(name = "sorts", required = false) String sorts
    ) {

        PageRequest pageRequest = PaginationUtil.pageRequest(page, size, sorts);

        Metadata metadata = new Metadata();

        metadata.setPageNumber(page);
        metadata.setPageSize(size);
        metadata.setTotalCount(categoriesService.countCategories());

        var categories = categoriesService.findAll(pageRequest);

        return SuccessResponse.of(categoriesMapper.listCategoriesToListDto(categories), metadata).toResponseEntity();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<CategoriesResponseDto>> getCategoriesById(@PathVariable long id) {
        var categories = categoriesService.findById(id);

        return SuccessResponse.of(categoriesMapper.categoriesToDto(categories)).toResponseEntity();
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<SuccessResponse<List<ProductsResponseDto>>> getProductsByCategoriesId(
            @PathVariable long categoryId,
            @RequestParam(defaultValue = "0", required = false, name = "page") int page,
            @RequestParam(defaultValue = "10", required = false, name = "size") int size,
            @RequestParam(name = "sorts", required = false) String sorts

    ) {
        PageRequest pageRequest = PaginationUtil.pageRequest(page, size, sorts);

        Metadata metadata = new Metadata();

        metadata.setPageNumber(page);
        metadata.setPageSize(size);
        metadata.setTotalCount(productsService.countProductsByCategoriesId(categoryId));

        var products = productsService.findProductsByCategoriesId(categoryId, pageRequest);

        return SuccessResponse.of(productsMapper.toListDto(products), metadata).toResponseEntity();
    }

    @PostMapping("")
    public ResponseEntity<SuccessResponse<CategoriesResponseDto>> create(@RequestBody CategoryRequestDto categories) {
        var category = categoriesService.save(categoriesMapper.categoryRequestDtoToCategories(categories));

        return SuccessResponse.of(categoriesMapper.categoriesToDto(category)).toResponseEntity();
    }

    @PutMapping("")
    public void update(
            @RequestBody CategoryRequestDto categories,
            @RequestParam(name = "categoryId") long id
    ) {
        categoriesService.updateCategory(categoriesMapper.categoryRequestDtoToCategories(categories), id);
    }

    @DeleteMapping("")
    public void delete(@RequestParam(name = "categoryId") long id) {
        categoriesService.delete(id);
    }

    @PostMapping("/assignProduct")
    public void assignProductToCategory(
            @RequestParam(name = "productId") long productId,
            @RequestParam(name = "categoryId") long categoryId) {

        categoriesService.assignProductToCategory(productId, categoryId);
    }
}
