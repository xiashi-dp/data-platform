package com.dataplatform.controller;

import com.dataplatform.common.BusinessException;
import com.dataplatform.common.JwtUtil;
import com.dataplatform.common.Result;
import com.dataplatform.model.User;
import com.dataplatform.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        return Result.success(user);
    }

    @PostMapping("/login")
    public Result<String> login(@RequestParam String username,
                                @RequestParam String password) {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new BusinessException("用户名或密码错误");
        }
        if (!user.getPassword().equals(JwtUtil.md5(password))) {
            throw new BusinessException("用户名或密码错误");
        }
        // 生成 Token，返回给前端
        String token = JwtUtil.generate(user.getId(), user.getUsername(), user.getRole());
        return Result.success(token);
    }

}
