package Personal.KPP.core;

import Personal.KPP.app.AppList;
import Personal.KPP.core.domain.member.Member;
import Personal.KPP.core.domain.member.MemberRepository;
import Personal.KPP.core.web.login.LoginForm;
import Personal.KPP.core.web.session.SessionConst;
import Personal.KPP.core.web.session.SessionMemberForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Controller
public class MainpageController {

    private final AppList appList;
    private final MemberRepository repository;

    public MainpageController(AppList appList, MemberRepository memberRepository) {
        this.appList = appList;
        this.repository = memberRepository;
    }


    @GetMapping("/")
    public String home(@SessionAttribute(name = SessionConst.LOGIN, required = false) SessionMemberForm loginMember, Model model) {
        if (loginMember == null) {
            return "index";
        } else {
            return "redirect:/app";
        }
    }

    @PostMapping("/login")
    public String loginHome(@ModelAttribute LoginForm loginForm, RedirectAttributes redirectAttributes,
                            HttpServletRequest request) {
        Optional<Member> optionalMember = repository.findByUserName(loginForm.getUserName());
        //로그인 실패
        if (optionalMember.isEmpty() || !optionalMember.get().getPassword().equals(loginForm.getPassword())) {
            redirectAttributes.addAttribute("loginFail", true);
            return "redirect:/";
        }

        //세션 생성
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN, new SessionMemberForm(optionalMember.get()));

        log.info("Login user: {}", optionalMember.get().getUserName());

        return "redirect:/app";
    }

    @PostMapping("/register")
    public String registerHome(@ModelAttribute LoginForm loginForm, RedirectAttributes redirectAttributes) {
        Optional<Member> optionalMember = repository.findByUserName(loginForm.getUserName());

        //회원가입 실패
        if (optionalMember.isEmpty() == false) {
            redirectAttributes.addAttribute("registerFail", true);
            return "redirect:/";
        }

        Member member = repository.save(new Member(loginForm.getUserName(), loginForm.getPassword()));
        log.info("Registration user: {}", member.getUserName());
        redirectAttributes.addAttribute("registerSuccess", true);
        return "redirect:/";
    }

    @GetMapping("/app")
    public String apptile(Model model){
        model.addAttribute("appList", appList.getAppList() );
        return "apptitle";
    }

}
