package spring.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import spring.persistence.vo.SpringbootJeremyOrderItem;

import java.util.List;

@Mapper
public interface SpringbootJeremyOrderItemDao extends BaseMapper<SpringbootJeremyOrderItem> {
    /**
     * 進xml獲得指定orderId的orderItemList
     * @param orderId
     * @return List
     */
    List<SpringbootJeremyOrderItem> getOrderItemsByOrderId( Integer orderId);
}
