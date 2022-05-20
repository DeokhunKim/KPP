package Personal.KPP.core.login.controller;

import Personal.KPP.core.login.service.LoginService;
import Personal.KPP.core.web.login.LoginForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class LoginController {

    final private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String loginHome(HttpServletRequest request,  HttpServletResponse response) {

        return "index";
    }

    @PostMapping("/login")
    public String loginHome(@ModelAttribute LoginForm loginForm, RedirectAttributes redirectAttributes,
                            HttpServletRequest request,  HttpServletResponse response) {
        String token = "";
        try {
            token = loginService.login(loginForm.getUserName(), loginForm.getPassword());
        } catch (ResponseStatusException e) {
            redirectAttributes.addAttribute("loginFail", true);
            return "redirect:/login";
        }
        //로그인 실패
        if (token == null) {
            redirectAttributes.addAttribute("loginFail", true);
            return "redirect:/login";
        }
        response.addCookie(new Cookie("kpp_t", token));
        log.info("Login user: {}",  loginForm.getUserName());

        return "redirect:/app";
    }

    @PostMapping("/register")
    public String registerHome(@ModelAttribute LoginForm loginForm, RedirectAttributes redirectAttributes) {

        boolean result = loginService.registryMember(loginForm.getUserName(), loginForm.getPassword());

        //회원가입 실패
        if (false == result) {
            redirectAttributes.addAttribute("registerFail", true);
            return "redirect:/";
        }

        redirectAttributes.addAttribute("registerSuccess", true);
        return "redirect:/";
    }
}
