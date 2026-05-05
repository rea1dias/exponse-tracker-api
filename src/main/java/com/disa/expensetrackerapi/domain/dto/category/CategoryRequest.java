package com.disa.expensetrackerapi.domain.dto.category;

import com.disa.expensetrackerapi.enums.CategoryType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {

    private String categoryName;
    private String categoryDescription;
    private CategoryType categoryType;

}
