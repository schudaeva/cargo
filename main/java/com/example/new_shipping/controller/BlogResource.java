package com.example.new_shipping.controller;

import com.example.new_shipping.entity.Blog;
import com.example.new_shipping.service.BlogService;
import com.example.new_shipping.service.CargoService;
import com.vaadin.flow.router.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
public class BlogResource {

    private BlogService blogService;

    public BlogResource(BlogService blogService) {
        this.blogService = blogService;
    }


    @GetMapping("/{id}")
    public Blog findById(@PathVariable long id) {
        return blogService.findById(id).orElseThrow(() -> new NotFoundException("not found"));
    }

    @PostMapping
    public Blog create(@RequestBody Blog blog) {
        return blogService.save(blog);
    }
}