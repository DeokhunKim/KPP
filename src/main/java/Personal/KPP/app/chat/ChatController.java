package Personal.KPP.app.chat;

import Personal.KPP.app.App;
import Personal.KPP.app.AppRegist;
import Personal.KPP.app.AppList;
import Personal.KPP.core.web.session.SessionConst;
import Personal.KPP.core.web.session.SessionMemberForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class ChatController extends AppRegist {

    private static final String baseUrl = "/Chat";

    public ChatController(AppList appList) {
        super(appList, new App("Chat", baseUrl, "app/icon/chat.png" ));
    }

    @GetMapping(name = "ChatController", value = baseUrl)
    public String chatController(
            @SessionAttribute(value = SessionConst.LOGIN, required = false) SessionMemberForm member,
            Model model){
        model.addAttribute("userName", member.getUserName());
        return "app/chat/chat";
    }
}
