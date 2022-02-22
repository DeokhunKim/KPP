package Personal.KPP.core.controller;

import Personal.KPP.app.AppList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainpageController {

    private final AppList appList;

    public MainpageController(AppList appList) {
        this.appList = appList;
    }

    @GetMapping(name = "indexController", value = "/")
    public String indexController(Model model){
        model.addAttribute("appList", appList.getAppList() );

        return "index";
    }

    public AppList getAppList() {
        return appList;
    }
}
