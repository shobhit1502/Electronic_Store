package com.lcwd.electronic.store.services;

import com.lcwd.electronic.store.dtos.CategoryDto;
import com.lcwd.electronic.store.dtos.PageableResponse;

import java.util.List;

public interface CategoryService {
    //1....create
     CategoryDto create(CategoryDto categoryDto);
     //2....update
     CategoryDto update(CategoryDto categoryDto,String categoryId);
     //3....delete
     void delete(String categoryId);
     //4....getAll
      PageableResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);
      //5....getSingleCategoryDetail
     CategoryDto get(String categoryId);
      //6....Search
    //CategoryDto SearchCategory(CategoryDto categoryDto);
     List<CategoryDto> searchCategory(String keywords);


}
