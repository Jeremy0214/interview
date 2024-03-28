package spring.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import spring.dto.OrderQueryParams;
import spring.persistence.vo.SpringbootJeremyOrder;
import spring.persistence.vo.SpringbootJeremyOrderItem;

import java.util.List;
@Mapper
public interface SpringbootJeremyOrderDao extends BaseMapper<SpringbootJeremyOrder> {
}
