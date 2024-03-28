package spring.service;

import spring.dto.UserLoginRequest;
import spring.dto.UserRegisterRequest;
import spring.persistence.vo.SpringbootJeremyUser;

public interface UserService {
    SpringbootJeremyUser getUserById(Integer userId);
    Integer register(UserRegisterRequest userRegisterRequest);
    SpringbootJeremyUser login(UserLoginRequest userLoginRequest);
}
