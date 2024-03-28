package spring.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import spring.dto.ProductQueryParams;
import spring.dto.ProductRequest;
import spring.persistence.vo.SpringbootJeremyProduct;

import java.util.List;
@Mapper
public interface SpringbootJeremyProductDao extends BaseMapper<SpringbootJeremyProduct> {

}
