//package spring.rowmapper;
//
//import spring.constant.ProductCategory;
//import spring.model.Product;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class ProductRowMapper  implements RowMapper<Product>{
//    @Override
//    public Product mapRow(ResultSet resultSet,int i)throws SQLException{
//        Product product = new Product();
//
//        product.setProductId(resultSet.getInt("PRODUCT_ID"));
//        product.setProductName(resultSet.getString("PRODUCT_NAME"));
//
//        String categoryStr = resultSet.getString("category");
//        ProductCategory category = ProductCategory.valueOf(categoryStr);
//        product.setCategory(category);
//
//        product.setImageUrl(resultSet.getString("IMAGE_URL"));
//        product.setPrice(resultSet.getInt("PRICE"));
//        product.setStock(resultSet.getInt("STOCK"));
//        product.setDescription(resultSet.getString("DESCRIPTION"));
//        product.setCreatedDate(resultSet.getTimestamp("CREATED_DATE"));
//        product.setLastModifiedDate(resultSet.getTimestamp("LAST_MODIFIED"));
//        return product;
//    }
//}
