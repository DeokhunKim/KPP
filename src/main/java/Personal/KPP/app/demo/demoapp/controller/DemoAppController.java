package Personal.KPP.app.demo.demoapp.controller;

import Personal.KPP.app.App;
import Personal.KPP.app.AppRegist;
import Personal.KPP.app.AppList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoAppController extends AppRegist {

    private static final String url = "/DemoApp";

    public DemoAppController(AppList appList) {
        super(appList, new App("Demo App", url));
    }

    @GetMapping(name = "DemoApp", value = url)
    public String demoApp(){
        System.out.println("DemoAppController.demoApp");
        return "ok";
    }
}
