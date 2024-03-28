package spring.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.constant.ProductCategory;
import spring.dto.ProductQueryParams;
import spring.dto.ProductRequest;
import spring.persistence.vo.SpringbootJeremyProduct;
import spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import spring.util.Page;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Validated//使用此註解max()和min()才會生效
@RestController
//@CrossOrigin("http://localhost/5173")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/api/products")//required = false讓category為可選可不選 default預設由created_date排序
    public ResponseEntity<Page<SpringbootJeremyProduct>> getProducts(//查詢條件filtering
                                                                     @RequestParam(required = false) ProductCategory category,
                                                                     @RequestParam(required = false) String search,
                                                                     //排序sorting
                                                                     @RequestParam(defaultValue = "created_date") String orderBy,
                                                                     @RequestParam(defaultValue = "desc") String sort,
                                                                     //分頁 pagination
                                                                     @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
                                                                     @RequestParam(defaultValue = "1") @Min(0) Integer offset
    ) {
        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);
        //取得productList
        List<SpringbootJeremyProduct> productList = productService.getProducts(productQueryParams);
        //取的product 總比數
        Long total = productService.countProduct(productQueryParams);//總比數會和前端輸入類別改變

        //分頁
        Page<SpringbootJeremyProduct> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResult(productList);


        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @GetMapping("/api/products/{productId}")//取得商品數據
    public ResponseEntity<SpringbootJeremyProduct> getProduct(@PathVariable Integer productId) {//productId由url進id
        SpringbootJeremyProduct product = productService.getProductById(productId);
        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/api/products")
    public ResponseEntity<SpringbootJeremyProduct> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        Integer productId = productService.createProduct(productRequest);
        SpringbootJeremyProduct product = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/api/products/{productId}")
    public ResponseEntity<SpringbootJeremyProduct> updateProduct(@PathVariable Integer productId,
                                                                 @RequestBody ProductRequest productRequest) {
        SpringbootJeremyProduct product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        productService.updateProduct(productId, productRequest);
        SpringbootJeremyProduct updatedProduct = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @DeleteMapping("/api/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProductById(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
