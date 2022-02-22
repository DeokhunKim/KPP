package Personal.KPP.app.chat.controller;

import Personal.KPP.app.App;
import Personal.KPP.app.AppController;
import Personal.KPP.app.AppList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController extends AppController {

    private static final String baseUrl = "/Chat";

    public ChatController(AppList appList) {
        super(appList, new App("Chat", baseUrl ));
    }

    @GetMapping(name = "ChatController", value = baseUrl)
    public String chatController(){
        System.out.println("ChatController.chatController");
        return "ok";
    }
}
