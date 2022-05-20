package Personal.KPP.app.apps;

import Personal.KPP.app.App;
import Personal.KPP.app.AppList;
import Personal.KPP.app.AppRegist;
import org.springframework.stereotype.Component;

@Component
public class ChatApp extends AppRegist {

    private static final String url = "/chat";

    public ChatApp(AppList appList) {
        super(appList, new App("Chat App", url, "app/icon/chat.png"));
    }

}
