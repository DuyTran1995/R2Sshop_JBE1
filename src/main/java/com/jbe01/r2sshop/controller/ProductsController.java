package com.jbe01.r2sshop.controller;

import com.jbe01.r2sshop.dto.requests.ProductRequestDto;
import com.jbe01.r2sshop.dto.responses.ProductsResponseDto;
import com.jbe01.r2sshop.handler.SuccessResponse;
import com.jbe01.r2sshop.mapper.ProductsMapper;
import com.jbe01.r2sshop.model.Metadata;
import com.jbe01.r2sshop.service.ProductsService;
import com.jbe01.r2sshop.util.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @Autowired
    private ProductsMapper productsMapper;

    @GetMapping("")
    public ResponseEntity<SuccessResponse<List<ProductsResponseDto>>> findAll(
            @RequestParam(defaultValue = "0", required = false, name = "page") int page,
            @RequestParam(defaultValue = "10", required = false, name = "size") int size,
            @RequestParam(name = "sorts", required = false) String sorts
    ) {

        PageRequest pageRequest = PaginationUtil.pageRequest(page, size, sorts);

        Metadata metadata = new Metadata();

        metadata.setPageNumber(page);
        metadata.setPageSize(size);
        metadata.setTotalCount(productsService.count());

        var products = productsService.findAll(pageRequest);

        return SuccessResponse.of((productsMapper.toListDto(products)), metadata).toResponseEntity();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<ProductsResponseDto>> findById(@PathVariable long id) {


        var products = productsService.findById(id);

        return SuccessResponse.of((productsMapper.toDto(products))).toResponseEntity();
    }

    @DeleteMapping("")
    public void delete(@RequestParam long id) {
        productsService.delete(id);
    }

    @PutMapping()
    public void update(
            @RequestParam(name = "productId") long id,
            @RequestBody ProductRequestDto productRequestDto
    ) {
        var products = productsMapper.productsRequestDtoToProduct(productRequestDto);

        productsService.update(products, id);
    }
}
