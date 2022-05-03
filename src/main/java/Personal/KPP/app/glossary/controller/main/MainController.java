package Personal.KPP.app.glossary.controller.main;

import Personal.KPP.app.glossary.dto.DocumentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("glossary")
public class MainController {

    @GetMapping
    public String viewGlossary(@RequestParam(required = false)String keyword,
                               HttpServletRequest request, HttpServletResponse response) throws IOException {
        // uri 마지막이 / 면 뺀 다음 redirect
        String requestURI = request.getRequestURI();
        if (requestURI.charAt(requestURI.length()-1) == '/') {
            response.sendRedirect(requestURI.substring(0, requestURI.length() - 1));
        }
        return "app/glossary/glossary";
    }

    @GetMapping("/new")
    public String newDocument(@RequestParam(required = false)String title, Model model) {
        model.addAttribute("title", title);
        model.addAttribute("mode", "new");
        return "app/glossary/editor";
    }

    @GetMapping("/{title}/edit")
    public String editDocument(@PathVariable String title, Model model) {
        model.addAttribute("title", title);
        model.addAttribute("mode", "edit");
        return "app/glossary/editor";
    }

}
