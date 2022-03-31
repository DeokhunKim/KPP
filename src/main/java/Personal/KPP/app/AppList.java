package Personal.KPP.app;

import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AppList {

    private final List<App> appList = new ArrayList<>();
    public AppList() {
    }

    public void addApp(Class c, String appName, String url, String iconImg){
        try {
            Constructor constructor = c.getConstructor(String.class, String.class, String.class);
            App app = (App) constructor.newInstance(appName, url, iconImg);
            appList.add(app);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public List<App> getAppList(){
        return appList;
    }

}
