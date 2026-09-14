package ru.group.lab1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller ("main")
public class MainController {
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("title", "Главная страница");
        model.addAttribute("data", "Добро пожаловать!");
        model.addAttribute("content", "В будущем здесь что то будет");
        return "main";
    }

    @GetMapping("about")
    public String about(
            @RequestParam(
                    name = "name",
                    required = false,
                    defaultValue = "Имя автора"
            ) String name,
            Model model) {

        model.addAttribute("title", "Страница автора");
        model.addAttribute("author", name);

        return "about";
    }

    @GetMapping("/form")
    public String mainForm(Model model){
        model.addAttribute("student", new Student());
        return "main-form";
    }

    @PostMapping("/form")
    public String mainForm(@ModelAttribute Student student, Model
            model){
        String year = String.valueOf(student.getYear());

        // Последние две цифры года
        String lastTwoDigits = year.substring(year.length() - 2);

        // группа
        String group = "ПИН-1" + lastTwoDigits;
        student.setGroup(group);

        // логин
        String login = "student-pin1" + lastTwoDigits + "-" + student.getId();
        student.setLogin(login);
        model.addAttribute("student", student);
        return "result";
    }

}

