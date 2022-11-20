package org.top.mvcstudentlsapplication;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// главный контроллер
@Controller
public class MainController {

    @GetMapping("")
    public String getHomePage() {
        // возвращаем представление
        return "index"; // index.html - шаблон
    }
}
