package spring.service;


import spring.dto.ProductQueryParams;
import spring.dto.ProductRequest;
import spring.persistence.vo.SpringbootJeremyProduct;

import java.util.List;

public interface ProductService {
    Long countProduct (ProductQueryParams productQueryParams);
    List<SpringbootJeremyProduct> getProducts(ProductQueryParams productQueryParams);
    SpringbootJeremyProduct getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId,ProductRequest productRequest);
    void deleteProductById(Integer productId);
}
