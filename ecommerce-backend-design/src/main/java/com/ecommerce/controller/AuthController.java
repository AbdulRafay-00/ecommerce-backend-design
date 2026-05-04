package com.ecommerce.controller;

import com.ecommerce.dto.AuthDto;
import com.ecommerce.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // ──── Show Login Page ────
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginRequest", new AuthDto.LoginRequest());
        return "auth/login";
    }

    // ──── Process Login ────
    @PostMapping("/process-login")
    public String processLogin(
            @Valid @ModelAttribute("loginRequest") AuthDto.LoginRequest request,
            BindingResult result,
            HttpServletResponse response,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (result.hasErrors()) {
            return "auth/login";
        }
        try {
            authService.login(request, response);
            return "redirect:/";
        } catch (BadCredentialsException e) {
            model.addAttribute("errorMessage", "Invalid email or password");
            return "auth/login";
        }
    }

    // ──── Show Signup Page ────
    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("signupRequest", new AuthDto.SignupRequest());
        return "auth/signup";
    }

    // ──── Process Signup ────
    @PostMapping("/process-signup")
    public String processSignup(
            @Valid @ModelAttribute("signupRequest") AuthDto.SignupRequest request,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (result.hasErrors()) {
            return "auth/signup";
        }
        try {
            authService.signup(request);
            redirectAttributes.addFlashAttribute("successMessage", "Account created! Please log in.");
            return "redirect:/auth/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "auth/signup";
        }
    }

    // ──── Logout ────
    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        authService.logout(response);
        return "redirect:/";
    }
}
