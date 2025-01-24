package com.capstone.hanperform.users.controller;

import com.capstone.hanperform.users.dto.UserDto;
import com.capstone.hanperform.users.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // 회원가입 폼 페이지
    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "signup"; // signup.html 렌더링
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String createUser(@ModelAttribute UserDto userDTO, Model model) {
        try {
            userService.createUser(userDTO);
            return "redirect:/users/login"; // 회원가입 후 로그인 페이지로 이동
        } catch (Exception e) {
            model.addAttribute("error", "회원가입 중 오류가 발생했습니다.");
            return "signup";
        }
    }

    // 첫 페이지를 로그인 페이지로 설정
    @GetMapping("/")
    public String redirectToLogin(HttpSession session) {
        if (session.getAttribute("loggedInUser") != null) {
            return "redirect:/index"; // 로그인한 사용자는 index로 이동
        }
        return "redirect:/users/login"; // 비로그인 사용자는 로그인 페이지로 이동
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String showLoginForm(HttpSession session) {
        if (session.getAttribute("loggedInUser") != null) {
            return "redirect:/index"; // 이미 로그인된 상태면 index로 이동
        }
        return "login"; // 로그인 페이지 렌더링
    }

    // 로그인 요청 처리 (POST)
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        log.info("로그인 시도 - username: {}", email);

        UserDto user = userService.authenticate(email, password);
        if (user != null) {
            log.info("로그인 성공 - username: {}", email);
            session.setAttribute("loggedInUser", user);
            return "redirect:/index";
        } else {
            log.warn("로그인 실패 - username: {}", email);
            model.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "login";
        }
    }


    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/users/login";
    }
}
