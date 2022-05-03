package Personal.KPP.app.glossary;

import Personal.KPP.app.App;
import Personal.KPP.app.AppList;
import Personal.KPP.app.AppRegist;
import org.springframework.stereotype.Component;

@Component
public class GlossaryApp extends AppRegist
{
    private static final String baseUrl = "/glossary";

    public GlossaryApp(AppList appList) {
        super(appList, new App("Glossary", baseUrl));
    }
}
