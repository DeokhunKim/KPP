package Personal.KPP.app.apps;

import Personal.KPP.app.App;
import Personal.KPP.app.AppList;
import Personal.KPP.app.AppRegist;
import org.springframework.stereotype.Component;

@Component
public class GlossaryApp extends AppRegist {

    private static final String url = "/glossary";

    public GlossaryApp(AppList appList) {
        super(appList, new App("Glossary App", url, "app/icon/glossary.png"));
    }
}
