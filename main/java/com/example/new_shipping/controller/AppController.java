package com.example.new_shipping.controller;

// Контроллер - это класс, предназначенный для непосредственной обработки запросов от клиента и возвращения результатов.
import java.util.List;

import com.example.new_shipping.dto.UserDto;
import com.example.new_shipping.entity.Blog;
import com.example.new_shipping.entity.Cargo;
import com.example.new_shipping.entity.User;
import com.example.new_shipping.service.BlogService;
import com.example.new_shipping.service.CargoService;
import com.example.new_shipping.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

    @Autowired
    private CargoService service;

    @RequestMapping("/")
    // Аннотация @RequestMapping используется для мапинга (связывания) с URL для всего класса или для конкретного метода обработчика.
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
        List<Cargo> listCargo = service.listAll(keyword);
        model.addAttribute("listCargo", listCargo);
        model.addAttribute("keyword", keyword);
        return "index";
    }
    @RequestMapping("/index")
    // Аннотация @RequestMapping используется для мапинга (связывания) с URL для всего класса или для конкретного метода обработчика.
    public String viewHome(Model model, @Param("keyword") String keyword) {
        List<Cargo> listCargo = service.listAll(keyword);
        model.addAttribute("listCargo", listCargo);
        model.addAttribute("keyword", keyword);
        return "index";
    }

    @RequestMapping("/check")
    // Аннотация @RequestMapping используется для мапинга (связывания) с URL для всего класса или для конкретного метода обработчика.
    public String viewCheck(Model model, @Param("keyword") String keyword) {
        List<Cargo> listCargo = service.listAll(keyword);
        model.addAttribute("listCargo", listCargo);
        model.addAttribute("keyword", keyword);
        return "check";
    }
    @RequestMapping("/new")
    public String showNewCargoForm(Model model) {
        Cargo cargo = new Cargo();
        model.addAttribute("cargo", cargo);
        return "new_cargo";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCargo(@ModelAttribute("cargo") Cargo cargo) {
        service.save(cargo);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditCargoForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_cargo");
        Cargo cargo = service.get(id);
        mav.addObject("cargo", cargo);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteCargo(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return "redirect:/";
    }

    private UserService userService;

    public AppController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
    @Autowired
    private BlogService blogService;
//    public AppController2(BlogService blogService) {
//        this.blogService = blogService;
//    }

    @RequestMapping("/blog")
    public String viewBlog(Model model, @Param("keyword") String keyword) {
        List<Blog> listBlog = blogService.listAll(keyword);
        model.addAttribute("listBlog", listBlog);
        model.addAttribute("keyword", keyword);
        return "blog";
    }

    @RequestMapping("/new_blog_entry")
    public String showNewBlogForm(Model model) {
        Blog blog = new Blog();
        model.addAttribute("blog", blog);
        return "new_blog_entry";
    }

    @RequestMapping(value = "/save_blog_entry", method = RequestMethod.POST)
    public String saveEntry(@ModelAttribute("entry") Blog blog) {
        blogService.save(blog);
        return "redirect:/blog";
    }

    @RequestMapping("/edit_blog_entry/{id}")
    public ModelAndView showEditBlogForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_blog_entry");
        Blog blog = blogService.get(id);
        mav.addObject("blog", blog);
        return mav;
    }

    @RequestMapping("/delete_blog_entry/{id}")
    public String deleteBlog(@PathVariable(name = "id") Long id) {
        blogService.delete(id);
        return "redirect:/blog";
    }
    @RequestMapping("/blog_entry/{id}")
    public ModelAndView showDesc(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("blog_entry");
        Blog blog = blogService.get(id);
        mav.addObject("blog", blog);
        return mav;
    }


}

