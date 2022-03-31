package Personal.KPP.app;

import org.springframework.stereotype.Controller;


public abstract class AppRegist {
    protected final AppList appList;

    public AppRegist(AppList appList, App app) {
        this.appList = appList;
        appList.addApp(app.getClass(), app.getAppName(), app.getUrl(), app.getIconImg());
    }


}
