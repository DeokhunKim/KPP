package Personal.KPP.app.apps;

import Personal.KPP.app.App;
import Personal.KPP.app.AppRegist;
import Personal.KPP.app.AppList;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class DemoApp extends AppRegist {

    private static final String url = "/DemoApp";

    public DemoApp(AppList appList) {
        super(appList, new App("Demo App", url));
    }

}
