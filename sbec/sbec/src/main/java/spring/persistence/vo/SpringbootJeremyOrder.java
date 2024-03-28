package spring.persistence.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SpringbootJeremyOrder {
    @TableId(type = IdType.AUTO)
    private Integer orderId;
    private Integer userId;
    private Integer totalAmount;
    private Date createdDate;
    private Date lastModifiedDate;
    @TableField(select = false)
    private List<SpringbootJeremyOrderItem> orderItemList;
}
