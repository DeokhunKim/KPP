package Personal.KPP.app.glossary.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/glossary/{title}/history")
public class HistoryController {

    @GetMapping
    public String getHistory(@PathVariable String title){
        System.out.println("HistoryController.getHistory");
        System.out.println("title = " + title);
        return "ok";

    }
}
