package Personal.KPP.core.web.interceptor;

import Personal.KPP.core.domain.member.Member;
import Personal.KPP.core.domain.member.MemberGrade;
import Personal.KPP.core.web.session.SessionConst;
import Personal.KPP.core.web.session.SessionMemberForm;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    private static final boolean isUseAutoLogin = false;

    /**
     * 로그인 세션이 없으면 접근 금지
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        DEBUG_autoLogin(request);

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(SessionConst.LOGIN) == null) {
            response.sendRedirect("/");
            return false;
        }

        return true;
    }

    // 개발 편의를 위한 어드민 계정 세션 자동 생성
    private void DEBUG_autoLogin(HttpServletRequest request){
        if(!isUseAutoLogin){
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN, new SessionMemberForm("admin", MemberGrade.ADMIN));
    }

}
