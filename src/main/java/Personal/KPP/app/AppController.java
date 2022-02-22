package Personal.KPP.app;

import org.springframework.stereotype.Controller;

@Controller
public abstract class AppController {
    protected final AppList appList;

    public AppController(AppList appList, App app) {
        this.appList = appList;
        appList.addApp(app.getClass(), app.getAppName(), app.getUrl(), app.getIconImg());
    }


}
