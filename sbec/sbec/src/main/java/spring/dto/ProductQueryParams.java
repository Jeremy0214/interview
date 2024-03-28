package spring.dto;

import lombok.Getter;
import lombok.Setter;
import spring.constant.ProductCategory;
@Getter
@Setter
public class ProductQueryParams {

    private ProductCategory category;
    private String search;
    private String orderBy;
    private String sort;
    private Integer limit;
    private Integer offset;
}
