package spring.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;
import spring.persistence.dao.SpringbootJeremyUserDao;
import spring.dto.UserLoginRequest;
import spring.dto.UserRegisterRequest;
import spring.persistence.vo.SpringbootJeremyUser;
import spring.service.UserService;

import java.util.Date;

@Component
public class UserServiceImpl implements UserService {
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private SpringbootJeremyUserDao userDao;
    public SpringbootJeremyUser getUserById(Integer userId){
        return userDao.selectById(userId);
    }
    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        //檢查註冊的email
        SpringbootJeremyUser user = userDao.selectOne((Wrappers.<SpringbootJeremyUser>lambdaQuery().eq(SpringbootJeremyUser::getEmail,userRegisterRequest.getEmail())));
        if(user!=null){//註冊過就跳exception出去
            log.warn("該email {} 已被註冊",userRegisterRequest.getEmail());//getEmail資料會導入前面{}
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        String hashedPassword = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());//讀密碼轉成bytes類型
        userRegisterRequest.setPassword(hashedPassword);

        SpringbootJeremyUser jeremyUser = new SpringbootJeremyUser();

        jeremyUser.setEmail(userRegisterRequest.getEmail());
        jeremyUser.setPassword(hashedPassword);
        Date now = new Date();
        jeremyUser.setCreatedDate(now);
        jeremyUser.setLastModifiedDate(now);
        //創建帳號
        userDao.insert(jeremyUser);
        return jeremyUser.getUserId();
    }
    public SpringbootJeremyUser login(UserLoginRequest userLoginRequest){
        SpringbootJeremyUser user = userDao.selectOne((Wrappers.<SpringbootJeremyUser>lambdaQuery().eq(SpringbootJeremyUser::getEmail,userLoginRequest.getEmail())));
       //檢查user是否存在
       if(user == null){
           log.warn("該 email {} 尚未註冊",userLoginRequest.getEmail());//EMAIL還未被註冊情況
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
       }
       //使用 MD5 生成密碼的雜湊值
        String hashedPassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());
       //比較密碼
       if(user.getPassword().equals(hashedPassword)) {
           return user;
       }else{
           log.warn("該 email {} 的密碼不正確",userLoginRequest.getEmail());
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
       }
    }
}
