package spring.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import spring.dto.ProductQueryParams;
import spring.dto.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.persistence.dao.SpringbootJeremyProductDao;
import spring.persistence.vo.SpringbootJeremyProduct;
import spring.service.ProductService;

import java.util.Date;
import java.util.List;
@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    private SpringbootJeremyProductDao productDao;
    @Override
    public Long countProduct (ProductQueryParams productQueryParams){
        LambdaQueryWrapper<SpringbootJeremyProduct> qw = new LambdaQueryWrapper<>();
        if (productQueryParams.getCategory() != null) {
            qw.like(SpringbootJeremyProduct::getCategory, productQueryParams.getCategory().name() );
        }
        if (productQueryParams.getSearch() != null) {//沒有設default所以要判斷是否為null
            qw.like(SpringbootJeremyProduct::getProductName, productQueryParams.getSearch());
        }
        return productDao.selectCount(qw);
    }
    @Override
    public List<SpringbootJeremyProduct> getProducts(ProductQueryParams productQueryParams) {
        LambdaQueryWrapper<SpringbootJeremyProduct> qw = new LambdaQueryWrapper<>();
        if (productQueryParams.getCategory() != null) {
            qw.like(SpringbootJeremyProduct::getCategory, productQueryParams.getCategory().name());
        }
            if (productQueryParams.getSearch() != null) {//沒有設default所以要判斷是否為null
                qw.like(SpringbootJeremyProduct::getProductName, productQueryParams.getSearch());
            }
            PageInfo<SpringbootJeremyProduct> empPage = PageHelper.startPage(productQueryParams.getOffset(),
                    productQueryParams.getLimit(), productQueryParams.getOrderBy()+" "+productQueryParams.getSort())
                    .doSelectPageInfo(() -> productDao.selectList(qw));

            return empPage.getList();
        }
    @Override
    public SpringbootJeremyProduct getProductById(Integer productId) {
        return productDao.selectById(productId);
    }


    @Override
    public Integer createProduct(ProductRequest productRequest) {
        SpringbootJeremyProduct product = new SpringbootJeremyProduct();
        product.setProductName(productRequest.getProductName());
        product.setCategory(productRequest.getCategory());
        product.setImageUrl(productRequest.getImageUrl());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setDescription(productRequest.getDescription());
        Date now = new Date();
        product.setCreatedDate(now);
        product.setLastModifiedDate(now);
        productDao.insert(product);
        return  product.getProductId();
    }
    @Override
    public void updateProduct(Integer productId,ProductRequest productRequest){
        LambdaUpdateWrapper<SpringbootJeremyProduct> uw = new LambdaUpdateWrapper<>();
        uw.eq(SpringbootJeremyProduct::getProductId,productId);
        SpringbootJeremyProduct product = new SpringbootJeremyProduct();
        product.setProductId(productId);
        product.setProductName(productRequest.getProductName());
        product.setCategory(productRequest.getCategory());
        product.setImageUrl(productRequest.getImageUrl());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setDescription(productRequest.getDescription());
        Date now = new Date();
        product.setCreatedDate(now);
        product.setLastModifiedDate(now);
        productDao.update(product,uw);
    }
    @Override
    public void deleteProductById(Integer productId){
        productDao.deleteById(productId);
    }
}
