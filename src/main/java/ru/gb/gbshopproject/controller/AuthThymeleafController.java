package ru.gb.gbshopproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.gbapi.security.AuthenticationUserDto;
import ru.gb.gbapi.security.UserDto;
import ru.gb.gbapi.security.api.AuthenticationUserGateway;
import ru.gb.gbshopproject.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class AuthThymeleafController {

    private final AuthenticationUserGateway authenticationUserGateway;
    private final UserService userService;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "registration-form";
    }

    @PostMapping("/addNewUser")
    public String saveUser(UserDto userDto) {

        userService.save(userDto);
        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model){
        model.addAttribute("authenticationUserDto", new AuthenticationUserDto());
        return "login-form";
    }

    @PostMapping("/login")
    public String sendAuth(AuthenticationUserDto authenticationUserDto, HttpServletResponse response){
        log.info(authenticationUserGateway.login(authenticationUserDto).getBody().get("token"));
        response.addCookie(new Cookie("jwt", authenticationUserGateway.login(authenticationUserDto).getBody().get("token")));
        return "redirect:/product/all";
    }


}
