package Personal.KPP.app;

import org.springframework.stereotype.Component;

public class App {
    private String appName;
    private String url;
    private String iconImg;

    public App(String appName, String url, String iconImg) {
        this.appName = appName;
        this.url = url;
        this.iconImg = iconImg;
    }

    public App(String appName, String url) {
        this(appName, url, "app/icon/default.png");
    }

    public String getUrl(){
        return url;
    }

    public String getAppName() {
        return appName;
    }

    public String getIconImg() {
        return iconImg;
    }
}
