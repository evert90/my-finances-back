package br.com.erp.controller;

import br.com.erp.bean.user.AuthenticatedUser;
import br.com.erp.bean.user.User;
import br.com.erp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    AuthenticatedUser save(@RequestBody User user) {
        return userService.saveAndAuthenticate(user);
    }

    @PostMapping("/auth")
    AuthenticatedUser authenticate(@RequestBody User user) {
        return userService.authenticate(user);
    }
}
