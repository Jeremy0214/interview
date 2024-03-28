package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.dto.UserLoginRequest;
import spring.dto.UserRegisterRequest;
import spring.persistence.vo.SpringbootJeremyUser;
import spring.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<SpringbootJeremyUser> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
        Integer userId = userService.register(userRegisterRequest);
        SpringbootJeremyUser user = userService.getUserById(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    @PostMapping("/login")
    public ResponseEntity<SpringbootJeremyUser> login(@RequestBody @Valid UserLoginRequest userLoginRequest){
        SpringbootJeremyUser user = userService.login(userLoginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(user);//OK回傳給前端
    }
}
