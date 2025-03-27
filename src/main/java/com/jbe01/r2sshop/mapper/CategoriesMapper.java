package com.jbe01.r2sshop.mapper;

import com.jbe01.r2sshop.dto.requests.CategoryRequestDto;
import com.jbe01.r2sshop.dto.responses.CategoriesResponseDto;
import com.jbe01.r2sshop.entity.Categories;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductsMapper.class})
public interface CategoriesMapper {

    @Mapping(target = "products", ignore = true)
    CategoriesResponseDto categoriesToDto(Categories categories);

    @Mapping(target = "product", ignore = true)
    List<CategoriesResponseDto> listCategoriesToListDto(List<Categories> categories);

    @Mapping(target = "products", ignore = true)
    @Mapping(target = "id", ignore = true)
    Categories categoryRequestDtoToCategories(CategoryRequestDto CategoryRequestDto);
}
