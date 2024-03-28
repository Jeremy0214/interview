package spring.dto;

import lombok.Getter;
import lombok.Setter;
import spring.constant.ProductCategory;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductRequest {
    @NotNull
    private String productName;
    @NotNull
    private ProductCategory category;
    private String imageUrl;
    @NotNull
    private Integer price;
    @NotNull
    private Integer stock;
    private String description;
}
