package spring.persistence.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;
@Data
public class SpringbootJeremyUser {

    @TableId(type = IdType.AUTO)
    private Integer userId;
    //@JsonProperty("e_mail")更改回傳json名稱
    private String email;
    @JsonIgnore//避免反傳回去會將密碼傳給前端
    private String password;
    private Date createdDate;
    private Date lastModifiedDate;
}