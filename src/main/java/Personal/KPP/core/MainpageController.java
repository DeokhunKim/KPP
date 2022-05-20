package Personal.KPP.core;

import Personal.KPP.app.AppList;
import Personal.KPP.core.login.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


@Slf4j
@Controller
public class MainpageController {

    private final AppList appList;
    private final LoginService loginService;

    public MainpageController(AppList appList, LoginService loginService) {
        this.appList = appList;
        this.loginService = loginService;
    }


    @GetMapping("/")
    public String home(Model model, HttpServletRequest request) {

        // Request Header 에 token 이 존재하지 않을 때
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return "redirect:/login";
        }
        Cookie cookie = Arrays.stream(cookies).filter(c -> c.getName().equals("kpp_t"))
                .findAny()
                .orElse(null);
        if(cookie == null){
            return "redirect:/login";
        }
        String value = cookie.getValue();
        if(value == null){
            return "redirect:/login";
        }

        // 인증 여부
        if (loginService.isAuthorized(value)) {
            return "redirect:/app";
        } else {
            return "redirect:/login";
        }
    }



    @GetMapping("/app")
    public String apptile(Model model){
        model.addAttribute("appList", appList.getAppList() );
        return "apptitle";
    }

}
