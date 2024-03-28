package spring.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import spring.persistence.vo.SpringbootJeremyUser;

@Mapper
public interface SpringbootJeremyUserDao extends BaseMapper<SpringbootJeremyUser> {
}
