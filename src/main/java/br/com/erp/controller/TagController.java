package br.com.erp.controller;

import br.com.erp.bean.tag.Tag;
import br.com.erp.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/tags")
public class TagController {

    private final TagService service;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/")
    Tag save(@RequestBody Tag tag) {
        return service.save(tag);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/")
    Set<Tag> getAll() {
        return service.getAll();
    }
}
