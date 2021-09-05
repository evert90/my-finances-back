package br.com.erp.controller;

import br.com.erp.api.user.User;
import br.com.erp.api.user.UserReadOnly;
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

    private final UserService service;

    @PostMapping("/")
    UserReadOnly save(@RequestBody User servico) {
        return service.save(servico);
    }
}
